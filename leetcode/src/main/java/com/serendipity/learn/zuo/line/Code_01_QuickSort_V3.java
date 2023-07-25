package com.serendipity.learn.zuo.line;

import com.serendipity.utils.*;

public class Code_01_QuickSort_V3 {
    public void sort(int[] arr) {
        if (arr == null || arr.length < 2) return;
        process(arr, 0, arr.length - 1);
    }

    public void process(int[] arr, int l, int r) {
        if (l >= r) return;
        ArrayUtils.swap(arr, r, l + (int) (Math.random() * (r - l + 1)));
        int[] partition = partition(arr, l, r);
        process(arr, l, partition[0]);
        process(arr, partition[1], r);
    }

    public int[] partition(int[] arr, int l, int r) {
        if (l > r) return new int[]{-1, -1};
        if (l == r) return new int[]{l, r};
        int more = r;
        int less = l - 1;
        int index = l;
        while (index < more) {
            if (arr[index] < arr[r]) {
                ArrayUtils.swap(arr, index++, ++less);
            } else if (arr[index] > arr[r]) {
                ArrayUtils.swap(arr, --more, index);
            } else {
                index++;
            }
        }
        ArrayUtils.swap(arr, r, more++);
        return new int[]{less, more};
    }

    public static void main(String[] args) {
        int[] ints = ArrayUtils.generateRandomArray(10, 200, 5);
        ArrayUtils.printArr(ints);
        new Code_01_QuickSort_V3().sort(ints);
        ArrayUtils.printArr(ints);
    }

}
