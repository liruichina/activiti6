package com.abc.springbootactiviti;

import org.activiti.engine.ManagementService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.abc.springbootactiviti.demo.AutoCompleteTasks;

/**
 * @author LiRui
 *	运行自动程序，按照用户查找到待办任务，并自动完成相应的待办任务
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class TaskCompleteTest {

	    @Autowired
	    ManagementService managementService;

	    @Autowired
	    private AutoCompleteTasks auto;

	    @Test
	    public void testAutoCompleteTasks(){
//	    	auto.process("user02");
	    	auto.process("user26");
	    }


}
