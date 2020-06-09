package com.moon.leetcode;

/**
 * 69. x 的平方根<br/>
 * 实现 int sqrt(int x) 函数。<br/>
 * 计算并返回 x 的平方根，其中 x 是非负整数。<br/>
 * 由于返回类型是整数，结果只保留整数的部分，小数部分将被舍去。<br/>
 * <br/>
 * 示例 1:<br/>
 * 输入: 4<br/>
 * 输出: 2<br/>
 * <br/>
 * 示例 2:<br/>
 * 输入: 8<br/>
 * 输出: 2<br/>
 * 说明: 8 的平方根是 2.82842...,
 * 由于返回类型是整数，小数部分将被舍去。
 */
public class No69_mySqrt {
    // /**
    //  * 二分法
    //  */
    // public static int mySqrt(int x) {
    //     if (x == 0) {
    //         return 0;
    //     } else if (x < 4) {
    //         return 1;
    //     } else if (x > 46340 * 46340) {
    //         return 46340;
    //     }
    //     int left = 0;
    //     int right = Math.min(x >>> 1, 46340 * 2);
    //     while (left <= right) {
    //         int mid = (right + left) >>> 1;
    //         if (mid * mid == x) {
    //             return mid;
    //         } else if (mid * mid > x) {
    //             right = mid - 1;
    //         } else {
    //             left = mid + 1;
    //         }
    //     }
    //     return right;
    // }
    // /**
    //  * 二分法，乘积转换为long，不考虑边界条件
    //  */
    // public static int mySqrt(int x) {
    //     int left = 0, right = x, ans = -1;
    //     while (left <= right) {
    //         int mid = left + (right - left) / 2;
    //         if ((long) mid * mid <= x) {
    //             ans = mid;
    //             left = mid + 1;
    //         } else {
    //             right = mid - 1;
    //         }
    //     }
    //     return ans;
    // }

    /**
     * 牛顿法  Xn+1 = (Xn + C / Xn) / 2，C为要求平方根的数
     */
    public static int mySqrt(int x) {
        if(x == 0) {
            return 0;
        }
        double x0 = x;
        while (true) {
            double xi = 0.5 * (x0 + (double) x / x0);
            // 当上一次的近似根和本次的近似根差值小于一定程度，则停止，
            if (Math.abs(x0 - xi) < 1e-7) {
                break;
            }
            x0 = xi;
        }
        return (int)x0;
    }

    public static void main(String[] args) {
        System.out.println(mySqrt(2147395599));
    }
}
