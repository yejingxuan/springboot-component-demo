package com.yjx.mbg.model;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Data;

/**
* @author: jxye 
*/
@Table(name = "hy_admin")
@Data
public class HyAdmin implements Serializable {
    /**
     * admin_id 管理员id
     */
    @Id
    @Column(name = "admin_id")
    private String adminId;

    /**
     * admin_name 管理员名称
     */
    @Column(name = "admin_name")
    private String adminName;

    /**
     * admin_password 管理员密码
     */
    @Column(name = "admin_password")
    private String adminPassword;

    /**
     * system_id 系统id
     */
    @Column(name = "system_id")
    private String systemId;

    /**
     * admin_email 管理员邮箱
     */
    @Column(name = "admin_email")
    private String adminEmail;

    /**
     * admin_create_time 创建时间
     */
    @Column(name = "update_time")
    private String updateTime;

    /**
     * admin_enable 是否禁用0正常1禁用
     */
    @Column(name = "admin_enable")
    private String adminEnable;

    private static final long serialVersionUID = 1L;
}