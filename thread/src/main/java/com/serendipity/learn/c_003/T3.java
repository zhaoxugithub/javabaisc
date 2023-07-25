package com.serendipity.learn.c_003;

/**
 * Copyright (C), 2017-2021, 赵旭
 * Author: serendipity
 * Date: 2021/9/9 10:02 下午
 * FileName: T3
 * Description: com.java.thread.c_003
 */
public class T3 {

    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(() -> {
            while (true) {
                // 响应中断
                if (Thread.currentThread().isInterrupted()) {
                    System.out.println("青秧线程被中断，程序退出。");
                    return;
                }
                try {
                    //执行 thread.interrupt()这个方法会导致sleep会被中断。
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    System.out.println("青秧线程休眠被中断，程序退出。");
                    Thread.currentThread().interrupt();
                }
            }
        });
        thread.start();
        Thread.sleep(2000);
        thread.interrupt();


    }
}
