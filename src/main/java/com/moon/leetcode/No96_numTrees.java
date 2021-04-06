package com.moon.leetcode;

// 96. 不同的二叉搜索树
//
//给定一个整数 n，求以 1 ... n 为节点组成的二叉搜索树有多少种？
//
// 示例:
//
// 输入: 3
//输出: 5
//解释:
//给定 n = 3, 一共有 5 种不同结构的二叉搜索树:
//
//   1         3     3      2      1
//    \       /     /      / \      \
//     3     2     1      1   3      2
//    /     /       \                 \
//   2     1         2                 3
// Related Topics 树 动态规划
// 👍 1090 👎 0

public class No96_numTrees {
    public static void main(String[] args) {
        System.out.println(new No96_numTrees().numTrees_v1(3));
    }

    /**
     * 动态规划
     * <p>
     * 以某个节点为根节点，然后小于该节点的组成左子树，大于的作为右子树，此时所有可能的数量为 左子树的数量 x 右子树的数量
     * 枚举所有节点，然后求和
     */
    public int numTrees_v1(int n) {
        int[] g = new int[n + 1];
        g[0] = 1;
        g[1] = 1;
        int[][] f = new int[n + 1][n + 1];
        for (int i = 2; i <= n; i++) {
            for (int j = 1; j <= i; j++) {
                f[i][j] = g[j - 1] * g[i - j];
                g[i] += f[i][j];
            }
        }
        return g[n];
    }

    private static int traverse(int n, int[] mem) {
        if (mem[n] != 0) {
            return mem[n];
        }
        if (n == 0 || n == 1) {
            mem[n] = 1;
            return mem[n];
        }
        for (int i = 1; i <= n; i++) {
            mem[n] += traverse(i - 1, mem) * traverse(n - i, mem);
        }
        return mem[n];
    }

    /**
     * 暴力 递归
     */
    public int numTrees_v2(int n) {
        if (n == 0 || n == 1) return 1;
        int res = 0;
        for (int i = 1; i <= n; i++) {
            res += numTrees_v2(i - 1) * numTrees_v2(n - i);
        }
        return res;
    }

    /**
     * 记忆化搜索
     */
    public int numTrees_v3(int n) {
        int[] mem = new int[n + 1];
        return traverse(n, mem);
    }
}
