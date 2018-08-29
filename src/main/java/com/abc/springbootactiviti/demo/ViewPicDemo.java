package com.abc.springbootactiviti.demo;



import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngines;
import org.activiti.engine.RepositoryService;
import org.apache.commons.io.FileUtils;


import java.io.File;
import java.io.InputStream;
import java.util.List;

public class ViewPicDemo {

    public static void main(String[] args) {
        ViewPicDemo vp = new ViewPicDemo();
        try {
            vp.viewImage();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**流程引擎（核心对象），默认加载类路径下命名为activiti.cfg.xml*/
    ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();

    public void viewImage() throws Exception {
        // 创建仓库服务对对象
        RepositoryService repositoryService = processEngine.getRepositoryService();
        // 从仓库中找需要展示的文件
        String deploymentId = "1";
        List<String> names = repositoryService.getDeploymentResourceNames(deploymentId);
        String imageName = null;
        for (String name : names) {
            if (name.indexOf(".png") >= 0) {
                imageName = name;
            }
        }
        imageName = "leave3.leave3.png";
        if (imageName != null) {
//     System.out.println(imageName);
            File f = new File("e:/" + imageName);
            // 通过部署ID和文件名称得到文件的输入流
            InputStream in = repositoryService.getResourceAsStream(deploymentId, imageName);
            FileUtils.copyInputStreamToFile(in, f);
        }
    }

}
