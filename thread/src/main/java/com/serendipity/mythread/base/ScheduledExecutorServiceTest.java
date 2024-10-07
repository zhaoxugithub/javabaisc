package com.serendipity.mythread.base;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class ScheduledExecutorServiceTest {

    public static void main(String[] args) {
        ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
        // 定时执行任务
        scheduler.schedule(() -> System.out.println("Task executed after 5 seconds"), 5, TimeUnit.SECONDS);
        // 周期性执行任务,每隔三秒执行一次
        scheduler.scheduleAtFixedRate(() -> {
            System.out.println("Periodic task executed every 3 seconds");
        }, 0, 3, TimeUnit.SECONDS);
    }
}
