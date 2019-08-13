package com.yjx.demo.dislock.support.dislock;

/**
 * 基于zookeeper的分布式锁
 * @author Administrator
 */
public class ZkDisLockImpl implements DisLock {

    @Override
    public boolean lock(String key, String uuid) {
        return false;
    }

    @Override
    public boolean unLock(String key, String uuid) {
        return false;
    }
}
