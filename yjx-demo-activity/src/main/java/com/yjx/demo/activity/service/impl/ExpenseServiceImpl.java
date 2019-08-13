package com.yjx.demo.activity.service.impl;

import com.yjx.demo.activity.dao.OaApprovalFlowDao;
import com.yjx.demo.activity.dao.OaExpenseInfoDao;
import com.yjx.demo.activity.enums.ApproveStatus;
import com.yjx.demo.activity.enums.ProcessEnum;
import com.yjx.demo.activity.model.OaApprovalFlow;
import com.yjx.demo.activity.model.OaExpenseInfo;
import com.yjx.demo.activity.service.ExpenseService;
import com.yjx.demo.activity.utils.DateUtil;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.activiti.engine.HistoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author jxye
 */
@Slf4j
@Service
public class ExpenseServiceImpl implements ExpenseService {

    @Resource
    private RuntimeService runtimeService;

    @Resource
    private TaskService taskService;

    @Resource
    private HistoryService historyService;

    @Resource
    private OaExpenseInfoDao oaExpenseInfoDao;

    @Resource
    private OaApprovalFlowDao oaApprovalFlowDao;


    @Transactional
    @Override
    public void createExpenseInstance(OaExpenseInfo oaExpenseInfo) {

        String nowDate = DateUtil.dateToStr(new Date(), "yyyyMMddHHmmss");
        // 保存报销单到数据库
        //oaExpenseInfo.setFormId();
        oaExpenseInfo.setCreateTime(nowDate);
        oaExpenseInfo.setUpdateTime(nowDate);
        OaExpenseInfo expense = oaExpenseInfoDao.save(oaExpenseInfo);
        log.info("创建报销流程success：{}", expense.toString());

        // 创建activit工作流
        String processInstanceId = this.createProcess(ProcessEnum.EXPENSE.getProcessId(), oaExpenseInfo.getFormId());

        // 保存申请流程
        OaApprovalFlow oaApprovalFlow = new OaApprovalFlow();
        oaApprovalFlow.setProcessInstanceId(processInstanceId);
        oaApprovalFlow.setFormId(oaExpenseInfo.getFormId());
        oaApprovalFlow.setFormTitle(ProcessEnum.EXPENSE.getProcessTitle());
        oaApprovalFlow.setApproveType(ProcessEnum.EXPENSE.getProcessType());
        oaApprovalFlow.setApproveStatus(ApproveStatus.DOING.getCode());
        oaApprovalFlow.setCreateUserId(oaExpenseInfo.getUserId());
        oaApprovalFlow.setCreateTime(nowDate);
        oaApprovalFlow.setUpdateTime(nowDate);
        oaApprovalFlow.setCurrentNode("");
        oaApprovalFlow.setCurrentUsers("");
        OaApprovalFlow flow = oaApprovalFlowDao.save(oaApprovalFlow);
        log.info("创建报销审批流程success：{}", flow.toString());
    }



    @Override
    public List<OaApprovalFlow> queryTodoFlows(String userId) {
        List<Task> tasks = taskService.createTaskQuery().taskCandidateOrAssigned(userId).list();

        Example example = new Example() {
            @Override
            public Object getProbe() {
                return null;
            }

            @Override
            public ExampleMatcher getMatcher() {
                return null;
            }
        };

        oaApprovalFlowDao.findAll(example);

        return null;
    }





    public String createProcess(String processId, String formId) {
        ProcessInstance expenseProcess = runtimeService
                .startProcessInstanceByKey(processId, formId);
        log.info("创建报销审批工作流success：{}", expenseProcess.toString());
        return expenseProcess.getProcessInstanceId();
    }


    @Override
    public void deleteTask(String taskId) {
        //taskService.deleteTask(taskId, true);
        runtimeService.deleteProcessInstance(taskId, "delete");
    }


    @Override
    public List<Task> findTaskByUserId(String userId) {
        List<Task> list = taskService.createTaskQuery().taskCandidateOrAssigned(userId).list();
        return list;
    }



   /* public List<Task> findTasks(){
        historyService.createHistoricTaskInstanceQuery().
    }*/


    @Override
    public void completeTask(String taskId, String userId, String processInstanceId,
            String content) {
        //taskService.addComment(taskId, processInstanceId, content);
        taskService.claim(taskId, userId);
        Map<String, Object> variables = new HashMap<>();
        variables.put("audit", content);
        taskService.complete(taskId, variables);
    }


    public void getTaskVariables(String bizKey) {
        runtimeService.getVariables(bizKey);

        taskService.createTaskQuery().orderByTaskCreateTime().desc().list();
    }


    @Override
    public void changeStatus(DelegateExecution execution, String status) {
        String expenseKey = execution.getProcessInstanceBusinessKey();
        String expenseId = execution.getProcessInstanceId();
        //根据id更改流程状态 // TODO: 2019/8/7
        log.info("expenseKey:{}, expenseId:{}, status:{}", expenseKey, expenseId, status);
    }

    @Override
    public List<String> findSelf(DelegateExecution execution) {
        String expenseId = execution.getProcessInstanceBusinessKey();
        //根据id查询发起人信息 // TODO: 2019/8/6
        List<String> res = Arrays.asList("zhangsan");
        return res;
    }

    @Override
    public List<String> findLeader(DelegateExecution execution) {
        String expenseId = execution.getProcessInstanceBusinessKey();
        //根据id查询发起人的leader信息 // TODO: 2019/8/6
        List<String> res = Arrays.asList("lisi", "wangwu");
        return res;
    }

}
