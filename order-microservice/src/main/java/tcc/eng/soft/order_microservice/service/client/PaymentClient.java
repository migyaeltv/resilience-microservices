package tcc.eng.soft.order_microservice.service.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import tcc.eng.soft.order_microservice.dto.PaymentRequestDTO;
import tcc.eng.soft.order_microservice.dto.PaymentResponseDTO;


@FeignClient(name = "payment-service", url = "http://localhost:8081")
public interface PaymentClient {

    @PostMapping("/process-payment")
    PaymentResponseDTO processPayment(@RequestBody PaymentRequestDTO paymentRequest);
}