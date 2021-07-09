package com.moon.leetcode;

// 剑指 Offer 56 - I. 数组中数字出现的次数
//
//一个整型数组 nums 里除两个数字之外，其他数字都出现了两次。请写程序找出这两个只出现一次的数字。要求时间复杂度是O(n)，空间复杂度是O(1)。
//
// 示例 1：
//
// 输入：nums = [4,1,4,6]
//输出：[1,6] 或 [6,1]
//
//
// 示例 2：
//
// 输入：nums = [1,2,10,4,1,4,3,3]
//输出：[2,10] 或 [10,2]
//
// 限制：
//
// 2 <= nums.length <= 10000
//
// Related Topics 位运算 数组
// 👍 423 👎 0
public class Offer_56_1_singleNumbers {
    public int[] singleNumbers(int[] nums) {
        int xor = 0;
        for (int num : nums) {
            xor ^= num;
        }
        int i = 32;
        while (i >= 0) {
            if (((xor >>> i) & 1) == 1) {
                break;
            }
            i--;
        }
        int a = 0, b = 0;
        for (int num : nums) {
            if (((num >>> i) & 1) == 1) {
                a ^= num;
            } else {
                b ^= num;
            }
        }
        return new int[]{a, b};
    }
}
