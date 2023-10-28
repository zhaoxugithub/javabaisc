package com.serendipity.learn.c_029_sellticket;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Copyright (C), 2017-2021, 赵旭
 * Author: serendipity
 * Date: 2021/9/12 6:37 下午
 * FileName: TicketSeller1
 * Description: com.java.thread.c_029_sellticket
 */
@SuppressWarnings("all")
public class TicketSeller1 {
    private static List<String> list = new ArrayList<>();

    static {
        for (int i = 0; i < Constants.TICKET_NUMS; i++) {
            list.add("票号：" + i);
        }
    }

    public static void main(String[] args) {
        for (int i = 0; i < Constants.THREAD_COUNT; i++) {
            new Thread(() -> {
                while (list.size() > 0) {
                    System.out.println(Thread.currentThread().getName() + "售出了票" + list.remove(0));
                    try {
                        TimeUnit.SECONDS.sleep(1);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }, "t" + i).start();
        }
    }
}
