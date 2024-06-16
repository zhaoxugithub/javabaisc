package com.serendipity.learn.labuladong.listnode;

import org.junit.Before;
import org.junit.Test;

/**
 * 单链表操作
 */
public class ListNodeDemo {

    private ListNode head = null;

    @Before
    public void before() {
        head = createLinkedList(new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9});
    }

    class ListNode {
        int val;
        ListNode next;

        ListNode(int val) {
            this.val = val;
        }
    }

    // 创建单链表
    public ListNode createLinkedList(int[] array) {
        if (array == null || array.length == 0) {
            return null;
        }
        ListNode head = new ListNode(array[0]);
        ListNode cur = head;
        for (int i = 1; i < array.length; i++) {
            cur.next = new ListNode(array[i]);
            cur = cur.next;
        }
        return head;
    }

    // 遍历节点
    @Test
    public void print() {
        System.out.print("head:");
        for (ListNode p = head; p != null; p = p.next) {
            System.out.print(p.val + "->");
        }
        System.out.println("null");
    }

    // 头节点添加元素
    @Test
    public void headInsert() {
        print();
        ListNode newHead = new ListNode(0);
        newHead.next = head;
        head = newHead;
        print();
    }

    // 尾部添加元素
    @Test
    public void tailInsert() {
        print();
        ListNode listNode = new ListNode(10);

        ListNode cur = head;
        while (cur.next != null) {
            cur = cur.next;
        }
        cur.next = listNode;

        print();
    }

    // 随机位置添加元素
    @Test
    public void rangeInsert() {
        print();

        ListNode cur = head;

        for (int i = 0; i < 2; i++) {
            cur = cur.next;
        }

        ListNode node = new ListNode(1000);
        node.next = cur.next;
        cur.next = node;

        print();
    }

    // 删除头结点
    @Test
    public void removeHeadNode() {
        print();

        ListNode tmp = head;
        head = head.next;
        tmp.next = null;

        print();
    }

    // 删除尾节点
    @Test
    public void removeTailNode() {

        print();
        ListNode cur = head;
        for (; cur.next.next != null; cur = cur.next) {
        }
        cur.next = null;

        print();
    }

    // 随机删除
    @Test
    public void removeRangeNode() {
        print();

        ListNode cur = head;
        for (int i = 0; i < 2; i++) {
            cur = cur.next;
        }

        ListNode tmp = cur.next;
        cur.next = cur.next.next;
        tmp.next = null;

        print();
    }
}
