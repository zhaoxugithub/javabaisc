package com.serendipity.learn.zuo.line;


import com.serendipity.utils.ArrayUtils;

public class Code_01_MergeSort {


    public void sort(int[] arr) {
        sort(arr, 0, arr.length - 1);
    }

    public void sort(int[] arr, int L, int R) {
        if (L == R) {
            return;
        }
        int mid = L + ((R - L) >> 1);
        sort(arr, L, mid);
        sort(arr, mid + 1, R);
        merge(arr, L, mid, R);
    }

    public void merge(int[] arr, int L, int mid, int R) {
        int[] helpArr = new int[R - L + 1];
        int p1 = L;
        int p2 = mid + 1;
        int i = 0;
        while (p1 <= mid && p2 <= R) {
            helpArr[i++] = arr[p1] > arr[p2] ? arr[p2++] : arr[p1++];
        }
        while (p1 <= mid) {
            helpArr[i++] = arr[p1++];
        }
        while (p2 <= R) {
            helpArr[i++] = arr[p2++];
        }

        for (int k = 0; k < helpArr.length; k++) {
            arr[L + k] = helpArr[k];
        }
    }


    //    --------------------------复习2---------------------------------------
    public void sort2(int[] arr) {
        if (arr == null || arr.length == 0) {
            return;
        }
        sort2(arr, 0, arr.length - 1);
    }

    public void sort2(int[] arr, int start, int end) {
        if (start == end) {
            return;
        }
        int mid = start + ((end - start) >> 1);
        sort2(arr, start, mid);
        sort2(arr, mid + 1, end);
        merge2(arr, start, mid, end);
    }

    public void merge2(int[] arr, int start, int mid, int end) {
        int[] help = new int[end - start + 1];
        int p = start;
        int r = mid + 1;
        int i = 0;
        while (p <= mid && r <= end) {
            help[i++] = arr[p] > arr[r] ? arr[r++] : arr[p++];
        }
        while (p <= mid) {
            help[i++] = arr[p++];
        }
        while (r <= end) {
            help[i++] = arr[r++];
        }
        for (int j = 0; j < help.length; j++) {
            arr[start + j] = help[j];
        }
    }


    // 复习3
    public void sort3(int[] array) {
        if (array == null || array.length == 0) {
            return;
        }
        sort3(array, 0, array.length - 1);
    }

    public void sort3(int[] array, int start, int end) {
        if (start == end) return;
        int mid = start + ((end - start) >> 1);
        sort3(array, start, mid);
        sort3(array, mid + 1, end);
        merger3(array, start, mid, end);
    }

    public void merger3(int[] array, int start, int mid, int end) {
        int[] help = new int[end - start + 1];
        int p = start;
        int n = mid + 1;
        int index = 0;
        while (p <= mid && n <= end) {
            if (array[p] < array[n]) {
                help[index++] = array[p++];
            } else {
                help[index++] = array[n++];
            }
        }

        while (p <= mid) {
            help[index++] = array[p++];
        }

        while (n <= end) {
            help[index++] = array[n++];
        }

        for (int i = 0; i < help.length; i++) {
            array[start + i] = help[i];
        }
    }


    public static void main(String[] args) {
        int[] ints = ArrayUtils.generateRandomArray(10, 10, 4);
        ArrayUtils.printArr(ints);
        new Code_01_MergeSort().sort3(ints);
        ArrayUtils.printArr(ints);
    }
}
