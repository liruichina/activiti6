package com.abc.springbootactiviti.chapter13;

import org.activiti.engine.ManagementService;
import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngines;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.impl.cmd.AbstractCustomSqlExecution;
import org.activiti.engine.impl.cmd.CustomSqlExecution;
import org.apache.commons.lang3.StringUtils;

import com.abc.springbootactiviti.mapper.TaskQueryMapper;

import java.util.List;

/**
 * @author LiRui
 * 测试自定义SQL执行的代码，需要使用spring管理进行启动，不能使用常规JAVA方式启动
 *
 */
public class CustomSqlDemo {

    public static void main(String[] args) {
        CustomSqlDemo demo = new CustomSqlDemo();
        demo.process("jianguanyi2");
    }

//    @Autowired
    /**流程引擎（核心对象），默认加载类路径下命名为activiti.cfg.xml*/
    ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();

    ManagementService managementService = processEngine.getManagementService();

//    @Autowired
    RepositoryService repositoryService = processEngine.getRepositoryService();

    public void process(String processKey){
        CustomSqlExecution<TaskQueryMapper, List<RunningTask>> customSqlExecution =
                new AbstractCustomSqlExecution<TaskQueryMapper, List<RunningTask>>(TaskQueryMapper.class) {

                    public List<RunningTask> execute(TaskQueryMapper customMapper) {

                        // 使用内置实体对象查询
                        // List<TaskEntity> taskByVariable = customMapper.findTasks("applyUserId");

                        List<RunningTask> tasks=null;
                        if (StringUtils.isBlank(processKey)) {
                            tasks = customMapper.selectRunningTasks();
                        } else {
                            tasks = customMapper.selectRunningTasksByProcessKey(processKey);
                        }
                        return tasks;
                    }
                };

        List<RunningTask> tasks = managementService.executeCustomSql(customSqlExecution);
//        mav.addObject("tasks", tasks);

        // 读取引擎中所有的流程定义（只查询最新版本，目的在于获取流程定义的KEY和NAME）
//        List<ProcessDefinition> processes = repositoryService.createProcessDefinitionQuery().latestVersion().list();
//        mav.addObject("processes", processes);



//        return mav;
    }


}
