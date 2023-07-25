package com.serendipity.learn.zuo.heap;

import com.serendipity.utils.ArrayUtils;

public class Code02_MaxHeap {
    // 给定一个数组，希望把他调整大根堆
    public static void heapify(int[] arr, int index, int heapSize) {
        int left = index * 2 + 1;
        while (left < heapSize) {
            int largest = left + 1 < heapSize && arr[left + 1] > arr[left] ? left + 1 : left;
            largest = arr[index] > arr[largest] ? index : largest;
            if (largest == index) {
                break;
            }
            ArrayUtils.swap(arr, largest, index);
            index = largest;
            left = index * 2 + 1;
        }
    }

    private static void heapify2(int[] arr, int index, int heapSize) {
        // 节点的左孩子
        int left = index * 2 + 1;
        while (left < heapSize) {
            // largest 是最大值的下标,如果左右子树的值相等，largest取左子树
            int largest = left + 1 < heapSize && arr[left + 1] > arr[left] ? left + 1 : left;
            largest = arr[index] > arr[largest] ? index : largest;
            // 如果根节点的是最大值
            if (largest == index) {
                break;
            }
            // 如果根节点不是最大值
            ArrayUtils.swap(arr, index, largest);
            index = largest;
            left = index * 2 + 1;
        }
    }

    public static void main(String[] args) {
        int[] ints = ArrayUtils.generateRandomArray(10, 100, 5);
        ArrayUtils.printArr(ints);
        for (int i = 0; i < ints.length; i++) {
            heapify(ints, i, ints.length);
        }
        ArrayUtils.printArr(ints);
    }


}
