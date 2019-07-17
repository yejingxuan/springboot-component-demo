package com.yjx.deom.kafka.model;

import java.io.Serializable;
import lombok.Data;


@Data
public class UserInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    private String id;
    private String stuId;
    private String stuName;
    private String createTime;


}
