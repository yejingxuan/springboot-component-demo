package com.yjx.rabbitmq.controller;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping(value = "/rabbit")
@RestController
public class RabbitController {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @PostMapping(value = "/sendMsgToTopic")
    public String sendMsgToTopic(String topic, String message){
        rabbitTemplate.convertAndSend(topic, message);
        return "success";
    }


    @PostMapping(value = "/sendMsg")
    public String sendMsgToExchange(String exchange, String routingKey, String message){
        rabbitTemplate.convertAndSend(exchange, routingKey, message);
        return "success";
    }

}
