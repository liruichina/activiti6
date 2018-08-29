package com.abc.springbootactiviti.demo;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngines;
import org.activiti.engine.TaskService;
import org.activiti.engine.task.Task;

import java.util.Date;
import java.util.Map;

public class QueryVarValuesDemo {

    public static void main(String[] args) {
        QueryVarValuesDemo demo = new QueryVarValuesDemo();
        demo.getProcessVariables("7502");
    }

    ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();

    TaskService taskService = processEngine.getTaskService();

    public void getProcessVariables(String taskId){
//        String processInstanceId = "2501";//流程实例ID
//        String assignee = "张三";//任务办理人
//        TaskService taskService = processEngine.getTaskService();
//        //获取当前办理人的任务ID
//        Task task = taskService.createTaskQuery()
//                .processInstanceId(processInstanceId)
////                .taskAssignee(assignee)
//                .singleResult();

        Map<String, Object> map = taskService.getVariables(taskId);
        map.forEach((k,v)->System.out.println(k+"-"+v));

//        //获取流程变量【基本类型】
//        String person = (String) taskService.getVariable(task.getId(), "请假人");
//        Integer day = (Integer) taskService.getVariableLocal(task.getId(), "请假天数");
//        Date date = (Date) taskService.getVariable(task.getId(), "请假日期");
//        System.out.println(person+"  "+day+"   "+date);

    }
}
