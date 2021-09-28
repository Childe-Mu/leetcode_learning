package com.moon.leetcode;

/**
 * 437. 路径总和 III
 * <p>
 * /**
 * <p>给定一个二叉树的根节点 <code>root</code> ，和一个整数 <code>targetSum</code> ，求该二叉树里节点值之和等于 <code>targetSum</code> 的 <strong>路径</strong> 的数目。</p>
 *
 * <p><strong>路径</strong> 不需要从根节点开始，也不需要在叶子节点结束，但是路径方向必须是向下的（只能从父节点到子节点）。</p>
 *
 * <p> </p>
 *
 * <p><strong>示例 1：</strong></p>
 *
 * <p><img src="https://assets.leetcode.com/uploads/2021/04/09/pathsum3-1-tree.jpg" style="width: 452px; " /></p>
 *
 * <pre>
 * <strong>输入：</strong>root = [10,5,-3,3,2,null,11,3,-2,null,1], targetSum = 8
 * <strong>输出：</strong>3
 * <strong>解释：</strong>和等于 8 的路径有 3 条，如图所示。
 * </pre>
 * <p>
 * <p><strong>示例 2：</strong></p>
 * <p>
 * <pre>
 * <strong>输入：</strong>root = [5,4,8,11,null,13,4,7,2,null,null,5,1], targetSum = 22
 * <strong>输出：</strong>3
 * </pre>
 * <p>
 * <p> </p>
 * <p>
 * <p><strong>提示:</strong></p>
 *
 * <ul>
 * <li>二叉树的节点个数的范围是 <code>[0,1000]</code></li>
 * <li><meta charset="UTF-8" /><code>-10<sup>9</sup> <= Node.val <= 10<sup>9</sup></code> </li>
 * <li><code>-1000 <= targetSum <= 1000</code> </li>
 * </ul>
 * <div><div>Related Topics</div><div><li>树</li><li>深度优先搜索</li><li>二叉树</li></div></div><br><div><li>👍 1029</li><li>👎 0</li></div>
 */

import java.util.HashMap;
import java.util.Map;

/**
 * Definition for a binary tree node.
 * public class TreeNode {
 * int val;
 * TreeNode left;
 * TreeNode right;
 * TreeNode() {}
 * TreeNode(int val) { this.val = val; }
 * TreeNode(int val, TreeNode left, TreeNode right) {
 * this.val = val;
 * this.left = left;
 * this.right = right;
 * }
 * }
 */
public class No437_pathSum {
    Map<Integer, Integer> map;
    int ans = 0;
    int t;

    public int pathSum_v1(TreeNode root, int t) {
        this.t = t;
        dfs(root);
        return ans;
    }

    private void dfs(TreeNode node) {
        if (node == null) {
            return;
        }
        sum(node, 0);
        dfs(node.left);
        dfs(node.right);
    }

    private void sum(TreeNode node, int s) {
        if (node == null) {
            return;
        }
        s += node.val;
        if (s == t) {
            ans++;
        }
        sum(node.left, s);
        sum(node.right, s);
    }

    public int pathSum_v2(TreeNode root, int t) {
        if (root == null) {
            return 0;
        }
        this.t = t;
        this.map = new HashMap<>();
        map.put(0, 1);
        dfs(root, root.val);
        return ans;
    }

    private void dfs(TreeNode node, int val) {
        if (node == null) {
            return;
        }
        if (map.containsKey(val - t)) {
            ans += map.get(val - t);
        }
        map.put(val, map.getOrDefault(val, 0) + 1);
        if (node.left != null) {
            dfs(node.left, node.left.val + val);
        }
        if (node.right != null) {
            dfs(node.right, node.right.val + val);
        }
        map.put(val, map.getOrDefault(val, 0) - 1);
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
