package tcc.eng.soft.payment_microservice.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
public class PaymentRequestDTO {
    @JsonProperty("orderId")
    private String orderId;
    @JsonProperty("amount")
    private Double amount;
}
