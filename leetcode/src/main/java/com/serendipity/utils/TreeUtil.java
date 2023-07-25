package com.serendipity.utils;

import com.serendipity.node.*;

import java.util.*;

/**
 * ClassName TreeUtil
 * Description TODO
 * Author 11931
 * Date 2022-10-16:10:59
 * Version 1.0
 **/
public class TreeUtil {

    public static TreeNode createTree(Integer... array) {

        if (array.length == 0) {
            return new TreeNode(0);
        }
        Deque<TreeNode> nodeQueue = new LinkedList<>();

        // 创建一个根节点
        TreeNode root = new TreeNode(array[0]);

        nodeQueue.offer(root);

        TreeNode current;

        // 记录当前行节点的数量（注意不一定是2的幂，而是上一行中非节点的数量乘2）
        int lineNodeNum = 2;
        // 记录当前行中数字在数字中的开始位置
        int startIndex = 1;

        // 记录数组中剩余的元素的数量
        int restLength = array.length - 1;

        while (restLength > 0) {
            // 只有最后一行可以不满，其余行必须是满的
//            // 若输入的数组的数量是错误的，直接跳出程序
//            if (restLength < lineNodeNum) {
//                System.out.println("Wrong Input!");
//                return new TreeNode(0);
//            }
            for (int i = startIndex; i < startIndex + lineNodeNum; i = i + 2) {
                // 说明已经将nums中的数字用完，此时应停止遍历，并可以直接返回root
                if (i == array.length) {
                    return root;
                }
                current = nodeQueue.poll();
                if (array[i] != null) {
                    current.left = new TreeNode(array[i]);
                    nodeQueue.offer(current.left);
                }
                // 同上，说明已经将nums中的数字用完，此时应停止遍历，并可以直接返回root
                if (i + 1 == array.length) {
                    return root;
                }
                if (array[i + 1] != null) {
                    current.right = new TreeNode(array[i + 1]);
                    nodeQueue.offer(current.right);
                }
            }
            startIndex += lineNodeNum;
            restLength -= lineNodeNum;
            lineNodeNum = nodeQueue.size() * 2;
        }
        return root;
    }

    public static void printNode(TreeNode root) {
        int maxLevel = maxLevel(root);
        printNodeInternal(Collections.singletonList(root), 1, maxLevel);
    }

    private static void printNodeInternal(List<TreeNode> nodes, int level, int maxLevel) {
        if (nodes.isEmpty() || isAllElementsNull(nodes)) {
            return;
        }

        int floor = maxLevel - level;
        int endgeLines = (int) Math.pow(2, (Math.max(floor - 1, 0)));
        int firstSpaces = (int) Math.pow(2, (floor)) - 1;
        int betweenSpaces = (int) Math.pow(2, (floor + 1)) - 1;

        printWhitespaces(firstSpaces);

        List<TreeNode> newNodes = new ArrayList<TreeNode>();
        for (TreeNode node : nodes) {
            if (node != null) {
                System.out.print(node.value);
                newNodes.add(node.left);
                newNodes.add(node.right);
            } else {
                newNodes.add(null);
                newNodes.add(null);
                System.out.print(" ");
            }

            printWhitespaces(betweenSpaces);
        }
        System.out.println("");

        for (int i = 1; i <= endgeLines; i++) {
            for (int j = 0; j < nodes.size(); j++) {
                printWhitespaces(firstSpaces - i);
                if (nodes.get(j) == null) {
                    printWhitespaces(endgeLines + endgeLines + i + 1);
                    continue;
                }
                if (nodes.get(j).left != null) {
                    System.out.print("/");
                } else {
                    printWhitespaces(1);
                }
                printWhitespaces(i + i - 1);

                if (nodes.get(j).right != null) {
                    System.out.print("\\");
                } else {
                    printWhitespaces(1);
                }
                printWhitespaces(endgeLines + endgeLines - i);
            }
            System.out.println("");
        }

        printNodeInternal(newNodes, level + 1, maxLevel);
    }

    private static void printWhitespaces(int count) {
        for (int i = 0; i < count; i++) {
            System.out.print(" ");
        }
    }

    private static int maxLevel(TreeNode node) {
        if (node == null) {
            return 0;
        }
        return Math.max(maxLevel(node.left), maxLevel(node.right)) + 1;
    }

    private static <T> boolean isAllElementsNull(List<T> list) {
        for (Object object : list) {
            if (object != null) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        int[] ints = ArrayUtils.generateRandomArray(10, 100, 1);
        Integer[] array = ArrayUtils.intToInteger(ints);
        ArrayUtils.printArr(ints);
        // 数组转成链表
        ListNode<Integer> listNode = LinkedListUtils.createListNode(ints);
        LinkedListUtils.printListNode(listNode);
        // 数组转成二叉树
        TreeNode tree = TreeUtil.createTree(array);
        TreeUtil.printNode(tree);
    }
}
