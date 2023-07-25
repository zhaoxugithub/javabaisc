package com.serendipity.learn.zuo.tree;


import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

//统计二叉树的最大宽度
public class Code01_TreeMaxWidth {

    private static class Node {
        public int value;
        public Node left;
        public Node right;

        public Node(int data) {
            this.value = data;
        }
    }

    public static int maxWidthUseMap(Node root) {
        if (root == null) {
            return 0;
        }
        //存放节点key =node,value=node所在的层级level
        Map<Node, Integer> map = new HashMap<Node, Integer>();
        Queue<Node> queue = new LinkedList<>();
        //当前遍历所在的层级
        int curLevel = 1;
        //当前节点在当前的层级的宽度
        int curLevelNodes = 0;
        //最大宽度
        int max = 0;
        map.put(root, curLevel);
        queue.add(root);
        while (!queue.isEmpty()) {
            //拿出当前遍历的节点和节点所在的层级
            Node curNode = queue.poll();
            Integer curNodeLeval = map.get(curNode);
            //分别将当前节点的子节点存放到map中
            if (curNode.left != null) {
                map.put(curNode.left, curNodeLeval + 1);
                queue.add(curNode.left);
            }
            if (curNode.right != null) {
                map.put(curNode.right, curNodeLeval + 1);
                queue.add(curNode.right);
            }
            //如果当前节点的层级和 所在遍历所在的层级相同
            //其实表示还没开始子层级的遍历
            if (curLevel == curNodeLeval) {
                curLevelNodes++;
            } else {
                //当开始遍历子层级第一个节点的时候，需要统计上一层级的宽度
                max = Math.max(max, curLevelNodes);
                //层级+1
                curLevel++;
                //当前所在的节点遍历数量=1
                curLevelNodes = 1;
            }
        }
        //计算最后一层的宽度和上几层的最大宽度
        max = Math.max(max, curLevelNodes);
        return max;
    }

    //方法二：不借助map
    public static int maxWidthUnUseMap(Node root) {

        if (root == null) {
            return 0;
        }

        Queue<Node> queue = new LinkedList<>();
        queue.add(root);

        //当前层最右边的节点
        Node curEnd = root;
        //下一层最右边的节点
        Node nextEnd = null;
        //最大的宽度
        int max = 0;
        //当前所在的层级的宽度
        int curLevelNodes = 0;

        while (!queue.isEmpty()) {
            Node poll = queue.poll();

            if (poll.left != null) {
                queue.add(poll.left);
                nextEnd = poll.left;
            }
            if (poll.right != null) {
                queue.add(poll.right);
                nextEnd = poll.right;
            }
            //当前所在的层级的宽度+1
            curLevelNodes++;
            //如果当前节点是所在层级的最后一个节点
            //就进行结算
            if (curEnd == poll) {
                max = Math.max(max, curLevelNodes);
                curLevelNodes = 0;
                curEnd = nextEnd;
            }
        }
        return max;
    }


    public static void level(Node root) {

        if (root == null) {
            return;
        }
        Queue<Node> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            Node poll = queue.poll();
            System.out.print(poll.value + " ");
            if (poll.left != null) {
                queue.add(poll.left);
            }
            if (poll.right != null) {
                queue.add(poll.right);
            }
        }
    }

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

    public static void main(String[] args) {
        int maxLevel = 10;
        int maxValue = 100;
        int testTimes = 1000000;
        for (int i = 0; i < testTimes; i++) {
            Node head = generateRandomBST(maxLevel, maxValue);
            level(head);
            System.out.println("-----------------------");
            if (maxWidthUseMap(head) != maxWidthUnUseMap(head)) {
                System.out.println("Oops!");
            }
        }
        System.out.println("finish!");
    }
}
