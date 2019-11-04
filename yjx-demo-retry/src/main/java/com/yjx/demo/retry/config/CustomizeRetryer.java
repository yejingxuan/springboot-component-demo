package com.yjx.demo.retry.config;

import com.github.rholder.retry.Retryer;
import com.github.rholder.retry.RetryerBuilder;
import com.github.rholder.retry.StopStrategies;
import com.github.rholder.retry.WaitStrategies;
import com.google.common.base.Predicates;
import java.util.concurrent.TimeUnit;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class CustomizeRetryer {

    //定义重试机制
    private Retryer<Boolean> mailRetryer;

    private Retryer<Boolean> msgRetryer;

    @Bean(value = "mailRetryer")
    public Retryer mailRetryer(){
        log.info("===========init mailRetryer==========");
        mailRetryer = RetryerBuilder.<Boolean>newBuilder()
                //retryIf 重试条件
                .retryIfException()
                .retryIfRuntimeException()
                .retryIfExceptionOfType(Exception.class)
                .retryIfException(Predicates.equalTo(new Exception()))
                .retryIfResult(Predicates.equalTo(false))
                //等待策略：每次请求间隔1s
                .withWaitStrategy(WaitStrategies.fixedWait(1, TimeUnit.SECONDS))
                //停止策略 : 尝试请求6次
                .withStopStrategy(StopStrategies.stopAfterAttempt(6))
                .build();
        return mailRetryer;
    }

    @Bean(value = "msgRetryer")
    public Retryer msgRetryer(){
        log.info("=========init msgRetryer===========");
        msgRetryer = RetryerBuilder.<Boolean>newBuilder()
                //retryIf 重试条件
                .retryIfException()
                .retryIfRuntimeException()
                .retryIfExceptionOfType(Exception.class)
                .retryIfException(Predicates.equalTo(new Exception()))
                .retryIfResult(Predicates.equalTo(false))
                //等待策略：每次请求间隔0.2秒
                .withWaitStrategy(WaitStrategies.fixedWait(200, TimeUnit.MILLISECONDS))
                //停止策略 : 尝试请求9次
                .withStopStrategy(StopStrategies.stopAfterAttempt(9))
                .build();
        return msgRetryer;
    }
}
