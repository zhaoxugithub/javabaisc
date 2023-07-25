package com.serendipity.myold.base;

import org.junit.Test;

import java.util.Arrays;
import java.util.Random;
import java.util.stream.IntStream;

public class RandomUtilsDemo1 {

    // 拥有相同种子的 Random 实例，在相同次数下，生成的伪随机数完全相同
    @Test
    public void test1() {
        for (int i = 0; i < 100; i++) {
            Random random1 = new Random(10);
            Random random2 = new Random(10);
            System.out.println(random1.nextInt());
            System.out.println(random2.nextInt());
        }
    }

    @Test
    public void test2() {
        Random random = new Random();
        byte[] bytes = new byte[5];
        // 每次给bytes数组里面赋值
        random.nextBytes(bytes);
        System.out.println(Arrays.toString(bytes));
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
}