package com.abc.springbootactiviti.demo;

import com.abc.springbootactiviti.chapter13.Page;
import com.abc.springbootactiviti.chapter13.PageUtil;

import java.util.List;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngines;
import org.activiti.engine.TaskService;
import org.activiti.engine.task.NativeTaskQuery;
import org.activiti.engine.task.Task;
import org.activiti.engine.task.TaskQuery;

public class QueryByPageDemo {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		QueryByPageDemo demo = new QueryByPageDemo();
		demo.queryByPage("user05");

	}
	
//	public Page<Task> queryByPage(){
//		return null;
//	}
	
	 ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
	 
	
    /**
     * 读取启动流程的表单字段
     */
//    @RequestMapping(value = "task/list")
    public Page<Task> queryByPage(String userId) {
        String viewName = "chapter6/task-list";
//        ModelAndView mav = new ModelAndView(viewName);

//        User user = UserUtil.getUserFromSession(request.getSession());
        Page<Task> page = new Page<Task>(PageUtil.PAGE_SIZE);
//        int[] pageParams = PageUtil.init(page, request);
        
        TaskService taskService = processEngine.getTaskService();

        NativeTaskQuery nativeTaskQuery = taskService.createNativeTaskQuery();

    /*
    过滤条件
     */
        String filters = "";
//        if (StringUtils.isNotBlank(taskName)) {
//            filters += " and RES.NAME_ like #{taskName}";
//            nativeTaskQuery.parameter("taskName", "%" + taskName + "%");
//            mav.addObject("taskName", taskName);
//        }

        // 当前人在候选人或者候选组范围之内
        String sql = "select distinct RES.* from ACT_RU_TASK RES WHERE RES.SUSPENSION_STATE_ = '1'"
        		+ " and RES.ASSIGNEE_ = #{userId} order by RES.CREATE_TIME_ desc";

        nativeTaskQuery.sql(sql).parameter("userId", userId);
        List<Task> tasks = nativeTaskQuery.listPage(10, 10);

        page.setResult(tasks);
        page.setTotalCount(nativeTaskQuery.sql("select count(*) from (" + sql + ") p").count());
        
        TaskQuery taskQuery = taskService.createTaskQuery().taskAssignee(userId);
//        taskQuery.listPage(firstResult, maxResults);

        // 从5.16版本开始可以使用以下方式
        /*TaskQuery taskQuery = taskService.createTaskQuery().taskCandidateOrAssigned(user.getId());
        page.setTotalCount(taskQuery.count());
        page.setResult(taskQuery.list());
        mav.addObject("tasks", tasks);*/

//        mav.addObject("page", page);

//        return mav;
        return page;
    }

}
