package tcc.eng.soft.order_microservice.service;
import feign.FeignException;
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
    @Retry(name = "orderServiceRetry", fallbackMethod = "fallbackOrder")
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
            if (e.status() >= 500) {  // Somente reintentar se for erro 5xx
                System.err.println("Erro 5xx detectado, aplicando retry: " + e.getMessage());
                throw e;
            }
            order.setStatus("FAILED"); // Se não for 5xx, marca a ordem como falha
        }

        orderRepository.save(order);

        return new OrderResponseDTO(order.getId(), order.getCustomerId(), order.getAmount(), order.getStatus());
    }

    public OrderResponseDTO fallbackOrder(OrderRequestDTO orderRequest, Exception e) {
        System.err.println("PaymentService não disponivel: " + e.getMessage());

        Order order = new Order();
        order.setCustomerId(orderRequest.getCustomerId());
        order.setAmount(orderRequest.getAmount());
        order.setStatus("PENDING_PAYMENT");
        orderRepository.save(order);

        return new OrderResponseDTO(order.getId(), order.getCustomerId(), order.getAmount(), "PENDING_PAYMENT");
    }

}