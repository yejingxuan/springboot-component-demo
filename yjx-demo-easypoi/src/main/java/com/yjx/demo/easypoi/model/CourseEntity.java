package com.yjx.demo.easypoi.model;

import cn.afterturn.easypoi.excel.annotation.Excel;
import cn.afterturn.easypoi.excel.annotation.ExcelCollection;
import cn.afterturn.easypoi.excel.annotation.ExcelEntity;
import cn.afterturn.easypoi.excel.annotation.ExcelTarget;
import java.io.Serializable;
import java.util.List;
import lombok.Data;

@Data
@ExcelTarget("courseEntity")
public class CourseEntity implements Serializable {

    private String id;

    @Excel(name = "课程名称", orderNum = "1", needMerge = true)
    private String name;

    @ExcelEntity(id = "absent")
    private TeacherEntity mathTeacher;

    @ExcelCollection(name = "学生", orderNum = "4")
    private List<StudentEntity> students;
}
