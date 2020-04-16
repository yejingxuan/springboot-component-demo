package com.yjx.demo.es.model.dto;

import java.util.Date;
import lombok.Data;

@Data
public class EmploySearchDto {

    /**
     * 信息id
     */
    private String employInfoId;

    /**
     * 招聘类别
     */
    private String employType;

    /**
     * 招聘公司
     */
    private String company;


    private String highLightCompany;

    /**
     * 招聘详情
     */
    private String description;

    private String highLightDescription;

    /**
     * 招聘链接
     */
    private String href;

    /**
     * 最低月薪
     */
    private Integer minSalary;

    /**
     * 最高月薪
     */
    private Integer maxSalary;

    /**
     * 截止日期
     */
    private Date deadLines;

    /**
     * 招聘人数
     */
    private Integer headerCount;

    /**
     * 创建时间
     */
    private Date createTime;

}
