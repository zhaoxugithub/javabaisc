package com.serendipity.myold.utils;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.Arrays;

/**
 * ClassName ArrayUtilsDemo2
 * Description TODO
 * Author 11931
 * Date 2023-11-25:11:39
 * Version 1.0
 **/
@Slf4j
@SuppressWarnings("all")
public class ArrayUtilsDemo2 {

    /*
        Arrays 方法:
            binarySearch
            compare
            copyOf
            copyOfRange
            equals
            deepEquals
            hashcode
            deepHashCode
            toString
            deepToString
            fill
            mismatch

            parallelPrefix
            parallelSetAll
            parallelSort
            sort
            spliterator
            stream
     */

    private static Integer[] oneDimensionArray = new Integer[20];
    private static Integer[][] twoDimensionsArray = new Integer[3][10];

    static {
        for (int i = 0; i < oneDimensionArray.length; i++) {
            oneDimensionArray[i] = (int) (Math.random() * 100);
        }


        for (int i = 0; i < twoDimensionsArray.length; i++) {
            for (int j = 0; j < twoDimensionsArray[i].length; j++) {
                twoDimensionsArray[i][j] = (int) (Math.random() * 100);
            }
        }
    }

    private void toArrayString(Object initArray) {
        if (initArray instanceof Integer[]) {
            log.info("initArray={}", Arrays.toString((Integer[]) initArray));
        } else if (initArray instanceof Integer[][]) {
            log.info("initArray={}", Arrays.toString((Integer[][]) initArray));
        } else {
            log.error("initArray parm is error,please check again");
        }
    }

    private void toDeepArrayString(Object initArray) {
        if (initArray instanceof Integer[]) {
            log.info("initArray={}", Arrays.deepToString((Integer[]) initArray));
        } else if (initArray instanceof Integer[][]) {
            log.info("initArray={}", Arrays.deepToString((Integer[][]) initArray));
        } else {
            log.error("initArray parm is error,please check again");
        }
    }

    @Test
    public void copyOfTest() {
        Integer[] oneDimensionArrayCopy1 = Arrays.copyOf(oneDimensionArray, oneDimensionArray.length);
        oneDimensionArrayCopy1[0] = 1000;
        toArrayString(oneDimensionArrayCopy1);
        toArrayString(oneDimensionArray);
        log.info("oneDimensionArray == oneDimensionArrayCopy1:{}", oneDimensionArrayCopy1 == oneDimensionArray);
        log.info("oneDimensionArray equals oneDimensionArrayCopy1:{}", Arrays.equals(oneDimensionArrayCopy1, oneDimensionArray));
        // 二维数组是浅拷贝
        Integer[][] twoDimensionsArrayCopy = Arrays.copyOf(twoDimensionsArray, twoDimensionsArray.length);
        twoDimensionsArrayCopy[0][0] = 10000;
        toDeepArrayString(twoDimensionsArrayCopy);
        toDeepArrayString(twoDimensionsArray);
        log.info("twoDimensionsArray ");
    }

    @Test
    public void binarySearchTest() {
        int result = Arrays.binarySearch(oneDimensionArray, 11);
        log.info("result ={}", result);
    }

    @Test
    public void compareTest() {
        // 按照字典顺序比较两个数组
        Integer[] oneDimensionArrayCopy = Arrays.copyOf(oneDimensionArray, oneDimensionArray.length);
        log.info("Arrays.compare:{}", Arrays.compare(oneDimensionArrayCopy, oneDimensionArray));
    }

    @Test
    public void fillTest() {
        Arrays.fill(oneDimensionArray, 10, oneDimensionArray.length, 1);
        toArrayString(oneDimensionArray);
    }

    @Test
    public void sortTest() {
        toArrayString(oneDimensionArray);
        Arrays.sort(oneDimensionArray);
        toArrayString(oneDimensionArray);
    }

    @Test
    public void missMatchTest() {
        // 返回两个数组第一个不相同的下标
        Integer[] oneDimensionArrayCopy = Arrays.copyOf(oneDimensionArray, oneDimensionArray.length);
        oneDimensionArrayCopy[1] = 10000;
        int mismatch = Arrays.mismatch(oneDimensionArrayCopy, oneDimensionArray);
        log.info("mismatch={}", mismatch);
    }

    @Test
    public void parallelPrefixTest() {
        toArrayString(oneDimensionArray);
        int sum = Arrays.stream(oneDimensionArray)
                        .mapToInt(Integer::intValue)
                        .sum();
        log.info("sum ={}", sum);
        Arrays.parallelPrefix(oneDimensionArray, (x, y) -> x + y);
        toArrayString(oneDimensionArray);
    }

    @Test
    public void parallelSetAll() {
        toArrayString(oneDimensionArray);
        Arrays.parallelSetAll(oneDimensionArray, item -> item * 2);
        toArrayString(oneDimensionArray);
    }
    @Test
    public void toStringTest() {
        // 打印一维数组
        toArrayString(oneDimensionArray);
        toDeepArrayString(oneDimensionArray);
        // 打印二维数组
        toArrayString(twoDimensionsArray);
        toDeepArrayString(twoDimensionsArray);
    }
}
