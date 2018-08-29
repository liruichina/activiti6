package com.abc.springbootactiviti.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Map;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngines;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.history.HistoricTaskInstance;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.IdentityLink;
import org.activiti.engine.task.Task;
import org.activiti.engine.task.TaskQuery;
import org.apache.commons.lang3.StringUtils;

public class QueryService {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = LoggerFactory.getLogger(QueryService.class);

	@Autowired
    private ProcessEngine processEngine;
    //= ProcessEngines.getDefaultProcessEngine();

	@Autowired
    private RuntimeService runtimeService;
    //= processEngine.getRuntimeService();
	@Autowired
	private TaskService taskService;


    public static void main(String[] args){
        QueryService qd = new QueryService();
//        qd.findHistoryTask("zhangsan");
//        System.out.println("******************************************");
//        qd.findHistoryTask("lisi");

//        qd.isProcessEnd();
        String processDefinitionKey = "jianguanyi2";
        processDefinitionKey = "leave_houxuan";
        processDefinitionKey = "jianguanyi3";
        qd.findPersonalTaskList(processDefinitionKey,"");
//        qd.queryFinishedHistoryProcessInstance();
    }



    /**查询历史任务*/
//    @Test
    public void findHistoryTask(String name){

//        String taskAssignee = "zhangsan";
        String taskAssignee = name;
        List<HistoricTaskInstance> list = processEngine.getHistoryService()//与历史数据（历史表）相关的Service
                .createHistoricTaskInstanceQuery()//创建历史任务实例查询
                .taskAssignee(taskAssignee)//指定历史任务的办理人
                .orderByHistoricTaskInstanceDuration().desc()
                .list();
        if(list!=null && list.size()>0){
            for(HistoricTaskInstance pi:list){
                System.out.println("流程实例ID:"+pi.getId());//流程实例ID
                System.out.println("流程定义ID:"+pi.getProcessDefinitionId());//流程定义ID
                System.out.println("流程名称:"+pi.getName());
                System.out.println("代理人:"+pi.getAssignee());
                System.out.println("====================");
            }
        }
    }

    public void isProcessEnd(){
        String processInstanceId = "2501";
        //去正在执行的任务表查询
        ProcessInstance pi = processEngine.getRuntimeService()//表示正在执行的流程实例和执行对象
                .createProcessInstanceQuery()//创建流程实例查询
                .processInstanceId(processInstanceId)//使用流程实例ID查询
                .singleResult();
        if(pi==null){
            System.out.println("该流程实例走完");
        }
        else{
            System.out.println("该流程实例还没走完");
        }
//      输出：
//      该流程实例还没走完
    }

    //查询当前用户的待办任务
//    @Test
    public List<Task> findPersonalTaskList(String processDefinitionKey,String assignee){
//        //得到runtimeService
//        RuntimeService runtimeService = processEngine.getRuntimeService();
//        //查询任务使用TaskService
//        TaskService taskService = processEngine.getTaskService();
        //创建查询对象
        TaskQuery taskQuery = taskService.createTaskQuery();
        if(StringUtils.isNotBlank(assignee)){
            //设置查询条件
            taskQuery.taskAssignee(assignee);
        }

        //指定流程定义key，只查询某个流程的任务
        taskQuery.processDefinitionKey(processDefinitionKey);
        //获取查询列表
        List<Task> list = taskQuery.list();

        for (Task task : list) {

            //流程实例id
            String processInstanceId = task.getProcessInstanceId();
            //根据流程实例id找到流程实例对象
            ProcessInstance processInstance = runtimeService
                    .createProcessInstanceQuery()
                    .processInstanceId(processInstanceId)
                    .singleResult();
            //从流程实例对象获取bussinesskey
            String businessKey = processInstance.getBusinessKey();
            System.out.println("businessKey：" + businessKey);
            //根据businessKey查询业务系统，获取相关的业务信息
            System.out.println("流程实例id：" + task.getProcessInstanceId());
            System.out.println("任务id：" + task.getId());
            System.out.println("任务标识：" + task.getTaskDefinitionKey());
            System.out.println("任务名称：" + task.getName());
            if(task.getAssignee()!=null&&task.getAssignee().length()>0){
                System.out.println("任务负责人：" + task.getAssignee());
            }
            //获取候选人或者候选组相关信息
            List<IdentityLink> cdlist = taskService.getIdentityLinksForTask(task.getId());
            if(cdlist!=null&&cdlist.size()>0){
                for(IdentityLink idlink:cdlist){
                    System.out.println("任务候选人：" + idlink.getType()+"---"+idlink.getGroupId()+"---"+idlink.getUserId());
                }
            }
            System.out.println("任务所有人：" + task.getOwner());
            System.out.println("任务创建时间：" + task.getCreateTime());
            if(taskService.getVariable(task.getId(), "startdate")!=null){
                Map<String,Object> map = taskService.getVariables(task.getId());
                for(String s:map.keySet()){
                    System.out.println("Form表单参数"+s+"：" + map.get(s));
                }


//                String startdate =(String) taskService.getVariable(task.getId(), "startdate");//“name”对应前面set的variableName
//                System.out.println("Form表单参数startdate：" + startdate);
//                String enddate =(String) taskService.getVariable(task.getId(), "enddate");//“name”对应前面set的variableName
//                System.out.println("Form表单参数enddate：" + enddate);
//                String reason =(String) taskService.getVariable(task.getId(), "reason");//“name”对应前面set的variableName
//                System.out.println("Form表单参数reason：" + reason);
            }


//            //获取流程定义对象
//            ProcessDefinitionEntity processDefinition = getProcessDefinitionEntityByProcessDefinitionId(task
//                    .getProcessDefinitionId());
//
//            TaskDefinition taskDefinition = (TaskDefinition) processDefinition
//                    .getTaskDefinitions().get(task.getTaskDefinitionKey());
//            DefaultFormHandler defaultFormHandler = (DefaultFormHandler) taskDefinition
//                    .getTaskFormHandler();
//            Expression expression = defaultFormHandler.getFormKey();
//            String formkey = "";
//            if (expression != null)
//                formkey = expression.getExpressionText();
        }
        return list;
    }

//    /**
//     *
//     *  @CreateUser:xxxx
//     *  @ReturnType:ProcessDefinitionEntity
//     *  @param processDefinitionId
//     *  @return
//     *  @CreateDate:2014-12-10下午7:39:01
//     *  @UseFor    :获得 ProcessDefinitionEntity
//     */
//    private ProcessDefinitionEntity getProcessDefinitionEntityByProcessDefinitionId(
//            String processDefinitionId) {
//        ProcessDefinitionEntity processDefinitionEntity = (ProcessDefinitionEntity) ((RepositoryServiceImpl) repositoryService)
//                .getDeployedProcessDefinition(processDefinitionId);
//        return processDefinitionEntity;
//    }
//
//
//    /**
//     *
//     *  @CreateUser:xxxx
//     *  @ReturnType:String
//     *  @param processDefinitionId
//     *  @return
//     *  @CreateDate:2014-12-10下午7:38:46
//     *  @UseFor    :获得第一个节点的Form key
//     */
//    private String getProcessDefinitionFormData(String processDefinitionId) {
//        StartFormData startFormData = formService
//                .getStartFormData(processDefinitionId);
//        return startFormData.getFormKey();
//    }


//    /**
//     * 查询已完成的流程
//     */
//    public void queryFinishedHistoryProcessInstance() {
//
//        HistoryService historyService = processEngine.getHistoryService();
//
//        //创建历史流程实例，查询对象
//        HistoricProcessInstanceQuery historicProcessInstanceQuery = historyService.createHistoricProcessInstanceQuery();
//
//        //设置查询条件
//        //指定流程定义key，只查询某个业务流程的实例
//        String processDefinitionKey = "exclusiveGateWay";
//        historicProcessInstanceQuery.processDefinitionKey(processDefinitionKey);
//        //设置只查询已完成的
//        historicProcessInstanceQuery.finished();
//        //数据列表
//        List<HistoricProcessInstance> list = historicProcessInstanceQuery.list();
//
//        for (HistoricProcessInstance historicProcessInstance : list) {
//            System.out.println("===================");
//            System.out.println("流程实例所属流程定义id：" + historicProcessInstance.getProcessDefinitionId());
//            System.out.println("流程实例id："+ historicProcessInstance.getId());
//            System.out.println("业务标识：" + historicProcessInstance.getBusinessKey());
//            System.out.println("开始执行时间：" + historicProcessInstance.getStartTime());
//            System.out.println("结束执行时间：" + historicProcessInstance.getEndTime());
//            System.out.println("执行时长：" + historicProcessInstance.getDurationInMillis());
//        }
//    }

}
