package tcc.eng.soft.payment_microservice.service;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import tcc.eng.soft.payment_microservice.dto.PaymentRequestDTO;
import tcc.eng.soft.payment_microservice.dto.PaymentResponseDTO;

@Service
public class PaymentService {
    public ResponseEntity <PaymentResponseDTO> processPayment(PaymentRequestDTO request) {
        double randomNumber = Math.random();
        System.out.println("Número aleatório: " + randomNumber);
        if (randomNumber > 0.9) { // Simular fallos controlado
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new PaymentResponseDTO("FAILED", "Error"));
        }
        return ResponseEntity.status(HttpStatus.OK).body(new PaymentResponseDTO("PAID", "Pago exitoso"));
    }
}
