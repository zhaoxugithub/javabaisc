package com.serendipity.java21.virtualThread;

import java.util.concurrent.TimeUnit;

public class VirtualThread02 {


    public static void main(String[] args) throws InterruptedException {
        method01();
        method02();
        method03();
        method04();
    }

    // 創建虚拟线程方法一
    public static void method01() throws InterruptedException {
        Thread.startVirtualThread(() -> {
            System.out.println("Virtual Thread 01");
        });
        TimeUnit.SECONDS.sleep(1);
    }

    // 創建虚拟线程方法二
    public static void method02() throws InterruptedException {
        Thread.ofVirtual()
              .name("Virtual Thread 02")
              .start(() -> {
                  System.out.println("Virtual Thread 02");
              });
        TimeUnit.SECONDS.sleep(1);
    }

    // 創建虚拟线程方法三
    public static void method03() throws InterruptedException {
        Thread thread = Thread.ofVirtual()
                              .name("Virtual Thread 03")
                              .unstarted(() -> {
                                  System.out.println("Virtual Thread 03");
                              });
        thread.start();
        System.out.println(thread.isVirtual());
        TimeUnit.SECONDS.sleep(1);
    }

    // 創建平台线程方法
    public static void method04() throws InterruptedException {
        Thread.ofPlatform()
              .name("Platform Thread")
              .priority(1)
              .daemon()
              .start(() -> {
                  System.out.println("Platform Thread");
              });
        TimeUnit.SECONDS.sleep(1);
    }
}
