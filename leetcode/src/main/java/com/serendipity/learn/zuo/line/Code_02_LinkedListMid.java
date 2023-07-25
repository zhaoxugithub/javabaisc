package com.serendipity.learn.zuo.line;


import java.util.Arrays;
import java.util.Stack;

/**
 * Copyright (C), 2017-2022, 赵旭
 * Author: 11931
 * Date: 2022/4/8 17:50
 * FileName: Code_02_LinkedListMid
 * Description: com.datastruct.zuo.line
 */

/*
快慢指针的运用
 */
public class Code_02_LinkedListMid {

    private class ListNode {
        public int value;
        public ListNode next;

        public ListNode(int value) {
            this.value = value;
        }
    }

    // 输入链表头结点，奇数长度返回中点，偶数长度返回上中点
    public ListNode MidOrUpNode(ListNode head) {
        // 如果是空节点或者只有1个，2个节点
        if (head == null || head.next == null || head.next.next == null) return head;
        // 至少含有三个节点
        ListNode slow = head;
        ListNode fast = head;
        while (fast.next != null && fast.next.next != null) {
            fast = fast.next.next;
            slow = slow.next;
        }
        return slow;
    }

    // github上的
    public static ListNode midOrUpMidNode(ListNode head) {
        if (head == null || head.next == null || head.next.next == null) {
            return head;
        }
        // 链表有3个点或以上
        ListNode slow = head.next;
        ListNode fast = head.next.next;
        while (fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }

    //---------------------------------------------

    // 输入链表头结点，奇数长度返回中点，偶数长度返回下中点
    public ListNode MidOrDownNode(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        if (head.next.next == null) {
            return head.next;
        }
        ListNode slow = head;
        ListNode fast = slow.next;
        while (fast.next != null && fast.next.next != null) {
            fast = fast.next.next;
            slow = slow.next;
        }
        return slow.next;
    }

    // github上的
    public static ListNode midOrDownMidNode(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode slow = head.next;
        ListNode fast = head.next;
        while (fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }

    //---------------------------------------

    // 输入链表头结点，奇数长度返回中点前一个，偶数长度返回上中点前一个
    public ListNode preMidOrPreUpNode(ListNode head) {

        if (head == null || head.next == null || head.next.next == null) {
            return null;
        }

        ListNode slow = head;
        ListNode fast = slow.next.next;
        while (fast.next != null && fast.next.next != null) {
            fast = fast.next.next;
            slow = slow.next;
        }
        return slow;
    }


    // github上的
    public static ListNode midOrUpMidPreNode(ListNode head) {
        if (head == null || head.next == null || head.next.next == null) {
            return null;
        }
        ListNode slow = head;
        ListNode fast = head.next.next;
        while (fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }

    //------------------------------------------------

    // 输入链表头结点，奇数长度返回中点前一个，偶数长度返回下中点前一个
    public ListNode preMidOrPreDownNode(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        if (head.next.next == null) {
            return head;
        }
        ListNode slow = head;
        ListNode fast = slow.next;
        while (fast.next != null && fast.next.next != null) {
            fast = fast.next.next;
            slow = slow.next;
        }
        return slow;
    }


    public static ListNode midOrDownMidPreNode(ListNode head) {
        if (head == null || head.next == null) {
            return null;
        }
        if (head.next.next == null) {
            return head;
        }
        ListNode slow = head;
        ListNode fast = head.next;
        while (fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }

    //---------------------------------
    public void showList(ListNode head) {
        while (head != null) {
            System.out.print(head.value + "=>");
            head = head.next;
        }
        System.out.print("null");
    }

    // 给定一个单链表的头结点head，请判断该链表是否是回文结构。
    // 方法一：入栈
    public boolean isMull(ListNode head) {
        if (null == head || head.next == null) {
            return true;
        }

        ListNode pre = head;
        Stack<ListNode> stack = new Stack<>();
        while (pre != null) {
            stack.push(pre);
            pre = pre.next;
        }
        pre = head;

        while (!stack.isEmpty() && pre != null) {
            ListNode pop = stack.pop();
            if (pre.value != pop.value) {
                return false;
            }
            pre = pre.next;
        }
        return true;
    }

    // 一半入栈，然后出栈对比（利用快慢指针）
    public boolean isMull02(ListNode head) {
        if (head == null || head.next == null) {
            return true;
        }
        if (head.next.next == null && head.next.value == head.value) {
            return true;
        }
        Stack<ListNode> stack = new Stack<>();
        ListNode slow = head;
        ListNode fast = head;
        while (fast.next != null && fast.next.next != null) {
            fast = fast.next.next;
            slow = slow.next;
        }
        slow = slow.next;
        while (slow != null) {
            stack.push(slow);
            slow = slow.next;
        }
        slow = head;
        while (!stack.isEmpty()) {
            ListNode pop = stack.pop();
            if (pop.value != slow.value) {
                return false;
            }
            slow = slow.next;
        }
        return true;
    }

    // 方法三：不用借助栈，将后半部分的链表反转（快慢指针）
    public boolean isMull03(ListNode head) {
        if (head == null || head.next == null) {
            return true;
        }
        if (head.next.next == null && head.value == head.next.value) {
            return true;
        }
        ListNode fast = head;
        ListNode slow = head;
        // 奇数中点，偶数上中点
        while (fast.next != null && fast.next.next != null) {
            fast = fast.next.next;
            slow = slow.next;
        }
        // 链表反转
        ListNode rightHead = slow;
        while (rightHead.next != null) {
            ListNode tempNode = rightHead.next;
            tempNode.next = rightHead;
            rightHead = rightHead.next;
        }

        while (rightHead.value != slow.value && head.value != slow.value) {
            if (rightHead.value != head.value) {
                return false;
            }
            rightHead = rightHead.next;
            head = head.next;
        }
        return true;
    }


    /*
    将单向链表按照某值分成左边小，中间相等，右边大的形式
     */
    // 方法一：把链表放入到 数组里，在数组上做partition(笔试用)
    public static ListNode listPartition1(ListNode head, int num) {

        if (head == null || head.next == null) {
            return head;
        }

        ListNode cur = head;
        int i = 0;
        while (cur != null) {
            i++;
            cur = cur.next;
        }

        ListNode[] arrNode = new ListNode[i];

        cur = head;
        i = 0;
        while (cur != null) {
            arrNode[i++] = cur;
            cur = cur.next;
        }
        partitionList(arrNode, num);
        System.out.println(Arrays.toString(arrNode));
        for (i = 1; i < arrNode.length; i++) {
            arrNode[i - 1].next = arrNode[i];
        }
        arrNode[i - 1].next = null;
        return arrNode[0];
    }

    private static void partitionList(ListNode[] arrNode, int num) {

        int l = -1;
        int r = arrNode.length;
        int index = 0;

        while (index < r) {

            if (arrNode[index].value < num) {
                swap(arrNode, index++, ++l);
            } else if (arrNode[index].value > num) {
                swap(arrNode, --r, index);
            } else {
                index++;
            }
        }
    }

    private static void swap(ListNode[] arrNode, int l, int r) {
        ListNode tempNode = arrNode[l];
        arrNode[l] = arrNode[r];
        arrNode[r] = tempNode;
    }


    public void testListNode() {
        ListNode head = new ListNode(0);
        ListNode cur = head;
        for (int i = 1; i < 10; i++) {
            int random = (int) (Math.random() * 100);
            cur.next = new ListNode(random);
            cur = cur.next;
        }
//        //回文结构
//        for (int i = 1; i < 4; i++) {
//            cur.next = new ListNode(3 - i);
//            cur = cur.next;
//        }
        showList(head);
        System.out.println();
//        System.out.println(isMull(head));
//        System.out.println(isMull02(head));
//        System.out.println(isMull03(head));
        ListNode listNode = listPartition1(head, 50);
        while (listNode != null) {
            System.out.print(listNode.value + ",");
            listNode = listNode.next;
        }
//        System.out.println(MidOrUpNode(head).value);
//        System.out.println(midOrUpMidNode(head).value);
//        System.out.println(MidOrDownNode(head).value);
//        System.out.println(midOrDownMidNode(head).value);
//        System.out.println(preMidOrPreUpNode(head).value);
//        System.out.println(midOrUpMidPreNode(head).value);
//        System.out.println(preMidOrPreDownNode(head).value);
//        System.out.println(midOrDownMidPreNode(head).value);
    }

    public static void main(String[] args) {
        new Code_02_LinkedListMid().testListNode();
    }
}
