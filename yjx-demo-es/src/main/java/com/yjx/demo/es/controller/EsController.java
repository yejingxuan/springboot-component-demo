package com.yjx.demo.es.controller;


import com.yjx.demo.es.model.UserInfoEs;
import com.yjx.demo.es.repository.UserInfoEsRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EsController {

    @Autowired
    private UserInfoEsRepository userInfoEsRepository;

    @GetMapping(value = "/test")
    public String test(){
        return "success";
    }


    @PutMapping(value = "/save")
    public String insertUserInfo(UserInfoEs userInfoEs){
        UserInfoEs save = userInfoEsRepository.save(userInfoEs);
        return "success";
    }


    @GetMapping(value = "/getUser")
    public Iterable<UserInfoEs> getUserInfo(){
        Iterable<UserInfoEs> all = userInfoEsRepository.findAll();
        return all;
    }
}
