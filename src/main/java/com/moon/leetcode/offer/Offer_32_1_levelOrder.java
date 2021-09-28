package com.moon.leetcode.offer;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

// 剑指 Offer 32 - I. 从上到下打印二叉树
//
//从上到下打印出二叉树的每个节点，同一层的节点按照从左到右的顺序打印。
//
// 例如:
//给定二叉树: [3,9,20,null,null,15,7],
//
//     3
//   / \
//  9  20
//    /  \
//   15   7
//
// 返回：
//
// [3,9,20,15,7]
//
// 提示：
//
// 节点总数 <= 1000
//
// Related Topics 树 广度优先搜索
// 👍 82 👎 0
public class Offer_32_1_levelOrder {
    public int[] levelOrder(TreeNode root) {
        if (root == null) {
            return new int[0];
        }
        Queue<TreeNode> queue = new ArrayDeque<>();
        List<Integer> res = new ArrayList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();
            res.add(node.val);
            if (node.left != null) {
                queue.offer(node.left);
            }
            if (node.right != null) {
                queue.offer(node.right);
            }
        }
        return res.stream().mapToInt(p -> p).toArray();
    }

    private static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }
}
