package com.learner.orderservice.configuration;

import org.springframework.amqp.core.*;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MyRabbitMQConfig {

    @Value("${rabbitmq.exchange.name}")
    private String exchange;

    @Value("${rabbitmq.queue.stock.name}")
    private String stockQueue;

    @Value("${rabbitmq.queue.email.name}")
    private String emailQueue;

    @Value("${rabbitmq.stock.routing.key}")
    private String stockRoutingKey;

    @Value("${rabbitmq.email.routing.key}")
    private String emailRoutingKey;

    @Bean
    public TopicExchange exchange(){
        return new TopicExchange(exchange);
    }

    @Bean
    public Queue stockQueue(){
        return new Queue(stockQueue);
    }

    @Bean
    public Queue emailQueue(){
        return new Queue(emailQueue);
    }

    //Bind order_stock_queue to order_exchange
    @Bean
    public Binding stockQueueBinding(){
        return BindingBuilder.bind(stockQueue())
                .to(exchange())
                .with(stockRoutingKey);
    }

    //Bind order_email_queue to order_exchange
    @Bean
    public Binding emailQueueBinding(){
        return BindingBuilder.bind(emailQueue())
                .to(exchange())
                .with(emailRoutingKey);
    }

    //To work with Json messages we have to explicitly RabbitTemplate with jason MessageConverter.
    @Bean
    public MessageConverter messageConverter(){
        return new Jackson2JsonMessageConverter();
    }

     /*These below infrastructure beans are also required by SpringBoot app to work with
      RabbitMQ Broker but these beans are auto-configured by SpringBoot.
      1. ConnectionFactory
      2. RabbitTemplate
      3. RabbitAdmin */


//    If a MessageConverter bean is defined, it is associated automatically to the auto-configured AmqpTemplate.

    //Explicitly Creating RabbitTemplate instance to set message converter
   /* @Bean
    public AmqpTemplate amqpTemplate(ConnectionFactory connectionFactory){
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(jsonMessageConverter());
        return rabbitTemplate;
    }*/



}
