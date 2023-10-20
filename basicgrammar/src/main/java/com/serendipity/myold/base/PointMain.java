package com.serendipity.myold.base;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Arrays;
import java.util.Random;

/**
 * Copyright (C), 2017-2022, 赵旭
 * Author: 11931
 * Date: 2022/4/5 0:13
 * FileName: PointMain
 * Description: com.base
 */
public class PointMain {

    public static void main(String[] args) {
        // 生成一个随机数x, 0 <= x < 1
        double random = Math.random();
        System.out.println(random);
        // 生成一个区间在[MIN,MAX]的随机数
        double x = Math.random();
        double min = 10;
        double max = 50;
        double y = x * (max - min) + min;
        long n = (long) y;

        System.out.println(y);
        System.out.println(n);
        System.out.println("-----------------------------------");

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
        System.out.println(i);
        System.out.println(i1);
        System.out.println(l);
        System.out.println(v);
        System.out.println(v1);
        //---------------------------------------------------------------
        /*
        前面我们使用的Math.random()实际上内部调用了Random类，所以它也是伪随机数，只是我们无法指定种子。
        使用SecureRandom 产生安全的随机数
         */
        System.out.println("-----------------------------------");
        SecureRandom secureRandom = new SecureRandom();
        System.out.println(secureRandom.nextInt());
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
        System.out.println(Arrays.toString(bytes));
    }
}
