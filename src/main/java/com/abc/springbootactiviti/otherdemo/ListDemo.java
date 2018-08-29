package com.abc.springbootactiviti.otherdemo;

import com.google.common.collect.Maps;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

public class ListDemo {

    public static void main(String[] args) {

        Map<String,Object> map = Maps.newHashMap();
        map.put("key1","value1");
        map.put("key2","value2");
        map.put("key3","value3");
        map.forEach((k,v)->System.out.println(k+"-----"+v));

    }

}
