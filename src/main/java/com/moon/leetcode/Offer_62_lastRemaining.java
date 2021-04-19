package com.moon.leetcode;

// 剑指 Offer 62. 圆圈中最后剩下的数字
//
//0,1,···,n-1这n个数字排成一个圆圈，从数字0开始，每次从这个圆圈里删除第m个数字（删除后从下一个数字开始计数）。求出这个圆圈里剩下的最后一个数字。
//
// 例如，0、1、2、3、4这5个数字组成一个圆圈，从数字0开始每次删除第3个数字，则删除的前4个数字依次是2、0、4、1，因此最后剩下的数字是3。
//
// 示例 1：
//
//输入: n = 5, m = 3
//输出: 3
//
// 示例 2：
//
//输入: n = 10, m = 17
//输出: 2
//
// 限制：
//
// 1 <= n <= 10^5
// 1 <= m <= 10^6
//
// 👍 350 👎 0
public class Offer_62_lastRemaining {
    public static void main(String[] args) {
        System.out.println(new Offer_62_lastRemaining().lastRemaining_v2(5, 2));
    }

    public int lastRemaining_v1(int n, int m) {
        return f(n, m);
    }

    private int f(int n, int m) {
        if (n == 1) {
            return 0;
        }
        int x = f(n - 1, m);
//        return (m % n + x) % n;
        return (m + x) % n;
    }

    public int lastRemaining_v2(int n, int m) {
        int f = 0;
        for (int i = 2; i <= n; ++i) {
            f = (m + f) % i;
        }
        return f;
    }
}
