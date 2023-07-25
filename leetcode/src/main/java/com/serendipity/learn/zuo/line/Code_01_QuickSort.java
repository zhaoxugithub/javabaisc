package com.serendipity.learn.zuo.line;

import java.util.Arrays;

import com.serendipity.utils.*;

/**
 * Copyright (C), 2017-2021, 赵旭
 * Author: 11931
 * Date: 2021/10/21 1:48
 * FileName: Code_01_QuickSort
 * Description: com.datastruct.zuo.line
 */
public class Code_01_QuickSort {

    public int[] sort(int[] sourceArray) throws Exception {
        // 对 arr 进行拷贝，不改变参数内容
        int[] arr = Arrays.copyOf(sourceArray, sourceArray.length);

        return quickSort(arr, 0, arr.length - 1);
    }

    private int[] quickSort(int[] arr, int left, int right) {
        if (left < right) {
            int partitionIndex = partition2(arr, left, right);
            quickSort(arr, left, partitionIndex - 1);
            quickSort(arr, partitionIndex + 1, right);
        }
        return arr;
    }

    private int partition(int[] arr, int left, int right) {
        // 设定基准值（pivot）
        int index = left + 1;
        for (int i = index; i <= right; i++) {
            if (arr[i] < arr[left]) {
                swap(arr, i, index);
                index++;
            }
        }
        swap(arr, left, index - 1);
        return index - 1;
    }


    public int partition2(int[] arr, int left, int right) {

        int i = left - 1;
        for (int j = left; j <= right; j++) {

            if (arr[j] < arr[right]) {
                ArrayUtils.swap(arr, ++i, j);
            }
        }
        ArrayUtils.swap(arr, right, ++i);
        return i;

    }

    private void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    public static void main(String[] args) throws Exception {
        int[] ints = ArrayUtils.generateRandomArray(10, 100, 8);
        ArrayUtils.printArr(ints);
        int[] sort = new Code_01_QuickSort().sort(ints);
        ArrayUtils.printArr(sort);
    }
}
