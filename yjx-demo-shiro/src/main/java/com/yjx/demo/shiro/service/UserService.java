package com.yjx.demo.shiro.service;

import com.yjx.demo.shiro.model.UserInfo;
import java.util.Set;

public interface UserService {

    UserInfo findByUserName(String username);

    Set<String> findByUserId(String userId);
}
