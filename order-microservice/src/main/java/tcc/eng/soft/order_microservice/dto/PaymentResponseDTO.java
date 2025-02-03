package tcc.eng.soft.order_microservice.dto;



public class PaymentResponseDTO {
    private String status;
    private String message;

    public PaymentResponseDTO(String success, String message) {
        this.status = success;
        this.message = message;
    }

    public PaymentResponseDTO() {
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}