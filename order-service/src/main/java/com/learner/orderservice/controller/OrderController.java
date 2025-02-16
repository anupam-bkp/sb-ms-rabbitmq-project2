package com.learner.orderservice.controller;

import com.learner.orderservice.dto.Order;
import com.learner.orderservice.dto.OrderEvent;
import com.learner.orderservice.publisher.OrderPublisher;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1")
public class OrderController {

    private OrderPublisher orderPublisher;

    public OrderController(OrderPublisher orderPublisher) {
        this.orderPublisher = orderPublisher;
    }

    @PostMapping("/orders")
    public ResponseEntity<String> placeOrder(@RequestBody Order order){

        order.setOrderId(UUID.randomUUID().toString());

        OrderEvent orderEvent = new OrderEvent("PENDING", "Order is Pending", order);
        orderPublisher.publishOrderEvent(orderEvent);

        return ResponseEntity.ok("Order sent to RabbitMQ ... ");
    }
}
