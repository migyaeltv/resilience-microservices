package tcc.eng.soft.order_microservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;


public class PaymentResponseDTO {
    private boolean success;
    private String message;

    public PaymentResponseDTO(boolean success, String message) {
        this.success = success;
        this.message = message;
    }

    public PaymentResponseDTO() {
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}