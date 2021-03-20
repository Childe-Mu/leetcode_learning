package com.moon.leetcode;

/**
 * 231. 2的幂
 * <p>
 * 给定一个整数，编写一个函数来判断它是否是 2 的幂次方。
 * <p>
 * 示例 1:
 * <p>
 * 输入: 1
 * 输出: true
 * 解释: 20= 1
 * <p>
 * 示例 2:
 * <p>
 * 输入: 16
 * 输出: true
 * 解释: 24= 16
 * <p>
 * 示例 3:
 * <p>
 * 输入: 218
 * 输出: false
 */
public class No231_isPowerOfTwo {
    public static boolean isPowerOfTwo_v1(int n) {
        if (n == 0) {
            return false;
        }
        while (n % 2 == 0) {
            n /= 2;
        }
        return n == 1;
    }

    public static boolean isPowerOfTwo_v2(int n) {
        if (n == 0) return false;
        return ((long) n & (-(long) n)) == (long) n;
    }

    public static boolean isPowerOfTwo_v3(int n) {
        if (n == 0) {
            return false;
        }
        return ((long) n & ((long) n - 1)) == 0;
    }

    public static void main(String[] args) {
        System.out.println(isPowerOfTwo_v3(18));
    }
}
