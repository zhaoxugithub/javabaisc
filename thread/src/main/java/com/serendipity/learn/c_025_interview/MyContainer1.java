package com.serendipity.learn.c_025_interview;

import java.util.LinkedList;

/**
 * Copyright (C), 2017-2021, 赵旭
 * Author: Administrator
 * Date: 2021/9/6 23:48
 * FileName: MyContainer1
 * Description: com.java.thread.c_025_interview
 * 面试题：写一个固定容量同步容器，拥有put和get方法，以及getCount方法，
 * 能够支持2个生产者线程以及10个消费者线程的阻塞调用
 * <p>
 * 使用wait和notify/notifyAll来实现
 */
public class MyContainer1<T> {

    final private LinkedList<T> list = new LinkedList<>();
    final int MAX = 10;
    private int count = 0;

    public synchronized void put(T t) {
        if (count == MAX) {
            // 阻塞，等待消费
            try {
                System.out.println(Thread.currentThread().getName() + ": put");
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        list.add(t);
        count++;
        //让其他线程进行消费
        System.out.println(count + ":" + t);
        this.notifyAll();
    }

    public synchronized int getCount() {
        return list.size();
    }

    public synchronized T get() {
        while (list.isEmpty()) {
            try {
                //阻塞消费者
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println(Thread.currentThread().getName() + ": get");
        T t = list.removeFirst();
        count--;
        this.notifyAll();
        return t;
    }

    public static void main(String[] args) {
        MyContainer1<String> container1 = new MyContainer1<>();
        for (int i = 0; i < 2; i++) {
            new Thread(() -> {
                for (int j = 0; j < 25; j++) {
                    String str = "zz" + j;
                    container1.put(str);
                }
            }, "productor" + i).start();
        }

        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                for (int j = 0; j < 5; j++) {
                    String s = container1.get();
                    System.out.println(s);
                }
            }, "costomer" + i).start();
        }
    }
}
