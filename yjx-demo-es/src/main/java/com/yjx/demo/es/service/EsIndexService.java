package com.yjx.demo.es.service;

import java.io.IOException;

public interface EsIndexService {

    Boolean createEmployIndex() throws IOException;

    Boolean existsIndex(String indexName) throws IOException;

    Boolean deleteIndex(String indexName) throws IOException;
}
