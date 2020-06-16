package com.yjx.limiter.service;

import com.yjx.limiter.config.LimiterProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LimiterService {

    @Autowired
    private LimiterProperties properties;


    public void wrap(String word) {
        System.out.println(word);
        System.out.println(properties.toString());
    }

    public void wrap2(String word) {
        System.out.println("wrap2"+word);
        System.out.println(properties.toString());
    }

}
