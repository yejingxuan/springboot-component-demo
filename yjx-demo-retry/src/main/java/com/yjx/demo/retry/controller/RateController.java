package com.yjx.demo.retry.controller;

import java.util.concurrent.atomic.AtomicInteger;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class RateController {

    private AtomicInteger i = new AtomicInteger(100);

    @GetMapping(value = "/rate")
    public String rate() {
        log.info("date:{}, rate:{}", System.currentTimeMillis(), i.getAndDecrement());
        return "success";
    }
}
