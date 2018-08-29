package com.abc.springbootactiviti.demo;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngines;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.delegate.BpmnError;
import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.delegate.JavaDelegate;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.activiti.engine.task.TaskQuery;
import org.apache.commons.lang3.StringUtils;

import java.util.List;
import java.util.Map;
import java.util.logging.Logger;
import java.util.stream.Stream;

public class JavaFormServiceDemo implements JavaDelegate {

    private final Logger log = Logger.getLogger(JavaServiceDemo.class.getName());

    ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();

    @Override
    public void execute(DelegateExecution execution) {
        try {
			Thread.sleep(20000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        log.info("variavles=" + execution.getVariables());
        //execution.setVariable("产品经理", "请假天数大约3天，同意请假。");
        System.out.println("=======================================");
        System.out.println("Java服务方式调用"+execution.getId());
        Map<String,Object> map = execution.getVariables();
        map.forEach((k,v)->System.out.println("Form表单参数"+k+"：" + v));
//        for(String s:map.keySet()){
//                    System.out.println("Form表单参数"+s+"：" + map.get(s));
//                }
//        System.out.println("Java服务方式调用"+execution.getVariable());
        System.out.println("=======================================");
        //抛出异常，执行到异常分支
//        throw new BpmnError("CCB_ERROR_001");
//        log.info("产品经理,请假天数大约3天，同意请假。");



    }

}
