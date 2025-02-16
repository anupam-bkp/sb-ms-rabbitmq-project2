package com.learner.emailservice.consumer;

import com.learner.emailservice.dto.OrderEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
public class OrderEmailConsumer {

    private static final Logger LOGGER = LoggerFactory.getLogger(OrderEmailConsumer.class);

    @RabbitListener(queues = {"${rabbitmq.queue.email.name}"})
    public void consumeOrderEvent(OrderEvent orderEvent) {
        LOGGER.info(String.format("Order event received -> %s", orderEvent.toString()));

        //send order email to customer
    }

}
