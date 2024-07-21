package com.serendipity.learn.labuladong.chapter01;


import org.junit.Test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

@SuppressWarnings("all")
// https://leetcode.cn/problems/two-sum/description/
public class LeetCode_01_twoSum {


    // 方法一：
    public int[] twoSum(int[] num, int target) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < num.length; i++) {
            int another = target - num[i];
            if (map.containsKey(another)) {
                return new int[]{i, map.get(another)};
            } else {
                map.put(num[i], i);
            }
        }
        return new int[]{-1, -1};
    }


    // 双指针
    public int[] twoSum02(int[] num, int target) {
        // 先排序
        Arrays.sort(num);
        // 定义两个指针
        int left = 0;
        int right = num.length - 1;
        while (left < right) {
            if (target == num[left] + num[right]) {
                return new int[]{left, right};
            } else if (target > num[left] + num[right]) {
                left++;
            } else {
                right--;
            }
        }
        return new int[]{-1, -1};
    }


    // num , x+y = target
    public int[] twoSum03(int[] num, int target) {

       /*

        1.    1 2 3 4   target = 4
        */
        // i= num.length-1;
        for (int i = 0; i < num.length; i++) {

            // j  = num.lenght
            for (int j = i + 1; j < num.length - 1; j++) {
                System.out.println(String.format("num[i]=%s, num[j]=%s, num[i] + num[j]=%s", num[i], num[j],
                        num[i] + num[j]));

                if (num[i] + num[j] == target) {
                    return new int[]{i, j};
                }
            }
        }
        return new int[]{-1, -1};


    }


    public void testFOR() {

        for (int i = 0; i < 100; i++) {
            System.out.println("ss");
        }

        int[] nums = new int[100];
        int i = 0;
        //i =  nums.length -1
        while (i < nums.length) {
            int j = i + 1;
            // j = num.length;
            while (j < nums.length) {
                j++;
                // todo
            }
            i++;
        }
    }

    @Test
    public void test() {
        int[] array = {3, 2, 4};
        int[] ints = twoSum03(array, 6);
        System.out.println(Arrays.toString(ints));

    }
}
