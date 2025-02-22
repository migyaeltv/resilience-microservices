package tcc.eng.soft.payment_microservice.service;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import tcc.eng.soft.payment_microservice.dto.PaymentRequestDTO;
import tcc.eng.soft.payment_microservice.dto.PaymentResponseDTO;

@Service
public class PaymentService {
    public ResponseEntity <PaymentResponseDTO> processPayment(PaymentRequestDTO request) throws InterruptedException {
        Thread.sleep(200);
        return ResponseEntity.status(HttpStatus.OK).body(new PaymentResponseDTO("PAID", "Pago exitoso"));
    }
}
