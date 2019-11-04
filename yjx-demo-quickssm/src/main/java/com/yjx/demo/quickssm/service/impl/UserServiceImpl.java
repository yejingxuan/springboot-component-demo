package com.yjx.demo.quickssm.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.yjx.demo.quickssm.base.PageDto;
import com.yjx.demo.quickssm.dao.UserMapper;
import com.yjx.demo.quickssm.model.pojo.User;
import com.yjx.demo.quickssm.service.UserService;
import java.util.List;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Resource
    private UserMapper userMapper;

    @Override
    public List<User> getAllUsers() {
        return userMapper.selectAll();
    }

    @Override
    public Long countAllUser() {
        return userMapper.selectAllUser();
    }

    @Override
    public PageDto<User> getPageUsers(PageDto<User> query) {
        PageHelper.startPage(query.getPageNo(), query.getPageSize(), true);
        Page<User> res = userMapper.selectPageUsers(query.getCondition());

        return null;
    }
}
