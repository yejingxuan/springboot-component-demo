package com.yjx.mbg.mapper;

import com.yjx.mbg.model.HyOrder;
import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Data;

public interface HyOrderMapper {
    int deleteByPrimaryKey(String orderId);

    int insert(HyOrder record);

    int insertSelective(HyOrder record);

    HyOrder selectByPrimaryKey(String orderId);

    int updateByPrimaryKeySelective(HyOrder record);

    int updateByPrimaryKey(HyOrder record);
}