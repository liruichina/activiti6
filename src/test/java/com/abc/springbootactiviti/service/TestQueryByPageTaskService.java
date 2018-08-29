package com.abc.springbootactiviti.service;

import java.util.List;

import org.activiti.engine.task.Task;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.abc.springbootactiviti.beans.Page;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TestQueryByPageTaskService {

    @Autowired
    private QueryByPageTaskService queryByPageTaskService;

//    @Autowired
//    private Demo4 demo4;

    @Test
    public void testQueryAssignedPengingTasksByUserId(){
    	Page<Task> page = new Page<Task>(10);
    	page.setPageNo(1);
        page = queryByPageTaskService.queryAssignedPengingTasksByUserId("user05",page);        
        Assert.assertEquals(17,page.getTotalCount());
        Assert.assertEquals(10,page.getResult().size());
        page.setPageNo(2);
        page = queryByPageTaskService.queryAssignedPengingTasksByUserId("user05",page);
        Assert.assertEquals(17,page.getTotalCount());
        Assert.assertEquals(7,page.getResult().size());
    }
	
}
