package com.serendipity.learn.c_030;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.DelayQueue;
import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

/**
 * Copyright (C), 2017-2021, 赵旭
 * Author: 11931
 * Date: 2021/10/2 17:32
 * FileName: DelayQueue_v1
 * Description: com.java.thread.c_030
 * <p>
 * <p>
 * 主要用作按照时间进行任务调度：也是阻塞队列
 */
public class DelayQueue_v1 {

    private static BlockingQueue tasks = new DelayQueue();

    static class MyTask implements Delayed {

        private String name;
        private long runTime;

        public MyTask(String name, long runTime) {
            this.name = name;
            this.runTime = runTime;
        }

        @Override
        public long getDelay(TimeUnit unit) {
            return unit.convert(runTime - System.currentTimeMillis(), TimeUnit.MILLISECONDS);
        }

        @Override
        public int compareTo(Delayed o) {
            if (this.getDelay(TimeUnit.MILLISECONDS) < o.getDelay(TimeUnit.MILLISECONDS))
                return -1;
            else if (this.getDelay(TimeUnit.MILLISECONDS) > o.getDelay(TimeUnit.MILLISECONDS))
                return 1;
            else
                return 0;
        }
    }


    public static void main(String[] args) throws InterruptedException {
        long now = System.currentTimeMillis();
        MyTask t1 = new MyTask("t1", now + 1000);
        MyTask t2 = new MyTask("t2", now + 2000);
        MyTask t3 = new MyTask("t3", now + 1500);
        MyTask t4 = new MyTask("t4", now + 2500);
        MyTask t5 = new MyTask("t5", now + 500);
        tasks.put(t1);
        tasks.put(t2);
        tasks.put(t3);
        tasks.put(t4);
        tasks.put(t5);
        System.out.println(tasks);
        for (int i = 0; i < 5; i++) {
            System.out.println(tasks.take());
        }
    }
}
