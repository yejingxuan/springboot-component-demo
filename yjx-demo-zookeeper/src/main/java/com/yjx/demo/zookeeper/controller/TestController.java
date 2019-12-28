package com.yjx.demo.zookeeper.controller;

import com.yjx.demo.zookeeper.service.ZkService;
import com.yjx.demo.zookeeper.service.impl.ZkServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.apache.curator.framework.CuratorFramework;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.data.Stat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class TestController {

    @Autowired
    private CuratorFramework zkClient;

    @Autowired
    private ZkService zkService;

    @PostMapping(value = "createNode")
    public String createNode() {
        try {
            zkClient.create().creatingParentsIfNeeded().forPath("/test2/c1","c11111-content".getBytes());
            Stat stat = zkClient.checkExists().forPath("/test2/c1");
            log.info(stat.toString());
        } catch (Exception e) {
            log.error("创建失败", e);
        }
        return "ok";
    }

    @GetMapping(value = "getNodeData")
    public String getNodeData(String path) {
        try {
            byte[] bytes = zkClient.getData().forPath(path);
            return new String(bytes);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }


    @GetMapping(value = "test")
    public String test(){
        zkService.printKeys();
        ZkService instance = new ZkServiceImpl();
        instance.printKeys();
        return "ok";
    }

}
