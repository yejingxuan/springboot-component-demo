package com.yjx.demo.activity.model;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "oa_approval_flow")
public class OaApprovalFlow implements Serializable {

    @Id
    @Column(name = "process_instance_id")
    private String processInstanceId;

    @Column(name = "form_id")
    private String formId;

    @Column(name = "form_title")
    private String formTitle;

    @Column(name = "approve_type")
    private String approveType;

    @Column(name = "approve_status")
    private String approveStatus;

    @Column(name = "create_user_id")
    private String createUserId;

    @Column(name = "create_time")
    private String createTime;

    @Column(name = "update_time")
    private String updateTime;

    @Column(name = "current_node")
    private String currentNode;

    @Column(name = "current_users")
    private String currentUsers;

}
