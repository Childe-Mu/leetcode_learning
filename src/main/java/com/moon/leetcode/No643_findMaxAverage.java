package com.moon.leetcode;

/**
 * 643. 子数组最大平均数 I
 * <p>
 * 给定 n 个整数，找出平均数最大且长度为 k 的连续子数组，并输出该最大平均数。
 * <p>
 * 示例：
 * <p>
 * 输入：[1,12,-5,-6,50,3], k = 4
 * 输出：12.75
 * 解释：最大平均数 (12-5-6+50)/4 = 51/4 = 12.75
 * <p>
 * 提示：
 * <p>
 * 1 <= k <= n <= 30,000。
 * 所给数据范围 [-10,000，10,000]。
 */
public class No643_findMaxAverage {
    public static double findMaxAverage(int[] nums, int k) {
        double maxAvg = 0;
        double preAvg = 0;
        for (int i = 0; i < k; i++) {
            maxAvg = preAvg += (double) nums[i] / k;
        }
        for (int i = 0; i < nums.length - k; i++) {
            preAvg = preAvg - (double) nums[i] / k + (double) nums[i + k] / k;
            maxAvg = Math.max(maxAvg, preAvg);
        }
        return maxAvg;
    }

    public static double findMaxAverage_v2(int[] nums, int k) {
        int maxSum = 0;
        int preSum = 0;
        for (int i = 0; i < k; i++) {
            maxSum = preSum += nums[i];
        }
        for (int i = 0; i < nums.length - k; i++) {
            preSum = preSum - nums[i] + nums[i + k];
            maxSum = Math.max(maxSum, preSum);
        }
        return (double) maxSum / k;
    }

    public static double findMaxAverage_v3(int[] nums, int k) {
        int maxSum = 0;
        int preSum = 0;
        for (int i = 0; i < nums.length; i++) {
            if (i < k) {
                maxSum = preSum += nums[i];
            } else {
                preSum = preSum - nums[i - k] + nums[i];
                maxSum = Math.max(maxSum, preSum);
            }
        }
        return (double) maxSum / k;
    }

    public static void main(String[] args) {
        System.out.println(findMaxAverage(new int[]{5}, 1));
    }
}
