package com.serendipity.mythread.base;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.RunnableFuture;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/**
 * ClassName MyThreadRunnableFuture
 * Description TODO
 * Author 11931
 * Date 2023-06-10:14:08
 * Version 1.0
 **/
@SuppressWarnings("all")
public class MyThreadRunnableFuture {
    /**
     * runnableFuture继承了runnable和Future接口
     */
    private static class MyRunnableFuture implements RunnableFuture {
        @Override
        public void run() {
        }

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

        /**
         * get方法是阻塞的
         *
         * @return
         * @throws InterruptedException
         * @throws ExecutionException
         */
        @Override
        public Object get() throws InterruptedException, ExecutionException {
            return null;
        }
        @Override
        public Object get(long timeout, TimeUnit unit) throws InterruptedException, ExecutionException, TimeoutException {
            return null;
        }
    }

    public static void main(String[] args) {
        Thread thread = new Thread(new MyRunnableFuture());
        thread.start();
    }
}
