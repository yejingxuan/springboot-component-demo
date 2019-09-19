package com.yjx.demo.easypoi.controller;


import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.entity.ExportParams;
import com.yjx.demo.easypoi.model.CourseEntity;
import com.yjx.demo.easypoi.model.StudentEntity;
import com.yjx.demo.easypoi.model.TeacherEntity;
import com.yjx.demo.easypoi.utils.ImageUtil;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import javax.servlet.http.HttpServletResponse;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/export")
public class EasypoiController {

    @GetMapping("/exportExcel")
    public void exportExcel(HttpServletResponse response) {

        List<StudentEntity> list = new ArrayList<>();
        list.add(new StudentEntity("1", "张三", 1, new Date(), new Date()));

        Workbook workbook = ExcelExportUtil.exportExcel(new ExportParams("计算机一班学生", "学生"),
                StudentEntity.class, list);

        try {
            response.setHeader("content-disposition", "attachment;filename=" + URLEncoder
                    .encode("excel导出结果" + ".xls", "UTF-8"));
            OutputStream os = response.getOutputStream();
            workbook.write(os);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @GetMapping("/exportComplexExcel")
    public void exportComplexExcel(HttpServletResponse response) {

        List<CourseEntity> courseEntity = new ArrayList<>();

        CourseEntity entity = new CourseEntity();
        List<StudentEntity> stus = new ArrayList<>();
        stus.add(new StudentEntity("1", "张三", 1, new Date(), new Date()));
        stus.add(new StudentEntity("2", "李四", 2, new Date(), new Date()));

        byte[] fileByNetwork = ImageUtil.getFileByNetwork(
                "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1567774578215&di=4c39a5c7eb6435c76bb47b747510a373&imgtype=0&src=http%3A%2F%2Ffile.youboy.com%2Fa%2F63%2F26%2F69%2F6%2F7890786.jpg");

        entity.setName("高等数学");
        entity.setMathTeacher(new TeacherEntity("1", "李行李行李行李行李行李行李行李行李行李行李行李行李行李行李行李行李行李行李行李行李行李行李行李行李行李行李行李行李行李行李行李行李行李行李行李行李行李行李行李行李行李行李行李行李行李行李行李行李行李行李行李行李行李行李行李行李行李行李行李行李行李行李行李行李行", fileByNetwork));
        entity.setStudents(stus);
        courseEntity.add(entity);

        CourseEntity entity2 = new CourseEntity();
        List<StudentEntity> stus2 = new ArrayList<>();
        stus2.add(new StudentEntity("1", "张三", 1, new Date(), new Date()));
        stus2.add(new StudentEntity("2", "李四", 2, new Date(), new Date()));
        entity2.setName("物理");
        entity2.setMathTeacher(new TeacherEntity("1", "欧阳", fileByNetwork));
        entity2.setStudents(stus2);
        courseEntity.add(entity2);

        Workbook workbook = ExcelExportUtil.exportExcel(new ExportParams("计算机一班", "学生"),
                CourseEntity.class, courseEntity);

        Sheet sheet = workbook.getSheetAt(0);

        // 解决自动设置列宽中文失效的问题

        // sheet.autoSizeColumn(1, true);

        try {
            response.setHeader("content-disposition", "attachment;filename=" + URLEncoder
                    .encode("选课表" + ".xls", "UTF-8"));
            OutputStream os = response.getOutputStream();
            workbook.write(os);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
