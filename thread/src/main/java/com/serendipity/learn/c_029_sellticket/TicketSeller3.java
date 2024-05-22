package com.serendipity.learn.c_029_sellticket;

import java.util.LinkedList;

/**
 * Copyright (C), 2017-2021, 赵旭
 * Author: serendipity
 * Date: 2021/9/12 7:04 下午
 * FileName: TicketSeller3
 * Description: com.java.thread.c_029_sellticket
 */
public class TicketSeller3 {

    private static final LinkedList<String> list = new LinkedList<>();

    static {
        for (int i = 0; i < Constants.TICKET_NUMS; i++) {
            list.add("票号：" + i);
        }
    }

    public static void main(String[] args) {
        for (int i = 0; i < Constants.THREAD_COUNT; i++) {
            new Thread(() -> {
                while (true) {

                    // 加锁 可以保证 list.size() 和 list.remove() 之间的原子性
                    synchronized (list) {
                        if (list.isEmpty()) break;

//                        try {
//                            TimeUnit.SECONDS.sleep(1);
//                        } catch (InterruptedException e) {
//                            e.printStackTrace();
//                        }
                        System.out.println(Thread.currentThread().getName() + "售出了票" + list.remove(0));
                    }

                }
            }, "t" + i).start();
        }
    }

}
