package com.serendipity.myold.lambda.ch05;

import com.google.common.collect.Lists;
import com.serendipity.myold.lambda.ch01.Employee;
import lombok.NonNull;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class TestLambda5 {
    /**
     * 创建数组的方法
     */
    @Test
    public void test02() {
        // 创建数组的方法
        int[] nums1 = {1, 2, 3, 4, 5};
        int[] nums2 = new int[5];
        int[] nums3 = (int[]) Array.newInstance(int.class, 5);
        int[] nums4 = nums3.clone();
        int[] nums5 = Arrays.copyOf(nums3, nums3.length);
        Integer[] array = (Integer[]) Stream.of(1, 2, 3, 4)
                                            .toArray();
    }

    /**
     * 创建list
     */
    @Test
    public void test03() {
        List<Integer> list1 = new ArrayList<>();
        List<Integer> list2 = Arrays.asList(new Integer[]{1, 2, 3});
        List<Integer> list2_1 = Arrays.asList(1, 2, 3);
        List<Integer> list3 = Collections.singletonList(1);
        List<Integer> list4 = List.of(1, 2, 3, 4, 5);
        List<Integer> list5 = Stream.of(1, 2, 3, 4)
                                    .toList();
    }

    /**
     * 创建stream
     */
    private static List<String> li1 = new ArrayList<>();
    private static Employee[] employees = new Employee[10];

    @Test
    public void test04() {
        Stream<String> stream1 = li1.stream();
        Stream<Employee> stream2 = Arrays.stream(employees);
        Stream<Integer> stream3 = Stream.of(1, 2, 3, 4);
        Stream<Double> stream4 = Stream.generate(Math::random);
        Stream<Integer> stream5 = Stream.iterate(0, x -> x + 2);
    }

    /**
     * list 和 array 数组相互转换
     */
    @Test
    public void test05() {
        // 数组 -> list
        String[] strs = {"a", "b", "c"};
        List<String> list1 = Arrays.asList(strs);
        List<String> list2 = Arrays.stream(strs)
                                   .toList();
        List<String> list3 = new ArrayList<String>(Arrays.asList(strs));
        List<String> list4 = new ArrayList<>();
        Collections.addAll(list4, strs);
        List<String> collect = Stream.of(strs)
                                     .toList();
        // list -> 数组
        String[] array = list2.toArray(new String[0]);
        String[] array1 = list2.stream()
                               .toArray(String[]::new);
    }

    @Test
    public void test01() {
        // 1. 可以通过 Collection 系列集合提供的 stream() 或 parallelStream()
        List<String> list = new ArrayList<>();
        Stream<String> stream = list.stream();
        // 2. 通过 Arrays 中的静态方法 stream() 获取数组流
        String[] strings = new String[10];
        Stream<String> stream1 = Arrays.stream(strings);
        // 3. 通过 Stream 类中的静态方法
        Stream<String> a = Stream.of("a", "b", "c");
        List<Integer> integers = List.of(1, 2, 3, 4);
        // 4. 创建无限流
        // 迭代
        Stream<Integer> iterate = Stream.iterate(0, x -> x + 2);
        iterate.limit(10)
               .forEach(System.out::print);
        // 生成
        System.out.println();
        Stream.generate(() -> Math.random())
              .limit(5)
              .forEach(System.out::println);
        Stream<Double> limit = Stream.generate(() -> Math.random())
                                     .limit(10);
    }

    @Test
    public void test06() {
        Stream<Integer> iterate = Stream.iterate(0, x -> x + 2);
        iterate.limit(10)
               .forEach(System.out::println);
        System.out.println("----------");
        Stream.generate(() -> Math.random())
              .limit(10)
              .forEach(System.out::println);
    }

    @Test
    public void test07() {
        ArrayList<Integer> list = Lists.newArrayList(1, 3, 4, 5);
        List<Integer> collect = list.stream()
                                    .filter(item -> item > 100)
                                    .toList();
        System.out.println(collect.isEmpty());
    }

    @Test
    public void test08() {

        // 这种是采用正则的方式去匹配的，所以效率很低
        String source = "a::1,b::2,c::3,d::4";
        String target = source.replaceAll("::", "=");
        String[] targets = source.split("::");

        // 这个是字符串匹配操作
        String source2 = "a::1,b::2,c::3,d::4";
        String target2 = source2.replace("::", "=");
    }

    @Test
    public void test09() {
        isValid(null);
    }

    public void isValid(@NonNull String str) {
        System.out.println(str.length());
    }
}
