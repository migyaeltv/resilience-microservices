package tcc.eng.soft.order_microservice.service;
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

    public OrderResponseDTO createOrder(OrderRequestDTO orderRequest) {
        Order order = new Order();
        order.setCustomerName(orderRequest.getCustomerName());
        order.setAmount(orderRequest.getAmount());
        order.setStatus("PENDING");
        orderRepository.save(order);

        try {
            PaymentRequestDTO paymentRequest = new PaymentRequestDTO(order.getId(), order.getAmount());
            PaymentResponseDTO paymentResponse = paymentClient.processPayment(paymentRequest);

            order.setStatus(paymentResponse.isSuccess() ? "PAID" : "FAILED");
        } catch (Exception e) {
            order.setStatus("FAILED");
        }

        orderRepository.save(order);

        return new OrderResponseDTO(order.getId(), order.getCustomerName(), order.getAmount(), order.getStatus());
    }
}