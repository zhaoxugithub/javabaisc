package com.serendipity.hot100;

import java.util.*;

public class Test {

    public int[] twoSum(int[] nums, int target) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            if (map.containsKey(target - nums[i])) {
                return new int[]{map.get(target - nums[i]), i};
            } else {
                map.put(nums[i], i);
            }
        }
        return new int[]{-1, -1};
    }


    public List<List<String>> groupAnagrams(String[] strs) {
        Map<Map<Character, Integer>, List<String>> tempMap = new HashMap<>();
        for (String str : strs) {
            Map<Character, Integer> wordMap = getWordMap(str);
            // 如果不存在key则创建一个新的ArrayList，然后直接添加字符串
            tempMap.computeIfAbsent(wordMap, k -> new ArrayList<>()).add(str);
        }
        return new ArrayList<>(tempMap.values());
    }

    public List<List<String>> groupAnagrams2(String[] strs) {
        Map<String, List<String>> map = new HashMap<>();
        for (String str : strs) {
            char[] charArray = str.toCharArray();
            Arrays.sort(charArray);
            String key = new String(charArray);
            map.computeIfAbsent(key, k -> new ArrayList<>()).add(str);
        }
        return new ArrayList<>(map.values());
    }


    public static void main(String[] args) {
        Map<Character, Integer> word = getWordMap("word");
        word.forEach((key, value) -> System.out.println(key + ":" + value));

        Map<Character, Integer> wordMap = getWordMap("ordw");
        System.out.println(word.equals(wordMap));
    }


    public static Map<Character, Integer> getWordMap(String str) {
        Map<Character, Integer> map = new HashMap<>();
        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            map.merge(c, 1, Integer::sum);
        }
        return map;
    }

    public int longestConsecutive(int[] nums) {
        return 0;
    }
}
