package tcc.eng.soft.order_microservice.service;
import feign.FeignException;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import org.springframework.stereotype.Service;
import tcc.eng.soft.order_microservice.domain.Order;
import tcc.eng.soft.order_microservice.dto.OrderRequestDTO;
import tcc.eng.soft.order_microservice.dto.OrderResponseDTO;
import tcc.eng.soft.order_microservice.dto.PaymentRequestDTO;
import tcc.eng.soft.order_microservice.dto.PaymentResponseDTO;
import tcc.eng.soft.order_microservice.repository.OrderRepository;



@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private final PaymentClient paymentClient;

    public OrderService(OrderRepository orderRepository, PaymentClient paymentClient) {
        this.orderRepository = orderRepository;
        this.paymentClient = paymentClient;
    }
    
    @CircuitBreaker(name = "paymentCircuitBreaker", fallbackMethod = "fallbackPayment")
    public OrderResponseDTO createOrder(OrderRequestDTO orderRequest) {
        Order order = new Order();
        order.setCustomerId(orderRequest.getCustomerId());
        order.setAmount(orderRequest.getAmount());
        order.setStatus("PENDING");
        orderRepository.save(order);

        try{
            PaymentRequestDTO paymentRequest = new PaymentRequestDTO(order.getId(), order.getAmount());
            PaymentResponseDTO paymentResponse = paymentClient.processPayment(paymentRequest);


            String paymentStatus = paymentResponse.getStatus().equals("PAID") ? "PAID" : "FAILED";
            order.setStatus(paymentStatus);
        }

        catch (FeignException e) {
            if (e.status() >= 500 && e.status() < 600) {
                System.err.println("Erro 5xx no serviço de pagamento, ativando Circuit Breaker.");
                throw e;  // O Circuit Breaker vai gerenciar a resposta
            }
            order.setStatus("FAILED"); // Se não for 5xx, marca a ordem como falha
        }

        orderRepository.save(order);

        return new OrderResponseDTO(order.getId(), order.getCustomerId(), order.getAmount(), order.getStatus());
    }
    
    public OrderResponseDTO fallbackPayment(OrderRequestDTO orderRequest, Exception e) {
        System.err.println("Pagamento indisponível! Circuit Breaker ativado. " + e.getMessage());

        Order order = new Order();
        order.setCustomerId(orderRequest.getCustomerId());
        order.setAmount(orderRequest.getAmount());
        order.setStatus("PENDING");
        orderRepository.save(order);

        return new OrderResponseDTO(order.getId(), order.getCustomerId(), order.getAmount(), "PENDING");
    }

}