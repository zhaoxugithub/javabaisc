package com.serendipity.utils;


import java.util.Arrays;

/**
 * 对数器
 */
public class ArrayUtils {
    /**
     * 生成数组
     *
     * @param maxSize  数组的最大长度
     * @param maxValue 数组元素最大值
     * @param flag     0:正数，可重复；>0:正数，不可重复；<0:负数
     * @return
     */
    public static int[] generateRandomArray(int maxSize, int maxValue, int flag) {
        // Math.random [0,1)
        // Math.random *N [0,N)
        // Math.random * (N+1)  [0,N]
        int length = 0;
        // 获取一个length=maxSize
        while (length < maxSize - 1) {
            length = (int) ((maxSize + 1) * Math.random());
        }
        Integer[] arr = new Integer[length];
        for (int i = 0; i < arr.length; i++) {
            int num = (int) ((maxValue + 1) * Math.random());
            if (flag > 0) {
                while (Arrays.asList(arr).contains(num)) {
                    num = (int) ((maxValue + 1) * Math.random());
                }
                arr[i] = num;
            } else if (flag == 0) {
                arr[i] = num;
            } else {
                arr[i] = (int) ((maxValue + 1) * Math.random()) - (int) ((maxValue) * Math.random());
            }
        }
        int[] arr_int = new int[length];
        for (int i = 0; i < arr.length; i++) {
            arr_int[i] = arr[i];
        }
        return arr_int;
    }


    public static Integer[] intToInteger(int[] array) {
        return Arrays.stream(array).boxed().toArray(Integer[]::new);
    }

    /**
     * 数组拷贝
     *
     * @param arr
     * @return
     */
    public static int[] copyArray(int[] arr) {

        if (arr == null) {
            return null;
        }

        int[] copyArr = new int[arr.length];
        for (int i = 0; i < arr.length; i++) {
            copyArr[i] = arr[i];
        }
        return copyArr;
    }

    /**
     * 两个数组判断是否相等
     *
     * @param arr1
     * @param arr2
     * @return
     */
    public static boolean isEqual(int[] arr1, int[] arr2) {

        if ((arr1 == null && arr2 != null) || (arr2 == null && arr1 != null)) {
            return false;
        }
        if (arr1 == null && arr2 == null) {
            return true;
        }
        if (arr1.length != arr2.length) {
            return false;
        }
        for (int i = 0; i < arr1.length; i++) {
            if (arr1[i] != arr2[i]) {
                return false;
            }
        }
        return true;
    }

    /**
     * 打印数组
     *
     * @param arr
     */
    public static void printArr(int[] arr) {
        if (arr == null) {
            return;
        }
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }

    /**
     * 数组元素交换位置
     *
     * @param array
     * @param i
     * @param j
     */
    private static void swap2(int[] array, int i, int j) {
        array[i] = array[i] ^ array[j];
        array[j] = array[i] ^ array[j];
        array[i] = array[i] ^ array[j];
    }


    public static void swap(int[] array, int i, int j) {
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }

    /**
     * 位运算
     */
    public static void bitOperation() {

        int a = 8;
        int b = 2;

        // a /(2^b)
        System.out.println(a >> b);
        // 无符号右移
        System.out.println(a >>> b);

        // 或运算
        System.out.println(a | b);

        System.out.println(6 ^ 5);

        int x = 10;
        System.out.println(x & ((~x) + 1));
        System.out.println(x & ((~x) | 1));
        System.out.println(~x & ((x) + 1));
        System.out.println(~x & ((x) | 1));
    }

    public static void main(String[] args) {
        bitOperation();
    }
}
