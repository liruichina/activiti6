package com.abc.springbootactiviti;

import com.abc.springbootactiviti.beans.PengdingTask;
import com.abc.springbootactiviti.chapter13.RunningTask;
import com.abc.springbootactiviti.demo.AotoRunTestData;
import com.abc.springbootactiviti.demo.Demo4;
import com.abc.springbootactiviti.demo.TestList2;
import com.abc.springbootactiviti.demo.beans.ProcessNode;
import com.abc.springbootactiviti.mapper.TaskQueryMapper;
import com.abc.springbootactiviti.service.QueryTaskService;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import org.activiti.engine.ManagementService;
import org.activiti.engine.history.HistoricActivityInstance;
import org.activiti.engine.impl.cmd.AbstractCustomSqlExecution;
import org.activiti.engine.impl.cmd.CustomSqlExecution;
import org.apache.commons.lang3.StringUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Map;

/**
 * @auther LiRui
 * @Date 2018/4/17
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class TaskQueryTest {

    @Autowired
    private TaskQueryMapper taskQueryMapper;

    @Autowired
    private QueryTaskService taskQueryService;

//    @Autowired
//    ProcessEngine processEngine;
//    //= ProcessEngines.getDefaultProcessEngine();
    @Autowired
    ManagementService managementService;
//    //= processEngine.getManagementService();
//
//    @Autowired
//    RepositoryService repositoryService;
//    //= processEngine.getRepositoryService();

//    @Test
//    public void testInsert() {
//        Department department = new Department();
//        department.setId(1);
//        department.setName("研发部");
//        department.setDescr("开发产品");
//        this.departmentMapper.insert(department);
//    }
//
//    @Test
//    public void testGetById() {
//        Department department = this.departmentMapper.getById(1);
//        System.out.println(department);
//    }

//    @Test
//    public void testQuery() {
//        String processKey ="";
//        List<RunningTask> tasks = taskQueryMapper.selectRunningTasks();
//        if(tasks!=null&&tasks.size()>0){
//            tasks.forEach(System.out::println);
//        }
//   }

//    @Test
    public void testCustom() {
        String processKey ="jianguanyi2";
//        List<RunningTask> tasks ;


        CustomSqlExecution<TaskQueryMapper, List<RunningTask>> customSqlExecution =
                new AbstractCustomSqlExecution<TaskQueryMapper, List<RunningTask>>(TaskQueryMapper.class) {

                    public List<RunningTask> execute(TaskQueryMapper customMapper) {

                        // 使用内置实体对象查询
                        // List<TaskEntity> taskByVariable = customMapper.findTasks("applyUserId");

                        List<RunningTask> tasks=null;
                        if (StringUtils.isBlank(processKey)) {
                            tasks = customMapper.selectRunningTasks();
                        }
                        else {
                            tasks = customMapper.selectRunningTasksByProcessKey(processKey);
                        }
                        return tasks;
                    }
                };

        List<RunningTask> tasks = managementService.executeCustomSql(customSqlExecution);
        if(tasks!=null&&tasks.size()>0){
            tasks.forEach(System.out::println);
        }
    }

    //@Test
    public void testGetPengdingTaskByUserId(){
        List<PengdingTask> tasks = taskQueryMapper.getPengdingTasksByUserId("user13");
        if(tasks!=null&&tasks.size()>0){
            tasks.forEach(System.out::println);
        }
    }

    //@Test
    public void testGetPengdingTaskByGroup(){
        List<String> group = Lists.newArrayList("group0","group1","group2");
        List<PengdingTask> tasks = taskQueryMapper.getPengdingTasksByGroup(group);
        if(tasks!=null&&tasks.size()>0){
            tasks.forEach(System.out::println);
        }
    }


//    @Test
    public void testGetPengdingTask(){
        List<String> group = Lists.newArrayList("group0","group1","group2");
        List<PengdingTask> tasks = taskQueryService.queryPengingTasks("user02",group);
        if(tasks!=null&&tasks.size()>0){
            tasks.forEach(System.out::println);
        }
    }

//    @Test
    public void testQueryCompleteTasks(){
//        Map<String,Object> map = Maps.newHashMap();
//        map.put("assignee","user12");
        List<HistoricActivityInstance> tasks = taskQueryService.queryCompleteTasks("user12");
    }

    @Autowired
    private AotoRunTestData demo3;

//    @Autowired
//    private Demo4 demo4;

    @Test
    public void testAuto(){
        demo3.createGroupUser();
        TestList2 data = new TestList2();
        List<ProcessNode> list =data.createTestData();
        for(int i=0;i<list.size();i++){
            demo3.process(list.get(i));
        }
    }

//    @Test
//    public void testAutoRun(){
////        demo4.createGroupUser();
//        TestList2 data = new TestList2();
//        List<ProcessNode> list =data.createTestData();
//        for(int i=0;i<list.size();i++){
//            Demo4 demo = new Demo4(list.get(i));
//            demo.createGroupUser();
//            new Thread(demo).start();
//        }
//    }

//    @Test
//    public void testDeleteById() {
//        this.departmentMapper.deleteById(1);
//    }
}
