package com.moon.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 204. 计数质数
 * <p>
 * 统计所有小于非负整数 n 的质数的数量。
 * <p>
 * 示例 1：
 * <p>
 * 输入：n = 10
 * 输出：4
 * 解释：小于 10 的质数一共有 4 个, 它们是 2, 3, 5, 7 。
 * <p>
 * 示例 2：
 * <p>
 * 输入：n = 0
 * 输出：0
 * <p>
 * 示例 3：
 * <p>
 * 输入：n = 1
 * 输出：0
 * <p>
 * 提示：
 * <p>
 * 0 <= n <= 5 * 106
 */
public class No204_countPrimes {
    /**
     * 方法一：枚举
     */
    public static int countPrimes_v1(int n) {
        int res = 0;
        for (int i = 2; i < n; i++) {
            int sqrt = (int) Math.sqrt(i);
            boolean f = true;
            for (int j = 2; j <= sqrt; j++) {
                if (i % j == 0) {
                    f = false;
                    break;
                }
            }
            if (f) {
                res++;
            }
        }
        return res;
    }

    /**
     * 方法二：埃氏筛
     */
    public static int countPrimes_v2(int n) {
        int res = 0;
        boolean[] b = new boolean[n];
        Arrays.fill(b, true);
        for (int i = 2; i < n; i++) {
            if (b[i]) {
                res++;
            }
            if ((long) i * i < n) {
                for (int j = i; i * j < n; j++) {
                    b[i * j] = false;
                }
            }
        }
        return res;
    }

    /**
     * 方法三：线性筛
     */
    public static int countPrimes_v3(int n) {
        List<Integer> primes = new ArrayList<>();
        int[] isPrime = new int[n];
        Arrays.fill(isPrime, 1);
        for (int i = 2; i < n; ++i) {
            if (isPrime[i] == 1) {
                primes.add(i);
            }
            for (int j = 0; j < primes.size() && i * primes.get(j) < n; ++j) {
                isPrime[i * primes.get(j)] = 0;
                System.out.println("i = " + i + " j = " + primes.get(j));
                if (i % primes.get(j) == 0) {
                    break;
                }
            }
        }
        return primes.size();
    }


    public static void main(String[] args) {
        long cur = System.currentTimeMillis();
        int res = countPrimes_v3(20);
        long end = System.currentTimeMillis();
        System.out.println("res = " + res + " time = " + (end - cur));
    }
}
