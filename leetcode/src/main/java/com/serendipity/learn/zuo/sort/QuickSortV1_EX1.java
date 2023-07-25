package com.serendipity.learn.zuo.sort;


import com.serendipity.utils.ArrayUtils;

import java.util.Arrays;

/**
 * 快速排序复习
 */
public class QuickSortV1_EX1 {
    public static void main(String[] args) {
        for (int i = 0; i < 100; i++) {
            int[] randomArray = ArrayUtils.generateRandomArray(10, 100, 2);
            int[] copyArray = ArrayUtils.copyArray(randomArray);
            System.out.println("sourceArray = " + Arrays.toString(copyArray));
            QuickSort_03.sort(copyArray);

            sort(randomArray);

            boolean equal = ArrayUtils.isEqual(copyArray, randomArray);

            if (!equal) {

                System.out.println("Arrays.toString(randomArray) = " + Arrays.toString(randomArray));
                System.out.println("Arrays.toString(copyArray) = " + Arrays.toString(copyArray));
                System.out.println("error");
                return;
            }
        }
    }

    public static void sort(int[] array) {
        if (array == null || array.length == 0) {
            return;
        }
        sort(array, 0, array.length - 1);
    }

    public static void sort(int[] array, int l, int r) {
        if (l > r) {
            return;
        }
        int partition = partition2(array, l, r);
        sort(array, l, partition - 1);
        sort(array, partition + 1, r);
    }
    /*
         方法一：通过划分值，一个指针从开始进行遍历，如果遇到一个元素比目标值target小的时候，
                与partition的下一个元素进行交换。
                注意边界：要遍历到最后一个元素
     */
    public static int partition(int[] array, int l, int r) {
        int less = l - 1;
        int target = array[r];
        int index = less + 1;
        while (index <= r) {
            // 如果左边元素比目标值大
            if (array[index] <= target) {
                ArrayUtils.swap(array, ++less, index);
            }
            index++;
        }
        return less;
    }

    public static int partition2(int[] array, int l, int r) {
        int less = l - 1;
        int target = array[r];
        int more = r + 1;
        int index = less + 1;
        while (index < more) {
            if (array[index] < target) {
                ArrayUtils.swap(array, ++less, index++);
            } else if (array[index] > target) {
                // 注意这里，指针index是不会发生变化，原因是
                // 没有遍历过的元素 与 index 对应元素发生交换
                ArrayUtils.swap(array, index, --more);
            } else {
                index++;
            }
        }
        return less + 1;
    }
    // public static int partition(int[] arr, int L, int R) {
    //     int index = L;
    //     int less = L - 1;
    //     int more = R + 1;
    //     int num = arr[R];
    //     while (index < more) {
    //         if (arr[index] <= num) {
    //             ArrayUtils.swap(arr, index, ++less);
    //         }
    //         index++;
    //     }
    //     return less;
    // }
}
