package com.serendipity.learn.c_032_threadpool;

import org.junit.Test;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
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

    @Test
    public void test03() {
        // 异步任务，无返回值，采用内部的forkjoin线程池
        CompletableFuture<Void> future1 = CompletableFuture.runAsync(() -> {
            System.out.println("步任务，无返回值，采用内部的forkjoin线程池");
        });

        // 异步任务,无返回值,使用自定义线程池
        CompletableFuture<Void> future2 = CompletableFuture.runAsync(() -> System.out.println("异步任务,无返回值,使用自定义线程池"));

        // 异步任务,有返回值,使用内部默认的线程池
        CompletableFuture<String> future3 = CompletableFuture.supplyAsync(() -> {
            System.out.println("异步任务,有返回值,使用内部默认的线程池");
            return "over1";
        });

        // 只要有一个完成,则完成,有一个抛出异常,则携带异常
        CompletableFuture.anyOf(future1, future3);

        // 都要完成才算完成
        CompletableFuture.allOf(future2, future3);
    }

    @Test
    public void test04() throws ExecutionException, InterruptedException {
        /**
         * supplyAsync -> thenAcceptAsync ->thenRunAsync 这三个方法是串行的,只不过是提交给了另外的线程池去执行
         */
        CompletableFuture<Void> future = CompletableFuture.supplyAsync(() -> {
                                                              try {
                                                                  System.out.println("[" + System.currentTimeMillis() + "]:" + Thread.currentThread()
                                                                                                                                     .getName() + ":[supplyAsync start]");
                                                                  TimeUnit.SECONDS.sleep(5);
                                                                  System.out.println("[" + System.currentTimeMillis() + "]:" + Thread.currentThread()
                                                                                                                                     .getName() + ":[supplyAsync end]");
                                                              } catch (InterruptedException e) {
                                                                  throw new RuntimeException(e);
                                                              }
                                                              return "over1";
                                                          })
                                                          .thenAcceptAsync(result -> {
                                                              try {
                                                                  System.out.println("[" + System.currentTimeMillis() + "]:" + Thread.currentThread()
                                                                                                                                     .getName() + ":[thenAcceptAsync start]");
                                                                  TimeUnit.SECONDS.sleep(3);
                                                              } catch (InterruptedException e) {
                                                                  throw new RuntimeException(e);
                                                              }
                                                              System.out.println("[" + System.currentTimeMillis() + "]:" + Thread.currentThread()
                                                                                                                                 .getName() + ":[thenAcceptAsync end]");
                                                          })
                                                          .thenRunAsync(() -> {
                                                              try {
                                                                  System.out.println("[" + System.currentTimeMillis() + "]:" + Thread.currentThread()
                                                                                                                                     .getName() + ":[thenRunAsync start]");
                                                                  TimeUnit.SECONDS.sleep(1);
                                                              } catch (InterruptedException e) {
                                                                  throw new RuntimeException(e);
                                                              }
                                                              System.out.println("[" + System.currentTimeMillis() + "]:" + Thread.currentThread()
                                                                                                                                 .getName() + ":[thenRunAsync end]");
                                                          });

        // thread.sleep/future.join 的原因: 防止主线程提前结束,上面打印不出来
        // Thread.sleep(10000);
        future.join();
        System.out.println(Thread.currentThread()
                                 .getName() + ":over");

    }

    private void printMsg(String msg) {
        System.out.println("[" + System.currentTimeMillis() + "]:" + Thread.currentThread()
                                                                           .getName() + ":[" + msg + "]");
    }

    @Test
    public void test06() throws InterruptedException {
        CompletableFuture<String> future1 = CompletableFuture.supplyAsync(() -> {
            System.out.println("[" + System.currentTimeMillis() + "]:" + Thread.currentThread()
                                                                               .getName() + ":[supplyAsync1 start]");
            return "supplyAsync1 end";
        });

        CompletableFuture<String> future2 = CompletableFuture.supplyAsync(() -> {
            System.out.println("[" + System.currentTimeMillis() + "]:" + Thread.currentThread()
                                                                               .getName() + ":[supplyAsync2 start]");
            return "supplyAsync2 end";
        });

        // thenCombineAsync有入参，有返回值
        future1.thenCombineAsync(future2, (f1, f2) -> {
            // 这里必须要等到future1和future2都执行完之后再回执行这里
            System.out.println("exec f1=" + f1 + ",f2=" + f2);
            return f1 + f2;
        });

        // 有入参，无返回值
        future1.thenAcceptBothAsync(future2, (f1, f2) -> {
            // 这里必须要等到future1和future2都执行完之后再回执行这里
            System.out.println("exec f1=" + f1 + ",f2=" + f2);
        });

        // 无入参，入参会之
        future1.runAfterBothAsync(future2, () -> {
            // 这里必须要等到future1和future2都执行完之后再回执行这里
            System.out.println("exec");
        });

        // 或者直接连接两个CompletableFuture
        future1.thenComposeAsync(r -> CompletableFuture.supplyAsync(() -> {
            System.out.println("开始煮牛奶");
            System.out.println("同时开始煮米饭");
            return "mike";
        }));


        Thread.sleep(5000);

    }

    @Test
    public void test07() throws InterruptedException {
        // whenCompleteAsync：处理完成或异常，无返回值
        // handleAsync：处理完成或异常，有返回值
        CompletableFuture.supplyAsync(() -> {
                             printMsg("开始煮米饭");
                             // todo something
                             return "煮熟的米饭";
                         })
                         .whenCompleteAsync((rich, exception) -> {
                             if (exception != null) {
                                 printMsg("电饭锅坏了,米饭没做熟");
                             } else {
                                 printMsg("米饭熟了,可以吃了");
                             }
                         });

        // 有返回值
        CompletableFuture.supplyAsync(() -> {
                             printMsg("开始煮米饭");
                             return "煮熟的米饭";
                         })
                         .handleAsync((rich, exception) -> {
                             if (exception != null) {
                                 printMsg("电饭锅坏了,米饭没做熟");
                             } else {
                                 printMsg("米饭熟了,可以吃了");
                             }

                             return "准备冷一冷再吃米饭";
                         });

        // 异常处理
        CompletableFuture.supplyAsync(() -> {
                             printMsg("开始煮米饭");
                             return "煮熟的米饭";
                         })
                         .handleAsync((rich, exception) -> {
                             if (exception != null) {
                                 printMsg("电饭煲坏了,米饭没做熟");
                             } else {
                                 printMsg("米饭熟了,可以吃了");
                             }
                             return "准备冷一冷再吃米饭";
                         })
                         .exceptionally((exception) -> {
                             // 前置动作必须的是一个又返回值的操作，不能是那种返回值的那种
                             return "";
                         });
        Thread.sleep(10000);
    }
}
