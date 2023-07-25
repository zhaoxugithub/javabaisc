package com.serendipity.learn.zuo.sort;

import static com.datastruct.zuo.common.ArrayUtils.generateRandomArray;
import static com.datastruct.zuo.common.ArrayUtils.printArr;

/**
 * 快排3.0
 */
public class QuickSort_03 {

    public static void sort(int[] arr) {
        if (arr == null || arr.length == 0) {
            return;
        }
        sort(arr, 0, arr.length - 1);
    }

    public static void sort(int[] arr, int L, int R) {
        if (L > R) {
            return;
        }
        swap(arr, L + (int) Math.random() * (R - L + 1), R);
        int[] m = partition(arr, L, R);
        sort(arr, L, m[0]);
        sort(arr, m[1], R);
    }


    public static void swap(int[] arr, int i, int j) {
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }

    public static int[] partition(int[] array, int L, int R) {

        int index = L;
        int less = L - 1;
        int more = R + 1;
        int num = array[R];

        while (index < more) {

            if (array[index] > num) {
                swap(array, --more, index);
            } else if (array[index] < num) {
                swap(array, ++less, index++);
            } else {
                index++;
            }
        }
        return new int[]{less, more};
    }

    public static void main(String[] args) {
        int[] array = generateRandomArray(10, 10, 0);
        printArr(array);
        sort(array);
        printArr(array);
    }


}
