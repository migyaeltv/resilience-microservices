package tcc.eng.soft.payment_microservice.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Getter
@Setter
public class PaymentResponseDTO {
    @JsonProperty("status")
    private String status;
    @JsonProperty("message")
    private String message;

    public PaymentResponseDTO() {}
    public PaymentResponseDTO(String status, String message) {
        this.status = status;
        this.message = message;
    }
}
