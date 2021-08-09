package com.moon.leetcode;

import java.util.Arrays;
import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Set;

// 313. 超级丑数
//
//超级丑数 是一个正整数，并满足其所有质因数都出现在质数数组 primes 中。
//
// 给你一个整数 n 和一个整数数组 primes ，返回第 n 个 超级丑数 。
//
// 题目数据保证第 n 个 超级丑数 在 32-bit 带符号整数范围内。
//
//
//
// 示例 1：
//
//
//输入：n = 12, primes = [2,7,13,19]
//输出：32
//解释：给定长度为 4 的质数数组 primes = [2,7,13,19]，前 12 个超级丑数序列为：[1,2,4,7,8,13,14,16,19,26,
//28,32] 。
//
// 示例 2：
//
//
//输入：n = 1, primes = [2,3,5]
//输出：1
//解释：1 不含质因数，因此它的所有质因数都在质数数组 primes = [2,3,5] 中。
//
//
//
//
//
//
// 提示：
//
//
// 1 <= n <= 106
// 1 <= primes.length <= 100
// 2 <= primes[i] <= 1000
// 题目数据 保证 primes[i] 是一个质数
// primes 中的所有值都 互不相同 ，且按 递增顺序 排列
//
//
//
//
// Related Topics 数组 哈希表 数学 动态规划 堆（优先队列）
// 👍 200 👎 0
public class No313_nthSuperUglyNumber {
    public static void main(String[] args) {
        System.out.println(new No313_nthSuperUglyNumber().nthSuperUglyNumber_v2(12, new int[]{2, 7, 13, 19}));
    }

    public int nthSuperUglyNumber_v1(int n, int[] primes) {
        PriorityQueue<Long> queue = new PriorityQueue<>();
        Set<Long> seen = new HashSet<>();
        queue.offer(1L);
        seen.add(1L);
        int i = 1;
        int ans = 1;
        while (i <= n && !queue.isEmpty()) {
            long poll = queue.poll();
            for (int prime : primes) {
                if (seen.add(prime * poll)) {
                    queue.offer(prime * poll);
                }
            }
            i++;
            ans = (int) poll;
        }
        return ans;
    }

    public int nthSuperUglyNumber_v2(int n, int[] primes) {
        int m = primes.length;
        int[] p = new int[m];
        int[] ans = new int[n];
        ans[0] = 1;
        Arrays.fill(p, 0);
        for (int i = 1; i < n; i++) {
            int t = Integer.MAX_VALUE;
            int[] tmp = new int[m];
            for (int j = 0; j < m; j++) {
                tmp[j] = primes[j] * ans[p[j]];
                t = Math.min(t, tmp[j]);
            }
            ans[i] = t;
            for (int j = 0; j < m; j++) {
                if (tmp[j] == t) {
                    p[j]++;
                }
            }
        }
        return ans[n - 1];
    }
}
