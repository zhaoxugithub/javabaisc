package com.serendipity.mythread.base;

import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

/**
 * ClassName MyThreadFuture
 * Description TODO
 * Author 11931
 * Date 2023-06-10:13:57
 * Version 1.0
 **/
public class MyThreadFuture {
    private class Myfuture implements Future<String> {
        @Override
        public boolean cancel(boolean mayInterruptIfRunning) {
            return false;
        }

        @Override
        public boolean isCancelled() {
            return false;
        }

        @Override
        public boolean isDone() {
            return false;
        }

        @Override
        public String get() {
            return null;
        }

        @Override
        public String get(long timeout, TimeUnit unit) {
            return null;
        }
    }

    public static void main(String[] args) {
        // Future 也无法像runnable一样给thread传参
        Thread thread = new Thread();
        thread.start();
    }
}
