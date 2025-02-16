package com.learner.stockservice.dto;

public class OrderEvent {

    private String status; //pending, progress, completed
    private String message;
    private Order order;

    public OrderEvent() {
    }

    public OrderEvent(String status, String message, Order order) {
        this.status = status;
        this.message = message;
        this.order = order;
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

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    @Override
    public String toString() {
        return "OrderEvent{" +
                "status='" + status + '\'' +
                ", message='" + message + '\'' +
                ", order=" + order +
                '}';
    }
}
