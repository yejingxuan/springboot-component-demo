package com.yjx.mbg.model;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Table;
import lombok.Data;

/**
* @author: jxye 
*/
@Table(name = "hy_order")
@Data
public class HyOrder implements Serializable {
    /**
     * order_id 订单id
     */
    @Column(name = "order_id")
    private String orderId;

    /**
     * system_id 订单归属系统
     */
    @Column(name = "system_id")
    private String systemId;

    /**
     * product_id 商品id
     */
    @Column(name = "product_id")
    private String productId;

    /**
     * product_retail_price 商品零售单价
     */
    @Column(name = "product_retail_price")
    private Double productRetailPrice;

    /**
     * contact_way 联系方式
     */
    @Column(name = "contact_way")
    private String contactWay;

    /**
     * account 充值账号
     */
    @Column(name = "account")
    private String account;

    /**
     * number 购买数量
     */
    @Column(name = "number")
    private Integer number;

    /**
     * user_ip 购买用户ip
     */
    @Column(name = "user_ip")
    private String userIp;

    /**
     * order_state 订单状态，0待支付，1已支付，2已发货，-1退单
     */
    @Column(name = "order_state")
    private String orderState;

    /**
     * user_id 购买账号
     */
    @Column(name = "user_id")
    private String userId;

    /**
     * total_amount 订单总价
     */
    @Column(name = "total_amount")
    private Double totalAmount;

    /**
     * create_time 创建时间
     */
    @Column(name = "create_time")
    private String createTime;

    /**
     * order_enable 是否删除0正常1删除
     */
    @Column(name = "order_enable")
    private String orderEnable;

    /**
     * 
     */
    @Column(name = "remark")
    private String remark;

    private static final long serialVersionUID = 1L;
}