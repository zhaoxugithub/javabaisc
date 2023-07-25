package com.serendipity.learn.zuo.line;

import java.util.Stack;

/**
 * Copyright (C), 2017-2021, 赵旭
 * Author: 11931
 * Date: 2021/10/20 0:08
 * FileName: Code_01_Stack_To_Queue
 * Description: com.datastruct.zuo.line
 */

// 用栈实现队列
public class Code_01_Stack_To_Queue {
    private final static Stack<Integer> dataStack = new Stack<>();
    private final static Stack<Integer> helpStack = new Stack<>();

    public static void add(Integer value) {
        dataStack.push(value);
    }

    public static Integer pop() {
        // todo 判断helpstack是否为空和
        if (helpStack.isEmpty()) {
            while (!dataStack.isEmpty()) {
                helpStack.push(dataStack.pop());
            }
        }
        return helpStack.pop();
    }

    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            add(i);
        }
        for (int i = 0; i < 5; i++) {
            System.out.println(pop());
        }
        add(99);
        add(90);
        System.out.println(pop());
        System.out.println(pop());
        System.out.println(pop());
        System.out.println(pop());
        System.out.println(pop());
        System.out.println(pop());
    }
}
