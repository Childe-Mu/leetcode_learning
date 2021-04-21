package com.moon.leetcode;

// 剑指 Offer 14- II. 剪绳子 II
//
//给你一根长度为 n 的绳子，请把绳子剪成整数长度的 m 段（m、n都是整数，n>1并且m>1），每段绳子的长度记为 k[0],k[1]...k[m - 1]
// 。请问 k[0]*k[1]*...*k[m - 1] 可能的最大乘积是多少？例如，当绳子的长度是8时，我们把它剪成长度分别为2、3、3的三段，此时得到的最大乘
//积是18。
//
// 答案需要取模 1e9+7（1000000007），如计算初始结果为：1000000008，请返回 1。
//
// 示例 1：
//
// 输入: 2
//输出: 1
//解释: 2 = 1 + 1, 1 × 1 = 1
//
// 示例 2:
//
// 输入: 10
//输出: 36
//解释: 10 = 3 + 3 + 4, 3 ×3 ×4 = 36
//
// 提示：
//
// 2 <= n <= 1000
//
// 注意：本题与主站 343 题相同：https://leetcode-cn.com/problems/integer-break/
// Related Topics 数学 动态规划
// 👍 102 👎 0
public class Offer_14_2_cuttingRope {
    public static void main(String[] args) {
        System.out.println(new Offer_14_2_cuttingRope().cuttingRope_v2(10));
    }

    public int cuttingRope_v1(int n) {
        if (n <= 3) {
            return n - 1;
        }
        int mod = n % 3;
        int base = n / 3;
        long res = 1;
        long pow = (long) Math.pow(3, 18);
        for (; base > 18; base -= 18) {
            res = (res * pow) % 1000000007;
        }
        if (mod == 0) {
            return (int) ((res * (long) Math.pow(3, base)) % 1000000007);
        } else if (mod == 1) {
            return (int) (res * ((long) Math.pow(3, base - 1) * 4) % 1000000007);
        } else {
            return (int) (res * ((long) Math.pow(3, base) * 2) % 1000000007);
        }
    }

    public int cuttingRope_v3(int n) {
        long[] dp = new long[n + 1];
        for (int i = 2; i <= n; i++) {
            long curMax = 0;
            for (int j = 1; j < i; j++) {
                curMax = Math.max(curMax, Math.max((long) j * (i - j), j * dp[i - j]));
            }
            dp[i] = curMax;
        }
        return (int) (dp[n] % 1000000007);
    }

    public int cuttingRope_v2(int n) {
        if (n <= 3) {
            return n - 1;
        }
        int b = n % 3, p = 1000000007;
        long rem = 1, x = 3;
//        for (int a = n / 3 - 1; a > 0; a /= 2) {
//            if (a % 2 == 1) {
//                rem = (rem * x) % p;
//            }
//            x = (x * x) % p;
//        }
        int c = n / 3 - 1;
        while (c > 0) {
            if ((c & 1) == 1) {
                rem = rem * x % p;
            }
            x = x * x % p;
            c >>= 1;
        }
        if (b == 0) {
            return (int) (rem * 3 % p);
        }
        if (b == 1) {
            return (int) (rem * 4 % p);
        }
        return (int) (rem * 6 % p);
    }
}
