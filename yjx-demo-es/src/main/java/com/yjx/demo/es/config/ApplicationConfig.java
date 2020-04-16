package com.yjx.demo.es.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@Data
public class ApplicationConfig {

    @Value("${server.port}")
    private String port;

    @Value("${server.servlet.context-path}")
    private String contextPath;

    @Value("${elasticsearch.host}")
    private String esHost;

    @Value("${elasticsearch.port}")
    private int esPort;

    @Value("${elasticsearch.clustername}")
    private String esClusterName;

    @Value("${elasticsearch.query.highlight.pretags}")
    private String preTags;

    @Value("${elasticsearch.query.highlight.posttags}")
    private String postTags;

    @Value("${elasticsearch.query.minimumShouldMatch}")
    private String minMatch;

}
