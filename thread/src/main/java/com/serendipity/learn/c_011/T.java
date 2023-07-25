package com.serendipity.learn.c_011;

import java.util.concurrent.TimeUnit;

/**
 * Copyright (C), 2017-2021, 赵旭
 * Author: serendipity
 * Date: 2021/9/1 7:04 下午
 * FileName: T
 * Description: com.java.thread.c_011
 * <p>
 * <p>
 * readme
 * synchronized的底层实现
 * JDK早期的 重量级 - OS
 * 后来的改进
 * 锁升级的概念：
 * 我就是厕所所长 （一 二）
 * <p>
 * sync (Object)
 * markword 记录这个线程ID （偏向锁）
 * 如果线程争用：升级为 自旋锁
 * 10次以后，
 * 升级为重量级锁 - OS
 * <p>
 * 执行时间短（加锁代码），线程数少，用自旋
 * 执行时间长，线程数多，用系统锁
 */
public class T {

    private int count;

    public synchronized void m() {
        System.out.println(Thread.currentThread().getName() + ":" + count);
        while (true) {
            System.out.println(count++);
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if (count == 5) {
                try {
                    int r = 1 / 0; ////此处抛出异常，锁将被释放，要想不被释放，可以在这里进行catch，然后让循环继续
                    System.out.println(r);
                } catch (Exception e) {
                    //加上这个可以让t2线程执行
                    try {
                        this.wait();
                    } catch (InterruptedException ex) {
                        ex.printStackTrace();
                    }
                    continue;
//                    e.printStackTrace();
                }
            }
        }
    }

    public static void main(String[] args) {
        T t = new T();
        new Thread(t::m, "t1").start();
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        new Thread(t::m, "t2").start();
    }
}
