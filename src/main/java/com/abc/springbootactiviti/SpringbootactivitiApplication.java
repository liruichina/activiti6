package com.abc.springbootactiviti;

import com.abc.springbootactiviti.beans.PengdingTask;
import com.abc.springbootactiviti.chapter13.RunningTask;
import com.abc.springbootactiviti.demo.GroupUserMap;
import com.abc.springbootactiviti.mapper.TaskQueryMapper;
import com.abc.springbootactiviti.service.QueryTaskService;
import com.google.common.collect.Lists;
import org.activiti.engine.ManagementService;
import org.activiti.engine.history.HistoricActivityInstance;
import org.activiti.engine.impl.cmd.AbstractCustomSqlExecution;
import org.activiti.engine.impl.cmd.CustomSqlExecution;
import org.apache.commons.lang3.StringUtils;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@SpringBootApplication(scanBasePackages = "com.abc")
@MapperScan("com.abc.springbootactiviti.mapper")//将项目中对应的mapper类的路径加进来就可以了
@Controller
public class SpringbootactivitiApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootactivitiApplication.class, args);
    }

    @Autowired
    private TaskQueryMapper taskQueryMapper;

    @Autowired
    ManagementService managementService;

    @RequestMapping("/")
    @ResponseBody
//    @RequestMapping(value = "/",produces = "text/plain;charset=UTF-8")
    public String getHotels(){
//        logger.info("从数据库读取住宿人员集合");
//        return hotelService.getList();
        List<RunningTask> tasks = taskQueryMapper.selectRunningTasks();
        if(tasks!=null&&tasks.size()>0){
            tasks.forEach(System.out::println);
        }

        String processKey ="";
        CustomSqlExecution<TaskQueryMapper, List<RunningTask>> customSqlExecution =
                new AbstractCustomSqlExecution<TaskQueryMapper, List<RunningTask>>(TaskQueryMapper.class) {

                    public List<RunningTask> execute(TaskQueryMapper customMapper) {

                        // 使用内置实体对象查询
                        // List<TaskEntity> taskByVariable = customMapper.findTasks("applyUserId");

                        List<RunningTask> tasks=null;
                        if (StringUtils.isBlank(processKey)) {
                            tasks = taskQueryMapper.selectRunningTasks();
                        }
//                        else {
//                            tasks = customMapper.selectRunningTasksByProcessKey(processKey);
//                        }
                        return tasks;
                    }
                };

        tasks = managementService.executeCustomSql(customSqlExecution);
        if(tasks!=null&&tasks.size()>0){
            tasks.forEach(System.out::println);
        }

        return "Hello World!";
    }

    @Autowired
    private QueryTaskService taskQueryService;

    @RequestMapping("/tasks")
    @ResponseBody
//    @RequestMapping(value = "/",produces = "text/plain;charset=UTF-8")
    public List<HistoricActivityInstance> getCompleteTasks(){
//        List<HistoricActivityInstance> tasks

        return taskQueryService.queryCompleteTasks("user12");
    }

    @RequestMapping("/pengdingtasks/{user}")
    @ResponseBody
//    @RequestMapping(value = "/",produces = "text/plain;charset=UTF-8")
    public List<PengdingTask> queryPengdingTasks(@PathVariable("user") String user){
//        List<HistoricActivityInstance> tasks
        GroupUserMap.createGroupUser();
        String group = GroupUserMap.userGroupMap.get(user);

        return taskQueryService.queryPengingTasks(user, Lists.newArrayList(group));
    }

//    @RequestMapping("/")
//    @ResponseBody
//    String home() {
//        return "Hello World!";
//    }
}
