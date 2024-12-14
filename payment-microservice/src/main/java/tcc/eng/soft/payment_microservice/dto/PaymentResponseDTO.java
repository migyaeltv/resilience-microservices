package tcc.eng.soft.payment_microservice.dto;

import lombok.*;

@Getter
@Setter
public class PaymentResponseDTO {
    private boolean success;
    private String message;

    public PaymentResponseDTO() {}
    public PaymentResponseDTO(boolean success, String message) {
        this.success = success;
        this.message = message;
    }
}
