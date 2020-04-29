package com.yjx.rabbitmq.config;


import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitConfig {

    @Value("${spring.rabbitmq.host}")
    private String rabbitHost;

    @Value("${spring.rabbitmq.port}")
    private int rabbitPort;

    @Value("${spring.rabbitmq.username}")
    private String rabbitUserName;

    @Value("${spring.rabbitmq.password}")
    private String rabbitPassWord;

    @Bean
    public ConnectionFactory connectionFactory() {
        CachingConnectionFactory connectionFactory = new CachingConnectionFactory(rabbitHost,
                rabbitPort);
        connectionFactory.setUsername(rabbitUserName);
        connectionFactory.setPassword(rabbitPassWord);
        connectionFactory.setVirtualHost("/");
        connectionFactory.setPublisherConfirms(true);
        return connectionFactory;
    }

    @Bean
    //@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
    //必须是prototype类型
    public RabbitTemplate rabbitTemplate() {
        RabbitTemplate template = new RabbitTemplate(connectionFactory());
        return template;
    }


    @Bean
    public TopicExchange captureExchange() {
        return new TopicExchange("user-exchange", true, false);
    }

    @Bean
    public Queue queueUserOne() {
        // 创建一个队列，名称为：first
        return new Queue("user-one");
    }


    @Bean
    public Queue queueUserTwo() {
        // 创建一个队列，名称为：first
        return new Queue("user-two");
    }


    @Bean
    Binding bindingExchangeOne(Queue queueUserOne, TopicExchange captureExchange) {
        return BindingBuilder.bind(queueUserOne).to(captureExchange).with("user");
    }

    @Bean
    Binding bindingExchangeTwo(Queue queueUserTwo, TopicExchange captureExchange) {
        return BindingBuilder.bind(queueUserTwo).to(captureExchange).with("user");
    }



    @Bean
    public Queue queueYjxOrder() {
        // 创建一个队列，名称为：first
        return new Queue("yjx-order");
    }


}
