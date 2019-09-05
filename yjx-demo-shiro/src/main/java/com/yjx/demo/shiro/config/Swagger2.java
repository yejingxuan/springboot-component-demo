package com.yjx.demo.shiro.config;


import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @author yejingxuan
 */
@Slf4j
@Configuration
@EnableSwagger2
public class Swagger2 {

    @Value("${server.servlet.context-path}")
    private String contextPath;

    @Value("${server.port}")
    private Integer port;

    @Bean
    public Docket createRestApi() {
        log.info("API文档地址: http://localhost:{}{}/swagger-ui.html", port, contextPath);
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
                .title("api文档")
                .description("shiro项目API文档")
                .termsOfServiceUrl("")
                .version("1.0")
                .build();
    }




}
