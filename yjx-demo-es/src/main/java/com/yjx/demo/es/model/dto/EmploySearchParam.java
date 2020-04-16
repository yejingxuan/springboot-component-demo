package com.yjx.demo.es.model.dto;

import io.swagger.annotations.ApiParam;
import lombok.Data;
import org.springframework.web.bind.annotation.RequestParam;

@Data
public class EmploySearchParam {

    /**
     * 关键词检索，包含company、description
     */
    private String keyWords;

    private String employInfoId;

    private String employType;

    /**
     * 1:最低月薪排序 2：最高月薪排序 3：截止日期排序 4：招聘人数排序 5:发布日期排序
     */
    private Integer sort = 5;

    private Integer pageNo = 1;

    private Integer pageSize = 10;
}
