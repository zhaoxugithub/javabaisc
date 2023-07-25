package com.serendipity.mythread.status;

import java.util.ArrayList;
import java.util.List;

public class StatusDemo {
    public static final long MAX_TURN = 5;
    private static int threadSeqNumber = 0;
    private static List<Thread> threadList = new ArrayList<>();

    private static void printThreadStatus() {
        threadList.forEach(thread -> {
            System.out.println(thread.getName() + " 状态为 " + thread.getState());
        });
    }

    private static void addStatusThread(Thread thread) {
        threadList.add(thread);
    }

    private static class StatusDemoThread extends Thread {
        public StatusDemoThread() {
            super("statusPrintThread" + (++threadSeqNumber));
            addStatusThread(this);
        }

        @Override
        public void run() {
            System.out.println(getName() + ", 状态为" + getState());
            for (int turn = 0; turn < MAX_TURN; turn++) {
                try {
                    Thread.sleep(500);
                    printThreadStatus();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                } finally {
                    System.out.println(getName() + "-运行结束.");
                }
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        // 将main线程加入全局列表
        addStatusThread(Thread.currentThread());
        StatusDemoThread thread1 = new StatusDemoThread();
        System.out.println(thread1.getName() + "- 状态为" + thread1.getState());
        StatusDemoThread thread2 = new StatusDemoThread();
        System.out.println(thread2.getName() + "- 状态为" + thread2.getState());
        StatusDemoThread thread3 = new StatusDemoThread();
        System.out.println(thread3.getName() + "- 状态为" + thread3.getState());
        thread1.start();
        Thread.sleep(500);
        thread2.start();
        Thread.sleep(500);
        thread3.start();
        Thread.sleep(100);
    }
}
