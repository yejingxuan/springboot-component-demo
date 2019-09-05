package com.yjx.demo.shiro.controller;

import com.yjx.demo.shiro.common.Result;
import com.yjx.demo.shiro.model.UserInfo;
import io.swagger.annotations.ApiOperation;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping(value = "/v1/user/")
@RestController
@Slf4j
public class UserController {

    @Autowired
    private RedissonClient redissonClient;

    @ApiOperation(value = "")
    @GetMapping(value = "getAllUser")
    public Result<List<UserInfo>> getAllUser() {

        //redissonClient.getBucket("test").set("123");
        Object test = redissonClient.getBucket("test").get();

        log.info(test.toString());

        UserInfo userInfo = new UserInfo();
        userInfo.setUserId("001");
        userInfo.setUserName("刘四");
        userInfo.setPassword("123");

        //redissonClient.getList("userinfo").add(userInfo);
        log.info(redissonClient.getList("userinfo").toString());
        return Result.genSuccessResult();
    }

}
