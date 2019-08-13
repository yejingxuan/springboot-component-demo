package com.yjx.demo.dislock.support.dislock;

/**
 * @author yejingxuan
 */
public interface DisLock {

    /**
     * 加锁
     * @param key
     * @param uuid
     * @return
     */
    boolean lock(String key, String uuid);


    /**
     * 解锁
     * @param key
     * @param uuid
     * @return
     */
    boolean unLock(String key, String uuid);

}
