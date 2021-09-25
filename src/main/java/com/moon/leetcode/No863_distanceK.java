package com.moon.leetcode;

// 863. 二叉树中所有距离为 K 的结点
//
//给定一个二叉树（具有根结点 root）， 一个目标结点 target ，和一个整数值 K 。
//
// 返回到目标结点 target 距离为 K 的所有结点的值的列表。 答案可以以任何顺序返回。
//
//
//
//
//
//
// 示例 1：
//
// 输入：root = [3,5,1,6,2,0,8,null,null,7,4], target = 5, K = 2
//输出：[7,4,1]
//解释：
//所求结点为与目标结点（值为 5）距离为 2 的结点，
//值分别为 7，4，以及 1
//
//
//
//注意，输入的 "root" 和 "target" 实际上是树上的结点。
//上面的输入仅仅是对这些对象进行了序列化描述。
//
//
//
//
// 提示：
//
//
// 给定的树是非空的。
// 树上的每个结点都具有唯一的值 0 <= node.val <= 500 。
// 目标结点 target 是树上的结点。
// 0 <= K <= 1000.
//
// Related Topics 树 深度优先搜索 广度优先搜索 二叉树
// 👍 380 👎 0


import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

//leetcode submit region begin(Prohibit modification and deletion)
public class No863_distanceK {
    Map<Integer, TreeNode> map = new HashMap<>();
    List<Integer> res = new ArrayList<>();

    public static void main(String[] args) {
        TreeNode root = new TreeNode(0);
        TreeNode n1 = new TreeNode(1);
        TreeNode n2 = new TreeNode(2);
        TreeNode n3 = new TreeNode(3);
        root.right = n1;
        n1.right = n2;
        n2.right = n3;
        System.out.println(new No863_distanceK().distanceK(root, n1, 2));
    }

    public List<Integer> distanceK(TreeNode root, TreeNode target, int k) {
        dfs(root, root.left);
        dfs(root, root.right);
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(target);
        int l = -1;
        cal(k, queue, l);
        TreeNode pre = target;
        for (int i = 1; i <= k; i++) {
            TreeNode node = map.get(pre.val);
            if (node == null) {
                break;
            }
            if (i == k) {
                this.res.add(node.val);
            } else {
                if (node.left != null && node.right != null) {
                    queue.clear();
                    if (node.left != pre) {
                        queue.offer(node.left);
                    } else {
                        queue.offer(node.right);
                    }
                    cal(k, queue, i);
                }
                pre = node;
            }
        }
        return this.res;
    }

    private void cal(int k, Queue<TreeNode> queue, int l) {
        while (!queue.isEmpty()) {
            l++;
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                TreeNode poll = queue.poll();
                if (l == k) {
                    res.add(poll.val);
                } else if (l < k) {
                    if (poll.left != null) {
                        queue.offer(poll.left);
                    }
                    if (poll.right != null) {
                        queue.offer(poll.right);
                    }
                }
            }
            if (l == k) {
                break;
            }
        }
    }

    private void dfs(TreeNode from, TreeNode node) {
        if (node == null) {
            return;
        }
        map.put(node.val, from);
        dfs(node, node.left);
        dfs(node, node.right);
    }

    private static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    class Solution {
        Map<Integer, TreeNode> parents = new HashMap<>();
        List<Integer> ans = new ArrayList<>();

        public List<Integer> distanceK(TreeNode root, TreeNode target, int k) {
            // 从 root 出发 DFS，记录每个结点的父结点
            findParents(root);

            // 从 target 出发 DFS，寻找所有深度为 k 的结点
            findAns(target, null, 0, k);

            return ans;
        }

        public void findParents(TreeNode node) {
            if (node.left != null) {
                parents.put(node.left.val, node);
                findParents(node.left);
            }
            if (node.right != null) {
                parents.put(node.right.val, node);
                findParents(node.right);
            }
        }

        public void findAns(TreeNode node, TreeNode from, int depth, int k) {
            if (node == null) {
                return;
            }
            if (depth == k) {
                ans.add(node.val);
                return;
            }
            if (node.left != from) {
                findAns(node.left, node, depth + 1, k);
            }
            if (node.right != from) {
                findAns(node.right, node, depth + 1, k);
            }
            if (parents.get(node.val) != from) {
                findAns(parents.get(node.val), node, depth + 1, k);
            }
        }
    }
}
