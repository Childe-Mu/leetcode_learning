package com.moon.leetcode;

import java.util.Arrays;

// 剑指 Offer 10- I. 斐波那契数列
//
//写一个函数，输入 n ，求斐波那契（Fibonacci）数列的第 n 项（即 F(N)）。斐波那契数列的定义如下：
//
//F(0) = 0, F(1)= 1
//F(N) = F(N - 1) + F(N - 2), 其中 N > 1.
//
// 斐波那契数列由 0 和 1 开始，之后的斐波那契数就是由之前的两数相加而得出。
//
// 答案需要取模 1e9+7（1000000007），如计算初始结果为：1000000008，请返回 1。
//
// 示例 1：
//
//输入：n = 2
//输出：1
//
// 示例 2：
//
//输入：n = 5
//输出：5
//
// 提示：
//
// 0 <= n <= 100
// Related Topics 递归
public class Offer_10_1_fib {
    public static void main(String[] args) {
        System.out.println(new Offer_10_1_fib().fib_v3(5));
    }

    public int fib_v1(int n) {
        if (n == 0) {
            return 0;
        }
        if (n == 1) {
            return 1;
        }
        return fib_v1(n - 1) + fib_v1(n - 2);
    }

    public int fib_v2(int n) {
        if (n == 0) {
            return 0;
        }
        if (n == 1) {
            return 1;
        }
        int[] mem = new int[n + 1];
        Arrays.fill(mem, -1);
        mem[0] = 0;
        mem[1] = 1;
        return traverseByMem(n, mem) % 1000000007;
    }

    private int traverseByMem(int n, int[] mem) {
        if (mem[n] != -1) {
            return mem[n];
        }
        return mem[n] = (traverseByMem(n - 1, mem) % 1000000007 + traverseByMem(n - 2, mem)) % 1000000007;
    }

    public int fib_v3(int n) {
        if (n == 0) {
            return 0;
        }
        if (n == 1) {
            return 1;
        }
        int[] f = new int[2];
        f[1] = 1;
        for (int i = 2; i <= n; i++) {
            int temp = f[0] + f[1];
            f[0] = f[1];
            f[1] = temp % 1000000007;
        }
        return f[1];
    }
}
