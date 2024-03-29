package com.moon.leetcode;

// 213. 打家劫舍 II
//
//你是一个专业的小偷，计划偷窃沿街的房屋，每间房内都藏有一定的现金。这个地方所有的房屋都 围成一圈 ，这意味着第一个房屋和最后一个房屋是紧挨着的。同时，相邻的
//房屋装有相互连通的防盗系统，如果两间相邻的房屋在同一晚上被小偷闯入，系统会自动报警 。
//
// 给定一个代表每个房屋存放金额的非负整数数组，计算你 在不触动警报装置的情况下 ，能够偷窃到的最高金额。
//
// 示例 1：
//
//输入：nums = [2,3,2]
//输出：3
//解释：你不能先偷窃 1 号房屋（金额 = 2），然后偷窃 3 号房屋（金额 = 2）, 因为他们是相邻的。
//
// 示例 2：
//
//输入：nums = [1,2,3,1]
//输出：4
//解释：你可以先偷窃 1 号房屋（金额 = 1），然后偷窃 3 号房屋（金额 = 3）。
//    偷窃到的最高金额 = 1 + 3 = 4 。
//
// 示例 3：
//
//输入：nums = [0]
//输出：0
//
// 提示：
//
// 1 <= nums.length <= 100
// 0 <= nums[i] <= 1000
//
// Related Topics 动态规划
// 👍 549 👎 0
public class No213_rob {
    public static void main(String[] args) {
        System.out.println(new No213_rob().rob_v2(new int[]{2, 2, 3}));
    }

    public int rob_v1(int[] nums) {
        int n = nums.length;
        if (n == 0) {
            return 0;
        }
        if (n == 1) {
            return nums[0];
        }
        return Math.max(tryRob(nums, 0, n - 1), tryRob(nums, 1, n));
    }

    private int tryRob(int[] nums, int start, int end) {
        int n = nums.length;
        int[] f = new int[n + 2];
        for (int i = start; i < end; i++) {
            f[i + 2] = Math.max(f[i + 1], f[i] + nums[i]);
        }
        return f[end + 1];
    }

    public int rob_v2(int[] nums) {
        int n = nums.length;
        if (n == 0) {
            return 0;
        }
        if (n == 1) {
            return nums[0];
        }
        Integer[] mem1 = new Integer[n];
        Integer[] mem2 = new Integer[n];
        return Math.max(traverse(nums, n - 1, 0, mem1), traverse(nums, n, 1, mem2));
    }

    private int traverse(int[] nums, int n, int i, Integer[] mem) {
        if (i >= n) {
            return 0;
        }
        if (mem[i] != null) {
            return mem[i];
        }
        return mem[i] = Math.max(traverse(nums, n, i + 1, mem), traverse(nums, n, i + 2, mem) + nums[i]);
    }
}
