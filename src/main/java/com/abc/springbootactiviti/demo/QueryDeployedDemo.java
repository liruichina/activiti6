
package com.abc.springbootactiviti.demo;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngines;
import org.activiti.engine.repository.ProcessDefinition;

import java.util.List;

/**
 * 查询已部署的工作量
 */
public class QueryDeployedDemo {

    public static void main(String[] args) {
        QueryDeployedDemo demo = new QueryDeployedDemo();
        demo.query();
    }

    ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();

    public void query(){
        List<ProcessDefinition> pdList = processEngine.getRepositoryService()
                .createProcessDefinitionQuery()
                .list();
        for(ProcessDefinition pd:pdList){
            System.out.print("name:"+pd.getName()+",\t");
            System.out.print("deploymentId:"+pd.getDeploymentId()+",\t");
            System.out.print("key:"+pd.getKey()+",\t");
            System.out.print("version:"+pd.getVersion()+",\t");
            System.out.println("pdid:"+pd.getId());
        }
    }


}
