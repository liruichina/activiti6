package com.abc.springbootactiviti.demo.beans;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

import java.util.List;
import java.util.Map;

public class TestList {
    private List<RunTestData> list;

    public void creatTestData(){
        RunTestData data0 = new RunTestData();
        RunTestData.Node node00 = data0.new Node();
//        node00.setCandidateGroup(Lists.newArrayList("group0"));
        Map<String,String> map00 = Maps.newHashMap();
        map00.put("group1","group0");
        node00.setOption(map00);
        data0.setStartNode(node00);

//        RunTestData data = new RunTestData();
        RunTestData.Node node01 = data0.new Node();
//        node01.setCandidateGroup("group0");
        List<String> userList = Lists.newArrayList("user11","user12","user15","user16");
        List<Integer> optionList = Lists.newArrayList(0,0,0,0);
        node01.setCandidate(userList);
        Map<String,String> map01 = Maps.newHashMap();
        map01.put("user11","0");
        map01.put("user12","0");
        map01.put("user15","0");
        map01.put("user16","0");
        node01.setOption(map01);
//        node.setOption(Maps.newHashMap().put("assigneelist","group0"));
        data0.setCreateCard(node01);



    }

    public List<RunTestData> getList() {
        return list;
    }

    public void setList(List<RunTestData> list) {
        this.list = list;
    }
}
