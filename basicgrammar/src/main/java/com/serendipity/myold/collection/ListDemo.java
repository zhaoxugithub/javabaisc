package com.serendipity.myold.collection;

import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.checkerframework.checker.nullness.qual.Nullable;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * ClassName ListDemo
 * Description TODO
 * Author 11931
 * Date 2023-12-03:12:19
 * Version 1.0
 **/

@Slf4j
@SuppressWarnings("all")
public class ListDemo {

    private class ArrayListDemo {
        public void addEle() {
            ArrayList<String> arrayList = new ArrayList<String>();
        }
    }

    private class LinkedListDemo {

    }

    private class VectorDemo {

    }

    /**
     * removeIf
     */
    @Test
    public void testRemoveIf() {
        // List<String> mock = JMockData.mock(new TypeReference<List<String>>() {});
        List<String> mockList = Lists.newArrayList("a", "b", "c");
        System.out.println(mockList);
        mockList.removeIf(s -> s.equals("a"));
        System.out.println(mockList);
        List<String> b = mockList.stream().filter(s -> !s.equals("b")).toList();
        System.out.println(b);
    }

    /**
     * 匿名实现类 + 方法块
     */
    @Test
    public void testSubClass() {
        ArrayList<@Nullable Object> objects = Lists.newArrayList();
        ArrayList<String> strings = new ArrayList<>() {
            // 方法块
            {
                add("a");
                add("b");
                add("c");
            }

            @Override
            public String get(int index) {
                return "hhhhh";
            }
        };
        System.out.println(strings);
        System.out.println(strings.get(1));
    }

    @Test
    public void subList() {
        List<String> list = new ArrayList<String>() {{
            add("a");
            add("b");
            add("c");
        }};
        // strings是list的一个视图，对strings的操作会影响list
        List<String> strings = list.subList(0, 1);
        System.out.println(strings);
        strings.add("d");
        // a b c d
        System.out.println(list);
        // 下面这个代码有异常
        // ArrayList<String> subList = list.subList(0, 2);
        list.add("e");
        System.out.println(list);
    }

    @Test
    public void testSubList2() {
        List<String> sourceList = new ArrayList<String>() {{
            add("H");
            add("O");
            add("L");
            add("L");
            add("I");
            add("S");
        }};
        List<String> subList = sourceList.subList(2, 5);
        System.out.println("sourceList: " + sourceList);
        System.out.println("subList: " + subList);
        sourceList.add("666");
        System.out.println("sourceList: " + sourceList);
        // java.util.ConcurrentModificationException
        System.out.println("subList: " + subList);
    }

    public void test() {
        LinkedList<String> aa = new LinkedList<>() {{
            add("aa");
        }};
    }

}
