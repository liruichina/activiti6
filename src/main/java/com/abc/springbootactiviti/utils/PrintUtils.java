package com.abc.springbootactiviti.utils;

import org.activiti.engine.task.Task;

public class PrintUtils {
	
	public static void print(Task task) {
        System.out.println("流程实例id：" + task.getProcessInstanceId());
        System.out.println("任务id：" + task.getId());
        System.out.println("任务标识：" + task.getTaskDefinitionKey());
        System.out.println("任务名称：" + task.getName());
	}

}
