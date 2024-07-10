package com.serendipity.hot100;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

//https://leetcode.cn/problems/longest-consecutive-sequence/?envType=study-plan-v2&envId=top-100-liked
public class LeetCode_128_longestConsecutive {


    private static final Logger log = LoggerFactory.getLogger(LeetCode_128_longestConsecutive.class);

    // 方法一： 排序+双指针
    public int longestConsecutive01(int[] nums) {
        if (nums.length == 0) return 0;
        Set<Integer> set = new TreeSet<>();
        for (int num : nums) {
            set.add(num);
        }
        List<Integer> list = new ArrayList<>(set);
        int result = 1;
        int tmpResult = 1;
        int index = 0, cur = 0;
        // 0,1,1,2,3,4
        while (index < list.size() && cur < list.size()) {
            int diff = list.get(cur) - list.get(index);
            if (diff == 1) {
                tmpResult++;
                result = Math.max(result, tmpResult);
                cur++;
                index++;
            } else {
                tmpResult = 1;
                index = cur;
                cur++;
            }
        }
        return result;
    }


    // 方法二: hashSet 去重
    public int longestConsecutive02(int[] nums) {
        Set<Integer> set = new HashSet<>();
        for (int num : nums) {
            set.add(num);
        }

        int result = 0;
        for (int num : nums) {
            // 为什么要加这个判断,可以保证 这个序列是以num为首
            if (!set.contains(num - 1)) {
                int currentNum = num;
                int currentIndex = 1;
                while (set.contains(currentNum + 1)) {

                    currentIndex++;
                    currentNum++;

                }
                result = Math.max(result, currentIndex);
            }
        }
        return result;
    }
}
