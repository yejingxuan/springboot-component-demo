package com.yjx.rabbitmq.config;


import java.util.HashMap;
import java.util.Map;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitConfig {

    @Autowired
    private SystemConfig systemConfig;

    @Bean
    public ConnectionFactory connectionFactory() {
        CachingConnectionFactory connectionFactory = new CachingConnectionFactory(
                systemConfig.getRabbitHost(),
                systemConfig.getRabbitPort());
        connectionFactory.setUsername(systemConfig.getRabbitUserName());
        connectionFactory.setPassword(systemConfig.getRabbitPassWord());
        connectionFactory.setVirtualHost("/");
        connectionFactory.setPublisherConfirms(true);
        return connectionFactory;
    }

    @Bean
    public RabbitTemplate rabbitTemplate() {
        RabbitTemplate template = new RabbitTemplate(connectionFactory());
        return template;
    }


    /**
     * 简单队列，不绑定任何交换机
     * 其实默认绑定了一个空字符串的交换机
     */
    @Bean
    public Queue queueYjxOrder() {
        return new Queue("yjx-queue");
    }


    /**
     * Topic模式的交换机绑定
     * 队列user-one、user-two绑定到user-exchange交换机
     * @return
     */
    @Bean
    public TopicExchange userExchange() {
        return new TopicExchange("user-exchange", true, false);
    }

    @Bean
    public Queue userOne() {
        return new Queue("user-one");
    }

    @Bean
    public Queue userTwo() {
        return new Queue("user-two");
    }

    @Bean
    Binding bindingExchangeOne(Queue userOne, TopicExchange userExchange) {
        return BindingBuilder.bind(userOne).to(userExchange).with("user");
    }

    @Bean
    Binding bindingExchangeTwo(Queue userTwo, TopicExchange userExchange) {
        return BindingBuilder.bind(userTwo).to(userExchange).with("user");
    }

    /**
     * 延时队列实现
     * @return
     */
    @Bean
    Queue orderDeadQueue() {
        return new Queue("order-dead-queue", true);
    }

    @Bean
    DirectExchange orderDeadExchange() {
        return new DirectExchange("order-dead-exchange");
    }

    @Bean
    Binding bindingDeadOrder() {
        return BindingBuilder.bind(orderDeadQueue()).to(orderDeadExchange()).with("order-dead-routing");
    }

    @Bean
    Queue orderQueue() {
        Map<String, Object> args = new HashMap<>(2);
        args.put("x-dead-letter-exchange", "order-dead-exchange");//交换机标识符
        args.put("x-dead-letter-routing-key", "order-dead-routing");//绑定键标识符
        return new Queue("order-queue", true, false, false, args);
    }

    @Bean
    DirectExchange orderExchange() {
        return new DirectExchange("order-exchange");
    }

    @Bean
    Binding bindingOrder() {
        return BindingBuilder.bind(orderQueue()).to(orderExchange()).with("order-routing");
    }

}
