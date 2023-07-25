package com.serendipity.learn.zuo.line;

import java.util.ArrayList;
import java.util.List;

//双向链表实现队列
public class Code_01_DoubleEndsQueue<T> {

    private static class DoubleNode<T> {
        private T value;
        private DoubleNode<T> next;
        private DoubleNode<T> pre;

        public DoubleNode(T value) {
            this.value = value;
        }
    }

    DoubleNode<T> head = null;
    DoubleNode<T> tail = null;

    public void addFromHead(T value) {
        DoubleNode<T> doubleNode = new DoubleNode<T>(value);
        if (head == null) {
            tail = doubleNode;
        } else {
            doubleNode.pre = head;
            head.next = doubleNode;
        }
        head = doubleNode;
    }

    public void addFromBottom(T value) {
        DoubleNode<T> node = new DoubleNode<T>(value);
        if (head == null) {
            head = node;
        } else {
            tail.pre = node;
            node.next = tail;
        }
        tail = node;
    }

    public T removeFromHead() {
        if (head == null) {
            return null;
        }
        DoubleNode<T> cur = head;
        if (head == tail) {
            head = null;
            tail = null;
        } else {
            DoubleNode<T> preNode = head.pre;
            preNode.next = null;
            head.pre = null;
            head = preNode;
        }
        return cur.value;
    }


    public T removeFromBottom() {
        if (tail == null) {
            return null;
        }
        DoubleNode<T> cur = tail;
        if (head == tail) {
            head = null;
            tail = null;
        } else {
            DoubleNode<T> next = tail.next;
            next.pre = null;
            tail.next = null;
            tail = next;
        }
        return cur.value;
    }


    public void showDoubleNode(DoubleNode<T> head) {
        while (head != null) {
            System.out.print(head.value + "<>");
            head = head.pre;
        }
        System.out.print("null");
    }

    public void testDoubleNode(List<T> list) {
        list.forEach(this::addFromHead);
//        list.forEach(System.out::print);
        showDoubleNode(head);
//        list.forEach(this::addFromBottom);
//        showDoubleNode(head);
    }

    public DoubleNode reserveDouble(DoubleNode head) {
        return null;
    }
    public static void main(String[] args) {
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            list.add(i);
        }
        list.forEach(System.out::print);
        Code_01_DoubleEndsQueue<Integer> integerCode_01_doubleEndsQueue = new Code_01_DoubleEndsQueue<>();
        integerCode_01_doubleEndsQueue.testDoubleNode(list);
    }
}
