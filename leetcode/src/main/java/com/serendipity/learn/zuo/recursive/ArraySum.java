package com.serendipity.learn.zuo.recursive;

import com.serendipity.utils.ArrayUtils;

public class ArraySum {


    public static int getSum(int[] array) {
        return getSum(array, array.length - 1);
    }

    /**
     * @Description:
     * @Param:
     * @return:
     * @Author: serendipity
     * @Date: 2021/8/17
     */
    public static int getSum(int[] array, int end) {
        if (end <= 0 || array.length == 1) {
            return 0;
        }
        int sum = 0;
        for (int i = 0; i < end; i++) {
            if (array[i] < array[end]) {
                sum += array[i];
            }
        }
        int temp = getSum(array, end - 1);
        sum += temp;
        return sum;
    }

    public static void main(String[] args) {
//        int[] array = generateRandomArray(10, 10, 0);
        int[] array = {1, 3, 4, 2, 5};
        ArrayUtils.printArr(array);
        int result = getSum(array);
        ArrayUtils.printArr(array);
        System.out.println(result);
    }
}
