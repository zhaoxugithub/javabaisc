package com.serendipity.learn.zuo.line;
import com.serendipity.utils.*;

// 在一个数组中，一个数左边比它小的数的总和，叫数的小和，所有数的小和累加起来，叫数的组小和，求数组的组小和
public class Code_01_SmallSum {
    public int smallSum(int[] arr) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        return process(arr, 0, arr.length - 1);
    }

    public int process(int[] arr, int l, int r) {
        if (l == r) {
            return 0;
        }
        int mid = l + ((r - l) >> 1);
        return process(arr, l, mid) + process(arr, mid + 1, r) + merge(arr, l, mid, r);
    }

    public int merge(int[] arr, int l, int mid, int r) {

        int[] help = new int[r - l + 1];
        int p1 = l;
        int p2 = mid + 1;
        int i = 0;
        int res = 0;
        while (p1 <= mid && p2 <= r) {
            res += (arr[p1] > arr[p2] ? 0 : arr[p1] * (r - p2 + 1));
            System.out.println("1---" + "left:" + p1 + ",right" + p2 + "=======" + res);
            help[i++] = arr[p1] > arr[p2] ? arr[p2++] : arr[p1++];
        }
        while (p1 <= mid) {
            help[i++] = arr[p1++];
        }
        while (p2 <= r) {
            help[i++] = arr[p2++];
        }
        for (int k = 0; k < help.length; k++) {
            arr[l + k] = help[k];
        }
        return res;
    }

    // 以下是复习代码
    public int smallSum2(int[] array) {
        if (array == null || array.length == 1) {
            return 0;
        }
        return process2(array, 0, array.length - 1);
    }

    public int process2(int[] array, int l, int r) {
        if (l == r) {
            return 0;
        }
        int mid = l + ((r - l) >> 1);
        // 左侧所有产生小和+右侧所有产生的小和+两边合并产生的小和
        return process2(array, l, mid) + process2(array, mid + 1, r) + merge2(array, l, mid, r);
    }

    public int merge2(int[] array, int l, int m, int r) {
        int[] help = new int[r - l + 1];
        int i = l;
        int j = m + 1;
        int index = 0;
        int sum = 0;
        while (i <= m && j <= r) {
            // 这里会产生小和
            if (array[i] < array[j]) {
                sum += (array[i] * (r - j + 1));
                System.out.println("2---" + "left:" + i + ",right" + j + "=====" + sum);
                help[index++] = array[i++];
            } else {
                help[index++] = array[j++];
            }
        }
        while (i <= m) help[index++] = array[i++];
        while (j <= r) help[index++] = array[j++];
        for (int i1 = 0; i1 < help.length; i1++) {
            array[i1 + l] = help[i1];
        }
        return sum;
    }

    public int smallSum3(int[] array) {
        if (array == null || array.length == 0) {
            return 0;
        }
        return smallSum3(array, 0, array.length - 1);
    }

    public int smallSum3(int[] array, int l, int r) {

        if (l == r) {
            return 0;
        }
        int mid = l + ((r - l) >> 1);
        return smallSum3(array, l, mid)
                + smallSum3(array, mid + 1, r)
                + merge3(array, l, mid, r);
    }

    public int merge3(int[] array, int l, int m, int r) {
        int[] helper = new int[r - l + 1];
        int p1 = l;
        int p2 = m + 1;
        int index = 0;
        int sum = 0;
        while (p1 <= m && p2 <= r) {
            if (array[p1] < array[p2]) {
                sum += array[p1] * (r - p2 + 1);
                helper[index++] = array[p1++];
            } else {
                helper[index++] = array[p2++];
            }
        }
        while (p1 <= m) {
            helper[index++] = array[p1++];
        }
        while (p2 <= r) {
            helper[index++] = array[p2++];
        }
        for (int i = 0; i < helper.length; i++) {
            array[i + l] = helper[i];
        }
        return sum;
    }


    public static void main(String[] args) {
        int[] ints = ArrayUtils.generateRandomArray(9, 10, 4);
        int[] ints1 = ArrayUtils.copyArray(ints);
        ArrayUtils.printArr(ints);
        int i = new Code_01_SmallSum().smallSum2(ints);
        int j = new Code_01_SmallSum().smallSum3(ints1);
        System.out.println(i);
        System.out.println(j);
    }
}
