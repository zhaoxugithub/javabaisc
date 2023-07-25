package com.serendipity.leetcode;

import java.util.Stack;

public class LeetCode_155_MinStack {

    Stack<Integer> stack = null;
    Stack<Integer> minStack = null;

    public LeetCode_155_MinStack() {
        stack = new Stack<>();
        minStack = new Stack<>();
    }

    public void push(int val) {
        stack.push(val);
        if (minStack.isEmpty() || minStack.peek() >= val) {
            minStack.push(val);
        }
    }

    public void pop() {
        if (!stack.isEmpty() ) {
            Integer pop = stack.pop();
            if (pop.equals(minStack.peek())) {
                minStack.pop();
            }
        }
    }

    public int top() {
        return stack.peek();
    }

    public int getMin() {
        return stack.isEmpty() ? -1 : minStack.peek();
    }

    public static void main(String[] args) {
        LeetCode_155_MinStack stack1 = new LeetCode_155_MinStack();
        stack1.push(512);
        stack1.push(-1024);
        stack1.push(-1024);
        stack1.push(512);

        stack1.pop();
        stack1.pop();
        stack1.pop();

        System.out.println(stack1.getMin());


    }


}
