package com.yjx.demo.shiro.model;

import java.io.Serializable;
import java.util.Set;
import lombok.Data;

@Data
public class UserInfo implements Serializable {

    private static final long serialVersionUID = -8375636962440675569L;

    private String userId;

    private String userName;

    private String password;

    private String salt;

    private Set<String> roles;

    private Set<String> perms;

}
