package com.learner.orderservice.publisher;

import com.learner.orderservice.dto.OrderEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class OrderPublisher {

    private static final Logger LOGGER = LoggerFactory.getLogger(OrderPublisher.class);

    @Value("${rabbitmq.exchange.name}")
    private String exchange;

    @Value("${rabbitmq.stock.routing.key}")
    private String stockRoutingKey;

    private RabbitTemplate rabbitTemplate;

    public OrderPublisher(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void publishOrderEvent(OrderEvent orderEvent){
        LOGGER.info(String.format("Order Event sent to RabbitMQ -> %s", orderEvent.toString()));
        rabbitTemplate.convertAndSend(exchange, stockRoutingKey, orderEvent);
    }

}
