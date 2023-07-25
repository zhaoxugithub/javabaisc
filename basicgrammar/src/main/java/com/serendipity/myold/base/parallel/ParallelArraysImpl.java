package com.serendipity.myold.base.parallel;

import java.util.Arrays;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Copyright (C), 2017-2022, 赵旭
 * Author: 11931
 * Date: 2022/4/20 13:22
 * FileName: ParallelArrays
 * Description: com.java8.base
 */
public class ParallelArraysImpl implements ParallelArrays {

    @Override
    public void testParallel() {
        long[] longs = new long[20000000];
        Arrays.parallelSetAll(longs, index -> ThreadLocalRandom.current().nextInt(10000000));
        Arrays.parallelSort(longs);
        Arrays.stream(longs).limit(100).forEach(i -> System.out.print(i + " "));
        System.out.println();
    }

    @Override
    public void testFor() {
        long[] arrLong = new long[20000000];
        for (int i = 0; i < arrLong.length; i++) {
            arrLong[i] = new Random().nextInt(10000000);
        }
        Arrays.sort(arrLong);
        Arrays.stream(arrLong).limit(100).forEach(i -> System.out.print(i + " "));
        System.out.println();
    }

    @Override
    public void testForWithOutPar() {
        long[] arrLong = new long[2000000];
        for (int i = 0; i < arrLong.length; i++) {
            arrLong[i] = new Random().nextInt(100000);
        }
//        Arrays.stream(arrLong).limit(20000).forEach(i -> System.out.print(""));
    }
}
