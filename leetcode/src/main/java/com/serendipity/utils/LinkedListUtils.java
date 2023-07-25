package com.serendipity.utils;

import com.serendipity.node.ListNode;

/**
 * ClassName LinkedListUtil
 * Description TODO
 * Author 11931
 * Date 2022-10-16:10:35
 * Version 1.0
 **/
public class LinkedListUtils {
    /**
     * 创建单链表
     *
     * @param array
     * @return
     */
    public static ListNode<Integer> createListNode(int... array) {
        if (array == null || array.length == 0) {
            return null;
        }
        ListNode<Integer> head = new ListNode<>(array[0]);
        ListNode<Integer> current = head;
        for (int i = 1; i < array.length; i++) {
            current.next = new ListNode(array[i]);
            current = current.next;
        }
        return head;
    }

    /**
     * 打印单链表
     *
     * @param msg
     * @param head
     */
    public static void printListNode(String msg, ListNode<Integer> head) {
        System.out.println(appendVal(head));
    }

    public static void printListNode(ListNode<Integer> head) {
        System.out.println(appendVal(head));
    }

    /**
     * @param head
     * @return
     */
    private static String appendVal(ListNode head) {
        StringBuilder sb = new StringBuilder();
        ListNode current = head;

        while (current != null) {
            sb.append(current.val);
            sb.append("->");
            current = current.next;
        }
        sb.append("NULL");
        return sb.toString();
    }
}
