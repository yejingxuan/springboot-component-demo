package com.yjx.limiter.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;


@Data
@ConfigurationProperties(prefix = "limiter")
public class LimiterProperties {

    private Boolean limitEnable;

    private String lockEnable;

    private String msg;

}
