package com.abc.springbootactiviti.demo;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngines;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.repository.ProcessDefinition;

public class DeleteDeployementDemo {

    public static void main(String[] args) {
        DeleteDeployementDemo dd = new DeleteDeployementDemo();
        dd.deleteProcessDefinition();
    }

    /**流程引擎（核心对象），默认加载类路径下命名为activiti.cfg.xml*/
    ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();

    public void deleteProcessDefinition(){
        //得到repositoryService
        RepositoryService repositoryService = processEngine
                .getRepositoryService();
        //根据流程定义id查询部署id
        String processDefinitionId = "jianguanyi2:2:22504";
        ProcessDefinition processDefinition = repositoryService
                .createProcessDefinitionQuery()
                .processDefinitionId(processDefinitionId)
                .singleResult();
        //流程定义所属部署id
        String deploymentId = processDefinition.getDeploymentId();
        //1.根据流程部署id删除这一次部署的所有流程定义
        //建议一次部署只部署一个流程，根据流程部署id删除一个流程的定义
        //约束：如果该流程定义没有启动流程实例可以删除，如果该流程定义以及启动流程实例，不允许删除，如果删除就抛出异常
        repositoryService.deleteDeployment(deploymentId);

        //2.级联删除：不管该流程定义是否启动流程实例（是否使用），通过级联删除将该流程定义及相关的信息全部删除
        //一般情况下不适用级联删除，一般情况下对流程定义执行暂停操作
        //特殊情况下需要删除流程定义及相关的信息，就要使用级联删除，删除的功能给超级管理员使用
        //repositoryService.deleteDeployment(deploymentId,true);
    }

    public void deleteDeployment() {
        String deploymentId = "15001";
        // 第二个参数代表级联操作
        processEngine.getRepositoryService().deleteDeployment(deploymentId, true);
        // 删除所有相关的activiti信息
    }

}
