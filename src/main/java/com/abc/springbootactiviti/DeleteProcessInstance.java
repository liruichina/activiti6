package com.abc.springbootactiviti;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngines;
import org.activiti.engine.TaskService;
import org.activiti.engine.impl.RepositoryServiceImpl;
import org.activiti.engine.impl.persistence.entity.ProcessDefinitionEntity;
import org.activiti.engine.impl.persistence.entity.TaskEntity;
//import org.activiti.engine.impl.pvm.PvmTransition;
//import org.activiti.engine.impl.pvm.process.ActivityImpl;
//import org.activiti.engine.impl.pvm.process.ProcessDefinitionImpl;
//import org.activiti.engine.impl.pvm.process.TransitionImpl;
import org.codehaus.groovy.util.StringUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DeleteProcessInstance {

    public static void main(String[] args) {

    }

//    ProcessEngine defaultProcessEngine = ProcessEngines.getDefaultProcessEngine();
//    TaskService taskService = defaultProcessEngine.getTaskService();
//
//    /**
//     * @param argument
//     * @throws Exception
//     */
//    @Override
//    public void modifyProcess(IRequestArgumentBody argument) {
//        String taskId = argument.getStringValue("taskId");
//        ActivityImpl endActivity = null;
//        try
//        {
//            endActivity = findActivitiImpl(taskId, "end");
//            commitProcess(taskId, null, endActivity.getId());
//        }
//        catch (Exception e)
//        {
//            logger.error("serviceImpl---流程中止失败失败" + e.getMessage(), e.getCause());
//            throw new ServiceRuntimeException(e.getCause(), "tips.oa.service.break.fail");
//        }
//    }
//
//    private void commitProcess(String taskId, Map<String, Object> variables,
//                               String activityId) throws Exception {
//        if (variables == null) {
//            variables = new HashMap<String, Object>();
//        }
//        // 跳转节点为空，默认提交操作
//        if (StringUtil.isNullOrEmpty(activityId)) {
//            taskService.complete(taskId, variables);
//        } else {// 流程转向操作
//            turnTransition(taskId, activityId, variables);
//        }
//    }
//
//    /**
//     *
//     * @param taskId
//     * @param activityId
//     * @param variables
//     * @throws Exception
//     */
//    private void turnTransition(String taskId, String activityId,
//                                Map<String, Object> variables) throws Exception {
//        // 当前节点
//        ActivityImpl currActivity = findActivitiImpl(taskId, null);
//        // 清空当前流向
//        List<PvmTransition> oriPvmTransitionList = clearTransition(currActivity);
//        // 创建新流向
//        TransitionImpl newTransition = currActivity.createOutgoingTransition();
//        // 目标节点
//        ActivityImpl pointActivity = findActivitiImpl(taskId, activityId);
//        // 设置新流向的目标节点
//        newTransition.setDestination(pointActivity);
//        // 执行转向任务
//        taskService.complete(taskId, variables);
//        //删除子表数据
//        this.taskExtService.deleteTaskById(taskId);
//        // 删除目标节点新流入
//        pointActivity.getIncomingTransitions().remove(newTransition);
//        // 还原以前流向
//        restoreTransition(currActivity, oriPvmTransitionList);
//    }
//
//    /**
//     *
//     * @param activityImpl
//     * @return List<PvmTransition>
//     */
//    private List<PvmTransition> clearTransition(ActivityImpl activityImpl) {
//        // 存储当前节点所有流向临时变量
//        List<PvmTransition> oriPvmTransitionList = new ArrayList<PvmTransition>();
//        // 获取当前节点所有流向，存储到临时变量，然后清空
//        List<PvmTransition> pvmTransitionList = activityImpl
//                .getOutgoingTransitions();
//        for (PvmTransition pvmTransition : pvmTransitionList) {
//            oriPvmTransitionList.add(pvmTransition);
//        }
//        pvmTransitionList.clear();
//
//        return oriPvmTransitionList;
//    }
//
//    /**
//     *
//     * @param activityImpl
//     * @param oriPvmTransitionList
//     */
//    private void restoreTransition(ActivityImpl activityImpl,
//                                   List<PvmTransition> oriPvmTransitionList) {
//        // 清空现有流向
//        List<PvmTransition> pvmTransitionList = activityImpl
//                .getOutgoingTransitions();
//        pvmTransitionList.clear();
//        // 还原以前流向
//        for (PvmTransition pvmTransition : oriPvmTransitionList) {
//            pvmTransitionList.add(pvmTransition);
//        }
//    }
//
//    private ActivityImpl findActivitiImpl(String taskId, String activityId)
//            throws Exception {
//        // 取得流程定义
//        ProcessDefinitionEntity processDefinition = findProcessDefinitionEntityByTaskId(taskId);
//
//        // 获取当前活动节点ID
//        if (StringUtil.isNullOrEmpty(activityId)) {
//            activityId = findTaskById(taskId).getTaskDefinitionKey();
//        }
//
//        // 根据流程定义，获取该流程实例的结束节点
//        if (activityId.toLowerCase().equals("end")) {
//            for (ActivityImpl activityImpl : processDefinition.getActivities()) {
//                List<PvmTransition> pvmTransitionList = activityImpl
//                        .getOutgoingTransitions();
//                if (pvmTransitionList.isEmpty()) {
//                    return activityImpl;
//                }
//            }
//        }
//
//        // 根据节点ID，获取对应的活动节点
//        ActivityImpl activityImpl = ((ProcessDefinitionImpl) processDefinition)
//                .findActivity(activityId);
//        return activityImpl;
//    }
//
//    private ProcessDefinitionEntity findProcessDefinitionEntityByTaskId(
//            String taskId) throws Exception {
//        // 取得流程定义
//        ProcessDefinitionEntity processDefinition = (ProcessDefinitionEntity) ((RepositoryServiceImpl) repositoryService)
//                .getDeployedProcessDefinition(findTaskById(taskId)
//                        .getProcessDefinitionId());
//        if (processDefinition == null) {
//            throw new Exception("流程定义未找到!");
//        }
//
//        return processDefinition;
//    }
//
//    private TaskEntity findTaskById(String taskId) throws Exception {
//        TaskEntity task = (TaskEntity) taskService.createTaskQuery().taskId(
//                taskId).singleResult();
//        if (task == null) {
//            throw new Exception("任务实例未找到!");
//        }
//        return task;
//    }

}
