package com.yjx.demo.activity.enums;

public enum ApproveStatus {

    DOING("0", "待审批"),
    PASS("1", "通过"),
    REJECT("-1", "退回");

    private String code;
    private String name;

    ApproveStatus(String code, String name) {
        this.code = code;
        this.name = name;
    }

    public String getCode() {
        return this.code;
    }

    public String getName() {
        return this.name;
    }
}
