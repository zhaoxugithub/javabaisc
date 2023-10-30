package com.serendipity.learn.zuo.gra;

import com.serendipity.node.struct.Node;

import java.util.*;

/**
 * ClassName Code03_DFS
 * Description TODO
 * Author 11931
 * Date 2022-09-25:22:45
 * Version 1.0
 **/
// 图的深度遍历
public class Code03_DFS {
    public static void bfs(Node node) {
        if (node == null) {
            return;
        }
        // 用来装需要遍历的节点信息
        Queue<Node> queue = new LinkedList<Node>();
        // 用来去重,如果已经遍历过了就放在这个set里面
        HashSet<Object> set = new HashSet<>();
        queue.add(node);
        set.add(set);
        // 从队列的首元素开始遍历
        while (!queue.isEmpty()) {
            Node poll = queue.poll();
            System.out.println(poll.value);
            for (Node next : poll.nexts) {
                // 如果没有被遍历过（set里面不存在）就添加到队里面
                if (!set.contains(next)) {
                    queue.add(next);
                    set.add(next);
                }
            }
        }
    }

    public static void dfs(Node node) {
        if (node == null) {
            return;
        }
        Stack<Node> stack = new Stack<>();
        // 去重
        HashSet<Node> set = new HashSet<>();

        stack.add(node);
        set.add(node);
        System.out.println(node.value);
        while (!stack.isEmpty()) {
            Node pop = stack.pop();
            for (Node next : pop.nexts) {
                // 如果节点没有被遍历过
                if (!set.contains(next)) {
                    // 为什么要把上游节点重新压进去？
                    stack.push(pop);
                    stack.push(next);
                    set.add(next);
                    System.out.println(next);
                    // 为什么需要break？？
                    break;
                }
            }
        }
    }

    public static void dfs2(Node node) {
        if (node == null) {
            return;
        }
        Stack<Node> stack = new Stack<>();
        Set<Node> set = new HashSet<>();
        stack.add(node);
        set.add(node);
        while (!stack.isEmpty()) {
            Node first = stack.pop();
            // todo something
            for (Node next : first.nexts) {
                if (!set.contains(next)) {
                    stack.push(first);
                    stack.push(next);
                    set.add(next);
                    break;
                }
            }
        }
    }
}
