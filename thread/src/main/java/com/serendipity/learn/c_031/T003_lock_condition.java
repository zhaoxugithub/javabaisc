package com.serendipity.learn.c_031;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Copyright (C), 2017-2021, 赵旭
 * Author: 11931
 * Date: 2021/10/4 18:16
 * FileName: T003_lock_condition
 * Description: com.java.thread.c_031
 */
public class T003_lock_condition {
    public static void main(String[] args) {

        Lock lock = new ReentrantLock();
        Condition condition = lock.newCondition();


        String nums = "1234567";
        String str = "ABCDEFG";
        char[] numsChar = nums.toCharArray();
        char[] chars = str.toCharArray();
        new Thread(() -> {
            try {
                lock.lock();
                for (char c : numsChar) {
                    System.out.print(c);
                    condition.signal();
                    condition.await();
                }
                System.out.println("12312");
                condition.signal();
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                System.out.println("线程结束");
                lock.unlock();
            }
        }).start();

        new Thread(() -> {
            try {
                lock.lock();
                for (char c : chars) {
                    System.out.print(c);
                    condition.signal();
                    condition.await();
                }
                condition.signal();
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        }).start();
    }
}
