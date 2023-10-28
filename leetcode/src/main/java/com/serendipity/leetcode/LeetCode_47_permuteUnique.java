package com.serendipity.leetcode;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * ClassName LeetCode_47_permuteUnique
 * Description TODO
 * Author 11931
 * Date 2023-10-25:0:26
 * Version 1.0
 **/
public class LeetCode_47_permuteUnique {
    private List<List<Integer>> result = new ArrayList<>();

    public List<List<Integer>> permuteUnique(int[] nums) {
        if (nums == null || nums.length == 0) {
            return result;
        }

        boolean[] visited = new boolean[nums.length];

        process(nums, new ArrayList<Integer>(), visited);
        return result;
    }

    private void process(int[] nums, ArrayList<Integer> tempList, boolean[] visited) {
        if (nums.length == tempList.size()) {
            if (isHas(result, tempList)) {
                result.add(new ArrayList<>(tempList));
            }
            return;
        }

        for (int i = 0; i < nums.length; i++) {
            if (visited[i]) {
                continue;
            }
            visited[i] = true;
            tempList.add(nums[i]);
            process(nums, tempList, visited);
            visited[i] = false;
            tempList.remove(tempList.size() - 1);
        }
    }

    public boolean isHas(List<List<Integer>> result, List<Integer> temp) {
        for (List<Integer> integers : result) {
            for (int i = 0; i < temp.size(); i++) {
                if (!temp.get(i)
                         .equals(integers.get(i))) {
                    break;
                }
            }
        }
        return true;
    }

    @Test
    public void test() {
        int[] arr = new int[]{1, 1, 2};

        permuteUnique(arr);
        System.out.println(result);
    }
}
