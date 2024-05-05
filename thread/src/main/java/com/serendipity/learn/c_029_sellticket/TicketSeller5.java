package com.serendipity.learn.c_029_sellticket;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Copyright (C), 2017-2021, 赵旭
 * Author: 11931
 * Date: 2021/10/1 13:59
 * FileName: TicketSeller5
 * Description: com.java.thread.c_029_sellticket
 */
public class TicketSeller5 {

    static List<String> list = Collections.synchronizedList(new LinkedList<>());
    public static final int TIMEOUT = 1;

    static {
        for (int i = 0; i < Constants.TICKET_NUMS; i++) {
            list.add("票号：" + i);
        }
    }

    public static void main(String[] args) {
        for (int i = 0; i < Constants.THREAD_COUNT; i++) {
            new Thread(() -> {
                while (!list.isEmpty()) {
                    try {
                        TimeUnit.SECONDS.sleep(TIMEOUT);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    //可能在list.remove()方法的时候出现.ArrayIndexOutOfBoundsException（数组下标越界）,
                    // 因为在list.size()和list.remove方法之间不能保证原子性
                    System.out.println(Thread.currentThread().getName() + "售出了票" + list.remove(0));

                }
            }, "t" + i).start();
        }
    }
}
