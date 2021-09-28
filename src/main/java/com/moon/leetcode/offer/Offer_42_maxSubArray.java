package com.moon.leetcode.offer;

/**
 * 剑指 Offer 42. 连续子数组的最大和
 * <p>
 * 输入一个整型数组，数组中的一个或连续多个整数组成一个子数组。求所有子数组的和的最大值。
 * <p>
 * 要求时间复杂度为O(n)。
 * <p>
 * 示例1:
 * <p>
 * 输入: nums = [-2,1,-3,4,-1,2,1,-5,4]
 * 输出: 6
 * 解释: 连续子数组 [4,-1,2,1] 的和最大，为 6。
 * <p>
 * 提示：
 * <p>
 * 1 <= arr.length <= 10^5
 * -100 <= arr[i] <= 100
 */
public class Offer_42_maxSubArray {
    /**
     * 动态规划
     */
    public static int maxSubArray_v1(int[] nums) {
        int n = nums.length;
        int[] f = new int[n + 1];
        int ans = Integer.MIN_VALUE;
        for (int i = 0; i < n; i++) {
            f[i + 1] = Math.max(f[i] + nums[i], nums[i]);
            ans = Math.max(ans, f[i + 1]);
        }
        return ans;
    }

    /**
     * 动态规划
     */
    public static int maxSubArray_v2(int[] nums) {
        int pre = nums[0];
        int ans = nums[0];
        for (int i = 1; i < nums.length; i++) {
            pre = Math.max(pre + nums[i], nums[i]);
            ans = Math.max(ans, pre);
        }
        return ans;
    }

    /**
     * 贪心
     */
    public static int maxSubArray_v3(int[] nums) {
        int max = nums[0];
        int sum = 0;
        for (int num : nums) {
            sum += num;
            max = Math.max(sum, max);
            if (sum < 0) {
                sum = 0;
            }
        }
        return max;
    }

    public static void main(String[] args) {
        System.out.println(maxSubArray_v2(new int[]{1}));
    }
}
