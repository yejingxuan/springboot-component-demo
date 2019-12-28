package com.yjx.demo.websocket.config;

import com.yjx.demo.websocket.handler.NoticeHandler;
import com.yjx.demo.websocket.interceptor.NoticeHandshakeInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;
import org.springframework.web.socket.server.HandshakeInterceptor;

//@Configuration
//@EnableWebSocket
public class RegistryWebSocketConfig /*implements WebSocketConfigurer*/ {

    /*@Autowired
    private NoticeHandler noticeHandler;

    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry webSocketHandlerRegistry) {
        webSocketHandlerRegistry
                .addHandler(noticeHandler, "/noticeHandler")
                .addInterceptors(makeNoticeHandshake())
                .setAllowedOrigins("*")
                .withSockJS();
    }


    @Bean
    public HandshakeInterceptor makeNoticeHandshake() {
        return new NoticeHandshakeInterceptor();
    }*/
}
