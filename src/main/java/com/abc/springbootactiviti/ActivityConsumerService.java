package com.abc.springbootactiviti;

import org.activiti.engine.task.TaskQuery;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/activityService")
public interface ActivityConsumerService {
    /**
     * 流程demo
     * @return
     */
    @RequestMapping(value="/startActivityDemo",method=RequestMethod.GET)
    public boolean startActivityDemo();

    /**
     * 流程demo
     * @return
     */
    @RequestMapping(value="/queryActivityDemo",method=RequestMethod.GET)
    public void queryActivityDemo();

}
