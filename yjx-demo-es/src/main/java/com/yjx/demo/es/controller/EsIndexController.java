package com.yjx.demo.es.controller;

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
    private RestHighLevelClient highLevelClient;


    @ApiOperation(value = "创建employ索引")
    @PostMapping(value = "/createEmployIndex")
    public String createEmployIndex() {
        try {
            //初始化mapping
            Resource resource = new ClassPathResource("es_mappings/employ_mapping.json");
            byte[] template = IOUtils.toByteArray(resource.getInputStream());
            String source = new String(template);

            //初始化索引
            String indexName = "employ_info";
            CreateIndexRequest indexRequest = new CreateIndexRequest(indexName)
                    .source(source, XContentType.JSON);

            //创建索引
            highLevelClient.indices().create(indexRequest, RequestOptions.DEFAULT);
        } catch (Exception e) {
            log.error("create error", e);
            return "error";
        }
        return "success";
    }

    @ApiOperation(value = "检测索引是否存在")
    @GetMapping(value = "/checkIndex")
    public Boolean checkIndex(String indexName) {
        GetIndexRequest request = new GetIndexRequest(indexName);
        Boolean res = false;
        try {
            res = highLevelClient.indices().exists(request, RequestOptions.DEFAULT);
        } catch (IOException e) {
            log.error("checkIndex异常", e);
        }
        return res;
    }

    @ApiOperation(value = "删除索引")
    @PostMapping(value = "/deleteIndex")
    public Boolean deleteIndex(String indexName) {
        DeleteIndexRequest request = new DeleteIndexRequest(indexName);
        Boolean res = false;
        try {
            AcknowledgedResponse rep = highLevelClient.indices()
                    .delete(request, RequestOptions.DEFAULT);
            res = rep.isAcknowledged();
        } catch (IOException e) {
            log.error("deleteIndex异常", e);
        }
        return res;
    }

}
