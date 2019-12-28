package com.yjx.demo.es.controller;

import com.alibaba.fastjson.JSONObject;
import com.yjx.demo.es.model.NetInfo;
import com.yjx.demo.es.model.es.EmployEs;
import java.io.IOException;
import java.util.Arrays;
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
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class EsSevenController {

    @Autowired
    private RestHighLevelClient highLevelClient;

    /**
     * 创建employ索引
     */
    @PostMapping(value = "createEmployIndex")
    public String createEmployIndex() {
        try {
            //初始化mapping
            Resource resource = new ClassPathResource("es_mappings/employ_mapping.json");
            byte[] template = IOUtils.toByteArray(resource.getInputStream());
            String index = new String(template);

            //初始化索引
            String indexName = "employ_index";
            CreateIndexRequest indexRequest = new CreateIndexRequest(indexName)
                    .source(index, XContentType.JSON);

            //创建索引
            highLevelClient.indices().create(indexRequest, RequestOptions.DEFAULT);
        } catch (Exception e) {
            log.error("create error", e);
            return "error";
        }
        return "success";
    }

    /**
     * 检测索引是否存在
     */
    @GetMapping(value = "checkIndex")
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

    /**
     * 删除索引
     */
    @PostMapping(value = "deleteIndex")
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


    /**
     * 添加数据
     */
    @PostMapping(value = "addDocuments")
    public Boolean addEmployDocuments() {
        Boolean res = false;
        String indexName = "employ_index";
        try {
            EmployEs info1 = new EmployEs("华中农业大学2020年度辅导员招聘公告（博士学位辅导员实行年薪制）",
                    "http://www.gaoxiaojob.com/zhaopin/fudaoyuan/20191209/415572.html",
                    "2019年12月24日", "若干");
            EmployEs info2 = new EmployEs("武昌工学院2020年各类教师引进计划",
                    "http://www.gaoxiaojob.com/zhaopin/gaoxiaojiaoshi/20191206/415305.html",
            "详见正文", "若干");
            String item1 = JSONObject.toJSONString(info1);
            String item2 = JSONObject.toJSONString(info2);

            BulkRequest bulkRequest = new BulkRequest();
            bulkRequest.add(new IndexRequest(indexName).source(item1, XContentType.JSON));
            bulkRequest.add(new IndexRequest(indexName).source(item2, XContentType.JSON));

            BulkResponse bulk = highLevelClient.bulk(bulkRequest, RequestOptions.DEFAULT);
            res = bulk.hasFailures();
        } catch (IOException e) {
            log.error("addDocuments异常", e);
        }
        return !res;
    }


    /**
     * 查询数据
     */
    @PostMapping(value = "searchDocs")
    public SearchHits searchEmployDocuments(String title) {
        String indexName = "employ_index";

        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        //searchSourceBuilder.query(QueryBuilders.matchAllQuery());
        searchSourceBuilder.query(QueryBuilders.matchQuery("title", title));
        searchSourceBuilder.from(0);
        searchSourceBuilder.size(10);

        HighlightBuilder highlightBuilder = new HighlightBuilder();
        highlightBuilder.preTags("<tag1>");
        highlightBuilder.postTags("</tag1>");
        highlightBuilder.field("title");
        searchSourceBuilder.highlighter(highlightBuilder);

        SearchRequest searchRequest = new SearchRequest().indices(indexName).source(searchSourceBuilder);
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


    @PostMapping(value = "getNetInfos")
    public List<NetInfo> getNetInfos() {

        List<NetInfo> netInfos = Arrays.asList();

        System.out.println(netInfos);
        return netInfos;
    }
}
