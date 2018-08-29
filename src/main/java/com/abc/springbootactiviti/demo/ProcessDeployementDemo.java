package com.abc.springbootactiviti.demo;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngines;
import org.activiti.engine.TaskService;
import org.activiti.engine.task.Attachment;

import java.io.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ProcessDeployementDemo {

    public static void main(String[] args) {
        ProcessDeployementDemo pd = new ProcessDeployementDemo();
//        pd.complete();
//        pd.completeMyPersonalTask();

        String taskId= "250002";

//        taskService.claim(taskId, assignee);

         Map<String, Object> variables= Maps.newHashMap();
//         variables.put("memo","true");
//        variables.put("postil","表单批注测试");
        variables.put("option",0);
        variables.put("assigneelist",Lists.newArrayList("user11","user13","user15","user16"));
        pd.complete(taskId);
//        pd.complete(taskId,variables);
//        pd.processAttachment("167507","167501");
//        pd.mytaskClaimAComplete(taskId,"user03");
    }

    /**流程引擎（核心对象），默认加载类路径下命名为activiti.cfg.xml*/
    ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();

    TaskService taskService = processEngine.getTaskService();

    public void processAttachment(String taskId,String piId) {
        // 设置任务附件
        Attachment att1 = taskService.createAttachment("web url", taskId, piId, "Attachement1",
                "baidu web page", "http://www.baidu.com");
// 创建图片输入流
        Attachment att2;
        try {
            try (InputStream is = new FileInputStream(new File("E:\\project\\springbootactiviti\\src\\main\\resources\\processes\\leave_service_exception.png"))) {
    // 设置输入流为任务附件
                att2 = taskService.createAttachment("web url", taskId, piId, "Attachement2", "Image InputStream", is);
                // 根据流程实例ID查询附件
                List<Attachment> attas1 = taskService.getProcessInstanceAttachments(piId);
                System.out.println("流程附件数量：" + attas1.size());
// 根据任务ID查询附件
                List<Attachment> attas2 = taskService.getTaskAttachments(taskId);
                System.out.println("任务附件数量：" + attas2.size());
// 根据附件ID查询附件
                Attachment attResult = taskService.getAttachment(att1.getId());
                System.out.println("附件1名称：" + attResult.getName());
// 根据附件ID查询附件内容
                InputStream stream1 = taskService.getAttachmentContent(att1.getId());
                System.out.println("附件1的输入流：" + stream1);
                InputStream stream2 = taskService.getAttachmentContent(att2.getId());
                System.out.println("附件2的输入流：" + stream2);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void complete(String taskId){
        ProcessEngine defaultProcessEngine = ProcessEngines.getDefaultProcessEngine();
        TaskService taskService = defaultProcessEngine.getTaskService();

        //根据ID完成任务,TaskId
        taskService.complete(taskId);
    }

    public void complete(String taskId,Map<String, Object> variables){
        ProcessEngine defaultProcessEngine = ProcessEngines.getDefaultProcessEngine();
        TaskService taskService = defaultProcessEngine.getTaskService();

//        Map<String, Object> variables= Maps.newHashMap();
//
//        variables.put("check1","lisi");
//        variables.put("check2","wangwu");

        //根据ID完成任务,TaskId
        taskService.complete(taskId,variables);
    }

    public void completeMyPersonalTask(){
        //任务ID
        String taskId = "65004";
        //完成任务的同时，设置流程变量，使用流程变量用来指定完成任务后，下一个连线，对应exclusiveGateWay.bpmn文件中${money>1000}
        Map<String, Object> variables = new HashMap<String, Object>();
        //走陈经理审批节点
        variables.put("day", 4);
        //与正在执行的任务管理相关的Service
        processEngine.getTaskService()
                .complete(taskId,variables);
        System.out.println("完成任务：任务ID："+taskId);
//      完成任务：任务ID：3004

    }


    /**
     * 对于候选任务，先签收再完成
     * @param taskId
     * @param assignee
     */
    public void mytaskClaimAComplete(String taskId,String assignee){
        //首先由user02进行签收
        taskService.claim(taskId, assignee);

        Map<String,Object>variables = new HashMap();//设置流程变量
        List<String> assigneelist = Lists.newArrayList("user12","user14","user16"
        );

        variables.put("assigneelist", assigneelist);
        variables.put("option", 0);

        taskService.complete(taskId,variables);

    }

    /**
     * 对于候选任务，先签收再完成
     * @param taskId
     * @param assignee
     */
    public void mytaskClaimAComplete(String taskId,String assignee,Map<String,Object> variables){
        //首先由user02进行签收
        taskService.claim(taskId, assignee);
        taskService.complete(taskId,variables);

    }

}
