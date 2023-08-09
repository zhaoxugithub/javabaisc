package com.serendipity.myold.generics;

import java.util.ArrayList;

/**
 * ClassName GenericsTest_08
 * Description TODO
 * Author 11931
 * Date 2023-02-02:23:30
 * Version 1.0
 **/
@SuppressWarnings("all")
public class GenericsTest_08 {

    public static void main(String[] args) {
        ArrayList<String> arrayList = new ArrayList<>();
        arrayList.add("1");
        // arrayList.add(1); 编译报错
        String s = arrayList.get(0);
        ArrayList arrayList2 = new ArrayList<>();
        arrayList2.add("1");
        arrayList2.add(2); // 编译可以通过
        Object o = arrayList2.get(0); // 返回的是object
        new ArrayList<String>().add("11");
        // new ArrayList<String>().add(22); 编译报错
        String s1 = new ArrayList<String>().get(0); // 返回的数据类型就是string
        System.out.println(s1);
    }
}
