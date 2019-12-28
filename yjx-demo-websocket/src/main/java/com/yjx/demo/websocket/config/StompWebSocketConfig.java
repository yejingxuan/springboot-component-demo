package com.yjx.demo.websocket.config;

import com.yjx.demo.websocket.interceptor.NoticeHandshakeInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;
import org.springframework.web.socket.server.HandshakeInterceptor;

@Configuration
@EnableWebSocketMessageBroker
public class StompWebSocketConfig implements WebSocketMessageBrokerConfigurer {

    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        registry.addEndpoint("/notice")
                .addInterceptors(makeNoticeHandshake())
                .setAllowedOrigins("*")
                .withSockJS();
    }

    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {
        registry.setApplicationDestinationPrefixes("/user") // 路由到消息处理方法
                .enableSimpleBroker("/topic"); // 广播式配置一个topic的消息代理

    }

    @Bean
    public HandshakeInterceptor makeNoticeHandshake() {
        return new NoticeHandshakeInterceptor();
    }
}
