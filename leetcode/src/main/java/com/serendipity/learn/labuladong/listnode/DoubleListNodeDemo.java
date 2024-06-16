package com.serendipity.learn.labuladong.listnode;

import org.junit.Before;
import org.junit.Test;

import java.util.HexFormat;


// 双链表
public class DoubleListNodeDemo {

    private DoubleListNode head;

    @Before
    public void before() {
        head = createDoubleLinkedList(new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9});
    }

    class DoubleListNode {
        int val;
        DoubleListNode next, pre;

        DoubleListNode(int val) {
            this.val = val;
        }
    }

    public DoubleListNode createDoubleLinkedList(int[] array) {
        if (array == null || array.length == 0) {
            return null;
        }
        DoubleListNode head = new DoubleListNode(array[0]);

        DoubleListNode cur = head;
        for (int i = 1; i < array.length; i++) {
            DoubleListNode next = new DoubleListNode(array[i]);
            cur.next = next;
            next.pre = cur;
            cur = cur.next;
        }
        return head;
    }


    @Test
    public void print() {
        DoubleListNode cur = head;
        StringBuilder sb = new StringBuilder();
        System.out.print("head:");
        while (cur != null) {
            sb.append(cur.val).append("<->");
            cur = cur.next;
        }
        sb.append("null");
        System.out.println(sb);
    }

    @Test
    public void printDoubleListNode() {
        // 正序遍历
        StringBuilder sb = new StringBuilder();
        DoubleListNode cur = head;
        for (; cur.next != null; cur = cur.next) {
            sb.append(cur.val).append("->");
        }
        sb.append(cur.val).append("->").append("null");
        System.out.println(sb);
        // 清空sb
        // 逆袭遍历
        sb.setLength(0);
        for (; cur != null; cur = cur.pre) {
            sb.append(cur.val).append("->");
        }
        sb.append("null");
        System.out.println(sb);
    }


    // 在双链表头部添加元素
    @Test
    public void insertHead() {
        print();
        DoubleListNode newHead = new DoubleListNode(0);
        newHead.next = head;
        head.pre = newHead;
        head = newHead;
        print();
    }


    //  尾部添加
    @Test
    public void insertTail() {
        print();
        DoubleListNode newHead = new DoubleListNode(10);

        DoubleListNode cur = head;
        for (; cur.next != null; cur = cur.next) {
        }

        newHead.pre = cur;
        cur.next = newHead;

        print();
    }

    // 随机添加
    @Test
    public void insertRange() {

        print();
        DoubleListNode newHead = new DoubleListNode(100);

        DoubleListNode cur = head;
        for (int i = 0; i < 2; i++) {
            cur = cur.next;
        }
        DoubleListNode next = cur.next;

        newHead.pre = next.pre;
        newHead.next = cur.next;
        cur.next = newHead;
        next.pre = newHead;

        print();
    }


    @Test
    public void removeHead() {
        print();

        DoubleListNode tmp = head;
        DoubleListNode next = head.next;
        next.pre = null;
        tmp.next = null;
        head = next;

        print();
    }


    @Test
    public void removeTail() {

        print();
        DoubleListNode cur = head;
        while (cur.next != null) {
            cur = cur.next;
        }
        DoubleListNode pre = cur.pre;

        pre.next = null;
        cur.pre = null;

        print();
    }

    @Test
    public void removeRange() {
        print();
        DoubleListNode temp = head;

        for (int i = 0; i < 3; i++) {
            temp = temp.next;
        }

        DoubleListNode pre = temp.pre;
        pre.next = temp.next;

        temp.next.pre = pre;
        temp.next = null;
        temp.pre = null;

        print();
    }
}






























