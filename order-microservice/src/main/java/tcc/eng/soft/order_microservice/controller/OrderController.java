package tcc.eng.soft.order_microservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tcc.eng.soft.order_microservice.dto.*;
import tcc.eng.soft.order_microservice.service.OrderService;
import org.springframework.http.HttpStatus;
import tcc.eng.soft.order_microservice.dto.OrderRequestDTO;
import tcc.eng.soft.order_microservice.dto.OrderResponseDTO;

@RestController
@RequestMapping("/orders")
public class OrderController {

    private final OrderService orderService;
    private PaymentResponseDTO paymentResponseDTO;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping
    public ResponseEntity<OrderResponseDTO> createOrder(@RequestBody OrderRequestDTO orderRequest) {
        OrderResponseDTO response = orderService.createOrder(orderRequest);

        if (response.getStatus().equals("PAID")) {
            return ResponseEntity.ok(response);
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }
}

