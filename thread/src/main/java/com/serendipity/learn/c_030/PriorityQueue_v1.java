package com.serendipity.learn.c_030;

import java.util.PriorityQueue;

/**
 * Copyright (C), 2017-2021, 赵旭
 * Author: 11931
 * Date: 2021/10/2 17:43
 * FileName: PriorityQueue_v1
 * Description: com.java.thread.c_030
 */
public class PriorityQueue_v1 {

    public static void main(String[] args) {

        //
        PriorityQueue<String> priorityQueue = new PriorityQueue<>();

        priorityQueue.add("a");
        priorityQueue.add("v");
        priorityQueue.add("c");
        priorityQueue.add("b");
        priorityQueue.add("f");
        priorityQueue.add("e");


//        priorityQueue.forEach(System.out::println);


        //下面的这种遍历方式是有问题的，打印出来的结果只有a,b,c
//        for (int i = 0; i < priorityQueue.size(); i++) {
//            System.out.println(priorityQueue.poll());
//        }


        //这种遍历也是正常的
        for (int i = 0; i < 6; i++) {
            System.out.println(priorityQueue.poll());
        }

        //使用迭代器的方式去遍历会正常输出
//        Iterator<String> iterator = priorityQueue.iterator();
//        while (iterator.hasNext()) {
//            System.out.println(priorityQueue.poll());
//        }
    }
}
