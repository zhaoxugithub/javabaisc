package com.serendipity.myold.collection;

import com.github.jsonzou.jmockdata.JMockData;
import com.github.jsonzou.jmockdata.MockConfig;
import com.github.jsonzou.jmockdata.TypeReference;
import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.checkerframework.checker.nullness.qual.Nullable;
import org.junit.Test;

import java.util.ArrayList;
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
     * subList
     */
    @Test
    public void testSubList() {
        ArrayList<@Nullable Object> objects = Lists.newArrayList();
        // 匿名内部类
        ArrayList<String> strings = new ArrayList<>() {{
            add("a");
            add("b");
            add("c");
        }};
    }
}
