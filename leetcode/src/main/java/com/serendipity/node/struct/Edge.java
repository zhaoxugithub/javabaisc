package com.serendipity.node.struct;

/**
 * ClassName Edge
 * Description 图结构中边的描述（有向图和无向图都可以)
 * Author 11931
 * Date 2022-09-25:0:49
 * Version 1.0
 **/


public class Edge {
    // 边的权重
    public int weight;
    // 边的起始位置
    public Node from;
    // 边的终点位置
    public Node to;

    public Edge(int weight, Node from, Node to) {
        this.weight = weight;
        this.from = from;
        this.to = to;
    }
}
