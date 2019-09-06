package com.yjx.demo.easypoi.model;

import cn.afterturn.easypoi.excel.annotation.Excel;
import cn.afterturn.easypoi.excel.annotation.ExcelTarget;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ExcelTarget("teacherEntity")
public class TeacherEntity implements java.io.Serializable {

    private String id;

    @Excel(name = "主讲老师_major,代课老师_absent", orderNum = "1", needMerge = true, isImportField = "true_major,true_absent")
    private String name;

    @Excel(name = "教师照片", orderNum = "2", type = 2, width = 40, height = 80, imageType = 2, needMerge = true)
    private byte[] teacherPic;
}
