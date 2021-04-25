package com.moon.leetcode;

import java.util.Arrays;

// 377. 组合总和 Ⅳ
//
//给你一个由 不同 整数组成的数组 nums ，和一个目标整数 target 。请你从 nums 中找出并返回总和为 target 的元素组合的个数。
//
// 题目数据保证答案符合 32 位整数范围。
//
// 示例 1：
//
//输入：nums = [1,2,3], target = 4
//输出：7
//解释：
//所有可能的组合为：
//(1, 1, 1, 1)
//(1, 1, 2)
//(1, 2, 1)
//(1, 3)
//(2, 1, 1)
//(2, 2)
//(3, 1)
//请注意，顺序不同的序列被视作不同的组合。
//
// 示例 2：
//
//输入：nums = [9], target = 3
//输出：0
//
// 提示：
//
// 1 <= nums.length <= 200
// 1 <= nums[i] <= 1000
// nums 中的所有元素 互不相同
// 1 <= target <= 1000
//
// 进阶：如果给定的数组中含有负数会发生什么？问题会产生何种变化？如果允许负数出现，需要向题目中添加哪些限制条件？
// Related Topics 动态规划
// 👍 413 👎 0
public class No337_combinationSum4 {
    public static void main(String[] args) {
        System.out.println(new No337_combinationSum4().combinationSum4_v1(new int[]{1, 2, 3}, 4));
    }

    public int combinationSum4_v1(int[] nums, int target) {
        Integer[] mem = new Integer[target + 1];
        return traverseByMem(nums, target, mem);
    }

    private int traverseByMem(int[] nums, int target, Integer[] mem) {
        if (mem[target] != null) {
            return mem[target];
        }
        if (target == 0) {
            return 1;
        }
        int tmp = 0;
        for (int num : nums) {
            if (num <= target) {
                tmp += traverseByMem(nums, target - num, mem);
            }
        }
        return mem[target] = tmp;
    }

    public int combinationSum4_v2(int[] nums, int target) {
        Arrays.sort(nums);
        int[] f = new int[target + 1];
        f[0] = 1;
        for (int i = 1; i <= target; i++) {
            for (int num : nums) {
                if (num > i) {
                    break;
                }
                f[i] += f[i - num];
            }
        }
        return f[target];
    }
}
