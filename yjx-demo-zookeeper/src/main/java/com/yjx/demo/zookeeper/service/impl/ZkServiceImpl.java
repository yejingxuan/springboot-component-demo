package com.yjx.demo.zookeeper.service.impl;

import com.yjx.demo.zookeeper.model.ZkNodeParam;
import com.yjx.demo.zookeeper.service.ZkService;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.api.GetChildrenBuilder;
import org.apache.curator.framework.recipes.cache.ChildData;
import org.apache.curator.framework.recipes.cache.NodeCache;
import org.apache.curator.framework.recipes.cache.NodeCacheListener;
import org.apache.curator.framework.recipes.cache.TreeCache;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.data.Stat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class ZkServiceImpl implements ZkService {

    @Autowired
    private CuratorFramework zkClient;

    @Override
    public Boolean createNodeByParam(ZkNodeParam zkNodeParam) throws Exception {
        switch (zkNodeParam.getType()) {
            case 1:
                //创建持久化节点
                zkClient.create().creatingParentsIfNeeded().withMode(CreateMode.PERSISTENT)
                        .forPath(zkNodeParam.getPath(), zkNodeParam.getContent().getBytes());
                break;
            case 2:
                //创建持久化顺序节点
                zkClient.create().creatingParentsIfNeeded()
                        .withMode(CreateMode.PERSISTENT_SEQUENTIAL)
                        .forPath(zkNodeParam.getPath(), zkNodeParam.getContent().getBytes());
                break;
            case 3:
                //创建临时节点
                zkClient.create().creatingParentsIfNeeded().withMode(CreateMode.EPHEMERAL)
                        .forPath(zkNodeParam.getPath(), zkNodeParam.getContent().getBytes());
                break;
            case 4:
                //创建临时顺序节点
                zkClient.create().creatingParentsIfNeeded()
                        .withMode(CreateMode.EPHEMERAL_SEQUENTIAL)
                        .forPath(zkNodeParam.getPath(), zkNodeParam.getContent().getBytes());
                break;
        }
        return true;
    }


    @Override
    public Boolean checkNodeExist(String path) throws Exception {
        Stat stat = zkClient.checkExists().forPath(path);
        log.info(stat.toString());
        return true;
    }


    @Override
    public String getNodeData(String path) throws Exception {
        byte[] bytes = zkClient.getData().forPath(path);
        return new String(bytes);
    }

    @Override
    public List<String> getChildNodeData(String path) throws Exception {
        List<String> res = zkClient.getChildren().forPath(path);
        return res;
    }

    @Override
    public void watchNode(String path) throws Exception {
        NodeCache nodeCache = new NodeCache(zkClient, path);
        nodeCache.getListenable().addListener(() -> {
            log.info("监听事件触发");
            log.info("重新获得节点内容为：" + new String(nodeCache.getCurrentData().getData()));
        });
        nodeCache.start();

    }

    @Override
    public void watchChildNode(String path) throws Exception {
        TreeCache treeCache = new TreeCache(zkClient, path);
        treeCache.getListenable().addListener((curatorFramework, treeCacheEvent) -> {
            ChildData eventData = treeCacheEvent.getData();
            switch (treeCacheEvent.getType()) {
                case NODE_ADDED:
                    log.warn(path + "节点添加" + eventData.getPath() + "\t添加数据为：" + new String(eventData.getData()));
                    break;
                case NODE_UPDATED:
                    log.warn(eventData.getPath() + "节点数据更新\t更新数据为：" + new String(eventData.getData()) + "\t版本为：" + eventData.getStat().getVersion());
                    break;
                case NODE_REMOVED:
                    log.warn(eventData.getPath() + "节点被删除");
                    break;
                default:
                    break;
            }
        });
        treeCache.start();

    }
}
