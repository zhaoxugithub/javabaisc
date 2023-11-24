package com.serendipity.learn.c_000;

import lombok.extern.slf4j.Slf4j;

/**
 * Copyright (C), 2017-2021, 赵旭
 * Author: serendipity
 * Date: 2021/8/31 12:13 上午
 * FileName: T03_Sleep_Yield_Join
 * Description: com.java.thread.c_001
 * <p>
 * <p>
 * sleep,wait,join 区别
 */
@Slf4j
@SuppressWarnings("all")
public class T03_Sleep_Yield_Join {
    public static void main(String[] args) {
        testSleep();
        // testYield();
        // testJoin();
    }

    /**
     * @Description: t.join(), t线程抢占执行
     * @Param:
     * @return:
     * @Author: serendipity
     * @Date: 2021/8/31
     */
    public static void testJoin() {
        Thread thread = new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                log.info("A={}", i);
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    log.error("exception={}", e.getMessage());
                }
            }
        });

        Thread thread1 = new Thread(() -> {
            try {
                // 先去执行thread方法，底层是调用wait方法，让当前线程先进入到等待队列
                thread.join();
            } catch (InterruptedException e) {
                log.error("exception={}", e.getMessage());
            }
            for (int i = 0; i < 10; i++) {
                log.info("B={}", i);
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    log.error("exception={}", e.getMessage());
                }
            }
        });
        thread.start();
        thread1.start();
    }

    /**
     * @Description: 在线程操作中，也可以使用 yield() 方法将一个线程的操作暂时让给其他线程执行
     * @Param:
     * @return:
     * @Author: serendipity
     * @Date: 2021/8/31
     */
    public static void testYield() {
        new Thread(() -> {
            for (int i = 0; i < 100; i++) {
                log.info("A" + i);
                // 虽然yield，但是并不表示其他线程就一定执行，有可能依然是当前线程执行
                if (i % 2 == 0) Thread.yield();
            }
        }).start();
        new Thread(() -> {
            for (int i = 0; i < 100; i++) {
                log.info("B" + i);
                if (i % 2 == 1) Thread.yield();
            }
        }).start();
    }

    /**
     * @Description:
     * @Param:
     * @return:
     * @Author: serendipity
     * @Date: 2021/8/31
     */
    public static void testSleep() {
        new Thread(() -> {
            for (int i = 0; i < 100; i++) {
                log.info("A={}" + i);
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    log.error("exception={}", e.getMessage());
                }
            }
        }).start();
    }
}
