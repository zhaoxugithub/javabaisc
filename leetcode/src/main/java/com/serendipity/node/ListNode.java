package com.serendipity.node;

/**
 * ClassName ListNode
 * Description TODO
 * Author 11931
 * Date 2022-10-16:10:36
 * Version 1.0
 **/

public class ListNode<T> {
    public T val;
    public ListNode next;

    public ListNode() {
    }

    public ListNode(T value) {
        this.val = value;
    }

    public ListNode(T val, ListNode next) {
        this.val = val;
        this.next = next;
    }

    @Override
    public String toString() {
        return "ListNode{" +
                "val=" + val +
                ", next=" + next +
                '}';
    }
}
