package com.abc.springbootactiviti.service;

import java.util.Map;

import org.activiti.engine.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProcessDeployementService {

    /**流程引擎（核心对象），默认加载类路径下命名为activiti.cfg.xml*/
//    @Autowired
//    private ProcessEngine processEngine;

    @Autowired
    TaskService taskService;
    //= processEngine.getTaskService();



    public void complete(String taskId){
        //根据ID完成任务,TaskId
        taskService.complete(taskId);
    }

    public void complete(String taskId,Map<String, Object> variables){
        //根据ID完成任务,TaskId
        taskService.complete(taskId,variables);
    }

    /**
     * 对于候选任务，先签收再完成
     * @param taskId
     * @param assignee
     */
    public void claimAndComplete(String taskId,String assignee){
        //首先由assignee进行签收
        taskService.claim(taskId, assignee);
        taskService.complete(taskId);
    }

    /**
     * 对于候选任务，先签收再完成
     * @param taskId
     * @param assignee
     */
    public void claimAndComplete(String taskId,String assignee,Map<String,Object> variables){
        //首先由user02进行签收
        taskService.claim(taskId, assignee);
        taskService.complete(taskId,variables);
    }

}
