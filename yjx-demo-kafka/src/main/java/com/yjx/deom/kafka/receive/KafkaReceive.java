package com.yjx.deom.kafka.receive;


import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class KafkaReceive {

    @KafkaListener(topics = {"yjx-test3"}, groupId = "0")
    public void listenUser1(ConsumerRecord message){
        log.info("listenUser1:{}",message.value().toString());
    }


/*    @KafkaListener(topics = {"yjx-test3"}, groupId = "0")
    public void listenUser2(ConsumerRecord message){
        log.info("listenUser2:{}",message.value().toString());
    }*/

/*    @KafkaListener(topics = {"yjx-212"}, groupId = "1")
    public void listenUser3(ConsumerRecord message){
        log.info("listenUser3:{}",message.value().toString());
    }*/


}
