package com.moon.leetcode;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * 107. 二叉树的层序遍历 II
 * <p>
 * 给定一个二叉树，返回其节点值自底向上的层序遍历。 （即按从叶子节点所在层到根节点所在的层，逐层从左向右遍历）
 * <p>
 * 例如：
 * 给定二叉树 [3,9,20,null,null,15,7],
 * <p>
 * 3
 * / \
 * 9  20
 * /  \
 * 15   7
 * 返回其自底向上的层序遍历为：
 * <p>
 * [
 * [15,7],
 * [9,20],
 * [3]
 * ]
 */
public class No107_levelOrderBottom {
    /**
     * 广度优先算法，使用ArrayList要在最后翻转一次，可以使用LinkedList，每次都从头部插入，即add(0, val)
     */
    public List<List<Integer>> levelOrderBottom(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        if (root == null) {
            return res;
        }
        Queue<TreeNode> q = new LinkedList<>();
        q.offer(root);
        while (!q.isEmpty()) {
            int levelSize = q.size();
            List<Integer> temp = new ArrayList<>();
            while (levelSize-- > 0) {
                TreeNode node = q.poll();
                if (node == null) {
                    continue;
                }
                temp.add(node.val);
                if (node.left != null) {
                    q.offer(node.left);
                }
                if (node.right != null) {
                    q.offer(node.right);
                }
            }
            res.add(temp);
        }
        Collections.reverse(res);
        return res;
    }

    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }
}
