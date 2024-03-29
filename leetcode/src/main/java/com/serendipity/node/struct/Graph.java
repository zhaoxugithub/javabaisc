package com.serendipity.node.struct;

import java.util.HashMap;
import java.util.HashSet;

/**
 * ClassName Graph
 * Description TODO
 * Author 11931
 * Date 2022-09-25:0:57
 * Version 1.0
 **/
public class Graph {
    // 描述具体的点，Key表示的图节点的编号，value表示的节点
    public HashMap<Integer, Node> nodes;
    // 描述的是图的边
    public HashSet<Edge> edges;

    public Graph() {
        nodes = new HashMap<>();
        edges = new HashSet<>();
    }
}
