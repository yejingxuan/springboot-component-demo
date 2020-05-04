package com.yjx.demo.zookeeper.controller;

import com.yjx.demo.zookeeper.model.ZkNodeParam;
import com.yjx.demo.zookeeper.service.ZkService;
import com.yjx.demo.zookeeper.service.impl.ZkServiceImpl;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.recipes.cache.NodeCache;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.data.Stat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RequestMapping("/zkhandle")
@RestController
public class ZkHandleController {

    @Autowired
    private ZkService zkService;

    /**
     * 创建节点
     * @param zkNodeParam
     * @return
     */
    @PostMapping(value = "/createNode")
    public Boolean createNode(@RequestBody ZkNodeParam zkNodeParam) {
        Boolean res = Boolean.FALSE;
        try {
            res = zkService.createNodeByParam(zkNodeParam);
        } catch (Exception e) {
            log.error("创建失败", e);
        }
        return res;
    }

    /**
     * 检测节点是否存在
     * @param path
     * @return
     */
    @GetMapping(value = "/checkNodeExist")
    public Boolean checkNodeExist(String path){
        Boolean res = Boolean.FALSE;
        try {
            res = zkService.checkNodeExist(path);
        } catch (Exception e) {
            log.error("系统错误", e);
        }
        return res;
    }

    /**
     * 获取节点数据
     * @param path
     * @return
     */
    @GetMapping(value = "/getNodeData")
    public String getNodeData(String path) {
        String res = "";
        try {
            res = zkService.getNodeData(path);
        } catch (Exception e) {
            log.error("系统错误", e);
        }
        return res;
    }

    /**
     * 获取目标下的子节点
     * @param path
     * @return
     */
    @GetMapping(value = "/getChildNodeData")
    public List<String> getChildNodeData(String path) {
        List<String> res = null;
        try {
            res = zkService.getChildNodeData(path);
        } catch (Exception e) {
            log.error("系统错误", e);
        }
        return res;
    }

    /**
     * 监听Node节点
     * @param path
     * @return
     */
    @PostMapping(value = "/watchNode")
    public void watchNode(String path){
        try {
            zkService.watchNode(path);
        } catch (Exception e) {
            log.error("系统错误", e);
        }
    }


    /**
     * 监听Node子节点
     * @param path
     * @return
     */
    @PostMapping(value = "/watchChildNode")
    public void watchChildNode(String path){
        try {
            zkService.watchChildNode(path);
        } catch (Exception e) {
            log.error("系统错误", e);
        }
    }

}
