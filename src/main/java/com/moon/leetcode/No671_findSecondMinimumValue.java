package com.moon.leetcode;

// 671. 二叉树中第二小的节点
//
//给定一个非空特殊的二叉树，每个节点都是正数，并且每个节点的子节点数量只能为 2 或 0。如果一个节点有两个子节点的话，那么该节点的值等于两个子节点中较小的一
//个。
//
// 更正式地说，root.val = min(root.left.val, root.right.val) 总成立。
//
// 给出这样的一个二叉树，你需要输出所有节点中的第二小的值。如果第二小的值不存在的话，输出 -1 。
//
//
//
// 示例 1：
//
//
//输入：root = [2,2,5,null,null,5,7]
//输出：5
//解释：最小的值是 2 ，第二小的值是 5 。
//
//
// 示例 2：
//
//
//输入：root = [2,2,2]
//输出：-1
//解释：最小的值是 2, 但是不存在第二小的值。
//
//
//
//
// 提示：
//
//
// 树中节点数目在范围 [1, 25] 内
// 1 <= Node.val <= 231 - 1
// 对于树中每个节点 root.val == min(root.left.val, root.right.val)
//
// Related Topics 树 深度优先搜索 二叉树
// 👍 199 👎 0


//leetcode submit region begin(Prohibit modification and deletion)

import java.util.LinkedList;
import java.util.Queue;

/**
 * Definition for a binary tree node.
 */
public class No671_findSecondMinimumValue {
    int ans = -1;
    int rootVal = -1;

    public int findSecondMinimumValue_v1(TreeNode root) {
        if (root.left == null) {
            return -1;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        int r = root.val;
        int ans = -1;
        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();
            if ((ans == -1 || ans > node.val) && node.val > r) {
                ans = node.val;
            }
            if (node.left != null) {
                queue.offer(node.left);
                queue.offer(node.right);
            }
        }
        return ans;
    }

    public int findSecondMinimumValue_v2(TreeNode root) {
        if (root.left == null) {
            return -1;
        }
        this.rootVal = root.val;
        dfs(root);
        return ans;
    }

    private void dfs(TreeNode node) {
        if (node == null) {
            return;
        }
        if ((this.ans == -1 || this.ans > node.val) && this.rootVal < node.val) {
            ans = node.val;
        }
        dfs(node.left);
        dfs(node.right);
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
