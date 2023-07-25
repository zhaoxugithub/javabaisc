package com.serendipity.learn.zuo.heap;

import java.util.Random;

import com.serendipity.utils.*;

public class Code02_Heap {
    public static class MyMaxHeap {
        private int[] heap;
        private final int limit;
        private int heapSize;

        public MyMaxHeap(int limit) {
            heap = new int[limit];
            this.limit = limit;
            this.heapSize = 0;
        }

        public boolean isEmpty() {
            return heapSize == 0;
        }

        public boolean isFull() {
            return heapSize == limit;
        }

        public void push(int value) {
            if (isFull()) {
                throw new RuntimeException("this heap is full");
            }
            heap[heapSize] = value;
            heapInsert(heap, heapSize++);
        }

        // 用户此时，让你返回最大值，并且在大根堆中，把最大值删掉
        // 剩下的数，依然保持大根堆组织
        public int pop() {
            int temp = heap[0];
            swap(heap, 0, --heapSize);
            // 作用：将0-heapsize范围上调成大根堆
            heapify(heap, 0, heapSize);
            return temp;
        }

        // 新加进来的数，现在停在了index位置，请依次往上移动，
        // 移动到0位置，或者干不掉自己的父亲了，停！
        // 指标index往上移动
        public void heapInsert(int[] arr, int index) {
            while (arr[index] > arr[(index - 1) / 2]) {
                swap(arr, index, (index - 1) / 2);
                index = (index - 1) / 2;
            }
        }

        // 从index位置，往下看，不断的下沉
        // 停：较大的孩子都不再比index位置的数大；已经没孩子了
        // index 是不断的往下移动
        public void heapify(int[] arr, int index, int heapSize) {
            // 节点的左孩子
            int left = index * 2 + 1;
            while (left < heapSize) {
                // largest 是最大值的下标
                int largest = left + 1 < heapSize && arr[left] > arr[left + 1] ? left : left + 1;
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

        public void push2(int value) {
            if (isFull()) {
                throw new RuntimeException("this heap is empty");
            }
            heap[heapSize] = value;
            heapInsert2(heap, heapSize++);
        }

        private void heapInsert2(int[] heap, int heapSize) {
            int index = heapSize;
            while (heap[index] > heap[(index - 1) / 2]) {
                swap(heap, index, (index - 1) / 2);
                index = (index - 1) / 2;
            }
        }

        public void pop2() {
            if (isEmpty()) {
                throw new RuntimeException("this heap is empty");
            }
            int res = heap[0];
            swap(heap, 0, --heapSize);
            heapfy2(heap, 0, heapSize);
        }

        private void heapfy2(int[] array, int i, int heapSize) {
            int left = i * 2 + 1;
            while (left < heapSize) {
                int larges = left + 1 < heapSize && array[left] > array[left + 1] ? left : left + 1;
                larges = array[larges] > array[i] ? larges : i;
                if (larges == i) {
                    break;
                }
                swap(array, larges, i);
                i = larges;
                left = i * 2 + 1;
            }
        }

        public void swap(int[] arr, int i, int j) {
            int temp = arr[i];
            arr[i] = arr[j];
            arr[j] = temp;
        }
    }

    public static void main(String[] args) {
        MyMaxHeap heap = new MyMaxHeap(10);
        MyMaxHeap heap2 = new MyMaxHeap(10);
        System.out.println("原始数据");
        for (int i = 0; i < 10; i++) {
            int i1 = new Random().nextInt(1000);
            System.out.print(i1 + ",");
            heap.push(i1);
            heap2.push(i1);
        }
        System.out.println();
        ArrayUtils.printArr(heap.heap);
        ArrayUtils.printArr(heap2.heap);
        System.out.println("------------------------------");
    }
}
