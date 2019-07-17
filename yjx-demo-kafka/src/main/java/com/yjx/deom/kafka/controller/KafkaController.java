package com.yjx.deom.kafka.controller;

import com.alibaba.fastjson.JSONObject;
import com.yjx.deom.kafka.config.KafkaConfig;
import com.yjx.deom.kafka.model.UserInfo;
import io.swagger.annotations.ApiOperation;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@Slf4j
@RestController
public class KafkaController {

    @Autowired
    private KafkaTemplate kafkaTemplate;

    @Autowired
    private KafkaConfig kafkaConfig;

    @ApiOperation(value = "创建topic")
    @PutMapping(value = "createTopic")
    public String createTopic(@RequestParam(value = "topicName") String topicName){
        List<NewTopic> topics = new ArrayList();
        NewTopic topic = new NewTopic(topicName, 1, Short.valueOf("1"));
        Map<String, String> configs = new HashMap<>();
        configs.put("max.message.bytes","4096000");
        topic.configs(configs);

        topics.add(topic);
        kafkaConfig.adminClient().createTopics(topics);
        return "success";
    }

    @ApiOperation(value = "发送kafka消息")
    @PutMapping(value = "sendMsg")
    public String sendMsg(UserInfo userInfo, @RequestParam(value = "topicName") String topicName){
        String msg = JSONObject.toJSONString(userInfo);
        log.info(String.valueOf(msg.length()));
        kafkaTemplate.send(topicName, msg);
        return "success";
    }

    @ApiOperation(value = "发送超大kafka消息")
    @PutMapping(value = "sendBigMsg")
    public String sendBigMsg(UserInfo userInfo, @RequestParam(value = "topicName") String topicName){
        String msg = JSONObject.toJSONString(userInfo);
        for (int i=0; i<15; i++){
            msg+=msg;
        }
        log.info(String.valueOf(msg.length()));
        kafkaTemplate.send(topicName, msg);
        return "success";
    }

}
