package com.serendipity.learn.zuo.sort;


import com.datastruct.zuo.common.ArrayUtils;

import static com.datastruct.zuo.common.ArrayUtils.generateRandomArray;

/**
 * 快排1.0
 * 特点：  L  > R
 * partition  sort sort
 * arr  l  r
 */
public class QuickSort_01 {

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
        int m = partition(arr, L, R);
        sort(arr, L, m - 1);
        sort(arr, m + 1, R);
    }

    public static int partition(int[] arr, int L, int R) {
        int index = L;
        int less = L - 1;
        int more = R + 1;
        int num = arr[R];
        while (index < more) {

            if (arr[index] <= num) {
                ArrayUtils.swap(arr, index, ++less);
            }
            index++;
        }
        return less;
    }


    public static void sort2(int[] array) {
        if (array == null || array.length == 0) {
            return;
        }
        sort2(array, 0, array.length - 1);
    }

    /*

       ) 3,2,1,4

       ) 表示是partition 分界
     */
    public static void sort2(int[] array, int l, int r) {
        if (l > r) {
            return;
        }
        int partition = partition2(array, l, r);
        sort2(array, 0, partition - 1);
        sort2(array, partition + 1, r);
    }

    //
    public static int partition2(int[] arr, int l, int r) {
        int index = l;
        int less = l - 1;
        int more = r + 1;
        int target = arr[r];
        while (index < more) {
            // 如果遍历的元素比目标元素小，与partition的下一个元素位置进行交换
            if (arr[index] <= target) {
                ArrayUtils.swap(arr, index, ++less);
            }
            index++;
        }
        return less;
    }


    public static void main(String[] args) {
        for (int i = 0; i < 100; i++) {
            int[] originArray = ArrayUtils.generateRandomArray(10, 30, 0);
            int[] copyArray = ArrayUtils.copyArray(originArray);
            sort(originArray);
            sort2(copyArray);
            if (!ArrayUtils.isEqual(originArray, copyArray)) {
                System.out.println("failed");
            }
        }
        System.out.println("pass");
    }

}
