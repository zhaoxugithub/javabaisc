package com.serendipity.learn.zuo.line;

/**
 * Copyright (C), 2017-2022, 赵旭
 * Author: 11931
 * Date: 2022/2/11 19:13
 * FileName: Code_01_Find_Odd
 * Description: com.datastruct.zuo.line
 * 一个数组中有两种数出现了奇数次，其他数都出现了偶数次，怎么找到并且打印这两种数
 */
public class Code_01_Find_Odd {

    public static void main(String[] args) {
        Code_01_Find_Odd code_01_find_odd = new Code_01_Find_Odd();
        int[] array = {1, 2, 2, 3, 3, 4, 4, 4, 5, 5};
        int[] ints = code_01_find_odd.find(array);
        System.out.printf("a = %d  b= %d ", ints[0], ints[1]);
    }


    public int[] find(int[] array) {
        int temp = 0;
        for (int i = 0; i < array.length; i++) {
            temp = temp ^ array[i];
        }
        int od = temp & ((~temp + 1));
        int one = 0;
        for (int i = 0; i < array.length; i++) {
            if ((array[i] & od) == 0) {
                one = one ^ array[i];
            }
        }
        int another = one ^ temp;
        return new int[]{one, another};
    }


}
