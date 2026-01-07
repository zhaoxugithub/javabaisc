package com.serendipity.leetcode;

import java.util.*;

//https://leetcode.cn/problems/group-anagrams/description/?envType=study-plan-v2&envId=top-100-liked
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


    /*
        输入: strs = ["eat", "tea", "tan", "ate", "nat", "bat"]
        输出: [["bat"],["nat","tan"],["ate","eat","tea"]]
     */

    public static void main(String[] args) {

        new LeetCode_49_groupAnagrams().groupAnagrams2(new String[]{"eat", "tea", "tan", "ate", "nat", "bat"});

    }

    public List<List<String>> groupAnagrams2(String[] strs) {

        List<List<String>> result = new ArrayList<>();
        Map<String, List<String>> map = new HashMap<>();
        for (int i = 0; i < strs.length; i++) {
            String singleStr = strs[i];
            String sortStr = converToSortString(singleStr);
            if (map.containsKey(sortStr)) {
                map.get(sortStr).add(singleStr);
            } else {
                List<String> list = new ArrayList<>();
                list.add(singleStr);
                map.put(sortStr, list);
            }
        }
        System.out.println(map);

        for (List<String> value : map.values()) {
            result.add(value);
        }

        System.out.println(result);
        return result;
    }

    private String converToSortString(String str) {
        char[] charArray = str.toCharArray();
        Arrays.sort(charArray);
        return new String(charArray);
    }

}
