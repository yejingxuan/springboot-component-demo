package com.yjx.mbg.mapper;

import com.yjx.mbg.model.HyAdmin;
import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Data;

public interface HyAdminMapper {
    int deleteByPrimaryKey(String adminId);

    int insert(HyAdmin record);

    int insertSelective(HyAdmin record);

    HyAdmin selectByPrimaryKey(String adminId);

    int updateByPrimaryKeySelective(HyAdmin record);

    int updateByPrimaryKey(HyAdmin record);
}