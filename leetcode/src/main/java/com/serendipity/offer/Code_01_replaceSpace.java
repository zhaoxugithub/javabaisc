package com.serendipity.offer;

/**
 * Copyright (C), 2017-2021, 赵旭
 * Author: serendipity
 * Date: 2021/8/31 10:57 上午
 * FileName: Code_01_replaceSpace
 * Description: com.toOffer_v2
 * 剑指 Offer 05. 替换空格
 * 请实现一个函数，把字符串 s 中的每个空格替换成"%20"。
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * 输入：s = "We are happy."
 * 输出："We%20are%20happy."
 * <p>
 * <p>
 * 限制：
 * <p>
 * 0 <= s 的长度 <= 10000
 * <p>
 * 通过次数263,281提交次数345,407
 */
public class Code_01_replaceSpace {
    public static String replaceSpace(String s) {
        StringBuilder sb = new StringBuilder(s);
        for (int i = 0; i < sb.length(); i++) {
            if (sb.charAt(i) == ' ') {
                if (i < sb.length() - 1) {
                    sb.replace(i, i + 1, "%20");
                } else {
                    sb.replace(i, i + 1, "%20");
                }
            }
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        String s = replaceSpace("We are happy. ");
        System.out.println(s);
    }
}
