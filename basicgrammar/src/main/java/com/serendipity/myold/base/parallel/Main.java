package com.serendipity.myold.base.parallel;

/**
 * Copyright (C), 2017-2022, 赵旭
 * Author: 11931
 * Date: 2022/4/20 13:39
 * FileName: Main
 * Description: com.java8.base.parallel
 */
public class Main {

    public static void main(String[] args) {

        ParallelArraysProxy parallelArraysProxy = new ParallelArraysProxy(new ParallelArraysImpl());
        parallelArraysProxy.testFor();
        System.out.println("==================================");
        // 大数据量的情况下，Parallel的性能要高于for
        parallelArraysProxy.testParallel();
        System.out.println("================================");
//        parallelArraysProxy.testForWithOutPar();
//        System.out.println("=================================");
    }
}
