package com.serendipity.learn.zuo.line;

import java.util.HashMap;
import java.util.Map;

/**
 * Copyright (C), 2017-2022, 赵旭
 * Author: 11931
 * Date: 2022/4/14 15:56
 * FileName: Code_02_CopyListWithRandom
 * Description: com.datastruct.zuo.line
 */

//https://leetcode-cn.com/problems/copy-list-with-random-pointer/
public class Code_02_CopyListWithRandom {

    public static class Node {
        int val;
        Node next;
        Node random;

        public Node(int val) {
            this.val = val;
            this.next = null;
            this.random = null;
        }
    }


    //方法一：借助hashMap
    public static Node copyRandomList1(Node head) {

        if (head == null) return head;

        Map<Node, Node> nodeNodeHashMap = new HashMap<Node, Node>();
        Node cur = head;
        while (cur != null) {
            Node valueCur = new Node(cur.val);
            nodeNodeHashMap.put(cur, valueCur);
            cur = cur.next;
        }

        for (Map.Entry<Node, Node> entry : nodeNodeHashMap.entrySet()) {
            Node key = entry.getKey();
            Node random = key.random;
            Node next = key.next;
            Node value = entry.getValue();
            value.random = nodeNodeHashMap.get(random);
            value.next = nodeNodeHashMap.get(next);
        }
        return nodeNodeHashMap.get(head);
    }

    //方法二
    public static Node copyRandomList2(Node head) {
        if (head == null) {
            return head;
        }
        Node cur = head;
        // 将 copy的节点插入到真节点的后面
        while (cur != null) {
            Node newNode = new Node(cur.val);
            newNode.next = cur.next;
            cur.next = newNode;
            cur = newNode.next;
        }
        cur = head;
        // 将当前节点的random复制到copy出来的节点的后面
        while (cur != null) {
            cur.next.random = cur.random != null ? cur.random.next : null;
            cur = cur.next.next;
        }

        //断开节点
        cur = head;
        Node newHead = head.next;
        while (cur != null) {
            Node next = cur.next;
            cur.next = next.next;
            cur = cur.next;
            next.next = cur == null ? null : cur.next;

        }
        return newHead;
    }

    public static void main(String[] args) {
        //todo 测试代码是否正确
    }

}
