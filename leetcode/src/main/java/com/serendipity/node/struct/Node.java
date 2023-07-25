package com.serendipity.node.struct;

import java.util.ArrayList;

/**
 * ClassName Node
 * Description 描述的是一个图节点的结构
 * Author 11931
 * Date 2022-09-25:0:47
 * Version 1.0
 **/
public class Node {

    // 是图节点的编号，可以是string类型
    public int value;
    // 入度
    public int in;
    // 出度
    public int out;
    // 节点直接邻居
    public ArrayList<Node> nexts;
    // 节点直接边
    public ArrayList<Edge> edges;

    public Node(int value) {
        this.value = value;
        this.in = 0;
        this.out = 0;
        nexts = new ArrayList<>();
        edges = new ArrayList<>();
    }
}
