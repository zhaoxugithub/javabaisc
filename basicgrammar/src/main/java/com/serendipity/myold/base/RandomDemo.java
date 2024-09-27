package com.serendipity.myold.base;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Arrays;
import java.util.Random;
import java.util.stream.IntStream;

/**
 * Copyright (C), 2017-2022, 赵旭
 * Author: 11931
 * Date: 2022/4/5 0:13
 * FileName: PointMain
 * Description: com.base
 */
@SuppressWarnings("all")
@Slf4j
public class RandomDemo {
    // 拥有相同种子的 Random 实例，在相同次数下，生成的伪随机数完全相同
    @Test
    public void test1() {
        for (int i = 0; i < 100; i++) {
            Random random1 = new Random(10);
            Random random2 = new Random(10);
            log.info("random1.nextInt()={}", random1.nextInt());
            log.info("random2.nextInt()={}", random2.nextInt());
        }
    }

    @Test
    public void test2() {
        Random random = new Random();
        byte[] bytes = new byte[5];
        // 每次给bytes数组里面赋值
        random.nextBytes(bytes);
        log.info(Arrays.toString(bytes));
    }

    /**
     * 无限流
     */
    @Test
    public void test3() {
        Random random = new Random();
        // 获取有效无限伪水机int输入流
        IntStream ints = random.ints();
        ints.forEach(System.out::println);
    }

    @Test
    public void test4() {
        Random random = new Random(100);
        IntStream ints = random.ints(10);
        ints.forEach(System.out::println);
    }

    @Test
    public void test5() {
        // 生成一个随机数x, 0 <= x < 1
        double random = Math.random();
        log.info("Math.random()={}", random);
        // 生成一个区间在[MIN,MAX]的随机数
        double x = Math.random();
        double min = 10;
        double max = 50;
        double y = x * (max - min) + min;
        long n = (long) y;
        log.info("y={}", y);
        log.info("n={},n");
        log.info("-----------------------------------");
        /*
        有些童鞋可能注意到Java标准库还提供了一个StrictMath，它提供了和Math几乎一模一样的方法。
        这两个类的区别在于，由于浮点数计算存在误差，不同的平台（例如x86和ARM）计算的结果可能不一致（指误差不同），
        因此，StrictMath保证所有平台计算结果都是完全相同的，而Math会尽量针对平台优化计算速度，
        所以，绝大多数情况下，使用Math就足够了。
         */
        Random random1 = new Random();
        int i = random1.nextInt();
        //[0,10)
        int i1 = random1.nextInt(10);
        long l = random1.nextLong();
        float v = random1.nextFloat();
        double v1 = random1.nextDouble();
        log.info("random1.nextInt={}", i);
        log.info("random1.nextInt(10)={}", i1);
        log.info("random1.nextLong={}", l);
        log.info("random1.nextFloat={}", v);
        log.info("random1.nextDouble={}", v1);
        //---------------------------------------------------------------
        /*
        前面我们使用的Math.random()实际上内部调用了Random类，所以它也是伪随机数，只是我们无法指定种子。
        使用SecureRandom 产生安全的随机数
         */
        log.info("-----------------------------------");
        SecureRandom secureRandom = new SecureRandom();
        log.info("secureRandom.nextInt={}", secureRandom.nextInt());
        SecureRandom sr;
        try {
            // 获取高强度的安全随机数生成器
            sr = SecureRandom.getInstanceStrong();
        } catch (NoSuchAlgorithmException e) {
            sr = new SecureRandom();
        }
        // 一个字节（byte） 八位,所以范围是 -128~127
        byte[] bytes = new byte[16];
        sr.nextBytes(bytes);
        log.info(Arrays.toString(bytes));
    }
}
