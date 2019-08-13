package com.yjx.demo.activity.model;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "oa_expense_info")
public class OaExpenseInfo implements Serializable {

    @Id
    @Column(name = "form_id")
    private String formId;

    @Column(name = "user_id")
    private String userId;

    @Column(name = "expense_time")
    private String expenseTime;

    @Column(name = "expense_msg")
    private String expenseMsg;

    @Column(name = "create_time")
    private String createTime;

    @Column(name = "update_time")
    private String updateTime;

}
