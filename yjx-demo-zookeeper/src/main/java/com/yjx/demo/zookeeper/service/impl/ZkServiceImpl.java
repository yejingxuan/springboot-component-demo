package com.yjx.demo.zookeeper.service.impl;

import com.yjx.demo.zookeeper.service.ZkService;
import org.springframework.stereotype.Service;

@Service
public class ZkServiceImpl implements ZkService {


    @Override
    public void printKeys() {
        System.out.println("1");
    }
}
