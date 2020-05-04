package com.yjx.mbg.model;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Data;

/**
* @author: jxye 
*/
@Table(name = "hy_product")
@Data
public class HyProduct implements Serializable {
    /**
     * product_id 商品id
     */
    @Id
    @Column(name = "product_id")
    private String productId;

    /**
     * product_name 商品名称
     */
    @Column(name = "product_name")
    private String productName;

    /**
     * system_id 系统id
     */
    @Column(name = "system_id")
    private String systemId;

    /**
     * product_retailPrice 零售价
     */
    @Column(name = "product_retail_price")
    private String productRetailPrice;

    /**
     * product_trade_price 批发价
     */
    @Column(name = "product_trade_price")
    private String productTradePrice;

    /**
     * product_inventory 库存
     */
    @Column(name = "product_inventory")
    private Integer productInventory;

    /**
     * product_create_time 上架时间
     */
    @Column(name = "product_create_time")
    private String productCreateTime;

    /**
     * product_category_id 种类id
     */
    @Column(name = "product_category_id")
    private String productCategoryId;

    /**
     * product_enable 是否删除0正常1删除
     */
    @Column(name = "product_enable")
    private String productEnable;

    private static final long serialVersionUID = 1L;
}