package com.serendipity.learn.c_031;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Copyright (C), 2017-2021, 赵旭
 * Author: 11931
 * Date: 2021/10/4 22:20
 * FileName: T004_lock_condition
 * Description: com.java.thread.c_031
 */
public class T004_lock_condition {


    public static void main(String[] args) {

        Lock lock = new ReentrantLock();

        //一个condition 可以理解成一个等待队列
        Condition condition1 = lock.newCondition();
        Condition condition2 = lock.newCondition();

        String nums = "1234567";
        String str = "ABCDEFG";
        char[] numsChar = nums.toCharArray();
        char[] chars = str.toCharArray();
        new Thread(() -> {
            try {
                lock.lock();
                for (char c : numsChar) {
                    System.out.print(c);
                    condition2.signal();
                    condition1.await();
                }
                condition2.signal();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        }).start();

        new Thread(() -> {
            try {
                lock.lock();
                for (char c : chars) {
                    System.out.print(c);
                    condition1.signal();
                    condition2.await();
                }
                condition1.signal();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        }).start();
    }
}
