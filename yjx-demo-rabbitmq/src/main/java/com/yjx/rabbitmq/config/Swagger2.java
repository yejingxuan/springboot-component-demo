package com.yjx.rabbitmq.config;


import java.net.InetAddress;
import java.net.UnknownHostException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Order(Ordered.LOWEST_PRECEDENCE)
@Slf4j
@Configuration
@EnableSwagger2
public class Swagger2 {

    @Autowired
    private SystemConfig config;

    @Bean
    public Docket createRestApi() {
        String hostAddress = "127.0.0.1";
        try {
            hostAddress = InetAddress.getLocalHost().getHostAddress();
        } catch (UnknownHostException e) {
            log.error("获取本地ip出错");
        }
        log.info("http://{}:{}{}/swagger-ui.html", hostAddress, config.getPort(), config.getContextPath());
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                //.apis(RequestHandlerSelectors.basePackage("com.yjx.homeweb.project.controller"))
                .apis(RequestHandlerSelectors.withClassAnnotation(RestController.class))
                .paths(PathSelectors.any())
                .build();
    }
    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("RabbitMQ接口文档")
                .description("RabbitMQ接口文档")
                .termsOfServiceUrl("")
                .version("1.0")
                .build();
    }




}
