package tcc.eng.soft.payment_microservice.service;

import org.springframework.stereotype.Service;
import tcc.eng.soft.payment_microservice.dto.PaymentRequestDTO;
import tcc.eng.soft.payment_microservice.dto.PaymentResponseDTO;

@Service
public class PaymentService {
    public PaymentResponseDTO processPayment(PaymentRequestDTO request) {
        return new PaymentResponseDTO(true, "Pago exitoso");
    }
}
