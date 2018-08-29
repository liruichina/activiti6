package com.abc.springbootactiviti.service;

import java.util.List;
import java.util.Map;

import org.activiti.engine.task.Task;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TestQueryTaskService {
	
	
    @Autowired
    private QueryTaskService queryTaskService;

//    @Autowired
//    private Demo4 demo4;

    @Test
    public void testQueryAssignedPengingTasksByUserId(){
        List<Task> tasks = queryTaskService.queryAssignedPengingTasksByUserId("user05");        
        Assert.assertTrue(tasks.size()>0);
    }
    
    @Test
    public void testQueryCandidatePengingTasksByUserId(){
        List<Task> tasks = queryTaskService.queryCandidatePengingTasksByUserId("user02");
        Assert.assertTrue(tasks.size()>0);
        tasks = queryTaskService.queryCandidatePengingTasksByUserId("user03");
        Assert.assertTrue(tasks.size()>0);
        for(Task task:tasks) {
        	System.out.println(task.getName());
//        	task.get
        }
        tasks = queryTaskService.queryCandidatePengingTasksByUserId("muser02");
        Assert.assertTrue(tasks.size()==0);
    }
    
    @Test
    public void testQueryCandidatePengingTasksByGroup(){
//    	String processDefinitionKey ="";
//        processDefinitionKey = "jianguanyi3";
//        Map<String, Object> variables= Maps.newHashMap();
//        variables.put("user1list",Lists.newArrayList("user05","muser02","group3"));
        List<Task> tasks = queryTaskService.queryCandidatePengingTasksByGroup("group3");
        Assert.assertTrue(tasks.size()>0);
        tasks = queryTaskService.queryCandidatePengingTasksByGroup("group4");
        Assert.assertTrue(tasks.size()==0);
        
        tasks = queryTaskService.queryCandidatePengingTasksByGroup(Lists.newArrayList("group1","group2","group3"));
        Assert.assertTrue(tasks.size()>0);
        tasks = queryTaskService.queryCandidatePengingTasksByGroup(Lists.newArrayList("group1","group2","group4"));
        Assert.assertTrue(tasks.size()==0);
    }
    
//    @Test
//    public void testQueryCandidatePengingTasksByGroups(){
////    	String processDefinitionKey ="";
////        processDefinitionKey = "jianguanyi3";
////        Map<String, Object> variables= Maps.newHashMap();
////        variables.put("user1list",Lists.newArrayList("user05","muser02","group3"));
//        List<Task> tasks = queryTaskService.queryCandidatePengingTasksByGroups("group03");
//        Assert.assertTrue(tasks.size()>0);
//        tasks = queryTaskService.queryCandidatePengingTasksByGroup("group04");
//        Assert.assertTrue(tasks.size()==0);
//    }

}
