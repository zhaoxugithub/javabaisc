package com.serendipity.learn.c_003;

/**
 * Copyright (C), 2017-2021, 赵旭
 * Author: serendipity
 * Date: 2021/9/12 10:42 下午
 * FileName: T4
 * Description: com.java.thread.c_003
 */

class MyThread extends Thread {
    public void run() {
        super.run();
        for (int i = 0; i < 500000; i++) {
            i++;
        }
    }
}

public class T4 {

    public static void main(String args[]) {
        Thread thread = new MyThread();
        thread.start();
        try {
            Thread.sleep(2000);
            thread.interrupt();
            // 表示thread线程是否是中断的
            System.out.println(thread.isInterrupted());
            System.out.println("stop 1->" + thread.interrupted());
            System.out.println("stop 2->" + thread.interrupted());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
