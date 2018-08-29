package com.abc.springbootactiviti.service;

import java.util.List;

import org.activiti.engine.ManagementService;
import org.activiti.engine.ProcessEngine;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.history.HistoricActivityInstance;
import org.activiti.engine.history.HistoricActivityInstanceQuery;
import org.activiti.engine.task.Task;
import org.activiti.engine.task.TaskQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.abc.springbootactiviti.beans.Page;
import com.abc.springbootactiviti.beans.PengdingTask;

@Service
public class QueryByPageTaskServiceImpl implements QueryByPageTaskService {

	@Autowired
	ManagementService managementService;

	@Autowired
	ProcessEngine processEngine;
	// = ProcessEngines.getDefaultProcessEngine();
	@Autowired
	private RuntimeService runtimeService;
	@Autowired
	private TaskService taskService;

	@Override
	public Page<Task> queryAssignedPengingTasksByUserId(String userId, Page<Task> page) {
		// 创建查询对象
		TaskQuery taskQuery = taskService.createTaskQuery().taskAssignee(userId);
		List<Task> result = taskQuery.listPage((page.getPageNo() - 1) * page.getPageSize(), page.getPageSize());
		// 获取查询列表
		long totalCount = taskQuery.count();
		page.setTotalCount(totalCount);
		page.setResult(result);
		return page;
	}

	@Override
	public Page<Task> queryCandidatePengingTasksByUserId(String userId, Page<Task> page) {
		// 创建查询对象
		TaskQuery taskQuery = taskService.createTaskQuery().taskCandidateUser(userId);
		List<Task> result = taskQuery.listPage((page.getPageNo() - 1) * page.getPageSize(), page.getPageSize());
		// 获取查询列表
		long totalCount = taskQuery.count();
		page.setTotalCount(totalCount);
		page.setResult(result);
		return page;
	}

	@Override
	public Page<Task> queryCandidatePengingTasksByGroup(String group, Page<Task> page) {
		// 创建查询对象
		TaskQuery taskQuery = taskService.createTaskQuery().taskCandidateGroup(group);
		List<Task> result = taskQuery.listPage((page.getPageNo() - 1) * page.getPageSize(), page.getPageSize());
		// 获取查询列表
		long totalCount = taskQuery.count();
		page.setTotalCount(totalCount);
		page.setResult(result);
		return page;
	}

	@Override
	public Page<Task> queryCandidatePengingTasksByGroup(List<String> groups, Page<Task> page) {
		// 创建查询对象
		TaskQuery taskQuery = taskService.createTaskQuery().taskCandidateGroupIn(groups);
		List<Task> result = taskQuery.listPage((page.getPageNo() - 1) * page.getPageSize(), page.getPageSize());
		// 获取查询列表
		long totalCount = taskQuery.count();
		page.setTotalCount(totalCount);
		page.setResult(result);
		return page;
	}

	@Override
	public Page<PengdingTask> queryPengingTasksByUserId(String userId, Page<Task> page) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Page<PengdingTask> queryPengingTasksByGroup(List<String> group, Page<Task> page) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Page<PengdingTask> queryPengingTasks(String userId, List<String> group, Page<Task> page) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Page<PengdingTask> queryPengingTasks(String userId, Page<Task> page) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Page<HistoricActivityInstance> queryCompleteTasks(String userId, Page<HistoricActivityInstance> page) {
		HistoricActivityInstanceQuery query = processEngine.getHistoryService() // 历史相关Service
				.createHistoricActivityInstanceQuery().taskAssignee(userId); // 创建历史活动实例查询
		List<HistoricActivityInstance> result = query.listPage((page.getPageNo() - 1) * page.getPageSize(),
				page.getPageSize());
		// 获取查询列表
		long totalCount = query.count();
		page.setTotalCount(totalCount);
		page.setResult(result);
		return page;
	}

	@Override
	public Page<HistoricActivityInstance> queryCompleteFinishedTasks(String userId,
			Page<HistoricActivityInstance> page) {
		HistoricActivityInstanceQuery query = processEngine.getHistoryService() // 历史相关Service
				.createHistoricActivityInstanceQuery().taskAssignee(userId).finished(); // 创建历史活动实例查询
		List<HistoricActivityInstance> result = query.listPage((page.getPageNo() - 1) * page.getPageSize(),
				page.getPageSize());
		// 获取查询列表
		long totalCount = query.count();
		page.setTotalCount(totalCount);
		page.setResult(result);
		return page;
	}

}
