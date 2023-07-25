package com.serendipity.learn.zuo.line;

import java.util.Stack;

import static com.serendipity.utils.ArrayUtils.generateRandomArray;
import static com.serendipity.utils.ArrayUtils.printArr;

// 栈实现，pop取出最小的当前栈最小元素时间复杂度为O(1)
public class Code_01_GetMinStack {
    Stack<Integer> stackData = null;
    Stack<Integer> minStack = null;

    public Code_01_GetMinStack() {
        stackData = new Stack<>();
        minStack = new Stack<>();
    }

    public void push(Integer value) {
        if (this.minStack.empty()) {
            minStack.push(value);
        } else if (minStack.peek() >= value) {
            minStack.push(value);
        }
        stackData.push(value);
    }

    public Integer pop() {
        if (this.stackData.empty()) {
            throw new RuntimeException("this stack is empty");
        }
        Integer pop = stackData.pop();
        if (pop.equals(minStack.peek())) {
            minStack.pop();
        }
        return pop;
    }

    public Integer getMin() {
        if (minStack.isEmpty()) {
            throw new RuntimeException("this stack is empty");
        }
        return minStack.peek();
    }

    public void test() {
        int[] array = generateRandomArray(10, 10, 0);
        printArr(array);
        for (int i = 0; i < array.length; i++) {
            push(array[i]);
        }
        System.out.println(getMin());
        pop();
        pop();
        pop();
        System.out.println(getMin());
    }

    public static void main(String[] args) {
        new Code_01_GetMinStack().test();
    }
}
