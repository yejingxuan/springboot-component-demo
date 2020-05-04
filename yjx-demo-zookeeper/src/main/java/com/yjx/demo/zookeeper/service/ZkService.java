package com.yjx.demo.zookeeper.service;

import com.yjx.demo.zookeeper.model.ZkNodeParam;
import java.util.List;

public interface ZkService {

    Boolean createNodeByParam(ZkNodeParam zkNodeParam) throws Exception;

    Boolean checkNodeExist(String path) throws Exception;

    String getNodeData(String path) throws Exception;

    List<String> getChildNodeData(String path) throws Exception;

    void watchNode(String path) throws Exception;

    void watchChildNode(String path) throws Exception;
}
