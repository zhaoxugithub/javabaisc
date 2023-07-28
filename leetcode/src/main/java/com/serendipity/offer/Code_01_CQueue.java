package com.serendipity.offer;

import java.util.Stack;

/**
 * Copyright (C), 2017-2021, 赵旭
 * Author: serendipity
 * Date: 2021/9/7 1:41 下午
 * FileName: Code_01_CQueue
 * Description: com.toOffer_v2
 */
public class Code_01_CQueue {
    Stack<Integer> s1 = new Stack<>();
    Stack<Integer> s2 = new Stack<>();

    public Code_01_CQueue() {

    }

    public void appendTail(int value) {
        s1.add(value);
    }

    public int deleteHead() {

        if (s2.empty()) {
            while (!s1.empty()) {
                Integer pop = s1.pop();
                s2.add(pop);
            }
        }
        return s2.empty() ? -1 : s2.pop();
    }
}
