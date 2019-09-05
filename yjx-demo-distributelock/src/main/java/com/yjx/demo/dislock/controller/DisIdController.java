package com.yjx.demo.dislock.controller;

import com.yjx.demo.dislock.support.disId.SnowFlake;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class DisIdController {



    @GetMapping(value = "getDisId")
    public String getDisId(){

        SnowFlake snowFlake = new SnowFlake(12, 1);
        String id = String.valueOf(snowFlake.nextId());
        log.info(id);
        return String.valueOf(snowFlake.nextId());
    }

}
