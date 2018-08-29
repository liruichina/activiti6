package com.abc.springbootactiviti.service;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngines;
import org.activiti.engine.repository.Deployment;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.zip.ZipInputStream;

/**
 * 部署新的流程
 */
public class DeployementProcessDefinitionService {

    public static void main(String[] args) {
        DeployementProcessDefinitionService dp = new DeployementProcessDefinitionService();
//        try {
////            dp.deployementProcessDefinitionByString();
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        }
        String name="监管易项目模拟2";
        String bpmnFileName = "processes/jianguanyi2.bpmn";
        String pngFileName = "processes/jianguanyi2.png";

        name="使用候选人模式的请假流程";
        bpmnFileName = "processes/leave_houxuan.bpmn";
        pngFileName = "processes/leave_houxuan.png";
        
        name="监管易项目模拟3";
        bpmnFileName = "processes/jianguanyi3.bpmn";
        pngFileName = "processes/jianguanyi3.png";

        dp.deployementProcessDefinition(name, bpmnFileName, pngFileName);

    }
    /**流程引擎（核心对象），默认加载类路径下命名为activiti.cfg.xml*/
    ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
    //classpath方式部署流程定义
//    @Test
    public void deployementProcessDefinition(String name,String bpmnFileName,String pngFileName){
        Deployment deployment = processEngine.getRepositoryService()//获取流程定义和部署对象相关的Service
                .createDeployment()//创建部署对象
                .name(name)//声明流程的名称
                .addClasspathResource(bpmnFileName)//加载资源文件，一次只能加载一个文件
                .addClasspathResource(pngFileName)//
                .deploy();//完成部署
        System.out.println("部署ID："+deployment.getId());//1
        System.out.println("部署时间："+deployment.getDeploymentTime());
    }

    //InputStream方式
    public void deployementProcessDefinitionByInputStream() throws FileNotFoundException {
        //获取资源相对路径
        String bpmnPath = "diagrams/helloworld.bpmn";
        String pngPath = "diagrams/helloworld.png";

        //读取资源作为一个输入流
        FileInputStream bpmnfileInputStream = new FileInputStream(bpmnPath);
        FileInputStream pngfileInputStream = new FileInputStream(pngPath);

        Deployment deployment = processEngine.getRepositoryService()//获取流程定义和部署对象相关的Service
                .createDeployment()//创建部署对象
                .addInputStream("helloworld.bpmn",bpmnfileInputStream)
                .addInputStream("helloworld.png", pngfileInputStream)
                .deploy();//完成部署
        System.out.println("部署ID："+deployment.getId());//1
        System.out.println("部署时间："+deployment.getDeploymentTime());
    }

    //字符串方式
    public void deployementProcessDefinitionByString() throws FileNotFoundException{
        //字符串
        String text = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" + "<definitions xmlns=\"http://www.omg.org/spec/BPMN/20100524/MODEL\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:activiti=\"http://activiti.org/bpmn\" xmlns:bpmndi=\"http://www.omg.org/spec/BPMN/20100524/DI\" xmlns:omgdc=\"http://www.omg.org/spec/DD/20100524/DC\" xmlns:omgdi=\"http://www.omg.org/spec/DD/20100524/DI\" typeLanguage=\"http://www.w3.org/2001/XMLSchema\" expressionLanguage=\"http://www.w3.org/1999/XPath\" targetNamespace=\"http://www.activiti.org/test\">\n" + "  <process id=\"myProcess\" name=\"My process\" isExecutable=\"true\">\n" + "    <startEvent id=\"startevent1\" name=\"Start\"></startEvent>\n" + "    <endEvent id=\"endevent1\" name=\"End\"></endEvent>\n" + "    <userTask id=\"makecard\" name=\"制卡\" activiti:assignee=\"zhangsan\"></userTask>\n" + "    <sequenceFlow id=\"flow1\" sourceRef=\"startevent1\" targetRef=\"makecard\"></sequenceFlow>\n" + "    <userTask id=\"fuhe1\" name=\"复核\" activiti:assignee=\"lisi\"></userTask>\n" + "    <sequenceFlow id=\"flow2\" sourceRef=\"makecard\" targetRef=\"fuhe1\"></sequenceFlow>\n" + "    <userTask id=\"fuhe2\" name=\"复核2\" activiti:assignee=\"wangwu\"></userTask>\n" + "    <sequenceFlow id=\"flow3\" sourceRef=\"fuhe1\" targetRef=\"fuhe2\"></sequenceFlow>\n" + "    <sequenceFlow id=\"flow4\" sourceRef=\"fuhe2\" targetRef=\"endevent1\"></sequenceFlow>\n" + "  </process>\n" + "  <bpmndi:BPMNDiagram id=\"BPMNDiagram_myProcess\">\n" + "    <bpmndi:BPMNPlane bpmnElement=\"myProcess\" id=\"BPMNPlane_myProcess\">\n" + "      <bpmndi:BPMNShape bpmnElement=\"startevent1\" id=\"BPMNShape_startevent1\">\n" + "        <omgdc:Bounds height=\"35.0\" width=\"35.0\" x=\"60.0\" y=\"240.0\"></omgdc:Bounds>\n" + "      </bpmndi:BPMNShape>\n" + "      <bpmndi:BPMNShape bpmnElement=\"endevent1\" id=\"BPMNShape_endevent1\">\n" + "        <omgdc:Bounds height=\"35.0\" width=\"35.0\" x=\"740.0\" y=\"240.0\"></omgdc:Bounds>\n" + "      </bpmndi:BPMNShape>\n" + "      <bpmndi:BPMNShape bpmnElement=\"makecard\" id=\"BPMNShape_makecard\">\n" + "        <omgdc:Bounds height=\"55.0\" width=\"105.0\" x=\"180.0\" y=\"230.0\"></omgdc:Bounds>\n" + "      </bpmndi:BPMNShape>\n" + "      <bpmndi:BPMNShape bpmnElement=\"fuhe1\" id=\"BPMNShape_fuhe1\">\n" + "        <omgdc:Bounds height=\"55.0\" width=\"105.0\" x=\"360.0\" y=\"230.0\"></omgdc:Bounds>\n" + "      </bpmndi:BPMNShape>\n" + "      <bpmndi:BPMNShape bpmnElement=\"fuhe2\" id=\"BPMNShape_fuhe2\">\n" + "        <omgdc:Bounds height=\"55.0\" width=\"105.0\" x=\"534.0\" y=\"230.0\"></omgdc:Bounds>\n" + "      </bpmndi:BPMNShape>\n" + "      <bpmndi:BPMNEdge bpmnElement=\"flow1\" id=\"BPMNEdge_flow1\">\n" + "        <omgdi:waypoint x=\"95.0\" y=\"257.0\"></omgdi:waypoint>\n" + "        <omgdi:waypoint x=\"180.0\" y=\"257.0\"></omgdi:waypoint>\n" + "      </bpmndi:BPMNEdge>\n" + "      <bpmndi:BPMNEdge bpmnElement=\"flow2\" id=\"BPMNEdge_flow2\">\n" + "        <omgdi:waypoint x=\"285.0\" y=\"257.0\"></omgdi:waypoint>\n" + "        <omgdi:waypoint x=\"360.0\" y=\"257.0\"></omgdi:waypoint>\n" + "      </bpmndi:BPMNEdge>\n" + "      <bpmndi:BPMNEdge bpmnElement=\"flow3\" id=\"BPMNEdge_flow3\">\n" + "        <omgdi:waypoint x=\"465.0\" y=\"257.0\"></omgdi:waypoint>\n" + "        <omgdi:waypoint x=\"534.0\" y=\"257.0\"></omgdi:waypoint>\n" + "      </bpmndi:BPMNEdge>\n" + "      <bpmndi:BPMNEdge bpmnElement=\"flow4\" id=\"BPMNEdge_flow4\">\n" + "        <omgdi:waypoint x=\"639.0\" y=\"257.0\"></omgdi:waypoint>\n" + "        <omgdi:waypoint x=\"740.0\" y=\"257.0\"></omgdi:waypoint>\n" + "      </bpmndi:BPMNEdge>\n" + "    </bpmndi:BPMNPlane>\n" + "  </bpmndi:BPMNDiagram>\n" + "</definitions>";

        Deployment deployment = processEngine.getRepositoryService()//获取流程定义和部署对象相关的Service
                .createDeployment()//创建部署对象
                .name("请假2")
                .category("leave")
//                .tenantId("leave")
                .addString("leave2.bpmn",text)
                .deploy();//完成部署
        System.out.println("部署ID："+deployment.getId());//1
        System.out.println("部署时间："+deployment.getDeploymentTime());
    }

    public void deployementProcessDefinitionByzip(){
        //从classpath路径下读取资源文件
        InputStream in = this.getClass().getClassLoader().getResourceAsStream("diagrams/helloworld.zip");
        ZipInputStream zipInputStream = new ZipInputStream(in);
        Deployment deployment = processEngine.getRepositoryService()//获取流程定义和部署对象相关的Service
                .createDeployment()//创建部署对象
                .addZipInputStream(zipInputStream)//使用zip方式部署，将helloworld.bpmn和helloworld.png压缩成zip格式的文件
                .deploy();//完成部署
        System.out.println("部署ID："+deployment.getId());//1
        System.out.println("部署时间："+deployment.getDeploymentTime());
    }

}
