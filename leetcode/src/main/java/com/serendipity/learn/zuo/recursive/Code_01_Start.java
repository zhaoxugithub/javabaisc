package com.serendipity.learn.zuo.recursive;

/**
 * Copyright (C), 2017-2021, 赵旭
 * Author: 11931
 * Date: 2021/10/19 20:51
 * FileName: Code_01_Start
 * Description: com.datastruct.zuo.recursive
 */
public class Code_01_Start {
    /**
     * 返回最右侧1 的下标
     *
     * @param arr
     * @return
     */
    public static int findRightOne(int[] arr) {
        int count = 0;
        for (int i = 0; i < arr.length; i++) {
            count ^= arr[i];
        }
        return count;
    }

    //题目四：一个数组中有两种数出现了奇数次，其他数都出现了偶数次，怎么找到并且打印这两种数
    public static void findTwoNums(int[] arr) {
        //首先先获取这两个数的异或结果
        int eor = 0;
        for (int i = 0; i < arr.length; i++) {
            eor ^= arr[i];
        }
        //因为这两个数是不相等的，所以这两个数异或的结果肯定不等于0
        //获取这个结果的最右边的那个1
        System.out.println(eor);
        int temp = eor & ((~eor) + 1);
        //将数组里面的元素分成两部分，一部分那个位置是1的，另外一部分是0的，这两个数肯定是分别处于这两部分
        int eor2 = 0;
        for (int i = 0; i < arr.length; i++) {
            if ((temp & arr[i]) != 0) {
                eor2 ^= arr[i];
            }
        }
        System.out.println(eor2);
        int another = eor ^ eor2;
        System.out.println(eor2 + "---" + another);
    }

    public static void main(String[] args) {
        int[] array = {2, 2, 3, 3, 4, 4, 4, 5, 5, 1};
        findTwoNums(array);
    }
}
