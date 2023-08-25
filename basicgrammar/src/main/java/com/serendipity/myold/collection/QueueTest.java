package com.serendipity.myold.collection;

import org.junit.Test;

import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;

/**
 * ClassName Test
 * Description TODO
 * Author 11931
 * Date 2023-08-21:5:38
 * Version 1.0
 **/
public class QueueTest {
    @Test
    public void test01() {
        ArrayBlockingQueue<Object> objects = new ArrayBlockingQueue<>(10);
        // Object remove = objects.remove(); // 抛出异常
        Object poll = objects.poll();// 返回空
        System.out.println(poll);
    }
}

