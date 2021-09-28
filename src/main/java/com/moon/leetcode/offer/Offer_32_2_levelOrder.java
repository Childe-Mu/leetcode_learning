package com.moon.leetcode.offer;

// 剑指 Offer 32 - II. 从上到下打印二叉树 II
//
//从上到下按层打印二叉树，同一层的节点按从左到右的顺序打印，每一层打印到一行。
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
//  [9,20],
//  [15,7]
//]
// 提示：
//
// 节点总数 <= 1000
//
// 注意：本题与主站 102 题相同：https://leetcode-cn.com/problems/binary-tree-level-order-tra
//versal/
// Related Topics 树 广度优先搜索
// 👍 100 👎 0


import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

//leetcode submit region begin(Prohibit modification and deletion)
public class Offer_32_2_levelOrder {
    public List<List<Integer>> levelOrder_v1(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        if (root == null) {
            return res;
        }
        Queue<TreeNode> q1 = new ArrayDeque<>();
        Queue<TreeNode> q2 = new ArrayDeque<>();
        q1.offer(root);
        while (!q1.isEmpty() || !q2.isEmpty()) {
            if (q1.isEmpty()) {
                Queue<TreeNode> temp = q1;
                q1 = q2;
                q2 = temp;
            }
            List<Integer> list = new ArrayList<>();
            while (!q1.isEmpty()) {
                TreeNode node = q1.poll();
                list.add(node.val);
                if (node.left != null) {
                    q2.offer(node.left);
                }
                if (node.right != null) {
                    q2.offer(node.right);
                }
            }
            res.add(list);
        }
        return res;
    }

    public List<List<Integer>> levelOrder_v2(TreeNode root) {
        Queue<TreeNode> queue = new LinkedList<>();
        List<List<Integer>> res = new ArrayList<>();
        if (root != null) queue.add(root);
        while (!queue.isEmpty()) {
            List<Integer> tmp = new ArrayList<>();
            for (int i = queue.size(); i > 0; i--) {
                TreeNode node = queue.poll();
                tmp.add(node.val);
                if (node.left != null) queue.add(node.left);
                if (node.right != null) queue.add(node.right);
            }
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
