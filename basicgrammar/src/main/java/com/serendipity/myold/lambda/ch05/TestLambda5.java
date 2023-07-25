package com.serendipity.myold.lambda.ch05;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public class TestLambda5 {

    @Test
    public void test() {
        // 1. 可以通过 Collection 系列集合提供的 stream() 或 parallelStream()
        List<String> list = new ArrayList<>();
        Stream<String> stream = list.stream();

        // 2. 通过 Arrays 中的静态方法 stream() 获取数组流
        String[] strings = new String[10];
        Stream<String> stream1 = Arrays.stream(strings);

        // 3. 通过 Stream 类中的静态方法
        Stream<String> a = Stream.of("a", "b", "c");

        // 4. 创建无限流
        // 迭代
        Stream<Integer> iterate = Stream.iterate(0, x -> x + 2);
        iterate.limit(10).forEach(System.out::print);
        // 生成
        System.out.println();
        Stream.generate(() -> Math.random()).limit(5).forEach(System.out::println);

        Stream<Double> limit = Stream.generate(() -> Math.random()).limit(10);
    }
}
