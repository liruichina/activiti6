package com.abc.springbootactiviti;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngines;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.impl.persistence.entity.ExecutionEntity;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.task.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@Service("activityService")
public class ActivityConsumerServiceImpl implements ActivityConsumerService {

    @Autowired
    private RuntimeService runtimeService;
    @Autowired
    private TaskService taskService;



    @Override
    public boolean startActivityDemo() {
        System.out.println("method startActivityDemo begin....");
        Map<String,Object> map = new HashMap<String,Object>();
        map.put("apply","zhangsan");
        map.put("approve","lisi");
//流程启动
        ExecutionEntity pi1 = (ExecutionEntity) runtimeService.startProcessInstanceByKey("leave3",map);
        String processId = pi1.getId();
        System.out.println("第一步申请processId ------"+processId);
        String taskId = pi1.getTasks().get(0).getId();
        System.out.println("第一步申请taskId ------"+taskId);
        taskService.complete(taskId, map);//完成第一步申请

        Task task = taskService.createTaskQuery().processInstanceId(processId).singleResult();
        String taskId2 = task.getId();
        System.out.println("第二步申请taskId ------"+taskId2);
//        map.put("pass", false);
        map.put("pass", true);
        taskService.complete(taskId2, map);//驳回申请
        System.out.println("method startActivityDemo end....");
//        return false;
        return true;
    }

    @RequestMapping(value="/queryActivityDemo",method= RequestMethod.GET)
    public void queryActivityDemo(){
        ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
        List<ProcessDefinition> list = processEngine.getRepositoryService()//与流程定义和部署对象相关的Service
                .createProcessDefinitionQuery()//创建一个流程定义查询
                /*指定查询条件,where条件*/
                //.deploymentId(deploymentId)//使用部署对象ID查询
                //.processDefinitionId(processDefinitionId)//使用流程定义ID查询
                //.processDefinitionKey(processDefinitionKey)//使用流程定义的KEY查询
                //.processDefinitionNameLike(processDefinitionNameLike)//使用流程定义的名称模糊查询

                /*排序*/
                .orderByProcessDefinitionVersion().asc()//按照版本的升序排列
                //.orderByProcessDefinitionName().desc()//按照流程定义的名称降序排列

                .list();//返回一个集合列表，封装流程定义
        //.singleResult();//返回唯一结果集
        //.count();//返回结果集数量
        //.listPage(firstResult, maxResults)//分页查询

        if(list != null && list.size()>0) {
            for (ProcessDefinition processDefinition : list) {
                System.out.println("流程定义ID:" + processDefinition.getId());//流程定义的key+版本+随机生成数
                System.out.println("流程定义名称:" + processDefinition.getName());//对应HelloWorld.bpmn文件中的name属性值
                System.out.println("流程定义的key:" + processDefinition.getKey());//对应HelloWorld.bpmn文件中的id属性值
                System.out.println("流程定义的版本:" + processDefinition.getVersion());//当流程定义的key值相同的情况下，版本升级，默认从1开始
                System.out.println("资源名称bpmn文件:" + processDefinition.getResourceName());
                System.out.println("资源名称png文件:" + processDefinition.getDiagramResourceName());
                System.out.println("部署对象ID:" + processDefinition.getDeploymentId());
                System.out.println("################################");
            }
        }
    }
}
