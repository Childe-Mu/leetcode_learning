package com.moon.leetcode;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 111. 二叉树的最小深度
 * <p>
 * 给定一个二叉树，找出其最小深度。
 * <p>
 * 最小深度是从根节点到最近叶子节点的最短路径上的节点数量。
 * <p>
 * 说明：叶子节点是指没有子节点的节点。
 * <p>
 * 示例 1：
 * <p>
 * 输入：root = [3,9,20,null,null,15,7]
 * 输出：2
 * <p>
 * 示例 2：
 * <p>
 * 输入：root = [2,null,3,null,4,null,5,null,6]
 * 输出：5
 * <p>
 * 提示：
 * <p>
 * 树中节点数的范围在 [0, 105] 内
 * -1000 <= Node.val <= 1000
 */
public class No111_minDepth {
    /**
     * 递归
     */
    public int minDepth_v1(TreeNode root) {
        if (root == null) {
            return 0;
        }
        if (root.left == null && root.right == null ){
            return 1;
        }
        int minDept = Integer.MAX_VALUE;
        if (root.left != null) {
            minDept = Math.min(minDepth_v1(root.left), minDept);
        }
        if (root.right != null) {
            minDept = Math.min(minDepth_v1(root.right), minDept);
        }

        return minDept + 1;
    }

   /**
     * 递归
     */
    public int minDepth_v2(TreeNode root) {
        if (root == null) {
            return 0;
        }
        Queue<TreeNode> q = new LinkedList<>();
        q.offer(root);
        int minDept = 1;
        while (!q.isEmpty()) {
            int size = q.size();
            while (size-- > 0) {
                TreeNode node = q.poll();
                if (node == null) {
                    continue;
                }
                if (node.left == null && node.right == null) {
                    return minDept;
                }
                q.offer(node.left);
                q.offer(node.right);
            }
            minDept++;
        }
        return minDept;
    }

    private static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode() {
        }

        TreeNode(int val) {
            this.val = val;
        }

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }
}
