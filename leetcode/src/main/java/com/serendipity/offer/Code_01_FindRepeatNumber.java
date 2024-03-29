package com.serendipity.offer;

import java.util.HashMap;
import java.util.Map;

/**
 * Copyright (C), 2017-2021, 赵旭
 * Author: serendipity
 * Date: 2021/8/31 10:31 上午
 * FileName: Code_01_FindRepeatNumber
 * Description: com.toOffer_v2
 * 剑指 Offer 03. 数组中重复的数字
 * 找出数组中重复的数字。
 * <p>
 * <p>
 * 在一个长度为 n 的数组 nums 里的所有数字都在 0～n-1 的范围内。数组中某些数字是重复的，但不知道有几个数字重复了，也不知道每个数字重复了几次。请找出数组中任意一个重复的数字。
 * <p>
 * 示例 1：
 * <p>
 * 输入：
 * [2, 3, 1, 0, 2, 5, 3]
 * 输出：2 或 3
 */
public class Code_01_FindRepeatNumber {
    public int findRepeatNumber(int[] nums) {
        Map<Integer, Integer> result = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            if (result.containsKey(nums[i])) {
                return nums[i];
            } else {
                result.put(nums[i], 1);
            }
        }
        return 0;
    }
}
