package tcc.eng.soft.payment_microservice.dto;

import lombok.Data;

@Data
public class PaymentRequestDTO {
    private String orderId;
    private Double amount;
}