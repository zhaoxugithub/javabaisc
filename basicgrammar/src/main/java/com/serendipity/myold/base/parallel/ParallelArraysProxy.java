package com.serendipity.myold.base.parallel;

/**
 * Copyright (C), 2017-2022, 赵旭
 * Author: 11931
 * Date: 2022/4/20 13:31
 * FileName: ParallelArraysParxy
 * Description: com.java8.base.parallel
 */
public class ParallelArraysProxy implements ParallelArrays {

    private ParallelArrays parallelArrays;

    public ParallelArraysProxy(ParallelArrays parallelArrays) {
        this.parallelArrays = parallelArrays;
    }

    @Override
    public void testParallel() {
        long start = System.currentTimeMillis();
        parallelArrays.testParallel();
        System.out.println(System.currentTimeMillis() - start);

    }

    @Override
    public void testFor() {
        long start = System.currentTimeMillis();
        parallelArrays.testFor();
        System.out.println(System.currentTimeMillis() - start);
    }

    @Override
    public void testForWithOutPar() {
        long start = System.currentTimeMillis();
        parallelArrays.testForWithOutPar();
        System.out.println(System.currentTimeMillis() - start);
    }

}
