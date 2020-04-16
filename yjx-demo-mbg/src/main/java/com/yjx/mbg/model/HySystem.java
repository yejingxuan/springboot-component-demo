package com.yjx.mbg.model;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Table;
import lombok.Data;

/**
* @author: jxye 
*/
@Table(name = "hy_system")
@Data
public class HySystem implements Serializable {
    /**
     * system_id 系统id
     */
    @Column(name = "system_id")
    private String systemId;

    /**
     * system_name 系统名称
     */
    @Column(name = "system_name")
    private String systemName;

    /**
     * system_path 系统路径
     */
    @Column(name = "system_path")
    private String systemPath;

    /**
     * system_create_time 创建时间
     */
    @Column(name = "system_create_time")
    private String systemCreateTime;

    /**
     * system_linkman 联系人
     */
    @Column(name = "system_linkman")
    private String systemLinkman;

    /**
     * system_linkway 联系方式
     */
    @Column(name = "system_linkway")
    private String systemLinkway;

    /**
     * system_enable 是否禁用0正常1禁用
     */
    @Column(name = "system_enable")
    private String systemEnable;

    private static final long serialVersionUID = 1L;
}