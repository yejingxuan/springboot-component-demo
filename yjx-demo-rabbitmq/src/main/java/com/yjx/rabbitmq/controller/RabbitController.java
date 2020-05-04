package com.yjx.rabbitmq.controller;

import java.time.Instant;
import java.util.Date;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping(value = "/send")
@RestController
public class RabbitController {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    /**
     * 直接发送消息到指定的队列
     */
    @PostMapping(value = "/sendMsgToQueue")
    public String sendMsgToQueue(String queueName, String message) {
        rabbitTemplate.convertAndSend(queueName, message);
        return "success";
    }

    /**
     * 发送消息到交换机，交换机路由到相关队列
     */
    @PostMapping(value = "/sendMsgToExchange")
    public String sendMsgToExchange(String exchange, String routingKey, String message) {
        rabbitTemplate.convertAndSend(exchange, routingKey, message);
        return "success";
    }

    /**
     * 发送消息至延时队列
     */
    @PostMapping(value = "/sendMsgToDelayQueue")
    public String sendMsgToDelayQueue(String exchange, String routingKey, String msg) {
        rabbitTemplate.convertAndSend(exchange, routingKey, msg, message -> {
            //设置延时时间
            //也可以在队列中设置
            message.getMessageProperties().setExpiration(5 * 1000 + "");
            return message;
        });
        System.out.println("生产时间:" + new Date());
        return null;
    }

}
