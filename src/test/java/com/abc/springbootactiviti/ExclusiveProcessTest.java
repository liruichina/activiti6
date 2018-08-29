package com.abc.springbootactiviti;

import org.activiti.engine.RuntimeService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;
import java.util.Map;

//@RunWith(SpringJUnit4ClassRunner.class)
//@SpringBootTest(classes = SpringBootActivitiApplication.class)
@RunWith(SpringRunner.class)
@SpringBootTest
public class ExclusiveProcessTest {

    @Autowired
    RuntimeService runtimeService;

    /**
     * 启动流程，并给流程传入参数type，排他网关根据参数type来判断流程走向
     */
    @Test
    public void testStartProcess() {
        Map<String, Object> map = new HashMap<>();
        map.put("type", 2);
        runtimeService.startProcessInstanceByKey("SimpleExclusiveProcess", map);

    }
}
