package com.abc.springbootactiviti.demo;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Maps;
import com.google.common.collect.Multimap;

import java.util.Map;

public class GroupUserMap {

//    static {
//        createGroupUser();
//    }

    public static Multimap<String, String> groupmap = ArrayListMultimap.create();

    public static Map<String,String> userGroupMap = Maps.newHashMap();

    public static Multimap<String, String> createGroupUser(){
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
        //获取用户与组的关系
        groupmap.forEach((k,v)->userGroupMap.put(v,k));
        return groupmap;
    }
}
