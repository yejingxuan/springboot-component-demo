package com.yjx.demo.quickssm.dao;

import com.yjx.demo.quickssm.base.MyMapper;
import com.yjx.demo.quickssm.model.pojo.User;

public interface UserMapper extends MyMapper<User> {

    long selectAllUser();
}
