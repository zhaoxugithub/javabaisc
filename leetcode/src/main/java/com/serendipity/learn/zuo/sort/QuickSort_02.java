package com.serendipity.learn.zuo.sort;

import static com.serendipity.utils.ArrayUtils.generateRandomArray;
import static com.serendipity.utils.ArrayUtils.printArr;

/**
 * 快排2.0
 */
public class QuickSort_02 {
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
        int[] m = netherlandsFlag(arr, L, R);
        sort(arr, L, m[0]);
        sort(arr, m[1], R);
    }


    public static void swap(int[] arr, int i, int j) {
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }

    /**
     * arr[L...R] 玩荷兰国旗问题的划分，以arr[R] 做划分值
     * <arr[R]放在左侧 ==arr[R]放中间   >arr[R]放右边
     * 返回等于左右边界
     *
     * @return
     */
    public static int[] netherlandsFlag(int[] arr, int L, int R) {
        int less = L - 1;
        int index = L;
        int more = R + 1;
        int num = arr[R];
        while (index < more) {

            // 如果遍历值比预选值小
            if (arr[index] < num) {
                swap(arr, ++less, index++);
            } else if (arr[index] > num) {
                swap(arr, --more, index);
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
