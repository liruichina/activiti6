package com.abc.springbootactiviti.demo;

import java.util.List;
import java.util.Map;
import java.util.Random;

//import org.activiti.engine.IdentityService;
import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngines;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.abc.springbootactiviti.beans.PengdingTask;
import com.abc.springbootactiviti.service.QueryTaskService;
import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Multimap;

/**
 * 处理特定用户的代办任务
 */
@Service
public class AutoCompleteTasks {

    ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
    //得到runtimeService
    RuntimeService runtimeService = processEngine.getRuntimeService();
    //查询任务使用TaskService
    TaskService taskService = processEngine.getTaskService();

//    IdentityService identityService = processEngine.getIdentityService();

    @Autowired
    private QueryTaskService queryTaskService;

//    StartDeployementDemo startDemo = new StartDeployementDemo();

    ProcessDeployementDemo processDemo = new ProcessDeployementDemo();

    Multimap<String, String> groupmap = ArrayListMultimap.create();
    Map<String,String> userGroupMap = Maps.newHashMap();

    List<String> assigneelist = Lists.newArrayList();

    public void process(String userId){
    	GroupUserMap.createGroupUser();
    	userGroupMap = GroupUserMap.userGroupMap;
  
    	//查询出作为候选人或者被分配人的待办任务
        List<PengdingTask> list = queryTaskService.queryPengingTasksByUserId(userId);

        //被分配人
        if(list!=null&&list.size()>0){
        	 System.out.println("作为被分配人或者候选人的待处理任务数:"+list.size());
            for(PengdingTask task:list){
                Map<String, Object> variables0= Maps.newHashMap();
                //制单操作
                if("createcard".equalsIgnoreCase(task.getAct_id())) {
                    variables0.put("option",0);
                    //监管易的模拟流程时，作为传递的参数
                    assigneelist = Lists.newArrayList("user12","user14");
                    variables0.put("assigneelist", assigneelist);
                    processDemo.complete(task.getTask_id(),variables0);
                //复核操作
                } else if("check".equalsIgnoreCase(task.getAct_id())) {
                    variables0.put("option",0);
                    processDemo.complete(task.getTask_id(),variables0);
                }

                if(userId.equals(task.getAssignee())){
                	System.out.println("作为直接处理人****************************"+task.getTask_id()+"---"+task.getAct_name());
                    if("createcard".equalsIgnoreCase(task.getAct_id())||"check".equalsIgnoreCase(task.getAct_id())) {
                        processDemo.complete(task.getTask_id(),variables0);
                    } else {
                        processDemo.complete(task.getTask_id());
                    }
                    
                }else if(userId.equals(task.getCandidate())){
                    System.out.println("作为候选人****************************"+task.getTask_id()+"---"+task.getAct_name());
                    if("createcard".equalsIgnoreCase(task.getAct_id())||"check".equalsIgnoreCase(task.getAct_id())) {
                        processDemo.mytaskClaimAComplete(task.getTask_id(), userId, variables0);
                    } else {
                        processDemo.mytaskClaimAComplete(task.getTask_id(), userId);
                    }
                }

                sleepRandomTime();
            }
        }

        List<PengdingTask> list2 = queryTaskService.queryPengingTasksByGroup(Lists.newArrayList(userGroupMap.get(userId)));
        if(list2!=null&&list2.size()>0){
            for(PengdingTask task:list2){
            	System.out.println("作为候选组****************************"+task.getTask_id());
                if("createcard".equals(task.getAct_id())){                    
                    Map<String, Object> variables0= Maps.newHashMap();
                    variables0.put("option",0);
                    variables0.put("assigneelist", assigneelist);
                    processDemo.mytaskClaimAComplete(task.getTask_id(), userId, variables0);
                    sleepRandomTime();
                }else if("check".equals(task.getAct_id())){
                    Map<String, Object> variables0= Maps.newHashMap();
                    variables0.put("option",0);
                    processDemo.mytaskClaimAComplete(task.getTask_id(), userId, variables0);
                    sleepRandomTime();
                }else {
//                    Map<String, Object> variables0= Maps.newHashMap();
//                    variables0.put("option",0);
                    processDemo.mytaskClaimAComplete(task.getTask_id(), userId);
                    sleepRandomTime();
                }
            }
        }


    }

    private void sleepRandomTime(){
        int time =  new Random(100).nextInt(20000);

        try {
            Thread.sleep(time+500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
