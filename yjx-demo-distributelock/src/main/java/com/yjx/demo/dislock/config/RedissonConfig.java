package com.yjx.demo.dislock.config;


import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.redisson.config.SingleServerConfig;
import org.redisson.config.TransportMode;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.StringUtils;

/**
 * @author yejingxuan
 */
@Configuration
public class RedissonConfig {

    /*@Value("${spring.redis.host}")
    private String hostName;

    @Value("${spring.redis.port}")
    private Integer port;

    @Value("${spring.redis.password}")
    private String password;


    *//**
     * 单机模式自动装配
     *//*
    @Bean
    public RedissonClient getRedisson() {
        Config config = new Config();
        config.useSingleServer()
                .setAddress("redis://"+hostName + ":" + port)
                //.setTimeout(redssionProperties.getTimeout())
                //.setConnectionPoolSize(redssionProperties.getConnectionPoolSize())
                //.setConnectionMinimumIdleSize(redssionProperties.getConnectionMinimumIdleSize())
                .setPassword(password)
                .setConnectionMinimumIdleSize(10);

        return Redisson.create(config);
    }*/







}
