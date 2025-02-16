package com.learner.stockservice.consumer;

import com.learner.stockservice.dto.OrderEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
public class OrderStockConsumer {

    private static final Logger LOGGER = LoggerFactory.getLogger(OrderStockConsumer.class);

    @RabbitListener(queues = {"${rabbitmq.queue.stock.name}"})
    public void consumeOrderEvent(OrderEvent orderEvent){
        LOGGER.info(String.format("Order Event received => %s", orderEvent.toString()));

        //save order event in database.
    }

}
