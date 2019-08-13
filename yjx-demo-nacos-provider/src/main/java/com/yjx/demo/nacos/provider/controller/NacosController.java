package com.yjx.demo.nacos.provider.controller;


import com.alibaba.nacos.api.config.annotation.NacosValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class NacosController {

    @NacosValue(value = "${appid:false}", autoRefreshed = true)
    private Boolean appID;

    @GetMapping(value = "getConfig")
    public Boolean getConfig(){
        return appID;
    }



}
