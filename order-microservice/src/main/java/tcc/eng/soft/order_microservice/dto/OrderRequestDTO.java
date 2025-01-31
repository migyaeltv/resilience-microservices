package tcc.eng.soft.order_microservice.dto;


public class OrderRequestDTO {
    private String customerId;
    private Double amount;

    public OrderRequestDTO(String customerId, Double amount) {
        this.customerId = customerId;
        this.amount = amount;
    }

    public OrderRequestDTO() {
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }
}
