package com.serendipity.learn.zuo.sort;

import com.datastruct.zuo.common.ArrayUtils;

/**
 * 归并排序:先分再合并
 * <p>
 * 三个特点：
 * l ==r
 * sort() sort(), merge
 * arr ,l ,r   => arr,l,mid,r
 */
public class GuiBinSort {

    public static void sort(int[] arr) {
        if (arr == null || arr.length == 0) {
            return;
        }
        sort(arr, 0, arr.length - 1);
    }

    private static void sort(int[] arr, int l, int r) {
        if (l == r) {
            return;
        }
        int mid = l + ((r - l) >> 1);
        sort(arr, l, mid);
        sort(arr, mid + 1, r);
        merge(arr, l, mid, r);
    }

    private static void merge(int[] arr, int l, int mid, int r) {
        int[] help = new int[r - l + 1];
        int p1 = l;
        int p2 = mid + 1;
        int i = 0;
        while (p1 <= mid && p2 <= r) {
            help[i++] = arr[p1] < arr[p2] ? arr[p1++] : arr[p2++];
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
    }

    //---------------------------复习一------------------------------------
    public static void sort2(int[] array) {
        if (array == null || array.length == 0) {
            return;
        }
        sort2(array, 0, array.length - 1);
    }

    public static void sort2(int[] array, int l, int r) {
        // 当二分到两个下标同时指向同一个元素的时候，返回
        if (l == r) {
            return;
        }
        int mid = l + ((r - l) >> 1);
        sort2(array, l, mid);
        sort2(array, mid + 1, r);
        merge2(array, l, mid, r);
    }

    public static void merge2(int[] array, int l, int mid, int r) {
        // 创建一个临时数组
        int[] help = new int[r - l + 1];
        int index = 0;
        int p1 = l;
        int p2 = mid + 1;
        while (p1 <= mid && p2 <= r) {
            if (array[p1] > array[p2]) {
                help[index++] = array[p2++];
            } else {
                help[index++] = array[p1++];
            }
        }

        while (p1 <= mid) {
            help[index++] = array[p1++];
        }
        while (p2 <= r) {
            help[index++] = array[p2++];
        }
        System.arraycopy(help, 0, array, l, help.length);
    }

    // -------------------------------------------------------------------------

    public static void sort3(int[] array) {
        if (array == null || array.length == 0) {
            return;
        }
        sort(array, 0, array.length - 1);
    }

    private static void sort3(int[] array, int l, int r) {
        int mid = l + ((r - l) >> 1);
        sort3(array, 0, mid);
        sort3(array, mid + 1, r);
        merge3(array, l, mid, r);
    }

    private static void merge3(int[] array, int l, int mid, int r) {
        int p1 = l;
        int p2 = mid + 1;
        int[] helpArr = new int[r - l + 1];
        int i = 0;
        while (p1 <= mid && p2 <= r) {
            helpArr[i++] = array[p1] > array[p2] ? array[p2++] : array[p1++];
        }
        while (p1 <= mid) {
            helpArr[i++] = array[p1++];
            while (p2 <= r) {
                helpArr[i++] = array[p2++];
            }
            for (int k = 0; k < helpArr.length; k++) {
                array[l + k] = helpArr[k];
            }
        }
    }


    public static void main(String[] args) {
        go:
        for (int i = 0; i < 100; i++) {
            int[] originArray = ArrayUtils.generateRandomArray(10, 30, 0);
            int[] copyArray = ArrayUtils.copyArray(originArray);
            sort(originArray);
            sort3(copyArray);
            for (int i1 = 0; i1 < originArray.length; i1++) {
                if (originArray[i1] != copyArray[i1]) {
                    System.out.println("error");
                    break go;
                }
            }
        }
        System.out.println("pass");
        int[] ints = ArrayUtils.generateRandomArray(10, 100, 1);
        int[] ccc = new int[10];

        /*
            数组拷贝
            ints 元数组
            srcPos: 从原数组的下标开始
            ccc: 目标数组
            destPos: 从目标数组的第几个下标开始
            length: 拷贝几个元素（个数）
         */
        System.arraycopy(ints, 0, ccc, 3, 5);
        ArrayUtils.printArr(ccc);
    }
}
