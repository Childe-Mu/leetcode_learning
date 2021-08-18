package com.moon.leetcode;

// 526. 优美的排列
//
//假设有从 1 到 N 的 N 个整数，如果从这 N 个数字中成功构造出一个数组，使得数组的第 i 位 (1 <= i <= N) 满足如下两个条件中的一个，
//我们就称这个数组为一个优美的排列。条件：
//
//
// 第 i 位的数字能被 i 整除
// i 能被第 i 位上的数字整除
//
//
// 现在给定一个整数 N，请问可以构造多少个优美的排列？
//
// 示例1:
//
//
//输入: 2
//输出: 2
//解释:
//
//第 1 个优美的排列是 [1, 2]:
//  第 1 个位置（i=1）上的数字是1，1能被 i（i=1）整除
//  第 2 个位置（i=2）上的数字是2，2能被 i（i=2）整除
//
//第 2 个优美的排列是 [2, 1]:
//  第 1 个位置（i=1）上的数字是2，2能被 i（i=1）整除
//  第 2 个位置（i=2）上的数字是1，i（i=2）能被 1 整除
//
//
// 说明:
//
//
// N 是一个正整数，并且不会超过15。
//
// Related Topics 位运算 数组 动态规划 回溯 状态压缩
// 👍 167 👎 0
public class No526_countArrangement {
    private int ans;
    private boolean[] used;
    private int use;
    private int n;

    public static void main(String[] args) {
        System.out.println(new No526_countArrangement().countArrangement_v3(3));
    }

    public int countArrangement_v1(int n) {
        this.n = n;
        this.ans = 0;
        this.used = new boolean[n];
        backtrack(1);
        return ans;
    }

    private void backtrack(int i) {
        if (i > n) {
            ans++;
            return;
        }
        for (int j = 1; j <= n; j++) {
            if (!used[j - 1] && (j % i == 0 || i % j == 0)) {
                used[j - 1] = true;
                backtrack(i + 1);
                used[j - 1] = false;
            }
        }
    }

    public int countArrangement_v2(int n) {
        this.n = n;
        this.ans = 0;
        this.use = 0;
        backtrack_v2(1);
        return ans;
    }

    private void backtrack_v2(int i) {
        if (i > n) {
            ans++;
            return;
        }
        for (int j = 1; j <= n; j++) {
            if (((use >> j) & 1) == 0 && (j % i == 0 || i % j == 0)) {
                use |= 1 << j;
                backtrack(i + 1);
                use ^= 1 << j;
            }
        }
    }

    public int countArrangement_v3(int n) {
        int mask = 1 << n;
        int[][] f = new int[n + 1][mask];
        f[0][0] = 1;
        for (int i = 1; i <= n; i++) {
            // 枚举所有的状态
            for (int state = 0; state < mask; state++) {
                // 枚举位置 i（最后一位）选的数值是 k
                for (int k = 1; k <= n; k++) {
                    // 首先 k 在 state 中必须是 1
                    if (((state >> (k - 1)) & 1) == 0) {
                        continue;
                    }
                    // 数值 k 和位置 i 之间满足任一整除关系
                    if (k % i != 0 && i % k != 0) {
                        continue;
                    }
                    // state & (~(1 << (k - 1))) 代表将 state 中数值 k 的位置置零
                    f[i][state] += f[i - 1][state & (~(1 << (k - 1)))];
                }
            }
        }
        return f[n][mask - 1];
    }

    public int countArrangement_v4(int n) {
        int max = 1 << n;
        int[] f = new int[max];
        f[0] = 1;
        for (int i = 1; i < max; i++) {
            int num = Integer.bitCount(i);
            for (int j = 0; j < n; j++) {
                if ((i & (1 << j)) == 1 && (num % (j + 1) == 0 || (j + 1) % num == 0)) {
                    f[i] += f[i ^ (1 << j)];
                }
            }
        }
        return f[(1 << n) - 1];
    }
}
