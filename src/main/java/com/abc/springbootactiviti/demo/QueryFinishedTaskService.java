package com.abc.springbootactiviti.demo;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.activiti.engine.HistoryService;
import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngines;
import org.activiti.engine.history.HistoricActivityInstance;
import org.activiti.engine.history.HistoricProcessInstance;
import org.activiti.engine.history.HistoricProcessInstanceQuery;
import org.activiti.engine.history.HistoricTaskInstance;
import org.activiti.engine.history.HistoricVariableInstance;
import org.apache.commons.io.FileUtils;

import com.google.common.collect.Lists;

/**
 * 查询已完成的任务
 */
public class QueryFinishedTaskService {

    StringBuffer sb = new StringBuffer();

    public static void main(String[] args) {
        QueryFinishedTaskService qdt = new QueryFinishedTaskService();
        //流程定义ID
        List<String> keys = Lists.newArrayList("jianguanyi2","jianguanyi3","leave_houxuan");
//        String processDefinitionKey = "jianguanyi2";
        for(String processDefinitionKey:keys) {
            List<HistoricProcessInstance> list = qdt.queryFinishedHistoryProcessInstance(processDefinitionKey,"",true);
            qdt.printTrace(list);
            qdt.print(processDefinitionKey);
        }

    }

    private void print(String name){
        File file = new File("E:\\",name+".txt");
        if(file.exists()){
            file.delete();
        }
        try {
            FileUtils.write(file, sb.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();

    /**
     * 查询已完成的流程
     */
    public List<HistoricProcessInstance> queryFinishedHistoryProcessInstance(String processDefinitionKey,
                                                                             String instanceId,boolean isFinished) {

        HistoryService historyService = processEngine.getHistoryService();

        //创建历史流程实例，查询对象
        HistoricProcessInstanceQuery historicProcessInstanceQuery = historyService.createHistoricProcessInstanceQuery();

        //设置查询条件
        //指定流程定义key，只查询某个业务流程的实例
//        String processDefinitionKey = "exclusiveGateWay";
        historicProcessInstanceQuery.processDefinitionKey(processDefinitionKey);
        if(instanceId!=null&&instanceId.length()>0){
            historicProcessInstanceQuery.processInstanceId(instanceId);
        }
        if(isFinished){
            //设置只查询已完成的
            historicProcessInstanceQuery.finished();
        }
        //数据列表
        List<HistoricProcessInstance> list = historicProcessInstanceQuery.orderByProcessInstanceStartTime().asc().list();

        return list;
    }

    private void printTrace(List<HistoricProcessInstance> list){
        sb.append("共有已完成流程------"+list.size()+"\n");
        for (HistoricProcessInstance historicProcessInstance : list) {
            StringBuffer temp = new StringBuffer();
            temp.append("===================\n");
            temp.append("流程实例所属流程定义id：" + historicProcessInstance.getProcessDefinitionId()+"\n");
            temp.append("流程实例id："+ historicProcessInstance.getId()+"\n");
            temp.append("业务标识：" + historicProcessInstance.getBusinessKey()+"\n");
            temp.append("开始执行时间：" + historicProcessInstance.getStartTime()+"\n");
            temp.append("结束执行时间：" + historicProcessInstance.getEndTime()+"\n");
            temp.append("执行时长：" + historicProcessInstance.getDurationInMillis()+"\n");
            System.out.println(temp);
            temp.append(temp);

            queryHistoricActivitiInstance(historicProcessInstance.getId());
        }
    }

    /**
     * 某一次流程执行了多少步
     */
    public void queryHistoricActivitiInstance(String processInstanceId) {
//        String processInstanceId = "27501";
        List<HistoricActivityInstance> list = processEngine.getHistoryService()
                .createHistoricActivityInstanceQuery()
                .processInstanceId(processInstanceId)
//                .executionId(executionId)
                .list();

        if (list != null && list.size() > 0) {
            int i=0;
            for (HistoricActivityInstance hai : list) {
                i++;
                StringBuffer temp = new StringBuffer();
                temp.append("执行步骤"+i+"======="+"\n");
                temp.append(hai.getId()+"\t");
                temp.append("步骤ID：" + hai.getActivityId()+"\t");
                temp.append("步骤名称：" + hai.getActivityName()+"\t");
                temp.append("执行人：" + hai.getAssignee()+"\t");
                temp.append("执行开始时间：" + hai.getStartTime()+"\n");
                System.out.println(temp);
                sb.append(temp);
            }
            System.out.println("*****************************");
            sb.append("*****************************\n");
        }
    }

    /**
     * 某一次流程执行了多少步
     */
    public void queryHistoricActivitiInstance(String processInstanceId,String executionId) {
//        String processInstanceId = "27501";
        List<HistoricActivityInstance> list = processEngine.getHistoryService()
                .createHistoricActivityInstanceQuery()
                .processInstanceId(processInstanceId)
                .executionId(executionId)
                .list();

        if (list != null && list.size() > 0) {
            int i=0;
            for (HistoricActivityInstance hai : list) {
                i++;
                System.out.println("执行步骤"+i+"=======");
                System.out.print(hai.getId()+"\t");
                System.out.print("步骤ID：" + hai.getActivityId()+"\t");
                System.out.print("步骤名称：" + hai.getActivityName()+"\t");
                System.out.println("执行人：" + hai.getAssignee());
            }
            System.out.println("*****************************");
        }
    }

    /**
     * 某一次流程的执行经历的多少任务
     */
    public void queryHistoricTask(String processInstanceId) {
//        String processInstanceId = "27501";
        List<HistoricTaskInstance> list = processEngine.getHistoryService()
                .createHistoricTaskInstanceQuery()
                .processInstanceId(processInstanceId)
                .list();
        if (list != null && list.size() > 0) {
            for (HistoricTaskInstance hti : list) {
                System.out.print("taskId:" + hti.getId()+"，");
                System.out.print("name:" + hti.getName()+"，");
                System.out.print("pdId:" + hti.getProcessDefinitionId()+"，");
                System.out.print("assignee:" + hti.getAssignee()+"，");
            }
        }
    }

    /**
     * 某一次流程的执行时设置的流程变量
     */
    public void queryHistoricVariables(String processInstanceId) {
//        String processInstanceId = "37501";
        List<HistoricVariableInstance> list = processEngine.getHistoryService()
                .createHistoricVariableInstanceQuery()
                .processInstanceId(processInstanceId)
                .list();
        if(list != null && list.size()>0){
            for(HistoricVariableInstance hvi : list){
                System.out.print("piId:"+hvi.getProcessInstanceId()+"，");
                System.out.print("variablesName:"+hvi.getVariableName()+"，");
                System.out.println("variablesValue:"+hvi.getValue()+";");
            }
        }
    }
}

