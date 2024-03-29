package com.moon.leetcode;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.Iterator;
import java.util.List;

/**
 * 257. 二叉树的所有路径
 * <p>
 * 给定一个二叉树，返回所有从根节点到叶子节点的路径。
 * <p>
 * 说明: 叶子节点是指没有子节点的节点。
 * <p>
 * 示例:
 * <p>
 * 输入:
 * <p>
 * 1
 * /   \
 * 2     3
 * \
 * 5
 * <p>
 * 输出: ["1->2->5", "1->3"]
 * <p>
 * 解释: 所有根节点到叶子节点的路径为: 1->2->5, 1->3
 */
public class No257_binaryTreePaths {

    public static List<String> binaryTreePaths_v1(TreeNode root) {
        List<String> paths = new ArrayList<>();
        constructPaths(root, "", paths);
        return paths;
    }

    public static void constructPaths(TreeNode root, String path, List<String> paths) {
        if (root != null) {
            StringBuilder pathSB = new StringBuilder(path);
            pathSB.append(root.val);
            if (root.left == null && root.right == null) {  // 当前节点是叶子节点
                paths.add(pathSB.toString());  // 把路径加入到答案中
            } else {
                pathSB.append("->");  // 当前节点不是叶子节点，继续递归遍历
                constructPaths(root.left, pathSB.toString(), paths);
                constructPaths(root.right, pathSB.toString(), paths);
            }
        }
    }

    /**
     *
     */
    public static List<String> binaryTreePaths_v2(TreeNode root) {
        List<String> res = new ArrayList<>();
        Deque<Integer> path = new ArrayDeque<>();
        backtrack(root, path, res);
        return res;
    }

    private static void backtrack(TreeNode node, Deque<Integer> path, List<String> res) {
        if (node == null) {
            return;
        }
        path.addLast(node.val);
        if (node.left == null && node.right == null) {
            res.add(buildPath(path));
            path.removeLast();
            return;
        }
        backtrack(node.left, path, res);
        backtrack(node.right, path, res);
        path.removeLast();
    }

    private static String buildPath(Deque<Integer> path) {
        Iterator<Integer> iterator = path.iterator();
        StringBuilder sb = new StringBuilder();
        sb.append(iterator.next());
        while (iterator.hasNext()) {
            sb.append("->").append(iterator.next());
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        Deque<Integer> path = new ArrayDeque<>();
        path.push(1);
        path.push(2);
        path.push(3);
        Iterator<Integer> iterator = path.iterator();
        System.out.println(iterator.next());
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
