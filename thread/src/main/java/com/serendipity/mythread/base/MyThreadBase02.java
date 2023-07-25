package com.serendipity.mythread.base;


import java.util.concurrent.CountDownLatch;

public class MyThreadBase02 {
    private static Long a = 300L;
    private static String str = "a";
    public static void main(String[] args) throws InterruptedException {
        CountDownLatch countDownLatch = new CountDownLatch(100);
        for (int i = 0; i < 100; i++) {
            int finalI = i;
            new Thread(() -> {
                a = a + 1;
                str = str + String.valueOf(finalI);
                countDownLatch.countDown();
            }).start();
        }
        countDownLatch.await();
        System.out.println(a);
        System.out.println(str);
    }
}
