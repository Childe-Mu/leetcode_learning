package com.moon.leetcode;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.List;

// 559. N 叉树的最大深度
//给定一个 N 叉树，找到其最大深度。
//
// 最大深度是指从根节点到最远叶子节点的最长路径上的节点总数。
//
// N 叉树输入按层序遍历序列化表示，每组子节点由空值分隔（请参见示例）。
//
//
//
// 示例 1：
//
//
//
//
//输入：root = [1,null,3,2,4,null,5,6]
//输出：3
//
//
// 示例 2：
//
//
//
//
//输入：root = [1,null,2,3,4,5,null,null,6,7,null,8,null,9,10,null,null,11,null,12,
//null,13,null,null,14]
//输出：5
//
//
//
//
// 提示：
//
//
// 树的深度不会超过 1000 。
// 树的节点数目位于 [0, 104] 之间。
//
// Related Topics 树 深度优先搜索 广度优先搜索
public class No559_maxDepth {
    public int maxDepth(Node root) {
        if (root == null) {
            return 0;
        }
        Deque<Node> queue = new ArrayDeque<>();
        queue.offerLast(root);
        int depth = 0;
        while (!queue.isEmpty()) {
            depth++;
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                Node node = queue.pollFirst();
                if (node.children == null || node.children.isEmpty()) {
                    continue;
                }
                for (Node n : node.children) {
                    queue.offerLast(n);
                }
            }
        }
        return depth;
    }

    private static class Node {
        public int val;
        public List<Node> children;

        public Node() {
        }

        public Node(int _val) {
            val = _val;
        }

        public Node(int _val, List<Node> _children) {
            val = _val;
            children = _children;
        }
    }

    ;
}
