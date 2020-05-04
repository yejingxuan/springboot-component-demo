package com.yjx.mbg.mapper;

import com.yjx.mbg.model.HyProduct;
import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Data;

public interface HyProductMapper {
    int deleteByPrimaryKey(String productId);

    int insert(HyProduct record);

    int insertSelective(HyProduct record);

    HyProduct selectByPrimaryKey(String productId);

    int updateByPrimaryKeySelective(HyProduct record);

    int updateByPrimaryKey(HyProduct record);
}