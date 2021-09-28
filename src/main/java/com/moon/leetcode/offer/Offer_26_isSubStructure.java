package com.moon.leetcode.offer;

// 剑指 Offer 26. 树的子结构
// 
//输入两棵二叉树A和B，判断B是不是A的子结构。(约定空树不是任意一个树的子结构) 
//
// B是A的子结构， 即 A中有出现和B相同的结构和节点值。 
//
// 例如: 
//给定的树 A: 
//
// 3 
// / \ 
// 4 5 
// / \ 
// 1 2 
//给定的树 B： 
//
// 4 
// / 
// 1 
//返回 true，因为 B 与 A 的一个子树拥有相同的结构和节点值。 
//
// 示例 1： 
//
// 输入：A = [1,2,3], B = [3,1]
//输出：false
// 
//
// 示例 2： 
//
// 输入：A = [3,4,5,1,2], B = [4,1]
//输出：true 
//
// 限制： 
//
// 0 <= 节点个数 <= 10000 
// Related Topics 树 
// 👍 256 👎 0
public class Offer_26_isSubStructure {
    boolean status = false;

    public boolean isSubStructure_v1(TreeNode A, TreeNode B) {
        if (A == null || B == null) {
            return false;
        }
        return traverse(A, B);
    }

    private boolean traverse(TreeNode a, TreeNode b) {
        if (a == null) {
            return false;
        }
        if (a.val == b.val && traverseCheck(a, b)) {
            status = true;
        }
        traverse(a.left, b);
        if (status) {
            return status;
        }
        traverse(a.right, b);
        return status;
    }

    private boolean traverseCheck(TreeNode a, TreeNode b) {
        if (b == null) {
            return true;
        }
        if (a == null || a.val != b.val) {
            return false;
        }
        return traverseCheck(a.left, b.left) && traverseCheck(a.right, b.right);
    }

    public boolean isSubStructure_v2(TreeNode A, TreeNode B) {
        return (A != null && B != null) && (recur(A, B) || isSubStructure_v2(A.left, B) || isSubStructure_v2(A.right, B));
    }

    boolean recur(TreeNode A, TreeNode B) {
        if (B == null) {
            return true;
        }
        if (A == null || A.val != B.val) {
            return false;
        }
        return recur(A.left, B.left) && recur(A.right, B.right);
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
