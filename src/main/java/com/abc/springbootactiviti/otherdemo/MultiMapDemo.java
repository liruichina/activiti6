package com.abc.springbootactiviti.otherdemo;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Maps;
import com.google.common.collect.Multimap;

import java.util.List;
import java.util.Map;
import java.util.Random;

public class MultiMapDemo {

    Multimap<String, String> groupmap = ArrayListMultimap.create();

    public static void main(String[] args) {
        MultiMapDemo demo = new MultiMapDemo();
        demo.createGroupUser();
        for(int i=0;i<10;i++){
            System.out.println(demo.getZhiDanUser());
            System.out.println(new Random().nextInt(6));
        }


    }

    private Multimap<String, String> createGroupUser(){
        groupmap.clear();
        groupmap.put("group0","user01");
        groupmap.put("group0","user02");
        groupmap.put("group0","user03");
        groupmap.put("group0","user04");
        groupmap.put("group0","user05");
        groupmap.put("group0","user06");

        groupmap.put("group1","user11");
        groupmap.put("group1","user12");
        groupmap.put("group1","user13");
        groupmap.put("group1","user14");
        groupmap.put("group1","user15");
        groupmap.put("group1","user16");

        groupmap.put("group2","user21");
        groupmap.put("group2","user22");
        groupmap.put("group2","user23");
        groupmap.put("group2","user24");
        groupmap.put("group2","user25");
        groupmap.put("group2","user26");
        return groupmap;
    }

    private String getZhiDanUser(){
        List<String> list = (List)groupmap.get("group0");
        int i = new Random(100).nextInt(list.size());
        return list.get(i);
    }

}
