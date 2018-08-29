package com.abc.springbootactiviti.listener;

import org.activiti.engine.delegate.DelegateTask;
import org.activiti.engine.delegate.TaskListener;

import java.util.HashMap;
import java.util.Map;

public class ChangeAssignListener2 implements TaskListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public void notify(DelegateTask task) {	
		System.out.println("listener");
//		String [] candidateUsers={"a","b","c"};
//		task.setVariable("分享牛原创", Arrays.asList(candidateUsers));
		
		Map<String, Object> variables =new HashMap<String, Object>();
////		variables.put("pass", Boolean.parseBoolean("true"));
//		List<String> assigneeList = Arrays.asList("manager1#manager1","manager2#manager2","manager3#manager3"); 
////		variables.put("assigneeList", assigneeList);
//		System.out.println(task.getId());
//		System.out.println(task.getAssignee());
//		for(String s: task.getVariables().keySet()){
//			System.out.println(s);
//		}
//		task.setVariable("assignee", assigneeList);
		printTask(task);
		int i = (int)(1+Math.random()*(3));
		System.out.println("i==="+i);
//		if(i == 3){
//			task.setAssignee("manager"+i);
//		}else{
			task.addCandidateGroup("group4");
//		}
		printTask(task);
	}
	
	private void printTask(DelegateTask task){
		System.out.println("start------");
    	System.out.println("processInstanceId="+task.getProcessInstanceId());
        System.out.println("id="+task.getId());
        System.out.println("name="+task.getName());
        System.out.println("assinee="+task.getAssignee());
        System.out.println("createTime="+task.getCreateTime());
        System.out.println("executionId="+task.getExecutionId());
        System.out.println("-----end");
	}
	 
	}