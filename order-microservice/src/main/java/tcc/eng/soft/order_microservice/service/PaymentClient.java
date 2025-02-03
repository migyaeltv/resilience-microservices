package tcc.eng.soft.order_microservice.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import tcc.eng.soft.order_microservice.dto.PaymentRequestDTO;
import tcc.eng.soft.order_microservice.dto.PaymentResponseDTO;


@FeignClient(name = "payment-service", url = "http://payment:8081")
public interface PaymentClient {

    @PostMapping(value = "/process-payment", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    PaymentResponseDTO processPayment(@RequestBody PaymentRequestDTO paymentRequest);
}