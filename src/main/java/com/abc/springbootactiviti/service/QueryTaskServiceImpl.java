package com.abc.springbootactiviti.service;

import java.util.List;
import java.util.Map;

import org.activiti.engine.ManagementService;
import org.activiti.engine.ProcessEngine;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.history.HistoricActivityInstance;
import org.activiti.engine.impl.cmd.AbstractCustomSqlExecution;
import org.activiti.engine.impl.cmd.CustomSqlExecution;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.IdentityLink;
import org.activiti.engine.task.Task;
import org.activiti.engine.task.TaskQuery;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.abc.springbootactiviti.beans.PengdingTask;
import com.abc.springbootactiviti.mapper.TaskQueryMapper;
import com.google.common.collect.Lists;

@Service
public class QueryTaskServiceImpl implements QueryTaskService {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = LoggerFactory.getLogger(QueryTaskServiceImpl.class);

//    @Autowired
//    private TaskQueryMapper taskQueryMapper;

    @Autowired
    ManagementService managementService;

    @Autowired
    ProcessEngine processEngine;
    //= ProcessEngines.getDefaultProcessEngine();
    @Autowired
    private RuntimeService runtimeService;
    @Autowired
    private TaskService taskService;
    
    
    /**
     * 根据用户名信息，获取被分配到的待办任务
   * @param userId 用户相关信息
   * @return
   */
  public List<Task> queryAssignedPengingTasksByUserId(String userId){
      //创建查询对象
      TaskQuery taskQuery = taskService.createTaskQuery().taskAssignee(userId);
      //获取查询列表
      List<Task> list = taskQuery.list();

//      for (Task task : list) {
//
//          //流程实例id
//          String processInstanceId = task.getProcessInstanceId();
//          //根据流程实例id找到流程实例对象
//          ProcessInstance processInstance = runtimeService
//                  .createProcessInstanceQuery()
//                  .processInstanceId(processInstanceId)
//                  .singleResult();
//          //从流程实例对象获取bussinesskey
//          //对于自定义表单来说启动的时候会传入businessKey作为业务和流程的关联属性
//          //对于动态表单来说不需要使用businessKey关联，因为所有的数据都保存在引擎的表中
//          //对于外部表单来说businessKey是可选的，但是一般不会为空，和自定义表单类似
//          String businessKey = processInstance.getBusinessKey();
//          System.out.println("businessKey：" + businessKey);
//          //根据businessKey查询业务系统，获取相关的业务信息
//          System.out.println("流程实例id：" + task.getProcessInstanceId());
//          System.out.println("任务id：" + task.getId());
//          System.out.println("任务标识：" + task.getTaskDefinitionKey());
//          System.out.println("任务名称：" + task.getName());
//          if(task.getAssignee()!=null&&task.getAssignee().length()>0){
//              System.out.println("任务负责人：" + task.getAssignee());
//          }
//          //获取候选人或者候选组相关信息
//          List<IdentityLink> cdlist = taskService.getIdentityLinksForTask(task.getId());
//          if(cdlist!=null&&cdlist.size()>0){
//              for(IdentityLink idlink:cdlist){
//                  System.out.println("任务候选人：" + idlink.getType()+"---"+idlink.getGroupId()+"---"+idlink.getUserId());
//              }
//          }
//          System.out.println("任务所有人：" + task.getOwner());
//          System.out.println("任务创建时间：" + task.getCreateTime());
//          if(taskService.getVariable(task.getId(), "startdate")!=null){
//              Map<String,Object> map = taskService.getVariables(task.getId());
//              for(String s:map.keySet()){
//                  System.out.println("Form表单参数"+s+"：" + map.get(s));
//              }
//
//
////              String startdate =(String) taskService.getVariable(task.getId(), "startdate");//“name”对应前面set的variableName
////              System.out.println("Form表单参数startdate：" + startdate);
////              String enddate =(String) taskService.getVariable(task.getId(), "enddate");//“name”对应前面set的variableName
////              System.out.println("Form表单参数enddate：" + enddate);
////              String reason =(String) taskService.getVariable(task.getId(), "reason");//“name”对应前面set的variableName
////              System.out.println("Form表单参数reason：" + reason);
//          }
//
//
////          //获取流程定义对象
////          ProcessDefinitionEntity processDefinition = getProcessDefinitionEntityByProcessDefinitionId(task
////                  .getProcessDefinitionId());
////
////          TaskDefinition taskDefinition = (TaskDefinition) processDefinition
////                  .getTaskDefinitions().get(task.getTaskDefinitionKey());
////          DefaultFormHandler defaultFormHandler = (DefaultFormHandler) taskDefinition
////                  .getTaskFormHandler();
////          Expression expression = defaultFormHandler.getFormKey();
////          String formkey = "";
////          if (expression != null)
////              formkey = expression.getExpressionText();
//      }
      return list;
  }
  
	/**
	 * 根据用户名信息，获取作为候选人的待办任务
	 * 
	 * @param userId 用户相关信息
	 * @return
	 */
	public List<Task> queryCandidatePengingTasksByUserId(String userId){
	      //创建查询对象
	      TaskQuery taskQuery = taskService.createTaskQuery().taskCandidateUser(userId);
	      //获取查询列表
	      List<Task> list = taskQuery.list();

//	      for (Task task : list) {
//
//	          //流程实例id
//	          String processInstanceId = task.getProcessInstanceId();
//	          //根据流程实例id找到流程实例对象
//	          ProcessInstance processInstance = runtimeService
//	                  .createProcessInstanceQuery()
//	                  .processInstanceId(processInstanceId)
//	                  .singleResult();
//	          //从流程实例对象获取bussinesskey
//	          //对于自定义表单来说启动的时候会传入businessKey作为业务和流程的关联属性
//	          //对于动态表单来说不需要使用businessKey关联，因为所有的数据都保存在引擎的表中
//	          //对于外部表单来说businessKey是可选的，但是一般不会为空，和自定义表单类似
//	          String businessKey = processInstance.getBusinessKey();
//	          System.out.println("businessKey：" + businessKey);
//	          //根据businessKey查询业务系统，获取相关的业务信息
//	          System.out.println("流程实例id：" + task.getProcessInstanceId());
//	          System.out.println("任务id：" + task.getId());
//	          System.out.println("任务标识：" + task.getTaskDefinitionKey());
//	          System.out.println("任务名称：" + task.getName());
//	          if(task.getAssignee()!=null&&task.getAssignee().length()>0){
//	              System.out.println("任务负责人：" + task.getAssignee());
//	          }
//	          //获取候选人或者候选组相关信息
//	          List<IdentityLink> cdlist = taskService.getIdentityLinksForTask(task.getId());
//	          if(cdlist!=null&&cdlist.size()>0){
//	              for(IdentityLink idlink:cdlist){
//	                  System.out.println("任务候选人：" + idlink.getType()+"---"+idlink.getGroupId()+"---"+idlink.getUserId());
//	              }
//	          }
//	          System.out.println("任务所有人：" + task.getOwner());
//	          System.out.println("任务创建时间：" + task.getCreateTime());
//	          if(taskService.getVariable(task.getId(), "startdate")!=null){
//	              Map<String,Object> map = taskService.getVariables(task.getId());
//	              for(String s:map.keySet()){
//	                  System.out.println("Form表单参数"+s+"：" + map.get(s));
//	              }
//
//
////	              String startdate =(String) taskService.getVariable(task.getId(), "startdate");//“name”对应前面set的variableName
////	              System.out.println("Form表单参数startdate：" + startdate);
////	              String enddate =(String) taskService.getVariable(task.getId(), "enddate");//“name”对应前面set的variableName
////	              System.out.println("Form表单参数enddate：" + enddate);
////	              String reason =(String) taskService.getVariable(task.getId(), "reason");//“name”对应前面set的variableName
////	              System.out.println("Form表单参数reason：" + reason);
//	          }
//
//
////	          //获取流程定义对象
////	          ProcessDefinitionEntity processDefinition = getProcessDefinitionEntityByProcessDefinitionId(task
////	                  .getProcessDefinitionId());
//	//
////	          TaskDefinition taskDefinition = (TaskDefinition) processDefinition
////	                  .getTaskDefinitions().get(task.getTaskDefinitionKey());
////	          DefaultFormHandler defaultFormHandler = (DefaultFormHandler) taskDefinition
////	                  .getTaskFormHandler();
////	          Expression expression = defaultFormHandler.getFormKey();
////	          String formkey = "";
////	          if (expression != null)
////	              formkey = expression.getExpressionText();
//	      }
	      return list;
	}
	
	/**
	 * 根据用户组信息，获取作为候选人的待办任务
	 * 
	 * @param userId 用户相关信息
	 * @return
	 */
	public List<Task> queryCandidatePengingTasksByGroup(String group){
	      //创建查询对象
	      TaskQuery taskQuery = taskService.createTaskQuery().taskCandidateGroup(group);
	      //获取查询列表
	      List<Task> list = taskQuery.list();
	      return list;
	}
	
	/**
	 * 根据用户组信息，获取作为候选人的待办任务
	 * 
	 * @param userId 用户相关信息
	 * @return
	 */
	public List<Task> queryCandidatePengingTasksByGroup(List<String> groups){
	      //创建查询对象
	      TaskQuery taskQuery = taskService.createTaskQuery().taskCandidateGroupIn(groups);
	      //获取查询列表
	      List<Task> list = taskQuery.list();
	      return list;
	}

    /**
     * 根据用户名信息，获取被分配到的待办任务或者作为候选人之一的待办任务
     *
     * @param userId
     * @return
     */
    @Override
    public List<PengdingTask> queryPengingTasksByUserId(String userId) {
        //方法一。直接调用mybatis的接口处理
//        return taskQueryMapper.getPengdingTasksByUserId(userId);
        //方法二，调用mybatis的官方封装好的接口，但是目前还没发现有什么明显的优点
        //并且需要配置spring.activiti.customMybatisXMLMappers
        CustomSqlExecution<TaskQueryMapper, List<PengdingTask>> customSqlExecution =
                new AbstractCustomSqlExecution<TaskQueryMapper, List<PengdingTask>>(TaskQueryMapper.class) {
                    public List<PengdingTask> execute(TaskQueryMapper customMapper) {
                        List<PengdingTask> tasks = customMapper.getPengdingTasksByUserId(userId);
                        return tasks;
                    }
                };

        List<PengdingTask> tasks = managementService.executeCustomSql(customSqlExecution);
        return tasks;
    }

    /**
     * 根据用户组信息，获取该组成员的代办任务
     *
     * @param group
     * @return
     */
    @Override
    public List<PengdingTask> queryPengingTasksByGroup(List<String> group) {
//        return taskQueryMapper.getPengdingTasksByGroup(group);
        CustomSqlExecution<TaskQueryMapper, List<PengdingTask>> customSqlExecution =
                new AbstractCustomSqlExecution<TaskQueryMapper, List<PengdingTask>>(TaskQueryMapper.class) {
                    public List<PengdingTask> execute(TaskQueryMapper customMapper) {
                        List<PengdingTask> tasks = customMapper.getPengdingTasksByGroup(group);
                        return tasks;
                    }
                };

        List<PengdingTask> tasks = managementService.executeCustomSql(customSqlExecution);
        return tasks;
    }

    /**
     * 根据用户名信息，获取被分配到的待办任务或者作为候选人之一的待办任务。
     * 并根据人员所在的组信息，获取该组成员的代办任务。
     * 将结果集合并，作为该用户的待办任务
     *
     * @param userId
     * @param group
     * @return
     */
    @Override
    public List<PengdingTask> queryPengingTasks(String userId, List<String> group) {
        List<PengdingTask> list1 = this.queryPengingTasksByUserId(userId);
        if(group!=null&&group.size()>0){
            List<PengdingTask> list2 = this.queryPengingTasksByGroup(group);
            list1.addAll(list2);
        }
        return list1;
    }

    /**
     * 根据用户名信息，获取被分配到的待办任务或者作为候选人之一的待办任务。
     * 并通过人员信息获取到人员所在的组信息，进而获取该组成员的代办任务。
     * 将结果集合并，作为该用户的待办任务
     *
     * @param userId
     * @return
     */
    @Override
    public List<PengdingTask> queryPengingTasks(String userId) {
        //需要替换为实际的获取逻辑
        List<String> group = Lists.newArrayList();
        return this.queryPengingTasks(userId,group);
    }

    /**
     * 根据用户名信息，获取被已被该用户处理过的任务
     * @param userId   用户信息
     * @return
     */
    @Override
    public List<HistoricActivityInstance> queryCompleteTasks(String userId){
        List<HistoricActivityInstance>  list=processEngine.getHistoryService() // 历史相关Service
                .createHistoricActivityInstanceQuery() // 创建历史活动实例查询
                .taskAssignee(userId)
                .list();
        return list;
    }

    /**
     * 根据用户名信息，获取被已被该用户处理过的且已走完完成流程的任务
     *
     * @param  userId   用户信息
     * @return
     */
    @Override
    public List<HistoricActivityInstance> queryCompleteFinishedTasks(String userId) {
        List<HistoricActivityInstance>  list=processEngine.getHistoryService() // 历史相关Service
                .createHistoricActivityInstanceQuery() // 创建历史活动实例查询
                .taskAssignee(userId)
                .finished()
                .list();
        return list;
    }
}
