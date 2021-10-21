package com.moon.leetcode;

import java.util.Arrays;

/**
 * 66. 加一
 * 给定一个由整数组成的非空数组所表示的非负整数，在该数的基础上加一。
 *
 * 最高位数字存放在数组的首位， 数组中每个元素只存储单个数字。
 *
 * 你可以假设除了整数 0 之外，这个整数不会以零开头。
 *
 * 示例 1:
 *
 * 输入: [1,2,3]
 * 输出: [1,2,4]
 * 解释: 输入数组表示数字 123。
 * 示例 2:
 *
 * 输入: [4,3,2,1]
 * 输出: [4,3,2,2]
 * 解释: 输入数组表示数字 4321。
 */
public class No66_plusOne {
    // public static int[] plusOne(int[] digits) {
    //     if (digits[digits.length - 1] < 9) {
    //         digits[digits.length - 1] = digits[digits.length - 1] + 1;
    //         return digits;
    //     } else {
    //         int[] result = new int[digits.length + 1];
    //         result[digits.length] = 0;
    //         int prev = 1;
    //         for (int i = digits.length - 2; i >= 0; i--) {
    //             if (digits[i] + prev < 10) {
    //                 result[i + 1] = digits[i] + prev;
    //                 prev = 0;
    //             } else {
    //                 result[i + 1] = 0;
    //                 prev = 1;
    //             }
    //         }
    //         if (prev == 1) {
    //             result[0] = 1;
    //             return result;
    //         }
    //         return Arrays.copyOfRange(result, 1, result.length);
    //     }
    // }

    /**
     * 秒啊
     */
    public static int[] plusOne_v1(int[] digits) {
        // 不是9,99，999这样情况，一定会在if模块里返回，即某个位置加一后小于10，则不用再进位
        for (int i = digits.length - 1; i >= 0; i--) {
            digits[i]++;
            digits[i] = digits[i] % 10;
            if (digits[i] != 0) {
                return digits;
            }
        }
        // 只有9,99,999这样的情况才需要扩充数组长度，这种情况下，
        // 结果一定是10,100,1000，就是下面这样扩充数组，收尾设置1，其他位置不管，默认值为0
        digits = new int[digits.length + 1];
        digits[0] = 1;
        return digits;
    }

    public static int[] plusOne_v2(int[] digits) {
        int carry = 1;
        int n = digits.length;
        for (int i = n - 1; i >= 0; i--) {
            int t = digits[i] + carry;
            carry = t / 10;
            digits[i] = t % 10;
            if (carry == 0) {
                return digits;
            }
        }
        int[] ans = new int[n + 1];
        System.arraycopy(digits, 0, ans, 1, n);
        ans[0] = carry;
        return ans;
    }

    public static void main(String[] args) {
        System.out.println(Arrays.toString(plusOne_v2(new int[]{9, 9, 9})));
    }
}
