package com.serendipity.learn.zuo.heap;

import java.util.Comparator;
import java.util.PriorityQueue;

public class Code02_Comparator {

    public static class MyCom implements Comparator<Integer> {
        @Override
        public int compare(Integer o1, Integer o2) {
            return o2 - o1;
        }
    }

    public void test() {
        PriorityQueue<Integer> smallHeap = new PriorityQueue<>();
        PriorityQueue<Integer> bigHeap = new PriorityQueue<>(new MyCom());
    }
}
