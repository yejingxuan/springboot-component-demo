package com.yjx.demo.shiro.service.impl;

import com.yjx.demo.shiro.model.UserInfo;
import com.yjx.demo.shiro.service.UserService;
import java.util.Set;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {


    @Override
    public UserInfo findByUserName(String username) {
        return null;
    }

    @Override
    public Set<String> findByUserId(String userId) {
        return null;
    }
}
