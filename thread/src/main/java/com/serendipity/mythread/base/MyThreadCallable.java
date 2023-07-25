package com.serendipity.mythread.base;

import java.util.concurrent.Callable;

/**
 * ClassName MyThreadCallable
 * Description TODO
 * Author 11931
 * Date 2023-06-10:13:31
 * Version 1.0
 **/
public class MyThreadCallable {
    private class MyCallable implements Callable<String> {
        @Override
        public String call() throws Exception {
            return "无法使用";
        }
    }

    public static void main(String[] args) {
        /**
         * callable 接口无法像runnable接口一样作为入参传给Thread构造器
         */
        Thread thread = new Thread();
        thread.start();
    }
}
