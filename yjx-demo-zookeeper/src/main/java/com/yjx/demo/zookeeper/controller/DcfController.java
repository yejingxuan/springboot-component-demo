package com.yjx.demo.zookeeper.controller;

import lombok.extern.slf4j.Slf4j;
import org.apache.curator.framework.CuratorFramework;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 分布式协调服务Distributed Coordination Function
 */
@Slf4j
@RequestMapping(value = "/dcf")
@RestController
public class DcfController {

    @Autowired
    private CuratorFramework zkClient;

    public String serviceA(String msg){
       /* zkClient.cr*/
        return msg;
    }

}
