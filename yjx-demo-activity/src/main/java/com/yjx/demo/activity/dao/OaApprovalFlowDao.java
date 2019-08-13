package com.yjx.demo.activity.dao;

import com.yjx.demo.activity.model.OaApprovalFlow;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OaApprovalFlowDao extends JpaRepository<OaApprovalFlow, String> {

}
