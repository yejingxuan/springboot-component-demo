package com.yjx.demo.es.service;

import com.yjx.demo.es.model.base.Page;
import com.yjx.demo.es.model.dto.EmploySearchDto;
import com.yjx.demo.es.model.dto.EmploySearchParam;
import com.yjx.demo.es.model.es.EmployEs;
import java.io.IOException;
import java.util.List;

public interface EsDataService {

    Boolean addEmployDocuments(List<EmployEs> employs) throws IOException;

    Page<EmploySearchDto> searchEmployDocuments(EmploySearchParam query) throws IOException;
}
