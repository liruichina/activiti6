package com.abc.springbootactiviti.listener;

import org.activiti.engine.delegate.DelegateTask;
import org.activiti.engine.delegate.TaskListener;
import org.activiti.engine.task.IdentityLink;

import com.google.common.collect.Lists;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class ChangeAssignListener implements TaskListener {
    /**
     *
     */
    private static final long serialVersionUID = 1L;

    @Override
    public void notify(DelegateTask task) {
        System.out.println("listener");

        Map<String, Object> variables =new HashMap<String, Object>();

        printTask(task);
        
        Set<IdentityLink> set = task.getCandidates();
        set.forEach((IdentityLink id)->System.out.println(id.getType()+"---"+id.getGroupId()+"---"+id.getUserId()));
        if("user01".equalsIgnoreCase(task.getAssignee())) {
        	System.out.println("当前任务归"+task.getAssignee()+"处理！");
        }
        for(IdentityLink id:set) {
        	System.out.println(id.getType()+"---"+id.getGroupId()+"---"+id.getUserId());
            if("group02".equals(id.getUserId())){
                task.addCandidateGroup("group02");                
                task.deleteCandidateUser("group02");
            }
            if("user33".equals(id.getUserId())) {
            	task.addCandidateUsers(Lists.newArrayList("user21","user22","user23"));
            	task.deleteCandidateUser("user33");
            }
            if("user01".equals(id.getUserId())){
                task.setAssignee(id.getUserId());                
//                task.deleteCandidateUser("group2");
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
        System.out.println("-----end");
    }

}