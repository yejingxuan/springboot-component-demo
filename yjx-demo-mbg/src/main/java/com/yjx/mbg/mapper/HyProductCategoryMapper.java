package com.yjx.mbg.mapper;

import com.yjx.mbg.model.HyProductCategory;
import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Data;

public interface HyProductCategoryMapper {
    int deleteByPrimaryKey(String categoryId);

    int insert(HyProductCategory record);

    int insertSelective(HyProductCategory record);

    HyProductCategory selectByPrimaryKey(String categoryId);

    int updateByPrimaryKeySelective(HyProductCategory record);

    int updateByPrimaryKey(HyProductCategory record);
}