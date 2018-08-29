/**
 * 
 */
package com.abc.springbootactiviti.service;

import java.util.List;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.history.HistoricActivityInstance;
import org.activiti.engine.history.HistoricTaskInstance;
import org.springframework.beans.factory.annotation.Autowired;

import com.abc.springbootactiviti.beans.PengdingTask;

/**
 * @author LiRui
 *
 */
public class QueryActivitiTaskServiceImpl implements QueryActivitiTaskService {
	
	@Autowired
	private ProcessEngine processEngine;

	/* (non-Javadoc)
	 * @see com.abc.springbootactiviti.service.QueryActivitiTaskService#queryPengingTasksByUserId(java.lang.String)
	 */
	@Override
	public List<PengdingTask> queryPengingTasksByUserId(String userId) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see com.abc.springbootactiviti.service.QueryActivitiTaskService#queryPengingTasksByGroup(java.util.List)
	 */
	@Override
	public List<PengdingTask> queryPengingTasksByGroup(List<String> group) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see com.abc.springbootactiviti.service.QueryActivitiTaskService#queryPengingTasks(java.lang.String, java.util.List)
	 */
	@Override
	public List<PengdingTask> queryPengingTasks(String userId, List<String> group) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see com.abc.springbootactiviti.service.QueryActivitiTaskService#queryPengingTasks(java.lang.String)
	 */
	@Override
	public List<PengdingTask> queryPengingTasks(String userId) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see com.abc.springbootactiviti.service.QueryActivitiTaskService#queryCompleteTasks(java.lang.String)
	 */
	@Override
	public List<HistoricActivityInstance> queryCompleteTasks(String userId) {
        String taskAssignee = "";
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
        return null;
	}

	/* (non-Javadoc)
	 * @see com.abc.springbootactiviti.service.QueryActivitiTaskService#queryCompleteFinishedTasks(java.lang.String)
	 */
	@Override
	public List<HistoricActivityInstance> queryCompleteFinishedTasks(String userId) {
		// TODO Auto-generated method stub
		return null;
	}

}
