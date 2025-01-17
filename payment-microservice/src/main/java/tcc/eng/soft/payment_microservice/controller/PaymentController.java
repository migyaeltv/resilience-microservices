package tcc.eng.soft.payment_microservice.controller;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tcc.eng.soft.payment_microservice.dto.PaymentRequestDTO;
import tcc.eng.soft.payment_microservice.dto.PaymentResponseDTO;
import tcc.eng.soft.payment_microservice.service.PaymentService;

@RestController
@RequestMapping("/process-payment")
public class PaymentController {

    private final PaymentService paymentService;

    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @PostMapping
  //  @CircuitBreaker(name = "paymentService", fallbackMethod = "fallbackPayment")
    public PaymentResponseDTO processPayment(@RequestBody PaymentRequestDTO request) {
        return paymentService.processPayment(request);
    }

    public PaymentResponseDTO fallbackPayment(PaymentRequestDTO request, Throwable ex) {
        return new PaymentResponseDTO(false, "Servicio no disponible temporalmente");
    }
}
