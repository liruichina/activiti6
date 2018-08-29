package com.abc.springbootactiviti.beans;

/**
 * 封装待办任务对象
 */
public class PengdingTask {
    /**任务Id*/
    private String task_id;
    /**流程实例Id*/
    private String proc_inst_id;
    /**任务定义key*/
    private String act_id;
    /**任务名称*/
    private String act_name;
    /**签收人或者委托人*/
    private String assignee;
    /**委托类型，DelegationState分为两种：PENDING，RESOLVED。如无委托则为空*/
    private String delegation_id;
    /**节点定义描述*/
    private String description;
    /**创建时间*/
    private String create_time;
    /**执行时间*/
    private String due_date;
    /**暂停状态，1代表激活 2代表挂起*/
    private String suspension_state;
    /**候选人*/
    private String candidate;
    /**候选组*/
    private String candidateGroup;

    public String getTask_id() {
        return task_id;
    }

    public void setTask_id(String task_id) {
        this.task_id = task_id;
    }

    public String getProc_inst_id() {
        return proc_inst_id;
    }

    public void setProc_inst_id(String proc_inst_id) {
        this.proc_inst_id = proc_inst_id;
    }

    public String getAct_id() {
        return act_id;
    }

    public void setAct_id(String act_id) {
        this.act_id = act_id;
    }

    public String getAct_name() {
        return act_name;
    }

    public void setAct_name(String act_name) {
        this.act_name = act_name;
    }

    public String getAssignee() {
        return assignee;
    }

    public void setAssignee(String assignee) {
        this.assignee = assignee;
    }

    public String getDelegation_id() {
        return delegation_id;
    }

    public void setDelegation_id(String delegation_id) {
        this.delegation_id = delegation_id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCreate_time() {
        return create_time;
    }

    public void setCreate_time(String create_time) {
        this.create_time = create_time;
    }

    public String getDue_date() {
        return due_date;
    }

    public void setDue_date(String due_date) {
        this.due_date = due_date;
    }

    public String getSuspension_state() {
        return suspension_state;
    }

    public void setSuspension_state(String suspension_state) {
        this.suspension_state = suspension_state;
    }

    public String getCandidate() {
        return candidate;
    }

    public void setCandidate(String candidate) {
        this.candidate = candidate;
    }

    public String getCandidateGroup() {
        return candidateGroup;
    }

    public void setCandidateGroup(String candidateGroup) {
        this.candidateGroup = candidateGroup;
    }

    @Override
    public String toString() {
        return "PengdingTask{" + "task_id='" + task_id + '\'' + ", proc_inst_id='" + proc_inst_id + '\'' + ", act_id='" + act_id + '\'' + ", act_name='" + act_name + '\'' + ", assignee='" + assignee + '\'' + ", delegation_id='" + delegation_id + '\'' + ", description='" + description + '\'' + ", create_time='" + create_time + '\'' + ", due_date='" + due_date + '\'' + ", suspension_state='" + suspension_state + '\'' + ", candidate='" + candidate + '\'' + ", candidateGroup='" + candidateGroup + '\'' + '}';
    }
}
