package com.yjx.mbg.mapper;

import com.yjx.mbg.model.HySystem;
import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Data;

public interface HySystemMapper {
    int deleteByPrimaryKey(String systemId);

    int insert(HySystem record);

    int insertSelective(HySystem record);

    HySystem selectByPrimaryKey(String systemId);

    int updateByPrimaryKeySelective(HySystem record);

    int updateByPrimaryKey(HySystem record);
}