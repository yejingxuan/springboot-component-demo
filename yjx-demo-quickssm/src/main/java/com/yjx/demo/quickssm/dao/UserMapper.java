package com.yjx.demo.quickssm.dao;

import com.github.pagehelper.Page;
import com.yjx.demo.quickssm.base.MyMapper;
import com.yjx.demo.quickssm.model.pojo.User;
import org.apache.ibatis.annotations.Select;

public interface UserMapper extends MyMapper<User> {

    long selectAllUser();

    @Select("select uid, username userName, password passWord from user_info")
    Page<User> selectPageUsers(User condition);
}
