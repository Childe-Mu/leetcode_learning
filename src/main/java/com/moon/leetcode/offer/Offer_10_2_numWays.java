package com.moon.leetcode.offer;

import java.util.Arrays;

// 剑指 Offer 10- II. 青蛙跳台阶问题
//
//一只青蛙一次可以跳上1级台阶，也可以跳上2级台阶。求该青蛙跳上一个 n 级的台阶总共有多少种跳法。
//
// 答案需要取模 1e9+7（1000000007），如计算初始结果为：1000000008，请返回 1。
//
// 示例 1：
//
// 输入：n = 2
//输出：2
//
// 示例 2：
//
// 输入：n = 7
//输出：21
//
// 示例 3：
//
// 输入：n = 0
//输出：1
//
// 提示：
//
// 0 <= n <= 100
//
// 注意：本题与主站 70 题相同：https://leetcode-cn.com/problems/climbing-stairs/
//
// Related Topics 递归
// 👍 147 👎 0
public class Offer_10_2_numWays {
    public static void main(String[] args) {
        System.out.println(new Offer_10_2_numWays().numWays_v2(100));
    }

    public int numWays_v1(int n) {
        int[] mem = new int[n];
        Arrays.fill(mem, -1);
        return traverseByMem(n, 0, mem);
    }

    private int traverseByMem(int n, int i, int[] mem) {
        if (i >= n) {
            return i > n ? 0 : 1;
        }
        if (mem[i] != -1) {
            return mem[i];
        }
        return mem[i] = (traverseByMem(n, i + 1, mem) + traverseByMem(n, i + 2, mem)) % 1000000007;
    }

    public int numWays_v2(int n) {
        if (n < 2) {
            return 1;
        }
        int[] f = new int[n + 1];
        f[0] = 1;
        f[1] = 1;
        for (int i = 2; i <= n; i++) {
            f[i] = (f[i - 2] + f[i - 1]) % 1000000007;
        }
        return f[n];
    }
}
