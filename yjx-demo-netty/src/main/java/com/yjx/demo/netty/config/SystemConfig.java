package com.yjx.demo.netty.config;

import org.springframework.context.annotation.Configuration;

@Configuration
public class SystemConfig {

    private String port = "8091";

    public String getPort(){
        System.out.println(port);
        return this.port;
    }

}
