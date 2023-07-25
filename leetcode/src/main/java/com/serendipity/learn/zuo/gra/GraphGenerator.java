package com.serendipity.learn.zuo.gra;

import com.datastruct.zuo.gra.struct.Edge;
import com.datastruct.zuo.gra.struct.Graph;
import com.datastruct.zuo.gra.struct.Node;

/**
 * ClassName GraphGenerator
 * Description TODO
 * Author 11931
 * Date 2022-09-25:1:06
 * Version 1.0
 **/
public class GraphGenerator {

    // matrix的数组描述是【weight,from节点编号，to节点上面的编号】
    public static Graph createGraph(int[][] matrix) {

        Graph graph = new Graph();

        for (int i = 0; i < matrix.length; i++) {
            Integer weight = matrix[i][0];
            Integer from = matrix[i][1];
            Integer to = matrix[i][2];
            if (!graph.nodes.containsKey(from)) {
                graph.nodes.put(from, new Node(from));
            }

            if (!graph.nodes.containsKey(to)) {
                graph.nodes.put(to, new Node(to));
            }
            Node fromNode = graph.nodes.get(from);
            Node toNode = graph.nodes.get(to);
            Edge edge = new Edge(weight, fromNode, toNode);
            fromNode.nexts.add(toNode);
            fromNode.out++;
            toNode.in++;
            fromNode.edges.add(edge);
            graph.edges.add(edge);
        }
        return graph;
        // 并查集结果和图相关算法 1；29；59

    }
}
