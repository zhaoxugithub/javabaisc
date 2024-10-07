package com.serendipity.java21.virtualThread;

public class VirtualThread03 {


    public static void main(String[] args) {
        test02();
    }

    // 所有的虚拟线程都是守护线程，所以主线程结束后，虚拟线程也会结束
    public static void test01() {
        Thread thread = Thread.ofVirtual()
                              .name("VirtualThread03")
                              .unstarted(() -> {
                                  while (true) {
                                      System.out.println("VirtualThread03 is running");
                                  }
                              });
        System.out.println("main Starting VirtualThread03");
        thread.start();
    }

    public static void test02() {
        Thread thread = Thread.ofPlatform()
                              .name("PlatformThread03")
                              .unstarted(() -> {
                                  while (true) {
                                      System.out.println("PlatformThread03 is running");
                                  }
                              });
        System.out.println("main Starting PlatformThread03");
        thread.start();
    }
}
