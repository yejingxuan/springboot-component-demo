package com.yjx.demo.dislock.support.dislock;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.stereotype.Service;


/**
 * 基于redis的分布式锁实现
 *
 * @author yejingxuan
 **/
@Service
@Slf4j
public class RedisDisLockImpl implements DisLock {
    /**
     * 分布式锁的名称
     */
    @Value("${DISTRIBUTED.LOCK.KEY:redis:lock:}")
    private String DISTRIBUTED_LOCK_KEY;

    /**
     * 分布式锁的超时时间（秒）
     */
    @Value("${DISTRIBUTED.LOCK.TIMEOUT:15}")
    private Integer DISTRIBUTED_LOCK_TIMEOUT;

    /**
     * 分布式锁的等待时间（毫秒）
     */
    @Value("${DISTRIBUTED.LOCK.WAITTIME:200}")
    private Integer DISTRIBUTED_LOCK_WAITTIME;

    @Autowired
    private RedisTemplate<Object, Object> redisTemplate;


    /**
     * 获取分布式锁
     * @author yejingxuan
     * @date 10:40 2019/7/23
     */
    @Override
    public boolean lock(String key, String uuid) {
        Boolean lock;
        key = DISTRIBUTED_LOCK_KEY + key;
        lock = redisTemplate.opsForValue()
                .setIfAbsent(key, uuid, DISTRIBUTED_LOCK_TIMEOUT, TimeUnit.SECONDS);
        /*if(!lock){
            log.info(key + ":" + uuid + "未获得锁");
            return lock;
        }*/
        while (!lock) {
            log.info(key + ":" + uuid + "未获得锁,重入");
            lock = redisTemplate.opsForValue()
                    .setIfAbsent(key, uuid, DISTRIBUTED_LOCK_TIMEOUT, TimeUnit.SECONDS);
            try {
                TimeUnit.MILLISECONDS.sleep(DISTRIBUTED_LOCK_WAITTIME + new Random().nextInt(100));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        log.info(key + ":" + uuid + "获得锁");
        return lock;
    }


    /**
     * @return java.lang.Boolean
     * @Author yejingxuan
     * @Description 解开分布式锁
     * @Date 14:46
     * @Param [key, uuid]
     **/
    @Override
    public boolean unLock(String key, String uuid) {
        Boolean res;
        DefaultRedisScript<Boolean> script = new DefaultRedisScript<Boolean>();
        script.setResultType(Boolean.class);
        script.setScriptText("if redis.call('get', KEYS[1]) == ARGV[1] "
                + "then "
                + "return redis.call('del', KEYS[1]) else return 0 end");
        //List设置lua的KEYS
        List<String> keyList = new ArrayList();
        keyList.add(DISTRIBUTED_LOCK_KEY + key);
        res = redisTemplate.execute(script, Collections.singletonList(keyList.get(0)), uuid);
        if (res) {
            log.info(key + ":" + uuid + "释放锁");
        } else {
            log.info(key + ":" + uuid + "未释放锁");
        }
        return res;
    }

}
