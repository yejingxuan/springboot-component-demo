package com.yjx.demo.es.service.impl;

import com.yjx.demo.es.service.EsIndexService;
import java.io.IOException;
import org.apache.commons.io.IOUtils;
import org.elasticsearch.action.admin.indices.delete.DeleteIndexRequest;
import org.elasticsearch.action.support.master.AcknowledgedResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.indices.CreateIndexRequest;
import org.elasticsearch.client.indices.CreateIndexResponse;
import org.elasticsearch.client.indices.GetIndexRequest;
import org.elasticsearch.common.xcontent.XContentType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

@Service
public class EsIndexServiceImpl implements EsIndexService {

    @Autowired
    private RestHighLevelClient highLevelClient;

    @Override
    public Boolean createEmployIndex() throws IOException {
        //初始化mapping
        Resource resource = new ClassPathResource("es_mappings/employ_mapping.json");
        byte[] template = IOUtils.toByteArray(resource.getInputStream());
        String source = new String(template);
        //初始化索引
        CreateIndexRequest indexRequest = new CreateIndexRequest("employ_info");
        indexRequest.mapping(source, XContentType.JSON);

        //创建索引
        CreateIndexResponse rep = highLevelClient.indices()
                .create(indexRequest, RequestOptions.DEFAULT);
        return rep.isAcknowledged();
    }


    @Override
    public Boolean existsIndex(String indexName) throws IOException {
        GetIndexRequest request = new GetIndexRequest(indexName);
        boolean exists = highLevelClient.indices().exists(request, RequestOptions.DEFAULT);
        return exists;
    }

    @Override
    public Boolean deleteIndex(String indexName) throws IOException {
        DeleteIndexRequest request = new DeleteIndexRequest(indexName);
        AcknowledgedResponse rep = highLevelClient.indices()
                .delete(request, RequestOptions.DEFAULT);
        return rep.isAcknowledged();
    }
}
