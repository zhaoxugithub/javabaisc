package com.serendipity.learn.c_032_threadpool;

import org.junit.Test;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

/**
 * ClassName CompletableFuture01
 * Description TODO
 * Author 11931
 * Date 2023-08-25:17:40
 * Version 1.0
 * https://juejin.cn/post/6844904195162636295
 **/
public class CompletableFuture01 {
    @Test
    public void test01() throws InterruptedException {
        CompletableFuture.supplyAsync(() -> {
                             System.out.println("[" + System.currentTimeMillis() + "]:电饭煲开始做饭");
                             try {
                                 TimeUnit.SECONDS.sleep(3);
                             } catch (InterruptedException e) {
                                 throw new RuntimeException(e);
                             }
                             return "白米饭";
                         })
                         .thenAccept(result -> {
                             // 基于回调通知,当上述方法完成之后就会调用这个这里面的代码块
                             System.out.println("[" + System.currentTimeMillis() + "]:开始吃" + result);
                         });
        System.out.println("[" + System.currentTimeMillis() + "]:我想去搞点牛奶和鸡蛋");
        System.out.println("[" + System.currentTimeMillis() + "]:main start...");
        Thread.sleep(4000);
        System.out.println("[" + System.currentTimeMillis() + "]:main end...");
        /*
        [1692957238738]:电饭煲开始做饭
        [1692957238739]:我想去搞点牛奶和鸡蛋
        [1692957238743]:main start...
        [1692957241756]:开始吃白米饭
        [1692957242748]:main end...
         */
    }


    @Test
    public void test02() throws InterruptedException {
        CompletableFuture<Void> future = CompletableFuture.supplyAsync(() -> {
                                                              System.out.println("[" + System.currentTimeMillis() + "]:电饭煲开始做饭");
                                                              try {
                                                                  TimeUnit.SECONDS.sleep(3);
                                                              } catch (InterruptedException e) {
                                                                  throw new RuntimeException(e);
                                                              }
                                                              return "白米饭";
                                                          })
                                                          .thenAccept(result -> {
                                                              // 基于回调通知,当上述方法完成之后就会调用这个这里面的代码块
                                                              System.out.println("[" + System.currentTimeMillis() + "]:开始吃" + result);
                                                          });
        System.out.println("[" + System.currentTimeMillis() + "]:我想去搞点牛奶和鸡蛋");
        future.join();
        System.out.println("[" + System.currentTimeMillis() + "]:main start...");
        Thread.sleep(4000);
        System.out.println("[" + System.currentTimeMillis() + "]:main end...");
        /*
        [1692957309488]:我想去搞点牛奶和鸡蛋
        [1692957309488]:电饭煲开始做饭
        [1692957312501]:开始吃白米饭
        [1692957312507]:main start...
        [1692957316516]:main end...
         */
    }

    public static void main(String[] args) {

    }
}
