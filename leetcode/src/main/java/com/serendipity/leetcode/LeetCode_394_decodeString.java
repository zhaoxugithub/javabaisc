package com.serendipity.leetcode;

import java.util.Stack;

/**
 * ClassName LeetCode_394_decodeString
 * Description TODO
 * Author 11931
 * Date 2022-12-08:23:18
 * Version 1.0
 **/
public class LeetCode_394_decodeString {

    /*
    输入：s = "3[a]2[bc]"
    输出："aaabcbc"
     */

    public String decodeString(String s) {

        if (s == null || s.length() == 0) {
            return "";
        }
        char[] charArray = s.toCharArray();
        Stack<Character> stack = new Stack<>();

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < charArray.length; i++) {
            // 如果是字母
            if (Character.isLetter(charArray[i])) {

                // 如果是数字
            } else if (Character.isDigit(charArray[i])) {

                Integer num = (int) charArray[i];
                int j = i + 1;

            } else if (charArray[i] == '[') {

                // 如果是右括号
            } else {

            }
            // charArray[i]
        }
        return "";
    }

    public static void main(String[] args) {

    }
}
