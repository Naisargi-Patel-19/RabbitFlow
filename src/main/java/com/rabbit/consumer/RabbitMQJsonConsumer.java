package com.rabbit.consumer;

import com.rabbit.dto.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class RabbitMQJsonConsumer {

    @Value("${rabbitmq.queue.exchange}")
    private String exchange;

    @Value("${rabbitmq.queue.json.routing_key}")
    private String jsonRoutingKey;

    private RabbitTemplate rabbitTemplate;

    @Autowired
    public RabbitMQJsonConsumer(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    @RabbitListener(queues = {"${rabbitmq.queue.json.name}"})
    public void receiveJsonMessage(User user){
        log.info("Received User is : " + user.toString());
        System.out.println(user.toString());
        System.out.println(((Object)user).getClass().getName());
    }
}
