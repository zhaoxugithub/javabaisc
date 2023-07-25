package com.serendipity.learn.zuo.heap;

import java.util.PriorityQueue;

//  1.已知一个几乎有序的数组，几乎有序是指，如果把数组排好顺序的话，每个元素移动的距离一定不超过k,并且k相对于数组长度来说是比较小的
//          请选择一个合适的排序规则策略，对这个数组进行排序。

// 时间复杂度：O(k*logN)
public class Code02_SortArrayDistanceLessK {


    public static void sortedArrDistanceLessK(int[] arr, int k) {
        if (k == 0) {
            return;
        }
        int index = 0;
        //默认是小根堆
        PriorityQueue<Integer> queue = new PriorityQueue();
        for (; index <= Math.min(k, arr.length - 1); index++) {
            queue.add(arr[index]);
        }
        int i = 0;
        for (; index < arr.length; index++, i++) {
            arr[i] = queue.poll();
            queue.add(arr[index]);
        }
        while (!queue.isEmpty()) {
            arr[i++] = queue.poll();
        }
    }

}
