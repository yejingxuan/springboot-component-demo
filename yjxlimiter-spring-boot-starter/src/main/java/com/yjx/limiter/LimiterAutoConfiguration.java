package com.yjx.limiter;

import com.yjx.limiter.config.LimiterProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration  //注释使类成为bean的工厂
@EnableConfigurationProperties(LimiterProperties.class) //使@ConfigurationProperties注解生效
@ComponentScan(basePackages = {"com.yjx.limiter.service", "com.yjx.limiter.domain.aop"})
public class LimiterAutoConfiguration {




}
