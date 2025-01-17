package tcc.eng.soft.order_microservice.dto;


public class OrderRequestDTO {
    private String customerName;
    private Double amount;

    public OrderRequestDTO(String customerName, Double amount) {
        this.customerName = customerName;
        this.amount = amount;
    }

    public OrderRequestDTO() {
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }
}
