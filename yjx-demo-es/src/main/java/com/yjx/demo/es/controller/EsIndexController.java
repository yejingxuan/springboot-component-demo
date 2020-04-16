package com.yjx.demo.es.controller;

import com.yjx.demo.es.service.EsIndexService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import java.io.IOException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;
import org.elasticsearch.action.admin.indices.delete.DeleteIndexRequest;
import org.elasticsearch.action.support.master.AcknowledgedResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.indices.CreateIndexRequest;
import org.elasticsearch.client.indices.GetIndexRequest;
import org.elasticsearch.common.xcontent.XContentType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping(value = "/es/index")
@Api(tags = "es索引操作")
public class EsIndexController {

    @Autowired
    private EsIndexService esIndexService;


    @ApiOperation(value = "创建employ索引")
    @PostMapping(value = "/createEmployIndex")
    public Boolean createEmployIndex() {
        Boolean res = false;
        try {
            res = esIndexService.createEmployIndex();
        } catch (Exception e) {
            log.error("create error", e);
            return res;
        }
        return res;
    }

    @ApiOperation(value = "检测索引是否存在")
    @GetMapping(value = "/existsIndex")
    public Boolean existsIndex(String indexName) {
        Boolean res = false;
        try {
            res = esIndexService.existsIndex(indexName);
        } catch (IOException e) {
            log.error("checkIndex异常", e);
        }
        return res;
    }

    @ApiOperation(value = "删除索引")
    @PostMapping(value = "/deleteIndex")
    public Boolean deleteIndex(String indexName) {
        Boolean res = false;
        try {
            res = esIndexService.deleteIndex(indexName);
        } catch (Exception e) {
            log.error("deleteIndex异常", e);
        }
        return res;
    }

}
