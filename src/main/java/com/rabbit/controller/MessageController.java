package com.rabbit.controller;

import com.rabbit.dto.User;
import com.rabbit.publisher.RabbitMQJsonProducer;
import com.rabbit.publisher.RabbitMQProducer;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
public class MessageController {

    private RabbitMQProducer rabbitMQProducer;
    private RabbitMQJsonProducer rabbitMQJsonProducer;

    @Autowired
    public MessageController(RabbitMQProducer rabbitMQProducer,RabbitMQJsonProducer rabbitMQJsonProducer) {
        this.rabbitMQProducer = rabbitMQProducer;
        this.rabbitMQJsonProducer = rabbitMQJsonProducer;
    }

    @PostMapping("/publish")
    public ResponseEntity<?> sendMessage(@RequestParam("message") String message){
        this.rabbitMQProducer.sendMessage(message);
        return ResponseEntity.ok("Message got published to RabbitMQ queue");
    }

    @PostMapping("/publishJson")
    public ResponseEntity<?> publishJsonMessage(@RequestBody User user){
        this.rabbitMQJsonProducer.publishJsonMessage(user);
        return ResponseEntity.ok(user.toString() + "got published to the json queue");
    }






}
