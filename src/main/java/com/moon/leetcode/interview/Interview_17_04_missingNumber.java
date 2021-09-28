package com.moon.leetcode.interview;

/**
 * 面试题 17.04. 消失的数字
 * <p>
 * 数组nums包含从0到n的所有整数，但其中缺了一个。请编写代码找出那个缺失的整数。你有办法在O(n)时间内完成吗？
 * <p>
 * 注意：本题相对书上原题稍作改动
 * <p>
 * 示例 1：
 * <p>
 * 输入：[3,0,1]
 * 输出：2
 * <p>
 * 示例 2：
 * <p>
 * 输入：[9,6,4,2,3,5,7,0,1]
 * 输出：8
 */
public class Interview_17_04_missingNumber {
    public static int missingNumber_v1(int[] nums) {
        int n = nums.length;
        int sum = n * (n + 1) / 2;
        for (int num : nums) {
            sum -= num;
        }
        return sum;
    }

    /**
     * a ^ a = 0，a ^ 0 = a
     * 假设是[1,0,3], 循环操作相当于
     * 3 ^ 0 ^ 1 ^ 1 ^ 0 ^ 2 ^ 3 = (3 ^ 3) ^ (1 ^ 1) ^ (0 ^ 0) ^ 2 = 0 ^ 0 ^ 0 ^ 2 = 2
     */
    public static int missingNumber_v2(int[] nums) {
        int n = nums.length;
        int res = n;
        for(int i = 0; i < n; i++) {
            res ^= i;
            res ^= nums[i];
        }
        return res;
    }

    public static void main(String[] args) {
        System.out.println(missingNumber_v2(new int[]{3, 0, 1}));
    }
}
