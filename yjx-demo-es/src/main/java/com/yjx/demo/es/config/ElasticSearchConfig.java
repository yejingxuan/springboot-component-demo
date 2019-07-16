package com.yjx.demo.es.config;

import java.net.InetAddress;
import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.client.Client;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.TransportAddress;
import org.elasticsearch.transport.client.PreBuiltTransportClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;

@Slf4j
@Configuration
@EnableElasticsearchRepositories(basePackages = "com.yjx.**.repository")
public class ElasticSearchConfig {

    @Value("${elasticsearch.host}")
    private String esHost;

    @Value("${elasticsearch.port}")
    private int esPort;

    @Value("${elasticsearch.clustername}")
    private String esClusterName;


    @Bean
    public Client client() throws Exception {

        Settings esSettings = Settings.builder()
                .put("cluster.name", esClusterName)
                .put("client.transport.sniff", true)//增加嗅探机制，找到ES集群
                .put("thread_pool.search.size", Integer.parseInt("5"))//增加线程池个数，暂时设为5
                .build();

        return new PreBuiltTransportClient(esSettings)
                .addTransportAddress(new TransportAddress(InetAddress.getByName(esHost), esPort));
    }

    @Bean
    public ElasticsearchOperations elasticsearchTemplateCustom() throws Exception {
        ElasticsearchTemplate elasticsearchTemplate;
        try {
            elasticsearchTemplate = new ElasticsearchTemplate(client());
            return elasticsearchTemplate;
        } catch (Exception e) {
            log.error("初始化ElasticsearchTemplate失败！");
            return new ElasticsearchTemplate(client());
        }
    }
}
