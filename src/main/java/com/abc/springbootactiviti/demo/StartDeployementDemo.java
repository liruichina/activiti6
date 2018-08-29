package com.abc.springbootactiviti.demo;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngines;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.runtime.ProcessInstance;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Map;

/**
 * 启动流程
 */
public class StartDeployementDemo {

    public static void main(String[] args) {
        StartDeployementDemo sd = new StartDeployementDemo();
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
        variables.put("userlist",Lists.newArrayList("user05","muser02","group3"));

        sd.startProcessInstance(processDefinitionKey,variables);
//        sd.startProcessInstance("leave_houxuan");


    }

    /**流程引擎（核心对象），默认加载类路径下命名为activiti.cfg.xml*/
    ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();

    public void startProcessInstance(String processDefinitionKey) {
        // 流程定义的key，定义在配置文件中
//        String processDefinitionKey = "leave";
        RuntimeService service = processEngine.getRuntimeService();
        /**
         * 使用流程定义的key启动流程实例，key对应hello.bpmn文件中id的属性值，
         * 使用key值启动，默认是按照最新版本的流程定义启动
         */
        ProcessInstance pi = service.startProcessInstanceByKey(processDefinitionKey);
        System.out.println("流程实例ID:" + pi.getId());// 流程实例ID:2501
        // 流程定义ID:hello:1:4
        System.out.println("流程定义ID:" + pi.getProcessDefinitionId());
    }

    public void startProcessInstance(String processDefinitionKey,Map<String, Object> variables) {
        // 流程定义的key，定义在配置文件中
//        String processDefinitionKey = "SimpleExclusiveProcess";
        RuntimeService service = processEngine.getRuntimeService();

//        Map<String, Object> variables= Maps.newHashMap();
//
//        variables.put("type",3);

        /**
         * 使用流程定义的key启动流程实例，key对应hello.bpmn文件中id的属性值，
         * 使用key值启动，默认是按照最新版本的流程定义启动
         */
        ProcessInstance pi = service.startProcessInstanceByKey(processDefinitionKey,variables);
        System.out.println("流程实例ID:" + pi.getId());// 流程实例ID:2501
        // 流程定义ID:hello:1:4
        System.out.println("流程定义ID:" + pi.getProcessDefinitionId());
    }

}
