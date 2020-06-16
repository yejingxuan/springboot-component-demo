package com.yjx.pg.dao;

import com.github.pagehelper.Page;
import com.yjx.pg.base.MyMapper;
import com.yjx.pg.model.pojo.Profile;
import org.apache.ibatis.annotations.Select;

public interface ProfileMapper extends MyMapper<Profile> {


    @Select("select profile_id profileId, version from profile order by created_time desc")
    Page<Profile> selectPageProfile(Profile condition);
}
