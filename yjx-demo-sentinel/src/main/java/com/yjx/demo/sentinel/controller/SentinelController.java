package com.yjx.demo.sentinel.controller;


import com.alibaba.csp.sentinel.Entry;
import com.alibaba.csp.sentinel.SphU;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping(value = "/test")
@RestController
@Slf4j
public class SentinelController {

    private static String RESOURCE_GETHOTINFO = "getHotInfo";

    @GetMapping(value = "/getHotInfo")
    public String getHotInfo(){
        String res = "";
        Entry entry = null;
        try {
            entry = SphU.entry(RESOURCE_GETHOTINFO);
            res = "ok";
        } catch (BlockException e) {
            res = "error";
            log.error("被限流了");
        } finally {
            entry.exit();
        }

        return res;
    }
}
