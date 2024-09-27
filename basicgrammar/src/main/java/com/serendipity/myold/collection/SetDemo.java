package com.serendipity.myold.collection;

import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;

public class SetDemo {

    /**
     * LinkedHashset
     * LinkedHashMap
     */
    @Test
    public void test() {
        LinkedHashSet<String> set = new LinkedHashSet<String>();
        set.add("f");
        set.add("a");
        set.add("b");
        set.add("c");
        List<String> list = set.stream().toList();
        System.out.println(list.get(1)); // a
    }

    @Test
    public void testHashSet() {
        HashSet<String> hashSet = new HashSet<>();
        hashSet.add("f");
        hashSet.add("a");
        hashSet.add("b");
        hashSet.add("c");
        List<String> list = hashSet.stream().toList();
        System.out.println(list.get(1)); // b
    }
}