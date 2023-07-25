package com.serendipity.learn.zuo.line;


import com.serendipity.utils.ArrayUtils;

public class Code_01_GetMax {

    public void getMax(int[] arr) {
        if (arr == null) return;
        int max = partition(arr, 0, arr.length - 1);
        System.out.println(max);
    }

    public int partition(int[] arr, int L, int R) {
        if (L == R) return arr[R];
        int mid = L + ((R - L) >> 1);
        int left = partition(arr, L, mid);
        int right = partition(arr, mid + 1, R);
        return Math.max(left, right);
    }

    public static void main(String[] args) {
        int[] ints = ArrayUtils.generateRandomArray(10, 10, 2);
        ArrayUtils.printArr(ints);
        new Code_01_GetMax().getMax(ints);
    }
}
