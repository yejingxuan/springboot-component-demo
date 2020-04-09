package com.yjx.demo.es.model.es;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.sql.Timestamp;
import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmployEs {

    private String description;

    private String employType;

    private String company;

    private String href;

    private String salary;

    private String deadLines;

    private Long headerCount;

    @JsonFormat
    private Date createTime;

}
