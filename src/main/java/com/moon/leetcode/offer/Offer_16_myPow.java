package com.moon.leetcode.offer;

// 剑指 Offer 16. 数值的整数次方
//
//实现 pow(x, n) ，即计算 x 的 n 次幂函数（即，xn）。不得使用库函数，同时不需要考虑大数问题。
//
// 示例 1：
//
//输入：x = 2.00000, n = 10
//输出：1024.00000
//
// 示例 2：
//
//输入：x = 2.10000, n = 3
//输出：9.26100
//
// 示例 3：
//
//输入：x = 2.00000, n = -2
//输出：0.25000
//解释：2-2 = 1/(2 * 2) = 1/4 = 0.25
//
// 提示：
//
// -100.0 < x < 100.0
// -231 <= n <= 231-1
// -104 <= xn <= 104
//
// 注意：本题与主站 50 题相同：https://leetcode-cn.com/problems/powx-n/
// Related Topics 递归
// 👍 149 👎 0
public class Offer_16_myPow {
    public static void main(String[] args) {
        System.out.println(new Offer_16_myPow().myPow_v2(2.00000, -2147483648));
    }

    public double myPow_v1(double x, int n) {
        long nn = Math.abs((long) n);
        double ans = 1;
        while (nn > 0) {
            if ((nn & 1) == 1) {
                ans *= x;
            }
            x *= x;
            nn >>= 1;
        }
        return n >= 0 ? ans : (double) 1 / ans;
    }

    public double myPow_v2(double x, int n) {
        double ans = 1;
        for (long i = Math.abs((long) n); i > 0; i /= 2) {
            if ((i & 1) == 1) {
                ans *= x;
            }
            x *= x;
        }
        return n >= 0 ? ans : (double) 1 / ans;
    }

    public double myPow_v3(double x, int n) {
        double ans = pow(x, Math.abs(n));
        return n >= 0 ? ans : (double) 1 / ans;
    }

    private double pow(double x, int n) {
        if (n == 0) {
            return 1;
        } else if ((n & 1) == 1) {
            double pow = pow(x, (n - 1) / 2);
            return pow * pow * x;
        } else {
            double pow = pow(x, n / 2);
            return pow * pow;
        }
    }
}
