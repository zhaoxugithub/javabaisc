package com.serendipity.learn.c_030;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Copyright (C), 2017-2021, 赵旭
 * Author: serendipity
 * Date: 2021/9/13 8:23 下午
 * FileName: HashMap
 * Description: com.java.thread.c_030
 */
public class HashMapTest {

    public void test(){
        HashMapTest hashMapTest = new HashMapTest();
    }
    public static void main(String[] args) {
        // 无序的
        // 试试输入法
        // Map<String, Integer> hashMap = new HashMap<>();
        // 有序的
        Map<String, Integer> hashMap = new LinkedHashMap<>();
        hashMap.put("zx", 10);
        hashMap.put("dd", 20);
        hashMap.put("ff", 40);
        hashMap.put("dddd", 40);
        for (Map.Entry<String, Integer> stringIntegerEntry : hashMap.entrySet()) {
            System.out.println(stringIntegerEntry);
        }
        System.out.println("fdsfdf");
        System.out.println("fdsf");
        System.out.println("dsadas");
    }
}
