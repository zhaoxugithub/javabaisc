package com.serendipity.myold.base.array.utils;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.function.BinaryOperator;
import java.util.function.IntBinaryOperator;
import java.util.function.IntUnaryOperator;
import java.util.stream.Collectors;

/**
 * ClassName ArrayUtilsDemo1
 * Description TODO
 * Author 11931
 * Date 2023-04-05:15:47
 * Version 1.0
 **/
@SuppressWarnings("all")
@Slf4j
public class ArrayUtilsDemo1 {
    /*
        parallelSort
        Sort
        parallelPrefix
     */
    @Test
    public void test1() {
        int[] test1 = {1, 2, 3, 4, 5, 6};
        // 1 3
        // IntBinaryOperator:  (int,int) -> int
        Arrays.parallelPrefix(test1, Integer::sum);
        System.out.println(Arrays.toString(test1));
        Arrays.parallelPrefix(test1, new IntBinaryOperator() {
            @Override
            public int applyAsInt(int left, int right) {
                return right - left;
            }
        });
        System.out.println(Arrays.toString(test1));
    }

    /**
     * BinaryOperator使用方法
     */
    @Test
    public void test2() {
        BinaryOperator<Integer> add = (n1, n2) -> n1 + n2;
        // 等价于
        BinaryOperator<Integer> add2 = Integer::sum;
        // apply方法用于接收参数，并返回BinaryOperator中的Integer类型
        System.out.println(add.apply(3, 4));
        System.out.println(add2.apply(4, 5));
    }

    @Test
    public void test2_1() {
        BinaryOperator<Object> t1 = (n1, n2) -> String.valueOf(n2) + n1;
        BinaryOperator<Object> t2 = (n3, n4) -> n3.toString() + n4.toString();
    }

    @Test
    public void test3() {
        int[] test2 = {1, 2, 3, 4, 5, 6};
        // 二分查找
        int i = Arrays.binarySearch(test2, 5);
        System.out.println(i);
    }

    @Test
    public void test4() {
        int[] arr1 = {1, 2, 3, 4, 5};
        int[] arr2 = {1, 2, 3, 4};
        int[] arr3 = {1, 2, 3, 4};
        int[] arr4 = {1, 2, 3, 5};
        System.out.println(Arrays.equals(arr1, arr2));
        System.out.println(Arrays.equals(arr3, arr4));
        System.out.println(Arrays.equals(arr2, arr3));
    }

    /**
     * deepEquals
     */
    @Test
    public void test7() {
        int[][] test1 = {{1, 2, 3}, {1, 2, 3}, {1, 2, 3}};
        int[][] test2 = {{1, 2, 3}, {1, 2, 3}, {1, 2, 3}};
        System.out.println(Arrays.equals(test1, test2));
        System.out.println(Arrays.deepEquals(test1, test2));
    }


    /*
     Array.copu of
     */
    @Test
    public void test5() {
        int[] arr1 = {1, 2, 3, 4, 5};
        int[] ar = Arrays.copyOf(arr1, 10);
        System.out.println(Arrays.toString(ar));

        int[] ints = Arrays.copyOf(arr1, 3);
        System.out.println(Arrays.toString(ints));
    }


    @Test
    public void test6() {
        List<String> list = Arrays.asList("1", "2", "3", "4", "5");
        System.out.println(Arrays.toString(list.toArray()));
    }

    /**
     * toString()
     */
    @Test
    public void test8() {
        int[] arr = {1, 2, 3, 4, 5, 6};
        System.out.println(Arrays.toString(arr));
    }

    /**
     * deepToString()
     */
    @Test
    public void test9() {
        int[][] arr = {{1, 2, 3}, {1, 2, 3}, {1, 2, 3}};
        System.out.println(Arrays.toString(arr));
        System.out.println(Arrays.deepToString(arr));
    }

    /**
     * setAll方法
     */
    @Test
    public void test10() {
        int[] arr = {1, 2, 3, 4, 5};
        Arrays.setAll(arr, new IntUnaryOperator() {
            @Override
            public int applyAsInt(int operand) {
                return arr[operand] * 2;
            }
        });
        System.out.println(Arrays.toString(arr));

        // 上述的执行过程等价于
        Arrays.setAll(arr, i -> arr[i] * 2);
        System.out.println(Arrays.toString(arr));
    }


    /**
     * parallelSetAll方法
     */
    @Test
    public void test11() {
        int[] arr = {1, 2, 3, 4, 5};
        Arrays.parallelSetAll(arr, i -> arr[i] * 3);
        System.out.println(Arrays.toString(arr));

        int[] arr2 = {1, 2, 3, 4, 5};
        Arrays.setAll(arr2, i -> arr2[i] * 3);
        System.out.println(Arrays.toString(arr2));
    }

    /**
     * 返回数组的分片迭代器,用于并行的遍历数组spliteratr
     */
    public void test12() {
    }

    @Test
    public void test13() {
        Integer[] arr = {1, 2, 3, 4, 5};
        List<Integer> collect = Arrays.stream(arr)
                                      .collect(Collectors.toList());
        System.out.println(collect);
    }
}
