package com.serendipity.learn.c_000;

import java.util.concurrent.locks.ReentrantLock;

/**
 * Copyright (C), 2017-2021, 赵旭
 * Author: serendipity
 * Date: 2021/9/8 12:13 上午
 * FileName: T05_SourceCode
 * Description: com.java.thread.c_000
 */
@SuppressWarnings("all")
public class T05_SourceCode {

    public static void main(String[] args) {
        ReentrantLock lock = new ReentrantLock();
        lock.lock();
    }
}
