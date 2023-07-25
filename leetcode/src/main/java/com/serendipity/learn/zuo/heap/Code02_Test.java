package com.serendipity.learn.zuo.heap;

import com.serendipity.utils.ArrayUtils;

public class Code02_Test {
    // 从下往上看
    // 转变成大跟堆
    public static void toMaxHeap(int[] nums, int index) {
        if (nums == null) return;
        while (nums[(index - 1) / 2] < nums[index]) {
            ArrayUtils.swap(nums, (index - 1) / 2, index);
            index = (index - 1) / 2;
        }
    }


    public static void main(String[] args) {

        int[] ints = ArrayUtils.generateRandomArray(10, 100, 5);
        ArrayUtils.printArr(ints);
        for (int i = 0; i < ints.length; i++) {
            toMaxHeap(ints, i);
        }
        int heapSize = ints.length;
        ArrayUtils.swap(ints, 0, --heapSize);

        while (heapSize > 0) {
            // 在0-heapSize 的区间范围内凑成大根堆
            heapfy(ints, 0, heapSize);
            ArrayUtils.swap(ints, 0, --heapSize);
        }
        ArrayUtils.printArr(ints);
    }

    private static void heapfy(int[] ints, int i, int heapSize) {
        int left = 2 * i + 1;
        while (left < heapSize) {
            int largest = left + 1 < heapSize && ints[left + 1] > ints[left] ? left + 1 : left;
            largest = ints[largest] > ints[i] ? largest : i;
            // 如果最大值就是父亲自己，返回
            if (largest == i) {
                break;
            }
            ArrayUtils.swap(ints, largest, i);
            i = largest;
            left = 2 * i + 1;
        }
    }
}
