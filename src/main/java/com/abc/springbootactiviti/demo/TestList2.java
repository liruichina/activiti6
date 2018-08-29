package com.abc.springbootactiviti.demo;

import com.abc.springbootactiviti.demo.beans.ProcessNode;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

import java.util.List;
import java.util.Map;

public class TestList2 {

    public List<ProcessNode> createTestData(){

        //传递的option值，0正常运行，1审核不通过，10强制结束，2回退到制单
        List<ProcessNode> list = Lists.newArrayList();
        //1
        ProcessNode node0 = genProcessNode("group0","user01","0",
                Lists.newArrayList("user11","user13","user16"),
                Lists.newArrayList(0,0,0),
                "");
        list.add(node0);
//        //2
//        ProcessNode node1 = genProcessNode("group0","user03","10",
//                Lists.newArrayList("user11","user13","user15","user12"),
//                Lists.newArrayList(0,0,0,0),
//                null);
//        list.add(node1);
//
//        //3
//        ProcessNode node2 = genProcessNode("group0","user02","0",
//                Lists.newArrayList("user11","user14","user15","user22"),
//                Lists.newArrayList(0,2,0,0),
//                "user22");
//        list.add(node2);
//        //3
//        ProcessNode node3 = genProcessNode("group0","user02","0",
//                Lists.newArrayList("user11","user13","user15","user22"),
//                Lists.newArrayList(0,0,10,0),
//                "user22");
//        list.add(node3);

        return list;
    }

    private ProcessNode genProcessNode(String startCandidateGroup,String startAssignee,String startOption,List<String> assigneelist,List<Integer> options,String stopUser){
        ProcessNode node0 = new ProcessNode();
        Map<String,String> map01 = Maps.newHashMap();
        map01.put("group1",startCandidateGroup);
        node0.setStartNode(map01);

//        List<String> assigneelist = Lists.newArrayList("user11","user12","user15","user16");
        Map<String,Object> map02 = Maps.newHashMap();
        map02.put("assignee",startAssignee);
        map02.put("option",startOption);
        map02.put("assigneelist", assigneelist);
        node0.setCreateCardNode(map02);

//        List<Integer> options = Lists.newArrayList(0,0,0,0);
        Map<String,Object> map03 = Maps.newHashMap();
        map03.put("option",options);
        node0.setCheckNode(map03);
        node0.setStopNode(stopUser);
        return node0;
    }
}
