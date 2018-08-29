package com.abc.springbootactiviti.demo;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngines;

public class ProcessInstanceNameDemo {

	public static void main(String[] args) {
		
		 ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();

		// TODO Auto-generated method stub
		processEngine.getRuntimeService().setProcessInstanceName("315181","测试加的名称");
	}
	
	

}
