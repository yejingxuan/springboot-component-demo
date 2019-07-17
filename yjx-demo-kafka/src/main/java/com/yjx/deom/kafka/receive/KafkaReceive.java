package com.yjx.deom.kafka.receive;


import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class KafkaReceive {

    @KafkaListener(topics = {"yjx-user"})
    public void listenUser(ConsumerRecord message){
        log.info(message.value().toString());
    }


}
