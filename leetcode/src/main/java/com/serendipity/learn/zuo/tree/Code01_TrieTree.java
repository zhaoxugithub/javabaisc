package com.serendipity.learn.zuo.tree;
/**
 * Copyright (C), 2017-2022, 赵旭
 * Author: 11931
 * Date: 2022/2/20 17:04
 * FileName: Code01_TrieTree
 * Description: com.datastruct.zuo.tree
 * <p>
 * 前缀树实现
 */
public class Code01_TrieTree {

    private static class Node {

        public int pass;
        public int end;
        public Node[] nexts;

        public Node() {
            this.pass = 0;
            this.end = 0;
            this.nexts = new Node[26];
        }
    }

    public static class TrieTree {
        private Node root;

        public TrieTree() {
            root = new Node();
        }

        public void insert(String word) {
            if (word == null) {
                return;
            }
            char[] chars = word.toCharArray();
            //创建一个遍历下标
            Node node = root;
            node.pass++;
            int index = 0;
            for (int i = 0; i < chars.length; i++) {
                //获取前缀的路径
                index = chars[i] - 'a';
                //如果当前枝干的下一个节点不存在就需要创建
                if (node.nexts[index] == null) {
                    node.nexts[index] = new Node();
                }
                node = node.nexts[index];
                node.pass++;
            }
            node.end++;
        }

        public int search(String word) {
            if (word == null) {
                return 0;
            }
            char[] chars = word.toCharArray();
            Node node = root;
            int index = 0;
            for (int i = 0; i < chars.length; i++) {
                index = chars[i] - 'a';
                if (node.nexts[index] == null) {
                    return 0;
                }
                node = node.nexts[index];
            }
            return node.end;
        }

        // 所有加入的字符串中，有几个是以pre这个字符串作为前缀的
        public int prefixNumber(String pre) {
            if (pre == null) {
                return 0;
            }
            char[] chars = pre.toCharArray();

            int index = 0;
            Node node = root;

            for (int i = 0; i < pre.length(); i++) {
                index = chars[i] - 'a';
                //如果没有支线
                if (node.nexts[index] == null) {
                    return 0;
                }
                node = node.nexts[index];
            }
            return node.pass;
        }
    }

    public static void main(String[] args) {
        String[] strings = {"abc", "ab", "abd", "fg", "efgh", "a"};
        TrieTree trieTree = new TrieTree();
        for (int i = 0; i < strings.length; i++) {
            trieTree.insert(strings[i]);
        }
        int ab = trieTree.prefixNumber("a");
        System.out.println(ab);
    }
}
