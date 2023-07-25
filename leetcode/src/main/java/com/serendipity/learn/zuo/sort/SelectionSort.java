package com.serendipity.learn.zuo.sort;

import com.datastruct.zuo.common.ArrayUtils;

public class SelectionSort {


    /*

        算法思想：
            1.在0 ~ n-1 上不断的找最小数然后依次放到指定位置上
     */
    public static void sort(int[] arr) {
        // 在 0 ~ n-1 上遍历
        for (int i = 0; i < arr.length - 1; i++) {

            // 这个循环是在i+1 ~ n-1 上找最小元素，找到的话，放到i位置上
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[i] > arr[j]) {
                    ArrayUtils.swap(arr, i, j);
                }
            }
        }
    }

    public static void sort2(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[j] < arr[i]) {
                    ArrayUtils.swap(arr, j, i);
                }
            }
        }
    }

    /**
     * 在 j- n 位置上选择一个最小的元素插入到i 位置上
     *
     * @param nums
     */
    public static void sort3(int[] nums) {
        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[i] > nums[j]) {
                    ArrayUtils.swap(nums, i, j);
                }
            }
        }
    }


    public static void main(String[] args) {
        go:
        for (int i = 0; i < 100; i++) {
            int[] originArray = ArrayUtils.generateRandomArray(10, 30, 0);
            int[] copyArray = ArrayUtils.copyArray(originArray);
            sort(originArray);
            sort2(copyArray);
            ArrayUtils.printArr(originArray);
            for (int i1 = 0; i1 < originArray.length; i1++) {
                if (originArray[i1] != copyArray[i1]) {
                    System.out.println("error");
                    break go;
                }
            }
        }
        System.out.println("pass");
    }
}
