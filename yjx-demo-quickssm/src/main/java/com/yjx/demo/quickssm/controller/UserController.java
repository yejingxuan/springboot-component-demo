package com.yjx.demo.quickssm.controller;


import com.yjx.demo.quickssm.model.pojo.User;
import com.yjx.demo.quickssm.service.UserService;
import io.swagger.annotations.ApiOperation;
import java.util.ArrayList;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RequestMapping("/api/user/")
@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @ApiOperation(value = "使用tk.mybatis进行curd")
    @PostMapping(value = "getAllUsers")
    public List<User> getAllUsers(){
        List<User> res = new ArrayList<>();
        try {
            res = userService.getAllUsers();
            log.info("getAllUsers query success");
        }catch (Exception e){
            log.error("getAllUsers query fail", e);
        }
        return res;
    }

    @ApiOperation(value = "手撸sql进行curd")
    @PostMapping(value = "countAllUser")
    public Long countAllUser(){
        Long res = 0L;
        try {
            res = userService.countAllUser();
            log.info("countAllUser query success");
        }catch (Exception e){
            log.error("countAllUser query fail", e);
        }
        return res;
    }

}
