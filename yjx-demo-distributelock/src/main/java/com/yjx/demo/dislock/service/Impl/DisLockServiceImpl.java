package com.yjx.demo.dislock.service.Impl;

import com.yjx.demo.dislock.service.DisLockService;
import com.yjx.demo.dislock.support.dislock.DisLock;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.TimeUnit;
import lombok.extern.slf4j.Slf4j;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.recipes.locks.InterProcessLock;
import org.apache.curator.framework.recipes.locks.InterProcessMultiLock;
import org.apache.curator.framework.recipes.locks.InterProcessMutex;
import org.apache.curator.framework.recipes.locks.InterProcessReadWriteLock;
import org.apache.curator.framework.recipes.locks.InterProcessSemaphoreMutex;
import org.redisson.RedissonMultiLock;
import org.redisson.api.RLock;
import org.redisson.api.RReadWriteLock;
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

    @Autowired
    private CuratorFramework zkClient;


    @Override
    public Boolean testZkLock() throws Exception {
        String lockPath = "/lock/lock1";
        InterProcessMutex lock = new InterProcessMutex(zkClient, lockPath);
        lock.acquire(100, TimeUnit.SECONDS);
        System.out.println("do service1");
        Boolean aBoolean = this.testZkLock2(lock);
        lock.release();
        return null;
    }

    private Boolean testZkLock2(InterProcessMutex lock) {
        try {
            lock.acquire(100, TimeUnit.SECONDS);
            System.out.println("do service2");
            lock.release();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return true;
    }


    private void testZkAllLocks() {
        String lockPath = "/lock/lock1";
        // 可重入公平锁
        InterProcessMutex interProcessMutex = new InterProcessMutex(zkClient, lockPath);
        // 不可重入锁
        InterProcessSemaphoreMutex processSemaphoreMutex = new InterProcessSemaphoreMutex(zkClient,
                lockPath);
        // 联锁
        // 构造方法1
        List<InterProcessLock> locks = new ArrayList<>();
        InterProcessMutex lock1 = new InterProcessMutex(zkClient, lockPath);
        InterProcessMutex lock2 = new InterProcessMutex(zkClient, lockPath);
        InterProcessMultiLock interProcessMultiLock1 = new InterProcessMultiLock(locks);
        // 构造方法2
        List<String> paths = Arrays.asList("/lock/lock1", "/lock/lock2", "/lock/lock3");
        InterProcessMultiLock interProcessMultiLock2 = new InterProcessMultiLock(zkClient, paths);
        // 读写锁
        InterProcessReadWriteLock readWriteLock = new InterProcessReadWriteLock(zkClient, lockPath);
        readWriteLock.readLock();
    }


    private void testRedissonAllLocks() {
        String key  = "test1";

        // 可重入锁
        RLock lock = redissonClient.getLock(key);
        // 公平锁
        RLock fairLock = redissonClient.getFairLock(key);
        // 联锁
        RLock lock1 = redissonClient.getLock("lock1");
        RLock lock2 = redissonClient.getLock("lock2");
        RLock lock3 = redissonClient.getLock("lock3");
        RLock locks = new RedissonMultiLock(lock1, lock2, lock3);
        // 红锁
        RLock redLock = redissonClient.getRedLock(lock1, lock2);
        // 读写锁
        RReadWriteLock readWriteLock = redissonClient.getReadWriteLock(key);

    }


    @Override
    public Boolean handleDataByRedisLock() {
        String key = "handleDataByRedisLock";
        String value = UUID.randomUUID().toString();
        boolean lock = redisDisLockImpl.lock(key, value);
        if (lock) {
            if (data > 0) {
                data = data - 1;
                log.info("data值为：{}", data);
            }
        }
        redisDisLockImpl.unLock(key, value);
        return true;
    }


    //@Lock(keys  = "handleDataByRedissonLock", attemptTimeout = 20000)
    @Override
    public Boolean testRedissonLock() throws InterruptedException {
        String key = "testRedissonLock";
        RLock lock = redissonClient.getLock(key);
        boolean b = lock.tryLock(100, 100, TimeUnit.SECONDS);
        if (data > 0) {
            data = data - 1;
            log.info("data值为：{}", data);
        }
        testRedissonLock2();
        lock.unlock();
        return true;
    }


    public Boolean testRedissonLock2() throws InterruptedException {
        String key = "testRedissonLock";
        RLock lock = redissonClient.getLock(key);
        boolean b = lock.tryLock(100, 100, TimeUnit.SECONDS);
        if (data > 0) {
            data = data - 1;
            log.info("data值为：{}", data);
        }
        lock.unlock();
        return true;
    }


    @Override
    public Boolean testRedissonFairLock(String key) throws InterruptedException {
        String redisKey = "testRedissonFairLock";
        RLock fairLock = redissonClient.getFairLock(redisKey);

        fairLock.lock(100, TimeUnit.SECONDS);

        Thread.sleep(200);
        //boolean b = fairLock.tryLock(100, 100, TimeUnit.SECONDS);
        /*if (data > 0) {
            data = data - 1;

        }*/
        log.info("data值为：{}", key);
        //this.testRedissonFairLock();

        fairLock.unlock();
        return null;
    }


    public Boolean testRedissonReadWriteLock() throws InterruptedException {
        String key = "testRedissonFairLock";
        RReadWriteLock readWriteLock = redissonClient.getReadWriteLock(key);
        readWriteLock.readLock();

        //boolean b = fairLock.tryLock(100, 100, TimeUnit.SECONDS);
        if (data > 0) {
            data = data - 1;
            log.info("data值为：{}", data);
        }

        //this.testRedissonFairLock();

        readWriteLock.writeLock();

        return null;
    }


}
