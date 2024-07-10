package com.rabbit.consumer;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;


@Slf4j
@Service
public class RabbitMQConsumer {

    @Value("${rabbitmq.queue.name}")
    private String queue;
    @Value("${rabbitmq.queue.exchange}")
    private String exchange;
    @Value("${rabbitmq.queue.routing_key}")
    private String routingKey;

    private RabbitTemplate rabbitTemplate;

    @Autowired
    public RabbitMQConsumer(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    @RabbitListener(queues = {"${rabbitmq.queue.name}"})
    public void receiveMessage(String message){
        log.info("Received message is : " + message);
        System.out.println(message);
    }
}
