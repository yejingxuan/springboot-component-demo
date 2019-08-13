package com.yjx.demo.nacos.provider;

import com.alibaba.nacos.spring.context.annotation.config.NacosPropertySource;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@NacosPropertySource(dataId = "yjx-demo-nacos-provider", autoRefreshed = true)
@SpringBootApplication
public class DemoNacosProviderApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoNacosProviderApplication.class, args);
    }

}
