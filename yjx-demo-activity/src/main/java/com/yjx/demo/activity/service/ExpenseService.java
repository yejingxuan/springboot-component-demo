package com.yjx.demo.activity.service;

import com.yjx.demo.activity.model.OaApprovalFlow;
import com.yjx.demo.activity.model.OaExpenseInfo;
import java.util.List;
import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.task.Task;

/**
 * @author jxye
 */
public interface ExpenseService {


    /**
     * 创建一个新的报销流程
     * @param oaExpenseInfo
     */
    void createExpenseInstance(OaExpenseInfo oaExpenseInfo);


    /**
     * 查询待办流程
     * @param userId
     * @return
     */
    List<OaApprovalFlow> queryTodoFlows(String userId);


    /**
     * 更改流程状态
     * @param execution
     * @param status
     * @return
     */
    void changeStatus(DelegateExecution execution, String status);


    /**
     * 查询流程发起人信息
     * @param execution
     * @return
     */
    List<String> findSelf(DelegateExecution execution);


    /**
     * 查询流程发起人的leader信息
     * @param execution
     * @return
     */
    List<String> findLeader(DelegateExecution execution);




    /**
     * 查询待办的流程任务
     * @param userId
     * @return
     */
    List<Task> findTaskByUserId(String userId);


    /**
     * 审批任务
     * @param taskId
     * @param userId
     * @param content
     */
    void completeTask(String taskId, String userId, String processInstanceId, String content);


    /**
     * 删除任务
     * @param taskId
     */
    void deleteTask(String taskId);



}
