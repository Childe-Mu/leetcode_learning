package com.moon.leetcode;

import java.util.Arrays;

// 198. 打家劫舍
//
//你是一个专业的小偷，计划偷窃沿街的房屋。每间房内都藏有一定的现金，影响你偷窃的唯一制约因素就是相邻的房屋装有相互连通的防盗系统，如果两间相邻的房屋在同一晚上
//被小偷闯入，系统会自动报警。
//
// 给定一个代表每个房屋存放金额的非负整数数组，计算你 不触动警报装置的情况下 ，一夜之内能够偷窃到的最高金额。
//
// 示例 1：
//
// 输入：[1,2,3,1]
//输出：4
//解释：偷窃 1 号房屋 (金额 = 1) ，然后偷窃 3 号房屋 (金额 = 3)。
//    偷窃到的最高金额 = 1 + 3 = 4 。
//
// 示例 2：
//
// 输入：[2,7,9,3,1]
//输出：12
//解释：偷窃 1 号房屋 (金额 = 2), 偷窃 3 号房屋 (金额 = 9)，接着偷窃 5 号房屋 (金额 = 1)。
//   偷窃到的最高金额 = 2 + 9 + 1 = 12 。
//
// 提示：
//
// 0 <= nums.length <= 100
// 0 <= nums[i] <= 400
//
// Related Topics 动态规划
// 👍 1384 👎 0
public class No198_rob {
    public static void main(String[] args) {
        System.out.println(new No198_rob().rob_v2(new int[]{2, 7, 9, 3, 1}));
    }

    public int rob_v1(int[] nums) {
        int n = nums.length;
        if (n == 0) {
            return 0;
        }
        int[] f = new int[n + 2];
        for (int i = 0; i < n; i++) {
            f[i + 2] = Math.max(f[i + 1], f[i] + nums[i]);
        }
        return f[n + 1];
    }

    public int rob_v2(int[] nums) {
        int n = nums.length;
        if (n == 0) {
            return 0;
        }
        int[] mem = new int[n];
        Arrays.fill(mem, -1);
        return traverseByMem(nums, n, 0, mem);
    }

    private int traverseByMem(int[] nums, int n, int i, int[] mem) {
        if (i >= n) {
            return 0;
        }
        if (mem[i] != -1) {
            return mem[i];
        }
        return mem[i] = Math.max(traverseByMem(nums, n, i + 1, mem), traverseByMem(nums, n, i + 2, mem) + nums[i]);
    }
}
