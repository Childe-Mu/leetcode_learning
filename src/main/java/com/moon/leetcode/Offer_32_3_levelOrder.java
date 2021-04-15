package com.moon.leetcode;

// 剑指 Offer 32 - III. 从上到下打印二叉树 III
//
//请实现一个函数按照之字形顺序打印二叉树，即第一行按照从左到右的顺序打印，第二层按照从右到左的顺序打印，第三行再按照从左到右的顺序打印，其他行以此类推。
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
//
// 返回其层次遍历结果：
//
// [
//  [3],
//  [20,9],
//  [15,7]
//]
//
// 提示：
//
// 节点总数 <= 1000
//
// Related Topics 树 广度优先搜索
// 👍 91 👎 0

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Offer_32_3_levelOrder {
    public List<List<Integer>> levelOrder_v1(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        if (root == null) {
            return res;
        }
        Deque<TreeNode> q1 = new ArrayDeque<>();
        Deque<TreeNode> q2 = new ArrayDeque<>();
        boolean status = true;
        q1.offer(root);
        while (!q1.isEmpty() || !q2.isEmpty()) {
            if (q1.isEmpty()) {
                Deque<TreeNode> temp = q1;
                q1 = q2;
                q2 = temp;
            }
            List<Integer> list = new ArrayList<>();
            while (!q1.isEmpty()) {
                TreeNode node = status ? q1.pollFirst() : q1.pollLast();
                list.add(node.val);
                if (status) {
                    if (node.left != null) {
                        q2.offerLast(node.left);
                    }
                    if (node.right != null) {
                        q2.offerLast(node.right);
                    }
                } else {
                    if (node.right != null) {
                        q2.offerFirst(node.right);
                    }
                    if (node.left != null) {
                        q2.offerFirst(node.left);
                    }
                }
            }
            status = !status;
            res.add(list);
        }
        return res;
    }

    public List<List<Integer>> levelOrder_v2(TreeNode root) {
        Queue<TreeNode> queue = new LinkedList<>();
        List<List<Integer>> res = new ArrayList<>();
        if (root != null) {
            queue.add(root);
        }
        boolean status = true;
        while (!queue.isEmpty()) {
            LinkedList<Integer> tmp = new LinkedList<>();
            for (int i = queue.size(); i > 0; i--) {
                TreeNode node = queue.poll();
                if (status) {
                    tmp.addLast(node.val);
                } else {
                    tmp.addFirst(node.val);
                }
                if (node.left != null) {
                    queue.add(node.left);
                }
                if (node.right != null) {
                    queue.add(node.right);
                }
            }
            status = !status;
            res.add(tmp);
        }
        return res;
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
