package com.yjx.demo.activity;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author jxye
 */
@SpringBootApplication(exclude={org.activiti.spring.boot.SecurityAutoConfiguration.class, org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration.class
})
public class DemoActivityApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoActivityApplication.class, args);
    }

}
