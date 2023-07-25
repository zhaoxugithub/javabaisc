package com.serendipity.learn.zuo.line;

public class Code_01_ReverseList {

    private class ListNode {
        public int value;
        public ListNode next;

        public ListNode(int value) {
            this.value = value;
        }
    }

    private class DoubleNode {
        public int value;
        public DoubleNode next;
        public DoubleNode last;

        public DoubleNode(int value) {
            this.value = value;
        }
    }

    //因为返回的头结点不是原来的头结点，所以需要另外返回一个节点
    /*
        1. 两个变量 pre,next =null
        2. 拿next抓一下head.next
        3.head.next 指向 pre
        4.pre  = head;
        5.head = next;
     */
    public ListNode reverserListNode(ListNode head) {
        ListNode pre = null;
        ListNode next = null;
        while (head != null) {
            //抓一下头节点的后继
            next = head.next;
            //重新给头结点赋予另外一个节点
            head.next = pre;
            pre = head;
            head = next;
        }
        return pre;
    }

    public DoubleNode reverseDoubleNode(DoubleNode head) {
        DoubleNode pre = null;
        DoubleNode next = null;
        while (head != null) {
            next = head.next;
            head.next = pre;
            head.last = next;
            pre = head;
            head = next;
        }
        return pre;
    }

    public void showList(ListNode head) {
        while (head != null) {
            System.out.print(head.value + "=>");
            head = head.next;
        }
        System.out.print("null");
    }


    public void testListNode() {
        ListNode head = new ListNode(0);
        ListNode cur = head;
        for (int i = 1; i < 10; i++) {
            cur.next = new ListNode(i);
            cur = cur.next;
        }
        showList(head);
        head = reverserListNode(head);
        System.out.println();
        showList(head);
    }

    public void showDoubleNode(DoubleNode head) {
        while (head != null) {
            System.out.print(head.value + "<>");
            head = head.next;
        }
        System.out.print("null");
    }

    public void testDoubleNode() {

        DoubleNode head = new DoubleNode(0);
        DoubleNode cur = head;

        for (int i = 1; i < 10; i++) {
            DoubleNode doubleNode = new DoubleNode(i);
            cur.next = doubleNode;
            cur = cur.next;
            doubleNode.last = cur;
        }
        showDoubleNode(head);
        head = reverseDoubleNode(head);
        System.out.println();
        showDoubleNode(head);

    }

    public static void main(String[] args) {
        new Code_01_ReverseList().testListNode();
//        new Code_01_ReverseList().testDoubleNode();
    }
}
