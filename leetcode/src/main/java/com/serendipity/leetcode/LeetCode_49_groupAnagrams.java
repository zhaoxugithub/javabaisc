package com.serendipity.leetcode;

import java.util.*;

public class LeetCode_49_groupAnagrams {

    public List<List<String>> groupAnagrams(String[] strs) {

        Map<String, List<String>> maps = new HashMap<>();
        for (String str : strs) {
            char[] chars = str.toCharArray();
            Arrays.sort(chars);
            String sortStr = Arrays.toString(chars);
            if (maps.containsKey(sortStr)) {
                maps.get(sortStr).add(str);
            } else {
                List<String> list = new ArrayList<>();
                list.add(str);
                maps.put(sortStr, list);
            }
        }

        List<List<String>> result = new ArrayList<>();
        maps.forEach((k, v) -> {
            result.add(v);
        });

        return result;
    }

}
