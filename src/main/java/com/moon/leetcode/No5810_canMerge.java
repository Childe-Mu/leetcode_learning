package com.moon.leetcode;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

// 5810. 合并多棵二叉搜索树
//
//给你 n 个 二叉搜索树的根节点 ，存储在数组 trees 中（下标从 0 开始），对应 n 棵不同的二叉搜索树。trees 中的每棵二叉搜索树 最多有 3
// 个节点 ，且不存在值相同的两个根节点。在一步操作中，将会完成下述步骤：
//
//
// 选择两个 不同的 下标 i 和 j ，要求满足在 trees[i] 中的某个 叶节点 的值等于 trees[j] 的 根节点的值 。
// 用 trees[j] 替换 trees[i] 中的那个叶节点。
// 从 trees 中移除 trees[j] 。
//
//
// 如果在执行 n - 1 次操作后，能形成一棵有效的二叉搜索树，则返回结果二叉树的 根节点 ；如果无法构造一棵有效的二叉搜索树，返回 null 。
//
// 二叉搜索树是一种二叉树，且树中每个节点均满足下述属性：
//
//
// 任意节点的左子树中的值都 严格小于 此节点的值。
// 任意节点的右子树中的值都 严格大于 此节点的值。
//
//
// 叶节点是不含子节点的节点。
//
//
//
// 示例 1：
//
//
//输入：trees = [[2,1],[3,2,5],[5,4]]
//输出：[3,2,5,1,null,4]
//解释：
//第一步操作中，选出 i=1 和 j=0 ，并将 trees[0] 合并到 trees[1] 中。
//删除 trees[0] ，trees = [[3,2,5,1],[5,4]] 。
//
//在第二步操作中，选出 i=0 和 j=1 ，将 trees[1] 合并到 trees[0] 中。
//删除 trees[1] ，trees = [[3,2,5,1,null,4]] 。
//
//结果树如上图所示，为一棵有效的二叉搜索树，所以返回该树的根节点。
//
// 示例 2：
//
//
//输入：trees = [[5,3,8],[3,2,6]]
//输出：[]
//解释：
//选出 i=0 和 j=1 ，然后将 trees[1] 合并到 trees[0] 中。
//删除 trees[1] ，trees = [[5,3,8,2,6]] 。
//
//结果树如上图所示。仅能执行一次有效的操作，但结果树不是一棵有效的二叉搜索树，所以返回 null 。
//
//
// 示例 3：
//
//
//输入：trees = [[5,4],[3]]
//输出：[]
//解释：无法执行任何操作。
//
//
// 示例 4：
//
//
//输入：trees = [[2,1,3]]
//输出：[2,1,3]
//解释：trees 中只有一棵树，且这棵树已经是一棵有效的二叉搜索树，所以返回该树的根节点。
//
//
//
//
// 提示：
//
//
// n == trees.length
// 1 <= n <= 5 * 104
// 每棵树中节点数目在范围 [1, 3] 内。
// trees 中不存在两棵树根节点值相同的情况。
// 输入中的所有树都是 有效的二叉树搜索树 。
// 1 <= TreeNode.val <= 5 * 104.
//
// 👍 0 👎 0
public class No5810_canMerge {
    private Map<Integer, TreeNode> map;

    public TreeNode canMerge(List<TreeNode> trees) {
        this.map = new HashMap<>();
        for (TreeNode tree : trees) {
            map.put(tree.val, tree);
        }
        TreeNode root = getRoot(trees);
        if (root == null) {
            return null;
        }
        if (root.left == null && root.right == null) {
            if (map.size() == 1) {
                return root;
            } else {
                return null;
            }
        }
        dfs(root, root.left, 1);
        dfs(root, root.right, 2);
        if (check(root, Integer.MIN_VALUE, Integer.MAX_VALUE) && map.size() == 1) {
            return root;
        }
        return null;
    }

    private boolean check(TreeNode root, int min, int max) {
        if (root == null) {
            return true;
        }
        if (root.val <= min || root.val >= max) {
            return false;
        }
        return check(root.left, min, root.val) && check(root.right, root.val, max);
    }

    private TreeNode getRoot(List<TreeNode> trees) {
        int[] cnt = new int[5 * (int) 1e4 + 1];
        for (TreeNode tree : trees) {
            cnt[tree.val]++;
            if (tree.left != null) {
                cnt[tree.left.val]++;
            }
            if (tree.right != null) {
                cnt[tree.right.val]++;
            }
        }
        for (int i = 0; i < 5 * (int) 1e4 + 1; i++) {
            if (cnt[i] == 1 && map.containsKey(i)) {
                return map.get(i);
            }
        }
        return null;
    }

    private void dfs(TreeNode pre, TreeNode node, int i) {
        if (node == null) {
            return;
        }
        if (node.left == null && node.right == null && map.containsKey(node.val)) {
            if (i == 1) {
                pre.left = map.remove(node.val);
            } else {
                pre.right = map.remove(node.val);
            }
        }
        pre = i == 1 ? pre.left : pre.right;
        dfs(pre, pre.left, 1);
        dfs(pre, pre.right, 2);
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
