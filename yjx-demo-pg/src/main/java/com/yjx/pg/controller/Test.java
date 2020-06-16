package com.yjx.pg.controller;

import com.yjx.limiter.domain.aop.LimiterRate;
import com.yjx.limiter.service.LimiterService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class Test {

    @Autowired
    private LimiterService limiterService;

    @GetMapping(value = "ISendSMS.jsp")
    public String test(@RequestParam(value = "MobilePhones") String MobilePhones,
            @RequestParam(value = "Content") String Content,
            @RequestParam(value = "MessageFlag") String MessageFlag,
            @RequestParam(value = "ModuleName") String ModuleName) {
        log.info("{}, {}, {}, {}", MobilePhones, Content, MessageFlag, ModuleName);
        return "200";
    }


    @LimiterRate(key = "test2", rate = 100)
    @GetMapping(value = "/test222")
    public String test2(){
        limiterService.wrap("111");
        limiterService.wrap2("steu");
        return null;
    }

}
