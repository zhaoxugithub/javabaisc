package com.serendipity.mythread.base;

import java.util.concurrent.TimeUnit;

public class DaemonMain {

    public static void main(String[] args) {
        test2();
    }

    public static void test1() {
        Thread thread = new Thread(() -> {
            while (true) {
                System.out.println("i am user thread");
                try {
                    TimeUnit.MICROSECONDS.sleep(1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        });
        thread.start();
        System.out.println("main thread is running");
    }


    // 如果存在用户线程，jvm 不会退出
    // 如果只有守护线程, jvm 会退出
    public static void test2() {
        Thread thread = new Thread(() -> {
            while (true) {
                System.out.println("i am user thread");
                try {
                    TimeUnit.MICROSECONDS.sleep(1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        });
        thread.setDaemon(true);
        thread.start();
        System.out.println("main thread is running");
    }

}
