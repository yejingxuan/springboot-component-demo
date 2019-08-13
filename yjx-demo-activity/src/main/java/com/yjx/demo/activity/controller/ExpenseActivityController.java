package com.yjx.demo.activity.controller;


import com.yjx.demo.activity.model.OaApprovalFlow;
import com.yjx.demo.activity.model.OaExpenseInfo;
import com.yjx.demo.activity.model.RespMsg;
import com.yjx.demo.activity.service.ExpenseService;
import com.yjx.demo.activity.utils.RespUtil;
import io.swagger.annotations.ApiOperation;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author jxye
 */
@Slf4j
@ApiOperation(value = "报销流程controller")
@RestController
public class ExpenseActivityController {

    @Autowired
    private ExpenseService expenseService;

    @Resource
    private RuntimeService runtimeService;

    @ApiOperation(value = "创建一个新的报销流程")
    @PutMapping(value = "/createExpenseInstance")
    public RespMsg createExpenseInstance(OaExpenseInfo oaExpenseInfo) {
        expenseService.createExpenseInstance(oaExpenseInfo);
        return RespUtil.success();
    }



    @ApiOperation(value = "查询待办流程")
    @GetMapping(value = "/queryTodoFlows")
    public RespMsg queryTodoFlows(String userId) {

        List<OaApprovalFlow> res = expenseService.queryTodoFlows(userId);

        /*List<Task> tasks = expenseService.findTaskByUserId(userId);
        tasks.forEach(task -> {
            log.info(task.toString());
        });*/
        return RespUtil.success(res);
    }


    @ApiOperation(value = "查询已办流程")
    @GetMapping(value = "/queryFinishFlows")
    public List<Task> queryFinishFlows(String userId) {
        List<Task> tasks = expenseService.findTaskByUserId(userId);
        tasks.forEach(task -> {
            log.info(task.toString());
        });
        return tasks;
    }






    @ApiOperation(value = "删除任务")
    @DeleteMapping(value = "/deleteTask")
    public void deleteTask(@RequestParam(value = "bizKey") String bizKey) {
        expenseService.deleteTask(bizKey);
    }



    @ApiOperation(value = "审批报销流程")
    @PutMapping(value = "/completeTask")
    public void completeTask(@RequestParam(value = "taskId") String taskId,
            @RequestParam(value = "userId") String userId,
            @RequestParam(value = "processInstanceId") String processInstanceId,
            @RequestParam(value = "content") String content) {
        expenseService.completeTask(taskId, userId, processInstanceId, content);
    }

}
