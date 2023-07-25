package com.serendipity.learn.zuo.sort;


import com.serendipity.utils.ArrayUtils;

public class BubbleSort {

    public static void main(String[] args) {
        g:
        for (int i = 0; i < 100; i++) {
            int[] originArr = ArrayUtils.generateRandomArray(10, 30, 0);
            int[] copyArr = ArrayUtils.copyArray(originArr);
            sort(originArr);
            sort3(copyArr);
            for (int i1 = 0; i1 < originArr.length; i1++) {
                if (originArr[i1] != copyArr[i1]) {
                    System.out.println("error");
                    break g;
                }
            }
        }
        System.out.println("pass");
    }

    /*
        算法想法：在0 ~ n-1 之间 两两比较谁大谁小，大的往后面，交换n-1次之后肯定可以找到最大的放在最后面
                第二次在 0~n-2 之间两两之间比较大小。。。
     */
    public static void sort(int[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            for (int j = 0; j < arr.length - i - 1; j++) {
                if (arr[j] > arr[j + 1]) {
                    ArrayUtils.swap(arr, j, j + 1);
                }
            }
        }
    }

    public static void sort2(int[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            for (int j = 0; j < arr.length - i - 1; j++) {
                if (arr[j] > arr[j + 1]) {
                    ArrayUtils.swap(arr, j, j + 1);
                }
            }
        }
    }

    public static void sort3(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr.length - 1 - i; j++) {
                if (arr[j] > arr[j + 1]) {
                    ArrayUtils.swap(arr, j, j + 1);
                }
            }
        }
    }

}
