package com.yjx.pg.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.yjx.pg.base.PageDto;
import com.yjx.pg.dao.ProfileMapper;
import com.yjx.pg.model.pojo.Profile;
import com.yjx.pg.service.ProfileService;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;

@Service
public class ProfileServiceImpl implements ProfileService {

    @Resource
    private ProfileMapper profileMapper;

    @Override
    public PageDto<Profile> getProfilePage(PageDto<Profile> page) {
        PageHelper.startPage(page.getPageNo(), page.getPageSize(), true);
        Page<Profile> select = profileMapper.selectPageProfile(page.getCondition());

        page.setRecords(select.getResult());
        page.setTotalPages(select.getPages());
        page.setTotalRecords(select.getTotal());

        return page;
    }
}
