package com.yjx.demo.netty;

import com.yjx.demo.netty.config.SystemConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class DemoNettyApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoNettyApplication.class, args);
    }
    
}
