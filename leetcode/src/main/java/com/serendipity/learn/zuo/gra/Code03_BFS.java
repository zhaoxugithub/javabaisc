package com.serendipity.learn.zuo.gra;


import com.serendipity.node.struct.Node;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

/**
 * ClassName Code03_BFS
 * Description TODO
 * Author 11931
 * Date 2022-09-25:22:28
 * Version 1.0
 **/

// 图的宽度遍历
public class Code03_BFS {
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

    public static void bfs2(Node node) {
        if (node == null) {
            return;
        }
        Queue<Node> queue = new LinkedList<>();
        Set<Node> set = new HashSet<>();
        queue.add(node);
        set.add(node);
        while (!queue.isEmpty()) {
            Node first = queue.poll();
            for (Node next : first.nexts) {
                if (!set.contains(next)) {
                    queue.add(next);
                    set.add(node);
                }
            }
        }
    }
}
