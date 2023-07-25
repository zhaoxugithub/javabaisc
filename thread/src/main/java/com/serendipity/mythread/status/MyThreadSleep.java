package com.serendipity.mythread.status;

/**
 * ClassName MyThreadSleep
 * Description TODO
 * Author 11931
 * Date 2023-06-12:23:25
 * Version 1.0
 **/
public class MyThreadSleep {
    private static final int SLEEP_GAP = 5000;
    private static final int MAX_TURN = 50;
    private static class SleepThread extends Thread {
        static int threadSeqNumber = 1;

        public SleepThread() {
            super("sleepThread-" + threadSeqNumber);
            threadSeqNumber++;
        }

        @Override
        public void run() {
            for (int i = 0; i < MAX_TURN; i++) {
                System.out.println(getName() + ",睡眠轮次: " + i);
                try {
                    Thread.sleep(SLEEP_GAP);
                } catch (InterruptedException e) {
                    System.out.println(getName() + " 发生异常被中断..");
                }
                System.out.println(getName() + " 运行结束.");
            }
        }
    }

    public static void main(String[] args) {
        for (int i = 0; i < 5; i++) {
            SleepThread sleepThread = new SleepThread();
            sleepThread.start();
        }
        System.out.println(Thread.currentThread().getName() + " 运行结束.");
    }
}
