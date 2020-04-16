package com.yjx.mbg.model;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Table;
import lombok.Data;

/**
* @author: jxye 
*/
@Table(name = "hy_userinfo")
@Data
public class HyUserinfo implements Serializable {
    /**
     * user_id 用户id
     */
    @Column(name = "user_id")
    private String userId;

    /**
     * user_name 用户名
     */
    @Column(name = "user_name")
    private String userName;

    /**
     * user_password 用户密码
     */
    @Column(name = "user_password")
    private String userPassword;

    /**
     * user_emali 用户邮箱
     */
    @Column(name = "user_emali")
    private String userEmali;

    /**
     * user_phone 用户电话
     */
    @Column(name = "user_phone")
    private String userPhone;

    /**
     * user_IP 用户IP
     */
    @Column(name = "user_IP")
    private String userIp;

    /**
     * user_wechat_id 用户微信id
     */
    @Column(name = "user_wechat_id")
    private String userWechatId;

    /**
     * user_qq_id 用户QQid
     */
    @Column(name = "user_qq_id")
    private String userQqId;

    /**
     * system_id 用户所属系统
     */
    @Column(name = "system_id")
    private String systemId;

    /**
     * user_enable 是否禁用0正常1禁用
     */
    @Column(name = "user_enable")
    private String userEnable;

    private static final long serialVersionUID = 1L;
}