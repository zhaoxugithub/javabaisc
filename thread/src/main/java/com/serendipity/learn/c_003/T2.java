package com.serendipity.learn.c_003;

/**
 * Copyright (C), 2017-2021, 赵旭
 * Author: serendipity
 * Date: 2021/9/9 9:34 下午
 * FileName: T
 * Description: com.java.thread.c_003
 *
 *
 * 中断：只是当前线程和目标线程进行通信的一个方法
 */
public class T2{
    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(() -> {
            while (true) {
                // 判断是否被中断，不会清除中断状态
                if (Thread.currentThread().isInterrupted()) {
                    System.out.println("青秧线程被中断，程序退出。");
                    break;
                }
                try {
                    //执行 thread.interrupt()这个方法会导致sleep会被中断。所以这条代码会抛出异常
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    System.out.println("青秧线程休眠被中断，程序退出。。。。");
                    Thread.currentThread().interrupt();
                }
            }
        });
        thread.start();
        Thread.sleep(2000);
        thread.interrupt();

        boolean interrupted = thread.isInterrupted();
        System.out.println(interrupted);
    }
}
