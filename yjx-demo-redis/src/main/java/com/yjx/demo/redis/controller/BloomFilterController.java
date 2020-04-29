package com.yjx.demo.redis.controller;


import lombok.extern.slf4j.Slf4j;
import org.redisson.api.RBloomFilter;
import org.redisson.api.RSet;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * 布隆过滤器，解决缓存穿透问题
 */
@Slf4j
@RestController
@RequestMapping(value = "/bloomfilter")
public class BloomFilterController {

    @Autowired
    private RedissonClient redissonClient;


    @PostMapping(value = "/createBloomFilter")
    public String createBloomFilter() {
        RBloomFilter<Object> crawle_urls = redissonClient.getBloomFilter("crawle_urls");

        crawle_urls.tryInit(100L, 0.01);
        long size = crawle_urls.getSize();
        log.info("size:{}", size);
        log.info("count:{}", crawle_urls.count());
        return null;
    }

    @PostMapping(value = "/createSet")
    public String createSet() {
        RSet<Object> crawle_set = redissonClient.getSet("crawle_set");
        for (int i = 0; i < 100; i++) {
            boolean add = crawle_set.add(i);
            log.info("{} add result:{}", i, add);
        }
        int size = crawle_set.size();
        log.info("crawle_set size:{}", size);
        return null;
    }

    @PostMapping(value = "/addBloomData")
    public String addBloomData() {
        RBloomFilter<Object> crawle_urls = redissonClient.getBloomFilter("crawle_urls");
        for (int i = 0; i < 100; i++) {
            boolean add = crawle_urls.add(i);
            log.info("{} add result:{}", i, add);
        }
        log.info("size:{}", crawle_urls.getSize());
        return null;
    }


    @PostMapping(value = "/checkBloomData")
    public Boolean checkBloomData(Integer data) {
        RBloomFilter<Object> crawle_urls = redissonClient.getBloomFilter("crawle_urls");
        boolean contains = crawle_urls.contains(data);
        log.info("size:{}", crawle_urls.count());
        return contains;
    }
}
