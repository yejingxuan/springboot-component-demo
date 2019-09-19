package com.yjx.demo.netty.schedule;


import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * @author yejingxuan
 */
@Configuration
public class DicDataWorker {


    @Scheduled(fixedRateString = "2000")
    public void getDicData() {
        // 更新字典，默认24小时更新一次
        System.out.println("init data"+System.currentTimeMillis());
    }

}
