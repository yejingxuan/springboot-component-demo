package com.yjx.demo.activity.enums;

public enum ProcessEnum {

    EXPENSE("expenseProcess", "报销流程", "expense"),
    ASKFORLEAVE("askForLeaveProcess", "请假流程", "askForLeave");

    private String processId;
    private String processTitle;
    private String processType;


    ProcessEnum(String processId, String processTitle, String processType) {
        this.processId = processId;
        this.processTitle = processTitle;
        this.processType = processType;
    }

    public String getProcessId() {
        return this.processId;
    }

    public String getProcessTitle() {
        return this.processTitle;
    }

    public String getProcessType() {
        return this.processType;
    }

}
