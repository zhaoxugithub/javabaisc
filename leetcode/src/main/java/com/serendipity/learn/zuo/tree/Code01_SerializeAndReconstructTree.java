package com.serendipity.learn.zuo.tree;


import java.util.LinkedList;
import java.util.Queue;

//二叉树的序列化和反序列化
public class Code01_SerializeAndReconstructTree {
    private static class Node {
        public int value;
        public Node left;
        public Node right;

        public Node(int data) {
            this.value = data;
        }
    }
    //采用前序遍历
    public static Queue<String> serPre(Node head) {
        Queue<String> queue = new LinkedList<>();
        pre(head, queue);
        return queue;
    }
    public static void pre(Node head, Queue<String> queue) {
        if (head == null) {
            queue.add(null);
        } else {
            queue.add(String.valueOf(head.value));
            pre(head.left, queue);
            pre(head.right, queue);
        }
    }
    //前序遍历反序列化
    public static Node buildFromPre(Queue<String> queue) {
        if (queue == null || queue.size() == 0) {
            return null;
        }
        return fromPre(queue);
    }

    public static Node fromPre(Queue<String> queue) {
        String value = queue.poll();
        if (value == null) {
            return null;
        }
        int val = Integer.parseInt(value);
        Node node = new Node(val);
        node.left = fromPre(queue);
        node.right = fromPre(queue);
        return node;
    }

    //todo 中序遍历 后续遍历，层级遍历 以及反序列化

    // for test
    public static Node generateRandomBST(int maxLevel, int maxValue) {
        return generate(1, maxLevel, maxValue);
    }

    // for test
    public static Node generate(int level, int maxLevel, int maxValue) {
        if (level > maxLevel || Math.random() < 0.5) {
            return null;
        }
        Node head = new Node((int) (Math.random() * maxValue));
        head.left = generate(level + 1, maxLevel, maxValue);
        head.right = generate(level + 1, maxLevel, maxValue);
        return head;
    }

    // for test
    public static boolean isSameValueStructure(Node head1, Node head2) {
        if (head1 == null && head2 != null) {
            return false;
        }
        if (head1 != null && head2 == null) {
            return false;
        }
        if (head1 == null) {
            return true;
        }
        if (head1.value != head2.value) {
            return false;
        }
        return isSameValueStructure(head1.left, head2.left) && isSameValueStructure(head1.right, head2.right);
    }

    // for test
    public static void printTree(Node head) {
        System.out.println("Binary Tree:");
        printInOrder(head, 0, "H", 17);
        System.out.println();
    }

    public static void printInOrder(Node head, int height, String to, int len) {
        if (head == null) {
            return;
        }
        printInOrder(head.right, height + 1, "v", len);
        String val = to + head.value + to;
        int lenM = val.length();
        int lenL = (len - lenM) / 2;
        int lenR = len - lenM - lenL;
        val = getSpace(lenL) + val + getSpace(lenR);
        System.out.println(getSpace(height * len) + val);
        printInOrder(head.left, height + 1, "^", len);
    }

    public static String getSpace(int num) {
        String space = " ";
        StringBuilder buf = new StringBuilder("");
        for (int i = 0; i < num; i++) {
            buf.append(space);
        }
        return buf.toString();
    }

    public static void main(String[] args) {
        int maxLevel = 5;
        int maxValue = 100;
        int testTimes = 10;
        System.out.println("test begin");
        for (int i = 0; i < testTimes; i++) {
            Node head = generateRandomBST(maxLevel, maxValue);
            printTree(head);
            Queue<String> pre = serPre(head);
            Node preBuild = buildFromPre(pre);
            printTree(preBuild);
//            if (!isSameValueStructure(preBuild, posBuild) || !isSameValueStructure(posBuild, levelBuild)) {
//                System.out.println("Oops!");
//            }
        }
        System.out.println("test finish!");

    }


}
