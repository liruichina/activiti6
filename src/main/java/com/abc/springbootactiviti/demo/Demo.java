package com.abc.springbootactiviti.demo;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Multimap;
import org.activiti.engine.*;
import org.activiti.engine.identity.User;
import org.activiti.engine.task.IdentityLink;
import org.activiti.engine.task.Task;

import java.util.*;

/**
 * 模拟自动执行全部流程的demo
 * 方便测试，提高测试效率
 */
public class Demo {

    ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
    //得到runtimeService
    RuntimeService runtimeService = processEngine.getRuntimeService();
    //查询任务使用TaskService
    TaskService taskService = processEngine.getTaskService();

    IdentityService identityService = processEngine.getIdentityService();



    public static void main(String[] args) {
        Demo demo = new Demo();
        //生成组与用户的关系
        demo.createGroupUser();
        for(int i=0;i<2;i++){
            demo.process();
        }
    }


    StartDeployementDemo startDemo = new StartDeployementDemo();

    ProcessDeployementDemo processDemo = new ProcessDeployementDemo();

    QueryDemo queryDemo = new QueryDemo();

    Multimap<String, String> groupmap = ArrayListMultimap.create();
    Map<String,String> userGroupMap = Maps.newHashMap();

    private String processDefinitionKey = "jianguanyi2";
    private String zhiDanUserId = "user03";

    List<String> assigneelist = Lists.newArrayList();

    //模拟异常情况，0不测试异常，1测试异常情况
    String ExceptionType = "1";

    int round=0;

    public void process(){
        round=0;
        //step1，启动流程
        Map<String, Object> variables= Maps.newHashMap();
        variables.put("group1","group0");
        //带有参数的启动
        startDemo.startProcessInstance(processDefinitionKey,variables);



        //Step2，查询特定用户的待办任务，包括已分配的任务和作为候选人的任务，或者所在候选组的任务
        //制单流程
//        String userId= "user02";
        //随机生成制单处理人
        zhiDanUserId = this.getZhiDanUser();
        //初始化组和用户的对应关系
//        Multimap<String, String> groupmap = this.createGroupUser();

        //获取用户与组的关系
        groupmap.forEach((k,v)->userGroupMap.put(v,k));

        Map<String,Object>variables1 = new HashMap();//设置流程变量

        List<Task> list = queryDemo.findPersonalTaskList(processDefinitionKey,zhiDanUserId);
        assigneelist = this.getFuheUser();
        //被分配人
        if(list!=null&&list.size()>0){
            System.out.println("作为被分配人****************************"+list.size());
            for(Task task:list){
                Map<String, Object> variables0= Maps.newHashMap();
                variables0.put("option",getZhiDanOption());
                variables0.put("assigneelist", assigneelist);
                processDemo.complete(task.getId(),variables0);
                sleepRandomTime();
            }
        }
        List<Task> list2 = queryDemo.findPersonalTaskList(processDefinitionKey,"");
        if(list2!=null&&list2.size()>0){
            for(Task task:list2){
              if(getCandidateTasks(task.getId(),zhiDanUserId)){
                  System.out.println("作为候选人或者组****************************"+task.getId()+"---"+task.getName());
                  Map<String, Object> variables0= Maps.newHashMap();
                  variables0.put("option",getZhiDanOption());
                  variables0.put("assigneelist", assigneelist);
                  processDemo.mytaskClaimAComplete(task.getId(), zhiDanUserId, variables0);
                  sleepRandomTime();
              }
            }
        }

//        //复核流程
//        for(String checkUser: assigneelist){
//            check( processDefinitionKey, checkUser);
//        }

    }

    private int getZhiDanOption(){
        //复核人超过3人并且包含user13时，强制终止流程
        if("1".equals(ExceptionType)&&assigneelist.size()>4&&(assigneelist.contains("user16"))){
            //10强制结束
            return 10;
        }
        return 0;
    }

    private int getFuheOption(String userId){
        //0正常结束，1审核不通过，10强制结束，2回退到制单
        //复核人超过3人并且包含user13时，强制终止流程
        if("1".equals(ExceptionType)&&assigneelist.size()>=3){
            if("user12".equals(userId)){
                return 1;
            }
            if("user11".equals(userId)){
                return 10;
            }
//            if("user15".equals(userId)&&round==0){
//                round=1;
//                return 2;
//            }
//            return 2;
        }
        return 0;
    }

    private void check(String processDefinitionKey,String userId){

        List<Task> list = queryDemo.findPersonalTaskList(processDefinitionKey,userId);
        //被分配人
        if(list!=null&&list.size()>0){
            System.out.println("作为被分配人****************************"+list.size());
            for(Task task:list){
                Map<String, Object> variables0= Maps.newHashMap();
                variables0.put("option",getFuheOption(userId));
                processDemo.complete(task.getId(),variables0);
                sleepRandomTime();
            }
        }
        List<Task> list2 = queryDemo.findPersonalTaskList(processDefinitionKey,"");
        if(list2!=null&&list2.size()>0){
            for(Task task:list2){
                if(getCandidateTasks(task.getId(),userId)){
                    System.out.println("作为候选人或者组****************************"+task.getId()+"---"+task.getName());
                    Map<String, Object> variables0= Maps.newHashMap();
                    variables0.put("option",getFuheOption(userId));
                    processDemo.mytaskClaimAComplete(task.getId(), userId, variables0);
                    sleepRandomTime();
                }
            }
        }
    }


    private Multimap<String, String> createGroupUser(){
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

    private String getZhiDanUser(){
        List<String> list = (List)groupmap.get("group0");
        int i = new Random().nextInt(list.size());
        return list.get(i);
    }

    private List<String> getFuheUser(){
        List<String> list = (List)groupmap.get("group1");
        int length = new Random().nextInt(list.size());
        if(length<2){
            length=2;
        }
        Collections.shuffle(list);
        List<String> result = Lists.newArrayList();
        for(int i=0;i<length;i++){
            result.add(list.get(i));
        }

         return result;
    }

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
