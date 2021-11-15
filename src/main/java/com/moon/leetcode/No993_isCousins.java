package com.moon.leetcode;

import java.util.LinkedList;
import java.util.Queue;

// 993. 二叉树的堂兄弟节点
//
//在二叉树中，根节点位于深度 0 处，每个深度为 k 的节点的子节点位于深度 k+1 处。
//
// 如果二叉树的两个节点深度相同，但 父节点不同 ，则它们是一对堂兄弟节点。
//
// 我们给出了具有唯一值的二叉树的根节点 root ，以及树中两个不同节点的值 x 和 y 。
//
// 只有与值 x 和 y 对应的节点是堂兄弟节点时，才返回 true 。否则，返回 false。
//
//
//
// 示例 1：
//
//
//
//输入：root = [1,2,3,4], x = 4, y = 3
//输出：false
//
//
// 示例 2：
//
//
//
//输入：root = [1,2,3,null,4,null,5], x = 5, y = 4
//输出：true
//
//
// 示例 3：
//
//
//
//
//输入：root = [1,2,3,null,4], x = 2, y = 3
//输出：false
//
//
//
// 提示：
//
//
// 二叉树的节点数介于 2 到 100 之间。
// 每个节点的值都是唯一的、范围为 1 到 100 的整数。
//
//
//
// Related Topics 树 广度优先搜索
// 👍 185 👎 0
public class No993_isCousins {
    int depthX = -1;
    TreeNode parentX = new TreeNode();
    int depthY = -2;
    TreeNode parentY = null;

    public boolean isCousins(TreeNode root, int x, int y) {
        if (root == null) {
            return false;
        }
        if (root.val == x || root.val == y) {
            return false;
        }
        dfs(root, x, y, 1);
        return depthX == depthY && parentX != parentY;
    }

    private void dfs(TreeNode node, int x, int y, int depth) {
        if (node.left != null) {
            if (node.left.val == x) {
                depthX = depth;
                parentX = node;
                return;
            } else if (node.left.val == y) {
                depthY = depth;
                parentY = node;
                return;
            }
            dfs(node, x, y, depth + 1);
        }
        if (node.right != null) {
            if (node.right.val == x) {
                depthX = depth;
                parentX = node;
                return;
            } else if (node.right.val == y) {
                depthX = depth;
                parentY = node;
                return;
            }
            dfs(node, x, y, depth + 1);
        }
    }

    public boolean isCousins_v2(TreeNode root, int x, int y) {
        if (root == null) {
            return false;
        }
        if (root.val == x || root.val == y) {
            return false;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        boolean xx = false;
        boolean yy = false;
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                TreeNode node = queue.poll();
                if (node.left != null) {
                    if (node.left.val == x) {
                        xx = true;
                        continue;
                    } else if (node.left.val == y) {
                        yy = true;
                        continue;
                    }
                    queue.offer(node.left);
                }
                if (node.right != null) {
                    if (node.right.val == x) {
                        xx = true;
                        continue;
                    } else if (node.right.val == y) {
                        yy = true;
                        continue;
                    }
                    queue.offer(node.right);
                }
            }
            if (xx && yy) {
                return true;
            }
            if (xx || yy) {
                return false;
            }
        }
        return false;
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
