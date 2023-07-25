package com.serendipity.learn.c_029_sellticket;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * Copyright (C), 2017-2021, 赵旭
 * Author: serendipity
 * Date: 2021/9/12 7:14 下午
 * FileName: TicketSeller4
 * Description: com.java.thread.c_029_sellticket
 * <p>
 * 推荐使用ConcurrentLinkedQueue,天生支持高并发
 */
public class TicketSeller4 {

    static Queue<String> tickets = new ConcurrentLinkedQueue<>();

    static {
        for (int i = 0; i < 1000; i++) tickets.add("票编号：" + i);
    }

    public static void main(String[] args) {
        for (int i = 0; i < Constants.THREAD_COUNT; i++) {
            new Thread(() -> {
                while (true) {
                    //这个原子性的实现不是用synchronized实现，使用无锁CAS实现的,这个poll 不是阻塞的
                    String poll = tickets.poll();
                    if (poll == null) {
                        System.out.println("over");
                        break;
                    }
                    System.out.println(Thread.currentThread().getName() + "售出了票" + poll);
                }
            }).start();
        }
    }
}
