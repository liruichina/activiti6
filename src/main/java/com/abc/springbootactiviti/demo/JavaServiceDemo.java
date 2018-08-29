package com.abc.springbootactiviti.demo;

import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.delegate.JavaDelegate;

import java.util.logging.Logger;
/**
 * execute方法的参数DelegateExecution execution可以在流程中各个结点之间传递流程变量。
 *
 *
 * <serviceTask id="servicetask2" name="产品经理同意" activiti:class="com.easyway.workflow.activiti.gateway.ProductManagerServiceTask"></serviceTask>

 * 产品经理审批过程
 * @author longgangbai
 *
 * 2011-12-17  下午07:45:47
 */
public class JavaServiceDemo implements JavaDelegate {

    private final Logger log = Logger.getLogger(JavaServiceDemo.class.getName());

    @Override
    public void execute(DelegateExecution execution) {
        try {
			Thread.sleep(10000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        log.info("variavles=" + execution.getVariables());
        //execution.setVariable("产品经理", "请假天数大约3天，同意请假。");
        System.out.println("=======================================");
        System.out.println("Java服务方式调用");
        System.out.println("=======================================");
        log.info("正常结束流程，进行本方法的调用。");
    }
}
