package com.yjx.demo.dislock.service.Impl;

import com.yjx.demo.dislock.service.DisLockService;
import com.yjx.demo.dislock.support.dislock.DisLock;
import com.zengtengpeng.annotation.Lock;
import java.util.UUID;
import java.util.concurrent.TimeUnit;
import lombok.extern.slf4j.Slf4j;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * @author yejingxuan
 */
@Slf4j
@Service
public class DisLockServiceImpl implements DisLockService {

    @Autowired
    private DisLock redisDisLockImpl;

    @Autowired
    private RedissonClient redissonClient;

    public Integer data = 20;

    @Override
    public Boolean handleDataByRedisLock() {
        String key = "handleDataByRedisLock";
        String value = UUID.randomUUID().toString();
        boolean lock = redisDisLockImpl.lock(key, value);
        if(lock){
            if(data>0){
                data = data-1;
                log.info("data值为：{}", data);
            }
        }
        redisDisLockImpl.unLock(key, value);
        return true;
    }


    @Lock(keys  = "handleDataByRedissonLock", attemptTimeout = 20000)
    @Override
    public Boolean handleDataByRedissonLock() {

        if(data>0){
            data = data-1;
            log.info("data值为：{}", data);
        }
        return true;
    }


    public Boolean handleDataByRedissonLock2() {
        String key = "handleDataByRedissonLock";
        RLock fairLock = redissonClient.getFairLock("");

        fairLock.lock();

        redissonClient.getFairLock("");
        redissonLock.lock(30, TimeUnit.SECONDS);
        if(data>0){
            data = data-1;
            log.info("data值为：{}", data);
        }
        redissonLock.unlock();

        return true;
    }
}
