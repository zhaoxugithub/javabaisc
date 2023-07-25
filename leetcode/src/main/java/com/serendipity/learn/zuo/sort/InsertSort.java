package com.serendipity.learn.zuo.sort;

import com.serendipity.utils.ArrayUtils;

/*
    插入排序： 首先保证在0 ~0 上有序
                     0 ~ 1 上有序
                     0 -2 上有序
                     ...
                     0 ~ n-1
                     0 ~ n 上有序
        依次往左看
 */
public class InsertSort {
    public static void sort(int[] arr) {
        for (int i = 1; i < arr.length; i++) {
            for (int j = i - 1; j >= 0 && arr[j] > arr[j + 1]; j--) {
                if (arr[j] > arr[j + 1]) {
                    ArrayUtils.swap(arr, j, j + 1);
                }
            }
        }
    }

    public static void sort2(int[] arr) {
        for (int i = 1; i < arr.length; i++) {
            for (int j = i - 1; j >= 0; j--) {
                if (arr[j] > arr[j + 1]) {
                    ArrayUtils.swap(arr, j, j + 1);
                }
            }
        }
    }


    public static void sort3(int[] arr) {
        for (int i = 1; i < arr.length; i++) {
            for (int j = i - 1; j >= 0; j--) {

                if (arr[j + 1] < arr[j]) {
                    ArrayUtils.swap(arr, j + 1, j);
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
