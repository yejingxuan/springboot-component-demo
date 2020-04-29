package com.yjx.demo.retry.controller;

import com.alibaba.fastjson.JSONObject;
import com.github.rholder.retry.RetryException;
import com.github.rholder.retry.Retryer;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping(value = "/testRetry")
public class RetryController {

    @Autowired
    private Retryer mailRetryer;

    @Autowired
    private Retryer msgRetryer;

    @PostMapping(value = "testMailRetryer")
    public String testMailRetryer() {
        String resp = "success";

        Callable<Boolean> mailCallable = new Callable<Boolean>() {
            int times = 0;

            @Override
            public Boolean call() {
                times++;
                log.info("mail重试第{}次", times);
                if (times == 7) {
                    return true;
                }
                return false;
            }
        };

        try {
            mailRetryer.call(mailCallable);
        } catch (Exception e) {
            System.out.println(e);
            resp = "fail";
        }

        return resp;
    }


    @PostMapping(value = "testMsgRetryer")
    public String testMsgRetryer() {
        String resp = "success";

        Callable<Boolean> msgCallable = new Callable<Boolean>() {
            int times = 0;

            @Override
            public Boolean call() {
                times++;
                log.info("msg重试第{}次", times);
                if (times == 7) {
                    return true;
                }
                return false;
            }
        };

        try {
            msgRetryer.call(msgCallable);
        } catch (Exception e) {
            System.out.println(e);
            resp = "fail";
        }

        return resp;
    }



    @PostMapping(value = "xzztryQueryResult.do")
    public JSONObject xzztryQueryResult(@RequestParam(value = "rnd") String rnd){
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("total", 11);
        log.info(jsonObject.toJSONString());
        return jsonObject;
    }
}
