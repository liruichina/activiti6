package com.abc.springbootactiviti.service;

import com.abc.springbootactiviti.beans.PengdingTask;
import org.activiti.engine.history.HistoricActivityInstance;
import org.activiti.engine.task.Task;

import java.util.List;




public interface QueryTaskService {

	/**
	 * 根据用户名信息，获取被分配到的待办任务
	 * 
	 * @param userId 用户相关信息
	 * @return
	 */
	public List<Task> queryAssignedPengingTasksByUserId(String userId);

	/**
	 * 根据用户名信息，获取作为候选人的待办任务
	 * 
	 * @param userId 用户相关信息
	 * @return
	 */
	public List<Task> queryCandidatePengingTasksByUserId(String userId);
	
	/**
	 * 根据用户组信息，获取作为候选人的待办任务
	 * 
	 * @param userId 用户相关信息
	 * @return
	 */
	public List<Task> queryCandidatePengingTasksByGroup(String group);
	
	/**
	 * 根据用户组信息，获取作为候选人的待办任务
	 * 
	 * @param userId 用户相关信息
	 * @return
	 */
	public List<Task> queryCandidatePengingTasksByGroup(List<String> groups);

	/**
	 * 根据用户名信息，获取被分配到的待办任务或者作为候选人之一的待办任务
	 * 
	 * @param userId 用户相关信息
	 * @return
	 */
	public List<PengdingTask> queryPengingTasksByUserId(String userId);

	/**
	 * 根据用户组信息，获取该组成员的代办任务
	 * 
	 * @param group 用户组相关信息
	 * @return
	 */
	public List<PengdingTask> queryPengingTasksByGroup(List<String> group);

	/**
	 * 根据用户名信息，获取被分配到的待办任务或者作为候选人之一的待办任务。 并根据人员所在的组信息，获取该组成员的代办任务。 将结果集合并，作为该用户的待办任务
	 * 
	 * @param userId 用户相关信息
	 * @param group  用户所在组相关信息
	 * @return
	 */
	public List<PengdingTask> queryPengingTasks(String userId, List<String> group);

	/**
	 * 根据用户名信息，获取被分配到的待办任务或者作为候选人之一的待办任务。 并通过人员信息获取到人员所在的组信息，进而获取该组成员的代办任务。
	 * 将结果集合并，作为该用户的待办任务
	 * 
	 * @param userId 用户相关信息
	 * @return
	 */
	public List<PengdingTask> queryPengingTasks(String userId);

	/**
	 * 根据用户名信息，获取被已被该用户处理过的任务
	 * 
	 * @param userId 用户信息
	 * @return
	 */
	public List<HistoricActivityInstance> queryCompleteTasks(String userId);

	/**
	 * 根据用户名信息，获取被已被该用户处理过的且已走完完成流程的任务
	 * 
	 * @param userId 用户信息
	 * @return
	 */
	public List<HistoricActivityInstance> queryCompleteFinishedTasks(String userId);
}
