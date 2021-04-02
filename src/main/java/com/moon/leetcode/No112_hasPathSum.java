package com.moon.leetcode;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 112. 路径总和
 * <p>
 * 给你二叉树的根节点 root 和一个表示目标和的整数 targetSum ，判断该树中是否存在 根节点到叶子节点 的路径，这条路径上所有节点值相加等于目标和 targetSum 。
 * <p>
 * 叶子节点 是指没有子节点的节点。
 * <p>
 * 示例 1：
 * <p>
 * 输入：root = [5,4,8,11,null,13,4,7,2,null,null,null,1], targetSum = 22
 * 输出：true
 * <p>
 * 示例 2：
 * <p>
 * 输入：root = [1,2,3], targetSum = 5
 * 输出：false\
 * <p>
 * 示例 3：
 * <p>
 * 输入：root = [1,2], targetSum = 0
 * 输出：false
 * <p>
 * 提示：
 * <p>
 * 树中节点的数目在范围 [0, 5000] 内
 * -1000 <= Node.val <= 1000
 * -1000 <= targetSum <= 1000
 */
public class No112_hasPathSum {
    /**
     * 递归
     */
    public boolean hasPathSum(TreeNode root, int targetSum) {
        if (root == null) {
            return false;
        }
        if (root.left == null && root.right == null) {
            return root.val == targetSum;
        }
        return hasPathSum(root.left, targetSum - root.val) || hasPathSum(root.right, targetSum - root.val);
    }

    /**
     * 迭代
     */
    public boolean hasPathSum_v2(TreeNode root, int targetSum) {
        if (root == null) {
            return false;
        }
        Queue<Helper> q = new LinkedList<>();
        q.offer(new Helper(root, targetSum));
        while (!q.isEmpty()) {
            Helper helper = q.poll();
            TreeNode node = helper.node;
            if (node.left == null && node.right == null && node.val == helper.val) {
                return true;
            }
            if (node.left != null) {
                q.offer(new Helper(node.left, helper.val - node.val));
            }
            if (node.right != null) {
                q.offer(new Helper(node.right, helper.val - node.val));
            }
        }
        return false;
    }

    private static class Helper {
        TreeNode node;
        int val;

        public Helper(TreeNode node, int val) {
            this.node = node;
            this.val = val;
        }
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
