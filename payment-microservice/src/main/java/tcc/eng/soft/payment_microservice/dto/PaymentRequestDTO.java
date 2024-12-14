package tcc.eng.soft.payment_microservice.dto;

import lombok.Data;

@Data
public class PaymentRequestDTO {
    private Long orderId;
    private Double amount;
}