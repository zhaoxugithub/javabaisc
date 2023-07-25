package com.serendipity.learn.zuo.line;

import com.serendipity.utils.*;

//快排2.0

/**
 *
 */
public class Code_01_QuickSort_V2 {
    public void sort(int[] arr) {
        if (arr == null || arr.length < 2) return;
        process(arr, 0, arr.length - 1);
    }

    public void process(int[] arr, int l, int r) {
        if (l >= r) return;
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


    //-----------------------复习----------------------------------


    public void sort2(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        process2(arr, 0, arr.length - 1);
    }

    public void process2(int[] arr, int l, int r) {
        if (l >= r) {
            return;
        }
        int[] mid = partition2(arr, l, r);
        partition2(arr, l, mid[0]);
        partition2(arr, mid[1], r);
    }

    public int[] partition2(int[] arr, int l, int r) {
        if (l > r) return new int[]{-1, -1};
        if (l == r) return new int[]{l, r};
        int less = l - 1;
        int index = l;
        int more = r;
        while (index < more) {
            if (arr[index] < arr[r]) {
                less++;
                ArrayUtils.swap(arr, index, less);
                index++;
            } else if (arr[index] > arr[r]) {
                more--;
                ArrayUtils.swap(arr, index, more);
            } else {
                index++;
            }
        }
        ArrayUtils.swap(arr, r, more++);
        return new int[]{less, more};
    }


    // 复习
    public void sort3(int[] arr) {
        if (arr == null || arr.length == 0) {
            return;
        }
        sort3(arr, 0, arr.length - 1);
    }

    public void sort3(int[] arr, int l, int r) {
        if (l >= r) {
            return;
        }
        int[] ints = partition3(arr, l, r);
        sort3(arr, l, ints[0]);
        sort3(arr, ints[1], r);

    }

    public int[] partition3(int[] arr, int l, int r) {
        if (l > r) return new int[]{-1, -1};
        if (l == r) return new int[]{l, r};
        int less = l - 1;
        int more = r;
        int index = l;
        while (index < more) {
            if (arr[index] < arr[r]) {
                ArrayUtils.swap(arr, index++, ++less);
            } else if (arr[index] > arr[r]) {
                ArrayUtils.swap(arr, index, --more);
            } else {
                index++;
            }
        }
        ArrayUtils.swap(arr, r, more++);
        return new int[]{less, more};
    }


    //复习
    public static void sort4(int[] array) {

        if (array == null || array.length == 0) {
            return;
        }
        sort4(array, 0, array.length - 1);
    }

    public static void sort4(int[] array, int start, int end) {
        if (start >= end) return;
        int[] mid = partition4(array, start, end);
        sort4(array, start, mid[0]);
        sort4(array, mid[1], end);
    }

    public static int[] partition4(int[] array, int start, int end) {
        int less = start - 1;
        int more = end;
        int i = start;
        while (i < more) {
            if (array[i] < array[end]) {
                ArrayUtils.swap(array, i++, ++less);
            } else if (array[i] > array[end]) {
                ArrayUtils.swap(array, i, --more);
            } else {
                i++;
            }
        }
        ArrayUtils.swap(array, end, more++);
        return new int[]{less, more};
    }

    public static void main(String[] args) {
        int[] ints = ArrayUtils.generateRandomArray(10, 200, 5);
        ArrayUtils.printArr(ints);
        new Code_01_QuickSort_V2().sort4(ints);
        ArrayUtils.printArr(ints);
    }
}
