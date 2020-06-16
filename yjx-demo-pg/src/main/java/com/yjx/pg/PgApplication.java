package com.yjx.pg;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import tk.mybatis.spring.annotation.MapperScan;

@MapperScan(basePackages = "com.yjx.pg.dao")
@SpringBootApplication
public class PgApplication {

    public static void main(String[] args) {
        SpringApplication.run(PgApplication.class, args);
    }

}
