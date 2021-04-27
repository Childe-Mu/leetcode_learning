package com.moon.leetcode;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

// 剑指 Offer 34. 二叉树中和为某一值的路径
//
//输入一棵二叉树和一个整数，打印出二叉树中节点值的和为输入整数的所有路径。从树的根节点开始往下一直到叶节点所经过的节点形成一条路径。
//
// 示例:
//给定如下二叉树，以及目标和 target = 22，
//
//              5
//             / \
//            4   8
//           /   / \
//          11  13  4
//         /  \    / \
//        7    2  5   1
//
// 返回:
//
//[
//   [5,4,11,2],
//   [5,8,4,5]
//]
//
// 提示：
//
// 节点总数 <= 10000
//
// 注意：本题与主站 113 题相同：https://leetcode-cn.com/problems/path-sum-ii/
// Related Topics 树 深度优先搜索
// 👍 169 👎 0
public class Offer_34_pathSum {
    private final List<List<Integer>> res = new ArrayList<>();
    Deque<Integer> path = new ArrayDeque<>();

    public List<List<Integer>> pathSum_v1(TreeNode root, int target) {
        Deque<Integer> path = new ArrayDeque<>();
        dfs(root, target, 0, path);
        return res;
    }

    private void dfs(TreeNode node, int target, int sum, Deque<Integer> path) {
        if (node == null) {
            return;
        }
        if (node.left == null && node.right == null) {
            if (sum + node.val == target) {
                path.addLast(node.val);
                res.add(new ArrayList<>(path));
                path.removeLast();
            }
            return;
        }
        path.addLast(node.val);
        dfs(node.left, target, sum + node.val, path);
        dfs(node.right, target, sum + node.val, path);
        path.removeLast();
    }

    public List<List<Integer>> pathSum_v2(TreeNode root, int target) {
        recur(root, target);
        return res;
    }

    void recur(TreeNode node, int target) {
        if (node == null) {
            return;
        }
        path.addLast(node.val);
        target -= node.val;
        if (target == 0 && node.left == null && node.right == null) {
            res.add(new LinkedList<>(path));
        }
        recur(node.left, target);
        recur(node.right, target);
        path.removeLast();
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
