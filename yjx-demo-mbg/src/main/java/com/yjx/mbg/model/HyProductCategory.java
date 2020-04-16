package com.yjx.mbg.model;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Table;
import lombok.Data;

/**
* @author: jxye 
*/
@Table(name = "hy_product_category")
@Data
public class HyProductCategory implements Serializable {
    /**
     * category_id 种类id
     */
    @Column(name = "category_id")
    private String categoryId;

    /**
     * category_name 种类名称
     */
    @Column(name = "category_name")
    private String categoryName;

    /**
     * system_id 系统id
     */
    @Column(name = "system_id")
    private String systemId;

    /**
     * category_enable 是否删除0正常1删除
     */
    @Column(name = "category_enable")
    private String categoryEnable;

    private static final long serialVersionUID = 1L;
}