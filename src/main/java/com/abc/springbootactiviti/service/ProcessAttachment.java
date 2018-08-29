package com.abc.springbootactiviti.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.TaskService;
import org.activiti.engine.task.Attachment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProcessAttachment {

    /**流程引擎（核心对象），默认加载类路径下命名为activiti.cfg.xml*/
    @Autowired
    private ProcessEngine processEngine;

    @Autowired
    TaskService taskService;
    //= processEngine.getTaskService();
	
    public void processAttachment(String taskId,String piId) {
        // 设置任务附件
        Attachment att1 = taskService.createAttachment("web url", taskId, piId, "Attachement1",
                "baidu web page", "http://www.baidu.com");
// 创建图片输入流
        Attachment att2;
        try {
            try (InputStream is = new FileInputStream(new File("E:\\project\\springbootactiviti\\src\\main\\resources\\processes\\leave_service_exception.png"))) {
    // 设置输入流为任务附件
                att2 = taskService.createAttachment("web url", taskId, piId, "Attachement2", "Image InputStream", is);
                // 根据流程实例ID查询附件
                List<Attachment> attas1 = taskService.getProcessInstanceAttachments(piId);
                System.out.println("流程附件数量：" + attas1.size());
// 根据任务ID查询附件
                List<Attachment> attas2 = taskService.getTaskAttachments(taskId);
                System.out.println("任务附件数量：" + attas2.size());
// 根据附件ID查询附件
                Attachment attResult = taskService.getAttachment(att1.getId());
                System.out.println("附件1名称：" + attResult.getName());
// 根据附件ID查询附件内容
                InputStream stream1 = taskService.getAttachmentContent(att1.getId());
                System.out.println("附件1的输入流：" + stream1);
                InputStream stream2 = taskService.getAttachmentContent(att2.getId());
                System.out.println("附件2的输入流：" + stream2);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
