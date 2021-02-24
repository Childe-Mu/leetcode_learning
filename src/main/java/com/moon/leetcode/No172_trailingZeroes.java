package com.moon.leetcode;

import java.math.BigInteger;

/**
 * 172. 阶乘后的零
 * <p>
 * 给定一个整数 n，返回 n! 结果尾数中零的数量。
 * <p>
 * 示例 1:
 * <p>
 * 输入: 3
 * 输出: 0
 * 解释: 3! = 6, 尾数中没有零。
 * <p>
 * 示例 2:
 * <p>
 * 输入: 5
 * 输出: 1
 * 解释: 5! = 120, 尾数中有 1 个零.
 * <p>
 * 说明: 你算法的时间复杂度应为 O(log n) 。
 */
public class No172_trailingZeroes {
    public static int trailingZeroes(int n) {
        BigInteger nFactorial = BigInteger.ONE;
        for (int i = 2; i <= n; i++) {
            nFactorial = nFactorial.multiply(BigInteger.valueOf(i));
        }
        int zeroCount = 0;
        String s = nFactorial.toString();
        for (int i = s.length() - 1; i >= 0; i--) {
            if (s.charAt(i) == '0') {
                zeroCount++;
            } else {
                break;
            }
        }
//        while (nFactorial.mod(BigInteger.TEN).equals(BigInteger.ZERO)) {
//            nFactorial = nFactorial.divide(BigInteger.TEN);
//            zeroCount++;
//        }
        return zeroCount;
    }

    public static int trailingZeroes_v1(int n) {
        int zeroCount = 0;
        for (int i = 5; i <= n; i += 5) {
            int currentFactor = i;
            while (currentFactor % 5 == 0) {
                zeroCount++;
                currentFactor /= 5;
            }
        }
        return zeroCount;
    }

    public static int trailingZeroes_v2(int n) {
        int zeroCount = 0;
        for (int i = 5; i <= n; i += 5) {
            int powerOf5 = 5;
            while (i % powerOf5 == 0) {
                zeroCount += 1;
                powerOf5 *= 5;
            }
        }
        return zeroCount;
    }

    public static int trailingZeroes_v3(int n) {
        int zeroCount = 0;
        long currentMultiple = 5;
        while (n >= currentMultiple) {
            zeroCount += (n / currentMultiple);
            currentMultiple *= 5;
        }
        return zeroCount;
    }

    public static int trailingZeroes_v4(int n) {
        int zeroCount = 0;
        while (n > 0) {
            n /= 5;
            zeroCount += n;
        }
        return zeroCount;
    }

    public static void main(String[] args) {
        System.out.println(BigInteger.ONE.toString());
        System.out.println(trailingZeroes(125));
    }
}
