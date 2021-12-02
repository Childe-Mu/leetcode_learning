package com.moon.leetcode;

import java.util.ArrayList;
import java.util.List;
// 109. 有序链表转换二叉搜索树
//给定一个单链表，其中的元素按升序排序，将其转换为高度平衡的二叉搜索树。
//
// 本题中，一个高度平衡二叉树是指一个二叉树每个节点 的左右两个子树的高度差的绝对值不超过 1。
//
// 示例:
//
// 给定的有序链表： [-10, -3, 0, 5, 9],
//
//一个可能的答案是：[0, -3, 9, -10, null, 5], 它可以表示下面这个高度平衡二叉搜索树：
//
//      0
//     / \
//   -3   9
//   /   /
// -10  5
//
// Related Topics 树 二叉搜索树 链表 分治 二叉树
// 👍 627 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
public class No109_sortedListToBST {
    public TreeNode sortedListToBST_v1(ListNode head) {
        List<Integer> list = new ArrayList<>();
        while (head != null) {
            list.add(head.val);
            head = head.next;
        }
        int l = 0;
        int r = list.size() - 1;
        return dfs(list, l, r);
    }

    private TreeNode dfs(List<Integer> list, int l, int r) {
        if (l > r) {
            return null;
        }
        if (l == r) {
            return new TreeNode(list.get(l));
        }
        int m = (l + r + 1) / 2;
        TreeNode node = new TreeNode(list.get(m));
        node.left = dfs(list, l, m - 1);
        node.right = dfs(list, m + 1, r);
        return node;
    }

    private ListNode head;
    public TreeNode sortedListToBST_v2(ListNode head) {
        this.head = head;
        ListNode t = head;
        int n = 0;
        while (t != null) {
            n++;
            t = t.next;
        }
        return buildTree( 0, n - 1);
    }

    private TreeNode buildTree(int l, int r) {
        if (l > r) {
            return null;
        }
        int m = (l + r + 1) / 2;
        TreeNode root = new TreeNode();
        root.left = buildTree(l, m - 1);
        root.val = head.val;
        head = head.next;
        root.right = buildTree( m + 1, r);
        return root;
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

    private static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }
}
