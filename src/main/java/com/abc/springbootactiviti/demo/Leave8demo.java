package com.abc.springbootactiviti.demo;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngines;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.runtime.ProcessInstance;

import java.io.InputStream;

public class Leave8demo {

    /**流程引擎（核心对象），默认加载类路径下命名为activiti.cfg.xml*/
    ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();

    /**部署流程定义（从inputStream）*/
//    @Test
    public void deploymentProcessDefinition_inputStream(){
        InputStream inputStreamBpmn = this.getClass().getResourceAsStream("exclusiveGateWay.bpmn");
        InputStream inputStreamPng = this.getClass().getResourceAsStream("exclusiveGateWay.png");
        Deployment deployment = processEngine.getRepositoryService()//与流程定义和部署对象相关的Service
                .createDeployment()//创建一个部署对象
                .name("排他网关")//添加部署的名称
                .addInputStream("exclusiveGateWay.bpmn", inputStreamBpmn)//
                .addInputStream("exclusiveGateWay.png", inputStreamPng)//
                .deploy();//完成部署
        System.out.println("部署ID："+deployment.getId());
        System.out.println("部署名称："+deployment.getName());
        System.out.println("部署时间："+deployment.getDeploymentTime());
//        部署ID：2901
//        部署名称：排他网关
//        部署时间：Wed Sep 06 20:49:24 CST 2017

    }

    /**启动流程实例*/
//    @Test
    public void startProcessInstance(){
        //流程定义的key.,key对应helloworld.bpmn文件中id的属性值，使用key值启动，默认是按照最新版本的流程定义启动
        String processDefinitionKey = "exclusiveGateWay";
        ProcessInstance pi = processEngine.getRuntimeService()//与正在执行的流程实例和执行对象相关的Service
                .startProcessInstanceByKey(processDefinitionKey);//使用流程定义的key启动流程实例，
        System.out.println("流程实例ID:"+pi.getId());//
        System.out.println("流程定义ID:"+pi.getProcessDefinitionId());//
//        流程实例ID:3001
//        流程定义ID:exclusiveGateWay:1:2904
    }

}
