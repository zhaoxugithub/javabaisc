package com.serendipity.myold.generics;

import java.util.ArrayList;

/**
 * ClassName GenericsTest_09
 * Description TODO
 * Author 11931
 * Date 2023-02-03:0:01
 * Version 1.0
 **/
public class GenericsTest_09 {
    public static void main(String[] args) {
        // ArrayList<String> list1 = new ArrayList<Object>();// 编译错误
        // 原因如下：
        ArrayList<Object> objects = new ArrayList<Object>();
        objects.add(new Object());
        objects.add(new Object());
        // ArrayList<String> list1 = objects;  编译错误
        // ArrayList<Object> list2 = new ArrayList<String>(); // 编译错误
        ArrayList<String> strings = new ArrayList<>();
        strings.add(new String());
        strings.add(new String());
        // ArrayList<Object> list2 = strings;  编译错误
    }
}
