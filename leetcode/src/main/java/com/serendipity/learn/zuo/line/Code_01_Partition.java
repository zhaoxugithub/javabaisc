package com.serendipity.learn.zuo.line;

import com.serendipity.utils.ArrayUtils;

/**
 * Copyright (C), 2017-2021, 赵旭
 * Author: 11931
 * Date: 2021/10/21 0:53
 * FileName: Code01_Partition
 * Description: com.datastruct.zuo.line
 */
// 给定一个数组和一个数，将比这个数小的放在左边，大的放在右边
public class Code_01_Partition {
    // 左边做划分值
    public static void partition(int[] arr, int val) {
        // 左划分值边界下标
        int index = -1;
        for (int i = 0; i < arr.length; i++) {
            // 注意要加等号
            if (arr[i] <= arr[arr.length - 1]) {
                ArrayUtils.swap(arr, ++index, i);
            }
        }
        ArrayUtils.swap(arr, ++index, arr.length - 1);
    }

    // 两边同时做划分值
    public static void partition2(int[] arr) {
        int less = -1;
        int more = arr.length - 1;
        for (int i = 0; i < more; ) {
            if (arr[i] < arr[arr.length - 1]) {
                ArrayUtils.swap(arr, ++less, i++);
            } else if (arr[i] > arr[arr.length - 1]) {
                ArrayUtils.swap(arr, --more, i);
            } else {
                i++;
            }
        }
        ArrayUtils.swap(arr, arr.length - 1, more);
    }

    // 因为num可能不在array数组中
    public static void partition3(int[] array, int num) {
        //<= 区域的最右侧边界
        int pre = -1;
        int i = 0;
        while (i < array.length) {
            // 如果当前元素要比num小，就拿当前元素和<=区的下一个元素进行交换
            if (array[i] <= num) {
                ArrayUtils.swap(array, i, ++pre);
            }
            i++;
        }
    }

    public static void partition4(int[] array) {
        int pre = -1;
        int i = 0;
        while (i < array.length) {
            if (array[i] <= array[array.length - 1]) {
                ArrayUtils.swap(array, i, ++pre);
            }
            i++;
        }
        ArrayUtils.swap(array, array.length - 1, ++pre);
    }

    public static void partition5(int[] array) {
        // 左边界下标
        int left = -1;
        // 右边界下标
        int right = array.length - 1;
        // 当前下标
        int i = 0;
        // 注意循环边界
        while (i < right) {
            if (array[i] < array[array.length - 1]) {
                ArrayUtils.swap(array, i++, ++left);
            } else if (array[i] > array[array.length - 1]) {
                // 这里的i停留在原地，因为交换的数还没有被比较
                ArrayUtils.swap(array, i, --right);
            } else {
                i++;
            }
        }
        // 12 36 65 37 45 24 24 24 3 43
        ArrayUtils.swap(array, array.length - 1, right);
        System.out.printf("left = %d,right = %d\n", left + 1, right);
        // left-1,和right是边界
    }

    public static void partition6(int[] array, int value) {
        int less = -1;
        int index = less + 1;
        while (index < array.length) {
            // 如果遍历到最后一个元素是要交换的
            if (array[index] <= value) {
                ArrayUtils.swap(array, ++less, index);
            }
            index++;
        }
    }

    public static void partition7(int[] array) {
        int less = -1;
        int index = less + 1;
        while (index < array.length) {
            //
            if (array[index] <= array[array.length - 1]) {
                ArrayUtils.swap(array, ++less, index);
            }
            index++;
        }
    }

    public static int partition8(int[] array, int l, int r) {
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
        return less + 1;
    }

    public static void main(String[] args) {
//        int[] ints = ArrayUtils.generateRandomArray(10, 10, 6);
        int[] ints = {12, 43, 24, 65, 45, 24, 24, 37, 3, 36};
        int[] copyArray = ArrayUtils.copyArray(ints);
        // ArrayUtils.printArr(ints);
        partition8(ints, 0, ints.length - 1);
        ArrayUtils.printArr(ints);

        partition7(copyArray);
        ArrayUtils.printArr(copyArray);
    }
}
