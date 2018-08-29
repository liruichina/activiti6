package com.abc.springbootactiviti.chapter13;

import java.util.Date;

import com.abc.springbootactiviti.mapper.TaskQueryMapper;

/**
 * 运行时任务Bean，用于封装{@link TaskQueryMapper}
 * @author: Henry Yan
 */
public class RunningTask {

    private String id;
    private String name;
    private String assignee;
    private String processName;
    private String processInstanceId;
    private String processDefinitionId;
    private Date createTime;

    public String getAssignee() {
        return assignee;
    }

    public void setAssignee(String assignee) {
        this.assignee = assignee;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getProcessDefinitionId() {
        return processDefinitionId;
    }

    public void setProcessDefinitionId(String processDefinitionId) {
        this.processDefinitionId = processDefinitionId;
    }

    public String getProcessInstanceId() {
        return processInstanceId;
    }

    public void setProcessInstanceId(String processInstanceId) {
        this.processInstanceId = processInstanceId;
    }

    public String getProcessName() {
        return processName;
    }

    public void setProcessName(String processName) {
        this.processName = processName;
    }

    @Override
    public String toString() {
        return "RunningTask{" + "id='" + id + '\'' + ", name='" + name + '\'' + ", assignee='" + assignee + '\'' + ", processName='" + processName + '\'' + ", processInstanceId='" + processInstanceId + '\'' + ", processDefinitionId='" + processDefinitionId + '\'' + ", createTime=" + createTime + '}';
    }
}
