package com.yjx.demo.quickssm;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import tk.mybatis.spring.annotation.MapperScan;

@MapperScan(basePackages = "com.yjx.demo.quickssm.dao")
@SpringBootApplication
public class DemoQuickssmApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoQuickssmApplication.class, args);
    }

}
