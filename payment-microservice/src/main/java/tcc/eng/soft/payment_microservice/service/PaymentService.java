package tcc.eng.soft.payment_microservice.service;

import org.springframework.stereotype.Service;
import tcc.eng.soft.payment_microservice.dto.PaymentRequestDTO;
import tcc.eng.soft.payment_microservice.dto.PaymentResponseDTO;

@Service
public class PaymentService {

    public PaymentResponseDTO processPayment(PaymentRequestDTO request) {
        if (Math.random() > 0.7) { // Simular fallos controlados
            throw new RuntimeException("Error procesando el pago");
        }
        return new PaymentResponseDTO(true, "Pago exitoso");
    }
}
