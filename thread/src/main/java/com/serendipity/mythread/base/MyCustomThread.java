package com.serendipity.mythread.base;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.stream.IntStream;

/**
 * ClassName MyCustomThread
 * Description 自定义线程池
 * Author 11931
 * Date 2023-06-09:21:37
 * Version 1.0
 * 博客地址: https://juejin.cn/post/6968721240592744455
 **/
public class MyCustomThread {
    private List<Thread> threads;
    private BlockingQueue<Runnable> tasks;

    public MyCustomThread(BlockingQueue<Runnable> tasks, int threadSize) {
        this.tasks = tasks;
        this.threads = new ArrayList<>(threadSize);
        IntStream.rangeClosed(1, threadSize)
                .forEach(i -> {
                    YesThread yesThread = new YesThread("thread-name-" + i);
                    yesThread.start();
                    threads.add(yesThread);
                });
    }

    public void executeTask(Runnable task) throws InterruptedException {
        tasks.put(task);
    }

    /*
         定义一个线程,run方法里面是这个线程要执行的操作
     */
    private class YesThread extends Thread {
        public YesThread(String threadName) {
            super(threadName);
        }

        // run 方法什么时候开始执行?
        // 只有获取CPU时间片的的时候才会调用
        public void run() {
            while (true) {
                try {
                    Runnable take = tasks.take();
                    take.run();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }

    public static void main(String[] args) {
        MyCustomThread myCustomThread = new MyCustomThread(new LinkedBlockingQueue<>(10), 3);
        IntStream.rangeClosed(1, 5).forEach(i -> {
            try {
                myCustomThread.executeTask(() -> System.out.println(Thread.currentThread().getName() + "zhh"));
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });
    }
}

