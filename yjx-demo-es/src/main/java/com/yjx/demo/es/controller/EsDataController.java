package com.yjx.demo.es.controller;

import com.yjx.demo.es.model.base.Page;
import com.yjx.demo.es.model.dto.EmploySearchDto;
import com.yjx.demo.es.model.dto.EmploySearchParam;
import com.yjx.demo.es.model.es.EmployEs;
import com.yjx.demo.es.service.EsDataService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
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
    private EsDataService esDataService;


    @ApiOperation(value = "向employ索引中插入文档")
    @PostMapping(value = "addEmployDocuments")
    public Boolean addEmployDocuments(@RequestBody List<EmployEs> employs) {
        Boolean res = false;
        try {
            res = esDataService.addEmployDocuments(employs);
        } catch (Exception e) {
            log.error("addDocuments异常", e);
        }
        return res;
    }


    @ApiOperation(value = "搜索employ索引文档")
    @PostMapping(value = "searchEmployDocuments")
    public Page<EmploySearchDto> searchEmployDocuments(@RequestBody EmploySearchParam query) {
        Page<EmploySearchDto> res = new Page<>();
        try {
            res = esDataService.searchEmployDocuments(query);
        }catch (Exception e){
            log.error("searchEmployDocuments异常", e);
        }
        return res;
    }

}
