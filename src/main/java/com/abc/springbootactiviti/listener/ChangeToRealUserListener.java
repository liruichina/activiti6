package com.abc.springbootactiviti.listener;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.activiti.engine.delegate.DelegateTask;
import org.activiti.engine.delegate.TaskListener;
import org.activiti.engine.task.IdentityLink;
import org.springframework.beans.factory.annotation.Autowired;

import com.abc.springbootactiviti.ActivitiType;
import com.abc.springbootactiviti.beans.UserType;
import com.abc.springbootactiviti.service.UserService;
import com.abc.springbootactiviti.service.UserServiceImpl;
import com.google.common.collect.Lists;

/**
 * @author LiRui
 * 根据当前节点的处理人信息，获取后设置为实际的处理人
 *
 */
public class ChangeToRealUserListener implements TaskListener {
    /**
    *
    */
   private static final long serialVersionUID = 1L;
   
//   @Autowired
   private UserService userService = new UserServiceImpl();

   @Override
   public void notify(DelegateTask task) {
       System.out.println("listener");

//       Map<String, Object> variables =new HashMap<String, Object>();

       printTask(task);
       
       Set<IdentityLink> set = task.getCandidates();
       set.forEach((IdentityLink id)->System.out.println(id.getType()+"---"+id.getGroupId()+"---"+id.getUserId()));
       //目前不使用Assignee，默认将值传入候选人清单中
//       if("user01".equalsIgnoreCase(task.getAssignee())) {
//       		System.out.println("当前任务归"+task.getAssignee()+"处理！");
//       }
       if(set.size()!=1) {
    	   System.out.println("*********************************************严重错误！严重错误！严重错误！*******************************************"); 
    	  return; 
       }
   
       for(IdentityLink id:set) {
    	   task.deleteCandidateUser(id.getUserId());
    	   List<UserType> list = userService.getRealUsers(id.getUserId());
    	   if(list==null||list.size()==0) {
    		   System.out.println("*********************************************严重错误！未查找到处理人！严重错误！*******************************************"); 
    	   }
    	   if(list.size()==1) {
    		   UserType userType = list.get(0);
    		   if(ActivitiType.USER.equals(userType.getType())) {
    			   task.setAssignee(userType.getValue());
    		   }
    		   if(ActivitiType.GROUP.equals(userType.getType())) {
    			   task.addCandidateGroup(userType.getValue());
    		   }
    	   }else {
    		   List<String> candidate = Lists.newArrayList();
    		   List<String> group = Lists.newArrayList();
    		   for(UserType userType:list) {
        		   if(ActivitiType.USER.equals(userType.getType())) {
        			   candidate.add(userType.getValue());
        		   }
        		   if(ActivitiType.GROUP.equals(userType.getType())) {
        			   group.add(userType.getValue());
        		   }
    		   }
    		   if(candidate.size()>0) {
    			   task.addCandidateUsers(candidate);
    		   }
    		   if(group.size()>0) {
    			   task.addCandidateGroups(candidate);
    		   }
    	   }

       }  

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
       if(task.getCandidates()!=null&&task.getCandidates().size()>0) {
    	   task.getCandidates().forEach(id->System.out.println(id.getType()+"---"+id.getGroupId()+"---"+id.getUserId()));
       }
       System.out.println("-----end");
   }
}
