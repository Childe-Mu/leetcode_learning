package com.moon.leetcode;

/**
 * 100. 相同的树
 * 给定两个二叉树，编写一个函数来检验它们是否相同。
 * 如果两个树在结构上相同，并且节点具有相同的值，则认为它们是相同的。
 * 示例 1:
 * <p>
 * 输入:       1         1
 * / \       / \
 * 2   3     2   3
 * <p>
 * [1,2,3],   [1,2,3]
 * <p>
 * 输出: true
 * 示例 2:
 * <p>
 * 输入:      1          1
 * /           \
 * 2             2
 * <p>
 * [1,2],     [1,null,2]
 * <p>
 * 输出: false
 * 示例 3:
 * <p>
 * 输入:       1         1
 * / \       / \
 * 2   1     1   2
 * <p>
 * [1,2,1],   [1,1,2]
 * <p>
 * 输出: false
 */
public class No100_isSameTree {

    // /**
    //  * 深度优先遍历
    //  */
    // boolean re = true;
    //
    // public boolean isSameTree(TreeNode p, TreeNode q) {
    //     traverseAndCompare(p, q);
    //     return re;
    // }
    //
    // private void traverseAndCompare(TreeNode p, TreeNode q) {
    //     if (p == null && q == null) {
    //         return;
    //     }
    //     if (p == null || q == null) {
    //         re = false;
    //         return ;
    //     }
    //     if (p.val != q.val) {
    //         re = false;
    //         return;
    //     }
    //     traverseAndCompare(p.left, q.left);
    //     traverseAndCompare(p.right, q.right);
    // }

    /**
     * 深度优先遍历
     */
    public boolean isSameTree(TreeNode p, TreeNode q) {
        if (p == null && q == null) {
            return true;
        } else if (p == null || q == null) {
            return false;
        } else if (p.val != q.val) {
            return false;
        } else {
            return isSameTree(p.left, q.left) && isSameTree(p.right, q.right);
        }
    }

    class TreeNode {
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

