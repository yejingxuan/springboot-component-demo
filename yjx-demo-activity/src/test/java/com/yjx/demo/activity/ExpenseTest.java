package com.yjx.demo.activity;

import com.yjx.demo.activity.service.ExpenseService;
import java.util.List;
import javax.annotation.Resource;
import org.activiti.engine.HistoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.history.HistoricTaskInstance;
import org.activiti.engine.task.Task;
import org.activiti.engine.task.TaskQuery;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ExpenseTest {

    @Autowired
    private ExpenseService expenseService;

    @Resource
    private RuntimeService runtimeService;

    @Resource
    private TaskService taskService;

    @Resource
    private HistoryService historyService;


    @Test
    public void test() {
        System.out.println("123");
    }


    @Test
    public void getTasks() {
        List<Task> task = expenseService.findTaskByUserId("zhangsan");
        System.out.println(task.toString());
    }

    @Test
    public void getAllTasks() {
        List<Task> tasks = taskService.createTaskQuery()
                .taskCandidateOrAssigned("zhangsan").orderByTaskCreateTime().desc().list();

        taskService.createTaskQuery().processDefinitionKey("").taskCandidateOrAssigned("zhangsan");

        List<HistoricTaskInstance> list = historyService.createHistoricTaskInstanceQuery()
                .finished().list();

        System.out.println(tasks.toString());
    }

}
