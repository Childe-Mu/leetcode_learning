package com.moon.leetcode;

// 剑指 Offer 37. 序列化二叉树
//
//请实现两个函数，分别用来序列化和反序列化二叉树。
//
// 你需要设计一个算法来实现二叉树的序列化与反序列化。这里不限定你的序列 / 反序列化算法执行逻辑，你只需要保证一个二叉树可以被序列化为一个字符串并且将这个字
//符串反序列化为原始的树结构。
//
// 提示：输入输出格式与 LeetCode 目前使用的方式一致，详情请参阅 LeetCode 序列化二叉树的格式。你并非必须采取这种方式，你也可以采用其他的方
//法解决这个问题。
//
// 示例：
//
//输入：root = [1,2,3,null,null,4,5]
//输出：[1,2,3,null,null,4,5]
//
// 注意：本题与主站 297 题相同：https://leetcode-cn.com/problems/serialize-and-deserialize-b
//inary-tree/
// Related Topics 树 深度优先搜索 广度优先搜索 设计 字符串 二叉树
// 👍 198 👎 0

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Offer_37_Codec {
    private static class Codec_V1 {

        public static void main(String[] args) {
            TreeNode root = new TreeNode(1);
            root.left = new TreeNode(2);
            root.right = new TreeNode(3);
            root.right.left = new TreeNode(4);
            root.right.right = new TreeNode(5);
            Codec_V1 codec = new Codec_V1();
            System.out.println(codec.deserialize(codec.serialize(root)));
        }

        // Encodes a tree to a single string.
        public String serialize(TreeNode root) {
            List<Integer> res = new ArrayList<>();
            Queue<TreeNode> queue = new LinkedList<>();
            queue.add(root);
            while (!queue.isEmpty()) {
                TreeNode node = queue.poll();
                if (node != null) {
                    queue.offer(node.left);
                    queue.offer(node.right);
                    res.add(node.val);
                } else {
                    res.add(null);
                }
            }
            return res.toString();
        }

        // Decodes your encoded data to tree.
        public TreeNode deserialize(String data) {
            data = data.substring(1, data.length() - 1);
            data = data.replaceAll(" ", "");
            String[] a = data.split(",");
            int n = a.length;
            if (n == 0 || a[0].equals("null")) {
                return null;
            }
            int i = 1;
            Queue<TreeNode> queue = new LinkedList<>();
            TreeNode root = new TreeNode(Integer.parseInt(a[0]));
            queue.offer(root);
            while (i < n && !queue.isEmpty()) {
                TreeNode node = queue.poll();
                String s = a[i++];
                if (!s.equals("null")) {
                    node.left = new TreeNode(Integer.parseInt(s));
                    queue.offer(node.left);
                }
                if (i < n && !(s = a[i++]).equals("null")) {
                    node.right = new TreeNode(Integer.parseInt(s));
                    queue.offer(node.right);
                }
            }
            return root;
        }

        private static class Codec_V2 {
            public String serialize(TreeNode root) {
                return rserialize(root, "");
            }

            public TreeNode deserialize(String data) {
                String[] dataArray = data.split(",");
                List<String> dataList = new LinkedList<>(Arrays.asList(dataArray));
                return rdeserialize(dataList);
            }

            public String rserialize(TreeNode root, String str) {
                if (root == null) {
                    str += "None,";
                } else {
                    str += root.val + ",";
                    str = rserialize(root.left, str);
                    str = rserialize(root.right, str);
                }
                return str;
            }

            public TreeNode rdeserialize(List<String> dataList) {
                if (dataList.get(0).equals("None")) {
                    dataList.remove(0);
                    return null;
                }
                TreeNode root = new TreeNode(Integer.parseInt(dataList.get(0)));
                dataList.remove(0);
                root.left = rdeserialize(dataList);
                root.right = rdeserialize(dataList);

                return root;
            }
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
}
