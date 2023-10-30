package com.serendipity.mythread.virtual;

import cn.hutool.core.date.DateUtil;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.lang.management.ManagementFactory;
import java.lang.management.ThreadInfo;
import java.lang.management.ThreadMXBean;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * ClassName MyVirtualTest01
 * Description TODO
 * Author 11931
 * Date 2023/9/21:17:13
 * Version 1.0
 **/
@Slf4j
public class MyVirtualTest01 {

    class CustomThread implements Runnable {
        @Override
        public void run() {
            System.out.println("CustomThread");
        }
    }

    /**
     * 创建虚拟线程
     */
    @Test
    public void test01() {
        CustomThread customThread = new CustomThread();
        Thread.startVirtualThread(customThread);
    }


    @Test
    public void test02() {
        CustomThread customThread = new CustomThread();
        Thread thread = Thread.ofVirtual()
                              .unstarted(customThread);

        thread.start();
    }


    @Test
    public void test03() {
        CustomThread customThread = new CustomThread();
        ThreadFactory factory = Thread.ofVirtual()
                                      .factory();
        Thread thread = factory.newThread(customThread);
        thread.start();
    }

    @Test
    public void test04() {
        CustomThread customThread = new CustomThread();
        ExecutorService executorService = Executors.newVirtualThreadPerTaskExecutor();
        executorService.submit(customThread);
    }

    @Test
    public void test05() {
        ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(1);
        scheduledExecutorService.scheduleAtFixedRate(() -> {
            ThreadMXBean threadMXBean = ManagementFactory.getThreadMXBean();
            ThreadInfo[] threadInfos = threadMXBean.dumpAllThreads(false, false);
            updateMaxThreadNum(threadInfos.length);
        }, 10, 10, TimeUnit.MILLISECONDS);
        long start = System.currentTimeMillis();
        ExecutorService executorService = Executors.newVirtualThreadPerTaskExecutor();
        for (int i = 0; i < 10000; i++) {
            executorService.submit(() -> {
                try {
                    TimeUnit.MILLISECONDS.sleep(500);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            });
        }
        executorService.close();
        System.out.println("max：" + list.get(0) + " platform thread/os thread");
        System.out.printf("totalMillis：%dms\n", System.currentTimeMillis() - start);
    }


    List<Integer> list = new ArrayList<>();

    private void updateMaxThreadNum(int num) {
        if (list.isEmpty()) {
            list.add(num);
        } else {
            Integer integer = list.get(0);
            if (num > integer) {
                list.add(0, num);
            }
        }
    }

    @Test
    public void test06() {
        var a = new AtomicInteger(0);
        // 创建一个固定200个线程的线程池
        try (var vs = Executors.newFixedThreadPool(200)) {
            List<Future<Integer>> futures = new ArrayList<>();
            var begin = System.currentTimeMillis();
            // 向线程池提交1000个sleep 1s的任务
            for (int i = 0; i < 1_000; i++) {
                var future = vs.submit(() -> {
                    Thread.sleep(Duration.ofSeconds(1));
                    return a.addAndGet(1);
                });
                futures.add(future);
            }
            // 获取这1000个任务的结果
            for (var future : futures) {
                var i = future.get();
                if (i % 100 == 0) {
                    System.out.print(i + " ");
                }
            }
            // 打印总耗时
            System.out.println("Exec finished.");
            System.out.printf("Exec time: %dms.%n", System.currentTimeMillis() - begin);
        } catch (ExecutionException | InterruptedException e) {
            e.printStackTrace();
        }
    }


    private void plateThread() {

    }

    private void vmThread() {
        ExecutorService es = Executors.newVirtualThreadPerTaskExecutor();
        for (int i = 0; i < 100000; i++) {
            es.submit(() -> {
                while (true) {
//                    System.out.println(Thread.currentThread()+"-"+DateUtil.now());
                    log.info("线程名称：{},时间:{}", Thread.currentThread(), DateUtil.now());
                    System.out.println("哈哈哈");
                    Thread.sleep(0);
                }
            });
        }

        // 如果不执行此代码，不会像用户线程一样一直执行，main线程一结束，那么协程就执行完毕，跟go一样，必须要有线程保持运行机制
        while (true) {
            System.out.println(Thread.currentThread() + "-" + DateUtil.now());
            try {
                Thread.sleep(1000 * 30);
                break;
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    @Test

    public void test07() {
        // vmThread();
        test09();
        test08();
    }


    public void test08() {
        ExecutorService es = Executors.newVirtualThreadPerTaskExecutor();
        long start = System.currentTimeMillis();
        for (int i = 0; i < 100000; i++) {
            es.submit(() -> {
                Thread.sleep(1000);
                return 0;
            });
        }
        es.close();
        long end = System.currentTimeMillis();
        System.out.printf("All virtual threads end at %s ms.\n", end - start);
    }


    public void test09(){
        ExecutorService es = Executors.newCachedThreadPool();
        long start = System.currentTimeMillis();
        for (int i = 0; i < 100000; i++) {
            es.submit(() -> {
                Thread.sleep(1000);
                return 0;
            });
        }
        es.close();
        long end = System.currentTimeMillis();
        System.out.printf("All plate threads end at %s ms.\n", end - start);
    }
}

