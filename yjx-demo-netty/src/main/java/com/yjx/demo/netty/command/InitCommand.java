package com.yjx.demo.netty.command;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class InitCommand implements CommandLineRunner {

    @Override
    public void run(String... args) throws Exception {
        Runtime  runtime = Runtime.getRuntime();

        System.out.println("1111");
    }
}
