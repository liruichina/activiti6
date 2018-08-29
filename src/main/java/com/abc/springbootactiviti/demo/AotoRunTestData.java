package com.abc.springbootactiviti.demo;

import com.abc.springbootactiviti.beans.PengdingTask;
import com.abc.springbootactiviti.demo.beans.ProcessNode;
import com.abc.springbootactiviti.service.QueryTaskService;
import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Multimap;
import org.activiti.engine.*;
import org.activiti.engine.identity.User;
import org.activiti.engine.task.IdentityLink;
import org.activiti.engine.task.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * 自动编写的测试案例，自动进行执行
 */
@Service
public class AotoRunTestData {
	@Autowired
    ProcessEngine processEngine;
    //= ProcessEngines.getDefaultProcessEngine();
    //得到runtimeService
	@Autowired
    RuntimeService runtimeService;
    //= processEngine.getRuntimeService();
    //查询任务使用TaskService
	@Autowired
    TaskService taskService;
    //= processEngine.getTaskService();
	@Autowired
    IdentityService identityService;
    //= processEngine.getIdentityService();

    @Autowired
    private QueryTaskService taskQueryService;

//    public static void main(String[] args) {
//        Demo3 demo = new Demo3();
//        //生成组与用户的关系
//        demo.createGroupUser();
//        TestList2 data = new TestList2();
//        List<ProcessNode> list =data.createTestData();
//        for(int i=0;i<list.size();i++){
//            demo.process(list.get(i));
//        }
//    }


    StartDeployementDemo startDemo = new StartDeployementDemo();

    ProcessDeployementDemo processDemo = new ProcessDeployementDemo();

//    QueryDemo queryDemo = new QueryDemo();

    Multimap<String, String> groupmap = ArrayListMultimap.create();
    Map<String,String> userGroupMap = Maps.newHashMap();

    private String processDefinitionKey = "jianguanyi2";
    private String zhiDanUserId = "";

    List<String> assigneelist = Lists.newArrayList();
    //遇到该用户时，中止整个流程的运行
    private String stopUser;

    public void process(ProcessNode data){
        stopUser = data.getStopNode();

        //step1，启动流程
        Map<String, Object> variables= Maps.newHashMap();
        variables.put("group1",data.getStartNode().get("group1"));
        //带有参数的启动
        startDemo.startProcessInstance(processDefinitionKey,variables);

        //获取用户与组的关系
        groupmap.forEach((k,v)->userGroupMap.put(v,k));

        zhiDanUserId = (String)data.getCreateCardNode().get("assignee");
        if(stopUser!=null&&stopUser.equals(zhiDanUserId)){
            return;
        }

//        List<Task> list = queryDemo.findPersonalTaskList(processDefinitionKey,zhiDanUserId);
        List<PengdingTask> list = taskQueryService.queryPengingTasksByUserId(zhiDanUserId);
        assigneelist = (List)data.getCreateCardNode().get("assigneelist");
        //被分配人
        if(list!=null&&list.size()>0){
            System.out.println("作为被分配人****************************"+list.size());
            for(PengdingTask task:list){
                Map<String, Object> variables0= Maps.newHashMap();
                variables0.put("option",data.getCreateCardNode().get("option"));
                variables0.put("assigneelist", assigneelist);
                if(zhiDanUserId.equals(task.getAssignee())){
                    processDemo.complete(task.getTask_id(),variables0);
                }else if(zhiDanUserId.equals(task.getCandidate())){
                    System.out.println("作为候选人****************************"+task.getTask_id()+"---"+task.getAct_name());
                    processDemo.mytaskClaimAComplete(task.getTask_id(), zhiDanUserId, variables0);
                }

                sleepRandomTime();
            }
        }
//        List<Task> list2 = queryDemo.findPersonalTaskList(processDefinitionKey,"");
        List<PengdingTask> list2 = taskQueryService.queryPengingTasksByGroup(Lists.newArrayList(userGroupMap.get(zhiDanUserId)));
        if(list2!=null&&list2.size()>0){
            for(PengdingTask task:list2){
                if("createcard".equals(task.getAct_id())){
                    System.out.println("作为候选组****************************"+task.getTask_id());
                    Map<String, Object> variables0= Maps.newHashMap();
                    variables0.put("option",data.getCreateCardNode().get("option"));
                    variables0.put("assigneelist", assigneelist);
                    processDemo.mytaskClaimAComplete(task.getTask_id(), zhiDanUserId, variables0);
                    sleepRandomTime();
                }
            }
        }

        List<Object> options = (List<Object>)data.getCheckNode().get("option");
        //复核流程
        if(assigneelist!=null&&assigneelist.size()>0){
            for(int i=0;i<assigneelist.size();i++){
                check( processDefinitionKey, assigneelist.get(i),(Integer)options.get(i));
            }
        }

    }

    private void check(String processDefinitionKey,String userId,Integer option){
        if(stopUser!=null&&stopUser.equals(userId)){
            return;
        }
//        if("user16".equals(userId)){
//
//        }
//        List<Task> list = queryDemo.findPersonalTaskList(processDefinitionKey,userId);
        List<PengdingTask> list = taskQueryService.queryPengingTasksByUserId(userId);
        //被分配人
        if(list!=null&&list.size()>0){
            System.out.println("作为被分配人****************************"+list.size());
            for(PengdingTask task:list){
                Map<String, Object> variables0= Maps.newHashMap();
                variables0.put("option",option);
                if(userId.equals(task.getAssignee())){
                    //作为被分配人
                    processDemo.complete(task.getTask_id(),variables0);
                }else{
                    //作为候选人的处理
                    processDemo.mytaskClaimAComplete(task.getTask_id(), userId, variables0);
                }
                sleepRandomTime();
            }
        }
        List<PengdingTask> list2 = taskQueryService.queryPengingTasksByGroup(Lists.newArrayList(userGroupMap.get(userId)));
        if(list2!=null&&list2.size()>0){
            for(PengdingTask task:list2){
//                if(getCandidateTasks(task.getTask_id(),userId)){
                    System.out.println("作为候选人或者组****************************"+task.getTask_id()+"---"+task.getAct_name());
                    Map<String, Object> variables0= Maps.newHashMap();
                    variables0.put("option",option);
                    processDemo.mytaskClaimAComplete(task.getTask_id(), userId, variables0);
                    sleepRandomTime();
//                }
            }
        }
    }


    public Multimap<String, String> createGroupUser(){
        groupmap.clear();
        groupmap.put("group0","user01");
        groupmap.put("group0","user02");
        groupmap.put("group0","user03");
        groupmap.put("group0","user04");
        groupmap.put("group0","user05");
        groupmap.put("group0","user06");

        groupmap.put("group1","user11");
        groupmap.put("group1","user12");
        groupmap.put("group1","user13");
        groupmap.put("group1","user14");
        groupmap.put("group1","user15");
        groupmap.put("group1","user16");

        groupmap.put("group2","user21");
        groupmap.put("group2","user22");
        groupmap.put("group2","user23");
        groupmap.put("group2","user24");
        groupmap.put("group2","user25");
        groupmap.put("group2","user26");
        return groupmap;
    }

//    private String getZhiDanUser(){
//        List<String> list = (List)groupmap.get("group0");
//        int i = new Random().nextInt(list.size());
//        return list.get(i);
//    }
//
//    private List<String> getFuheUser(){
//        List<String> list = (List)groupmap.get("group1");
//        int length = new Random().nextInt(list.size());
//        if(length<2){
//            length=2;
//        }
//        Collections.shuffle(list);
//        List<String> result = Lists.newArrayList();
//        for(int i=0;i<length;i++){
//            result.add(list.get(i));
//        }
//
//        return result;
//    }

    private boolean getCandidateTasks(String taskId,String userId){
        Set users = new HashSet();
        List<IdentityLink> identityLinkList = taskService.getIdentityLinksForTask(taskId);
        if (identityLinkList != null && identityLinkList.size() > 0) {
            for(IdentityLink identityLink:identityLinkList){
                if (identityLink.getUserId() != null&&identityLink.getUserId().equals(userId)) {
                    return true;
                }
                if (identityLink.getGroupId() != null&&identityLink.getGroupId().equals(userGroupMap.get(userId))) {
                    return true;
                }
            }
        }
        return false;
    }

    private Set getTaskCandidate(String taskId) {
        Set users = new HashSet();
        List identityLinkList = taskService.getIdentityLinksForTask(taskId);
        if (identityLinkList != null && identityLinkList.size() > 0) {
            for (Iterator iterator = identityLinkList.iterator(); iterator
                    .hasNext();) {
                IdentityLink identityLink = (IdentityLink) iterator.next();
                if (identityLink.getUserId() != null) {
                    User user = getUser(identityLink.getUserId());
                    if (user != null)
                        users.add(user);
                }
                if (identityLink.getGroupId() != null) {
                    // 根据组获得对应人员
                    List userList = identityService.createUserQuery()
                            .memberOfGroup(identityLink.getGroupId()).list();
                    if (userList != null && userList.size() > 0)
                        users.addAll(userList);
                }
            }

        }
        return users;
    }

    private User getUser(String userId) {
        User user = (User) identityService.createUserQuery().userId(userId)
                .singleResult();
        return user;
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
