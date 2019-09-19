package com.yjx.demo.quickssm.model.pojo;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Data;

@Data
@Table(name = "user_info")
public class User {

    @Id
    private String uid;

    @Column(name = "username")
    private String userName;

    @Column(name = "password")
    private String passWord;

}
