package com.abc.springbootactiviti.service;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngines;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.runtime.ProcessInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Map;

/**
 * 启动流程
 */
@Service
public class StartDeployementService {

    public static void main(String[] args) {
        StartDeployementService sd = new StartDeployementService();
        //无参数的启动
//        sd.startProcessInstance("leave_service");
        Map<String, Object> variables= Maps.newHashMap();
//        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
//        Calendar ca = Calendar.getInstance();
        String startDate="2018-08-02";
        String endDate = "2018-08-06";
        String reason = "测试附件用的请假流程";
//        variables.put("startdate",startDate);
//        variables.put("enddate",endDate);
//        variables.put("reason",reason);
//        variables.put("group1","group0");
        //带有参数的启动
        String processDefinitionKey = "jianguanyi2";
        processDefinitionKey = "jianguanyi3";
        variables.put("user1list",Lists.newArrayList("user05","muser02","group3"));

        sd.startProcessInstance(processDefinitionKey,variables);
//        sd.startProcessInstance("leave_houxuan");
    }

    /**流程引擎（核心对象），默认加载类路径下命名为activiti.cfg.xml*/
    @Autowired
    private ProcessEngine processEngine;
    
    @Autowired
    private RuntimeService runtimeService;
    //= ProcessEngines.getDefaultProcessEngine();

    public void startProcessInstance(String processDefinitionKey) {
        // 流程定义的key，定义在配置文件中
//        String processDefinitionKey = "leave";
//        RuntimeService runtimeService = processEngine.getRuntimeService();
        /**
         * 使用流程定义的key启动流程实例，key对应hello.bpmn文件中id的属性值，
         * 使用key值启动，默认是按照最新版本的流程定义启动
         */
        ProcessInstance pi = runtimeService.startProcessInstanceByKey(processDefinitionKey);
        System.out.println("流程实例ID:" + pi.getId());// 流程实例ID:2501
        // 流程定义ID:hello:1:4
        System.out.println("流程定义ID:" + pi.getProcessDefinitionId());
    }

    public void startProcessInstance(String processDefinitionKey,Map<String, Object> variables) {
        // 流程定义的key，定义在配置文件中
//        String processDefinitionKey = "SimpleExclusiveProcess";
//        RuntimeService runtimeService = processEngine.getRuntimeService();

        /**
         * 使用流程定义的key启动流程实例，key对应hello.bpmn文件中id的属性值，
         * 使用key值启动，默认是按照最新版本的流程定义启动
         */
        ProcessInstance pi = runtimeService.startProcessInstanceByKey(processDefinitionKey,variables);
        System.out.println("流程实例ID:" + pi.getId());// 流程实例ID:2501
        // 流程定义ID:hello:1:4
        System.out.println("流程定义ID:" + pi.getProcessDefinitionId());
    }
    
    public void startProcessInstance(String processDefinitionKey,String processDefinitionName,Map<String, Object> variables) {
        // 流程定义的key，定义在配置文件中
        /**
         * 使用流程定义的key启动流程实例，key对应hello.bpmn文件中id的属性值，
         * 使用key值启动，默认是按照最新版本的流程定义启动
         */
        ProcessInstance pi = runtimeService.startProcessInstanceByKey(processDefinitionKey,variables);
        System.out.println("流程实例ID:" + pi.getId());// 流程实例ID:2501
        // 流程定义ID:hello:1:4
        System.out.println("流程定义ID:" + pi.getProcessDefinitionId());
        runtimeService.setProcessInstanceName(pi.getId(),processDefinitionName);
    }

}
