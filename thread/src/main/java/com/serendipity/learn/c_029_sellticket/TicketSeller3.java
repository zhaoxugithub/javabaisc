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

    private static LinkedList<String> list  = new LinkedList<>();

    static {
        for (int i = 0; i < Constants.TICKET_NUMS; i++) {
            list.add("票号："+i);
        }
    }

    public static void main(String[] args) {
        for (int i = 0; i < Constants.THREAD_COUNT; i++) {
            new Thread(()->{
                while(true){
                    synchronized (list){
                        if(list.size() <= 0) break;

//                        try {
//                            TimeUnit.SECONDS.sleep(1);
//                        } catch (InterruptedException e) {
//                            e.printStackTrace();
//                        }
                        System.out.println(Thread.currentThread().getName()+"售出了票" + list.remove(0));
                    }

                }
            },"t"+i).start();
        }
    }

}
