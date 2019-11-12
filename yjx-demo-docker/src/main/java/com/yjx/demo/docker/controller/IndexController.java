package com.yjx.demo.docker.controller;

import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class IndexController {

    @ApiOperation(value = "获取项目当前状态")
    @GetMapping(value = "getProjectStatus")
    public String getProjectStatus(){
        return "success";
    }
}
