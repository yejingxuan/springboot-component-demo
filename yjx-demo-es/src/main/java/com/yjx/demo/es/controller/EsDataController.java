package com.yjx.demo.es.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.yjx.demo.es.model.es.EmployEs;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import java.io.IOException;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;
import org.elasticsearch.action.admin.indices.delete.DeleteIndexRequest;
import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.support.master.AcknowledgedResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.indices.CreateIndexRequest;
import org.elasticsearch.client.indices.GetIndexRequest;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping(value = "/es/data")
@Api(tags = "es数据操作")
public class EsDataController {

    @Autowired
    private RestHighLevelClient highLevelClient;


    @ApiOperation(value = "向employ索引中插入文档")
    @PostMapping(value = "addEmployDocuments")
    public Boolean addEmployDocuments(@RequestBody List<EmployEs> employs) {
        Boolean res = false;
        String indexName = "employ_info";
        try {
            BulkRequest bulkRequest = new BulkRequest();
            employs.forEach(employEs -> {
                employEs.setCreateTime(new Date());
                String item = JSONObject
                        .toJSONStringWithDateFormat(employEs, JSON.DEFFAULT_DATE_FORMAT);
                bulkRequest.add(new IndexRequest(indexName).source(item, XContentType.JSON));
            });
            BulkResponse bulk = highLevelClient.bulk(bulkRequest, RequestOptions.DEFAULT);
            res = bulk.hasFailures();
        } catch (IOException e) {
            log.error("addDocuments异常", e);
        }
        return !res;
    }


    @ApiOperation(value = "搜索employ索引文档")
    @PostMapping(value = "searchEmployDocuments")
    public SearchHits searchEmployDocuments(EmployEs query) {
        String indexName = "employ_index";

        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        //searchSourceBuilder.query(QueryBuilders.matchAllQuery());

        HighlightBuilder highlightBuilder = new HighlightBuilder();
        highlightBuilder.preTags("<tag1>");
        highlightBuilder.postTags("</tag1>");
        highlightBuilder.field("title");

        searchSourceBuilder.query(QueryBuilders.matchQuery("description", query.getDescription()));
        searchSourceBuilder.from(0);
        searchSourceBuilder.size(10);
        searchSourceBuilder.highlighter(highlightBuilder);

        SearchRequest searchRequest = new SearchRequest().indices(indexName)
                .source(searchSourceBuilder);
        SearchHits hits = null;
        try {
            SearchResponse search = highLevelClient.search(searchRequest, RequestOptions.DEFAULT);
            hits = search.getHits();
            log.info(hits.getAt(0).getSourceAsMap().toString());
            log.info(hits.getAt(0).getHighlightFields().toString());
        } catch (IOException e) {
            e.printStackTrace();
        }

        return hits;

    }

}
