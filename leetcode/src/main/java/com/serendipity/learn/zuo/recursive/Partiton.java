package com.serendipity.learn.zuo.recursive;

import com.serendipity.utils.ArrayUtils;

public class Partiton {
    public static void partition(int[] arr, int num) {
        int k = -1;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] <= num) {
                int temp = arr[i];
                arr[i] = arr[++k];
                arr[k] = temp;
            }
        }
    }

    public static void main(String[] args) {
//        int[] ints = ArrayUtils.generateRandomArray(10, 10, 0);
        int[] ints = {3, 2, 3, 1, 7, 4, 5};
        ArrayUtils.printArr(ints);
        partition(ints, 3);
        ArrayUtils.printArr(ints);
    }
}
