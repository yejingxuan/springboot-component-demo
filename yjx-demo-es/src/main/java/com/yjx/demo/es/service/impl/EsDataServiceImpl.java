package com.yjx.demo.es.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.yjx.demo.es.config.ApplicationConfig;
import com.yjx.demo.es.model.base.Page;
import com.yjx.demo.es.model.dto.EmploySearchDto;
import com.yjx.demo.es.model.dto.EmploySearchParam;
import com.yjx.demo.es.model.es.EmployEs;
import com.yjx.demo.es.service.EsDataService;
import com.yjx.demo.es.utils.HighLightFieldUtil;
import com.yjx.demo.es.utils.MyDataConverter;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Map;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.ConvertUtils;
import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightBuilder;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightField;
import org.elasticsearch.search.sort.SortOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Service
@Slf4j
public class EsDataServiceImpl implements EsDataService {

    @Autowired
    private RestHighLevelClient highLevelClient;

    @Autowired
    private ApplicationConfig applicationConfig;

    @Override
    public Boolean addEmployDocuments(List<EmployEs> employs) throws IOException {
        BulkRequest bulkRequest = new BulkRequest();

        employs.forEach(employEs -> {
            employEs.setCreateTime(new Date());
            String item = JSONObject
                    .toJSONStringWithDateFormat(employEs, JSON.DEFFAULT_DATE_FORMAT);
            bulkRequest.add(new IndexRequest("employ_info").source(item, XContentType.JSON));
        });

        BulkResponse bulk = highLevelClient.bulk(bulkRequest, RequestOptions.DEFAULT);
        boolean res = bulk.hasFailures();
        return !res;
    }


    @Override
    public Page<EmploySearchDto> searchEmployDocuments(EmploySearchParam query) throws IOException {
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();

        //封装查询条件
        //关键词搜索
        BoolQueryBuilder queryBuilderKeyWords = QueryBuilders.boolQuery();
        if (!StringUtils.isEmpty(query.getKeyWords())) {
            queryBuilderKeyWords.should(QueryBuilders.matchQuery("company", query.getKeyWords())
                    .minimumShouldMatch(applicationConfig.getMinMatch()));
            queryBuilderKeyWords.should(QueryBuilders.matchQuery("description", query.getKeyWords())
                    .minimumShouldMatch(applicationConfig.getMinMatch()));
        }
        //过滤
        BoolQueryBuilder queryBuilderParams = QueryBuilders.boolQuery();
        if (!StringUtils.isEmpty(query.getEmployInfoId())) {
            queryBuilderParams
                    .must(QueryBuilders.termQuery("employInfoId", query.getEmployInfoId()));
        }
        if (!StringUtils.isEmpty(query.getEmployType())) {
            queryBuilderParams.must(QueryBuilders.termQuery("employType", query.getEmployType()));
        }
        BoolQueryBuilder queryBuilder = QueryBuilders.boolQuery();
        queryBuilder.must(queryBuilderKeyWords);
        queryBuilder.must(queryBuilderParams);
        searchSourceBuilder.query(queryBuilder);

        //排序 & 分页
        searchSourceBuilder.from((query.getPageNo() - 1) * query.getPageSize());
        searchSourceBuilder.size(query.getPageSize());
        switch (query.getSort()) {
            case 1:
                searchSourceBuilder.sort("minSalary", SortOrder.DESC);
                break;
            case 2:
                searchSourceBuilder.sort("maxSalary", SortOrder.DESC);
                break;
            case 3:
                searchSourceBuilder.sort("deadLines", SortOrder.DESC);
                break;
            case 4:
                searchSourceBuilder.sort("headerCount", SortOrder.DESC);
                break;
            default:
                searchSourceBuilder.sort("createTime", SortOrder.DESC);
        }

        //高亮
        HighlightBuilder highlightBuilder = new HighlightBuilder();
        highlightBuilder.preTags(applicationConfig.getPreTags());
        highlightBuilder.postTags(applicationConfig.getPostTags());
        highlightBuilder.field("description").field("company");
        searchSourceBuilder.highlighter(highlightBuilder);

        //计算分数
        searchSourceBuilder.trackScores(true);
        log.info(searchSourceBuilder.toString());

        //执行搜索
        SearchRequest searchRequest = new SearchRequest().indices("employ_info")
                .source(searchSourceBuilder);

        SearchResponse search = highLevelClient.search(searchRequest, RequestOptions.DEFAULT);

        //处理结果
        SearchHits hits = search.getHits();
        Page<EmploySearchDto> page = new Page<>();
        page.setPageNo(query.getPageNo());
        page.setPageSize(query.getPageSize());
        page.setTotalCount(hits.getTotalHits().value);

        ConvertUtils.register(new MyDataConverter(), Date.class);
        hits.forEach(hit -> {
            EmploySearchDto item = new EmploySearchDto();
            try {
                BeanUtils.populate(item, hit.getSourceAsMap());
                Map<String, HighlightField> highlightFields = hit.getHighlightFields();
                item.setHighLightCompany(
                        HighLightFieldUtil.getValue(highlightFields.get("company")));
                item.setHighLightDescription(
                        HighLightFieldUtil.getValue(highlightFields.get("description")));
                page.getRecords().add(item);
            } catch (Exception e) {
                log.error("BeanUtils.populate error", e);
            }
            //log.info(hit.getHighlightFields().toString());
        });

        log.info(page.toString());
        return page;
    }

}
