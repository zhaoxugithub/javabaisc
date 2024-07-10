package com.serendipity.learn.labuladong.chapter01;


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

}
