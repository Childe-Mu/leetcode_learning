package com.moon.leetcode;

/**
 * 783. 二叉搜索树节点最小距离
 * 给定一个二叉搜索树的根节点 root，返回树中任意两节点的差的最小值。
 * <p>
 * <p>
 * <p>
 * 示例：
 * <p>
 * 输入: root = [4,2,6,1,3,null,null]
 * 输出: 1
 * 解释:
 * 注意，root是树节点对象(TreeNode object)，而不是数组。
 * <p>
 * 给定的树 [4,2,6,1,3,null,null] 可表示为下图:
 * <p>
 * 4
 * /   \
 * 2      6
 * / \
 * 1   3
 * <p>
 * 最小的差值是 1, 它是节点1和节点2的差值, 也是节点3和节点2的差值。
 * <p>
 * <p>
 * 注意：
 * <p>
 * 二叉树的大小范围在 2 到 100。
 * 二叉树总是有效的，每个节点的值都是整数，且不重复。
 * 本题与 530：https://leetcode-cn.com/problems/minimum-absolute-difference-in-bst/ 相同
 */
public class No783_minDiffInBST {
    /**
     * 前序遍历或者后序遍历+排序
     */
    // private List<Integer> valList = new ArrayList<>();
    //
    // public int minDiffInBST(TreeNode root) {
    //     int min = Integer.MAX_VALUE;
    //     traverseTree(root);
    //     valList.sort(Integer::compareTo);
    //     for (int i = 0; i < valList.size() - 1; i++) {
    //         min = Math.min(Math.abs(valList.get(i) - valList.get(i+1)), min);
    //     }
    //     return min;
    // }
    //
    // private void traverseTree(TreeNode node) {
    //     if (node == null) {
    //         return;
    //     }
    //     valList.add(node.val);
    //     traverseTree(node.left);
    //     traverseTree(node.right);
    // }
    /**
     * 因为是二叉搜索树，所以本身就是有序的，这样中序遍历就不用排序，只需要把前一个节点记录下来，和当前节点求差即可
     */
    Integer prev, ans;

    public int minDiffInBST_v1(TreeNode root) {
        prev = null;
        ans = Integer.MAX_VALUE;
        dfs(root);
        return ans;
    }

    public void dfs(TreeNode node) {
        if (node == null) {
            return;
        }
        dfs(node.left);
        if (prev != null) {
            ans = Math.min(ans, node.val - prev);
        }
        prev = node.val;
        dfs(node.right);
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