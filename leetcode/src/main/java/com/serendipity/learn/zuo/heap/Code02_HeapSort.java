package com.serendipity.learn.zuo.heap;

import java.util.Arrays;

import com.serendipity.utils.*;

/**
 * 如果数全部给出堆排序可以优化成O(N)的时间复杂度
 * 一个一个数给出的话，时间复杂度的是O(N*logN)
 * 空间复杂度是O(1)
 */
public class Code02_HeapSort {

    public void heapSort01(int[] array) {
        if (array == null || array.length < 2) {
            return;
        }
        // 这个循环走完，可以保证这个数组是一个大根堆
        // 一个一个元素的来
        for (int i = 0; i < array.length; i++) {
            heapInsert(array, i);
        }
        int heapSize = array.length;
        swap(array, 0, --heapSize);
        while (heapSize > 0) {
            // 在0-heapSize 范围内进行大根堆的重建
            heapify(array, 0, heapSize);
            // 每一次构建完大跟堆之后，都将堆最大的元素和最后一个元素交换
            swap(array, 0, --heapSize);
        }
    }

    public void heapSort02(int[] array) {
        if (array == null || array.length < 2) {
            return;
        }
        for (int i = array.length - 1; i >= 0; i--) {
            heapify(array, i, array.length);
        }
        int heapSize = array.length;
        swap(array, 0, --heapSize);
        while (heapSize > 0) {
            heapify(array, 0, heapSize);
            swap(array, 0, --heapSize);
        }
    }

    // 新加进来的数，现在停在了index位置，请依次往上移动，
    // 移动到0位置，或者干不掉自己的父亲了，停！
    public void heapInsert(int[] arr, int index) {
        while (arr[index] > arr[(index - 1) / 2]) {
            swap(arr, index, (index - 1) / 2);
            index = (index - 1) / 2;
        }
    }

    // 从index位置，往下看，不断的下沉
    // 停：较大的孩子都不再比index位置的数大；已经没孩子了
    private void heapify(int[] arr, int index, int heapSize) {
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
            swap(arr, index, largest);
            index = largest;
            left = index * 2 + 1;
        }
    }


    public void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    public void heapInsert2(int[] arr, int index) {
        if (index < 0 || index > arr.length) return;
        while (arr[index] > arr[(index - 1) / 2]) {
            ArrayUtils.swap(arr, index, (index - 1) / 2);
            index = (index - 1) / 2;
        }
    }

    public void heapify2(int[] arr, int index, int heapSize) {
        int left = index * 2 + 1;
        // 如果左孩子的下标超过heapSize，则终止循环
        while (left < heapSize) {
            int largest = arr[left + 1] > arr[left] && left + 1 < heapSize ? left + 1 : left;
            largest = arr[largest] > arr[index] ? largest : index;
            if (largest == index) {
                break;
            }
            // 如果根结
            ArrayUtils.swap(arr, largest, index);
            // 将下标移到左右孩子上
            index = largest;
            left = index * 2 + 1;
        }
    }

    public static void test() {
        int[] ints = ArrayUtils.generateRandomArray(10, 100, 1);
        Arrays.stream(ints).forEach((x) -> System.out.print(x + ","));
        new Code02_HeapSort().heapSort01(ints);
        System.out.println();
        Arrays.stream(ints).forEach((x) -> System.out.print(x + ","));
    }

    public static void main(String[] args) {
        test();
    }
}
