package com.serendipity.learn.c_032_threadpool;

import java.util.concurrent.Executor;

/**
 * Copyright (C), 2017-2021, 赵旭
 * Author: 11931
 * Date: 2021/10/5 0:06
 * FileName: T01_MyExecutor
 * Description: com.java.thread.c_032_threadpool
 */
public class T01_MyExecutor implements Executor {
    @Override
    public void execute(Runnable command) {
        command.run();
    }

    public static void main(String[] args) {
        T01_MyExecutor t01_myExecutor = new T01_MyExecutor();

        // 类比new Thread(()->{})
        t01_myExecutor.execute(()->{
            System.out.println("haha");
        });
    }
}
