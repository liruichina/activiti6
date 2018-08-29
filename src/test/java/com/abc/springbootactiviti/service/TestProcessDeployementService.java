package com.abc.springbootactiviti.service;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.abc.springbootactiviti.demo.AotoRunTestData;
import com.abc.springbootactiviti.demo.TestList2;
import com.abc.springbootactiviti.demo.beans.ProcessNode;
import com.google.common.collect.Lists;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TestProcessDeployementService {
	
    @Autowired
    private ProcessDeployementService processDeployementService;

//    @Autowired
//    private Demo4 demo4;

    @Test
    public void testProcess(){
//    	String taskId ="310002";
    	List<String> taskIds = Lists.newArrayList("2560","2576","2592","2608","2624","2640","2656","2672","2688","2704","2720","2736","2752","2768","2784","2800","2816");
    	taskIds.forEach(taskId->processDeployementService.complete(taskId));
//    	processDeployementService.complete(taskId);
    }
    
}
