package com.moon.leetcode;

/**
 * 面试题 16.17. 连续数列
 * <p>
 * 给定一个整数数组，找出总和最大的连续数列，并返回总和。
 * <p>
 * 示例：
 * <p>
 * 输入： [-2,1,-3,4,-1,2,1,-5,4]
 * 输出： 6
 * 解释： 连续子数组 [4,-1,2,1] 的和最大，为 6。
 * 进阶：
 * <p>
 * 如果你已经实现复杂度为 O(n) 的解法，尝试使用更为精妙的分治法求解。
 */
public class Interview_16_17_maxSubArray {
    /**
     * 动态规划
     */
    public static int maxSubArray_v1(int[] nums) {
        int pre = 0, max = nums[0];
        for (int num : nums) {
            pre = Math.max(num, pre + num);
            max = Math.max(max, pre);
        }
        return max;
    }

    /**
     * 分治
     */
    public static int maxSubArray_v2(int[] nums) {
        return getInfo(nums, 0, nums.length - 1).mSum;
    }

    private static class Status {
        int lSum, rSum, mSum, iSum;
        public Status(int lSum, int rSum, int mSum, int iSum) {
            this.lSum = lSum;
            this.rSum = rSum;
            this.mSum = mSum;
            this.iSum = iSum;
        }
    }

    public static Status getInfo(int[] a, int l, int r) {
        if (l == r) {
            return new Status(a[l], a[l], a[l], a[l]);
        }
        int m = (l + r) >> 1;
        Status lSub = getInfo(a, l, m);
        Status rSub = getInfo(a, m + 1, r);
        return pushUp(lSub, rSub);
    }

    public static Status pushUp(Status l, Status r) {
        int iSum = l.iSum + r.iSum;
        int lSum = Math.max(l.lSum, l.iSum + r.lSum);
        int rSum = Math.max(r.rSum, r.iSum + l.rSum);
        int mSum = Math.max(Math.max(l.mSum, r.mSum), l.rSum + r.lSum);
        return new Status(lSum, rSum, mSum, iSum);
    }

    public static void main(String[] args) {
        System.out.println(maxSubArray_v1(new int[]{-2,1,-3,4,-1,2,1,-5,4}));
    }
}
