package com.yjx.demo.retry.config;

import java.io.IOException;
import lombok.extern.slf4j.Slf4j;
import net.anumbrella.seaweedfs.core.FileSource;
import net.anumbrella.seaweedfs.core.FileTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class SeaweedConfig {
    @Bean
    public FileTemplate fileTemplate(){
        FileTemplate template;
        FileSource fileSource = new FileSource();
        fileSource.setHost("47.98.48.32");
        fileSource.setPort(9333);
        fileSource.setConnectionTimeout(5000);
        try {
            fileSource.startup();
            template = new FileTemplate(fileSource.getConnection());
            log.error("seaweed连接成功");
            return template;
        } catch (IOException e) {
            log.error("seaweed连接失败", e);
            return null;
        }
    }
}
