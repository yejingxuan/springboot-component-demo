package com.yjx.mbg.mapper;

import com.yjx.mbg.model.HyUserinfo;
import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Data;

public interface HyUserinfoMapper {
    int deleteByPrimaryKey(String userId);

    int insert(HyUserinfo record);

    int insertSelective(HyUserinfo record);

    HyUserinfo selectByPrimaryKey(String userId);

    int updateByPrimaryKeySelective(HyUserinfo record);

    int updateByPrimaryKey(HyUserinfo record);
}