package com.moon.leetcode;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

// 剑指 Offer 68 - II. 二叉树的最近公共祖先
//
//给定一个二叉树, 找到该树中两个指定节点的最近公共祖先。
//
// 百度百科中最近公共祖先的定义为：“对于有根树 T 的两个结点 p、q，最近公共祖先表示为一个结点 x，满足 x 是 p、q 的祖先且 x 的深度尽可能大（
//一个节点也可以是它自己的祖先）。”
//
// 例如，给定如下二叉树: root = [3,5,1,6,2,0,8,null,null,7,4]
//
// 示例 1:
//
// 输入: root = [3,5,1,6,2,0,8,null,null,7,4], p = 5, q = 1
//输出: 3
//解释: 节点 5 和节点 1 的最近公共祖先是节点 3。
//
// 示例 2:
//
// 输入: root = [3,5,1,6,2,0,8,null,null,7,4], p = 5, q = 4
//输出: 5
//解释: 节点 5 和节点 4 的最近公共祖先是节点 5。因为根据定义最近公共祖先节点可以为节点本身。
//
// 说明:
//
// 所有节点的值都是唯一的。
// p、q 为不同节点且均存在于给定的二叉树中。
//
// 注意：本题与主站 236 题相同：https://leetcode-cn.com/problems/lowest-common-ancestor-of-a
//-binary-tree/
// Related Topics 树
// 👍 239 👎 0
public class Offer_68_2_lowestCommonAncestor {

    Map<Integer, TreeNode> parent = new HashMap<>();
    Set<Integer> visited = new HashSet<>();
    private TreeNode ans = null;
    private boolean status = false;

    public TreeNode lowestCommonAncestor_v1(TreeNode root, TreeNode p, TreeNode q) {
        this.dfs(root, p, q);
        return this.ans;
    }

    private boolean dfs(TreeNode node, TreeNode p, TreeNode q) {
        if (node == null) {
            return false;
        }
        boolean lson = dfs(node.left, p, q);
        boolean rson = dfs(node.right, p, q);
        if ((lson && rson) || ((node.val == p.val || node.val == q.val) && (lson || rson))) {
            ans = node;
        }
        return lson || rson || (node.val == p.val || node.val == q.val);
    }

    public TreeNode lowestCommonAncestor_v2(TreeNode root, TreeNode p, TreeNode q) {
        dfs(root);
        while (p != null) {
            visited.add(p.val);
            p = parent.get(p.val);
        }
        while (q != null) {
            if (visited.contains(q.val)) {
                return q;
            }
            q = parent.get(q.val);
        }
        return null;
    }

    public void dfs(TreeNode root) {
        if (root.left != null) {
            parent.put(root.left.val, root);
            dfs(root.left);
        }
        if (root.right != null) {
            parent.put(root.right.val, root);
            dfs(root.right);
        }
    }

    public TreeNode lowestCommonAncestor_v3(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null || p == root || q == root) {
            return root;
        }
        TreeNode l = lowestCommonAncestor_v3(root.left, p, q);
        TreeNode r = lowestCommonAncestor_v3(root.right, p, q);
        //l、r 非空时，说明 p、q 分居 root 的两侧，root 就是 LCA
        //l、r 任一为空，说明 LCA 位于另一子树或其祖先中
        return l == null ? r : (r == null ? l : root);
    }

    public TreeNode lowestCommonAncestor_v4(TreeNode root, TreeNode p, TreeNode q) {
        Deque<TreeNode> pp = new ArrayDeque<>();
        Deque<TreeNode> qq = new ArrayDeque<>();
        searchNodePath(root, p, pp);
        status = false;
        searchNodePath(root, q, qq);
        TreeNode pre = null;
        while (!pp.isEmpty() && !qq.isEmpty()) {
            if (pp.peekFirst() == qq.peekFirst()) {
                pre = pp.pollFirst();
                qq.pollFirst();
            } else {
                break;
            }
        }
        return pre;
    }

    private void searchNodePath(TreeNode node, TreeNode search, Deque<TreeNode> path) {
        if (node == null) {
            return;
        }
        if (node.val == search.val) {
            path.offerLast(node);
            status = true;
            return;
        }
        path.offerLast(node);
        searchNodePath(node.left, search, path);
        if (status) {
            return;
        }
        searchNodePath(node.right, search, path);
        if (!status) {
            path.removeLast();
        }
    }

    private static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }

        @Override
        public String toString() {
            return "val=" + val;
        }
    }
}
