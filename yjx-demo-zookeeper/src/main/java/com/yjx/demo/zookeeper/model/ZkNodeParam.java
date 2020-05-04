package com.yjx.demo.zookeeper.model;

import lombok.Data;

@Data
public class ZkNodeParam {

    private Integer type = 1;

    private String content;

    private String path;
}
