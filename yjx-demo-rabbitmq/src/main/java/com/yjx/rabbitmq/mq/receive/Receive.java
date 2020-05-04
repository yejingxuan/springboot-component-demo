package com.yjx.rabbitmq.mq.receive;

import java.time.Instant;
import java.util.Date;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class Receive {


    /**
     * 启动报警监听
     */
    @RabbitListener(queues = "user-one")
    public void userOne1(String message) {
        log.info("userOne1 receive info:{}", message);
    }

    @RabbitListener(queues = "user-one")
    public void userOne2(String message) {
        log.info("userOne2 receive info:{}", message);
    }

    @RabbitListener(queues = "user-two")
    public void userTwo1(String message) {
        log.info("userTwo1 receive info:{}", message);
    }

    /**
     * 接收延时队列
     * @param message
     */
    @RabbitListener(queues = "order-dead-queue")
    public void orderDeadQueue(String message) {
        log.info("order-dead-queue receive info:{}, date:{}", message, new Date());
    }


}
