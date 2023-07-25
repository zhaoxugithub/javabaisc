package com.serendipity.learn.c_001;

/**
 * Copyright (C), 2017-2021, 赵旭
 * Author: serendipity
 * Date: 2021/9/22 8:07 下午
 * FileName: InterruptExample
 * Description: com.java.thread.c_001
 */
public class InterruptExample {

    private static class MyThread1 extends Thread {
        @Override
        public void run() {
//            try {
//                synchronized (MyThread1.class) {
//                    Thread.sleep(2000);
//                    System.out.println("Thread run");
//                }
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
            while (!isInterrupted()) {
            }
            System.out.println("interrupted over");
        }
    }


    public static void main(String[] args) throws InterruptedException {
        Thread thread1 = new MyThread1();
        thread1.start();
        Thread.sleep(10000);
        thread1.interrupt();
        System.out.println("Main run");
    }


}
