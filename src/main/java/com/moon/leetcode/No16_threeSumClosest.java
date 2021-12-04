package com.moon.leetcode;

// 16. 最接近的三数之和
//给你一个长度为 n 的整数数组 nums 和 一个目标值 target。请你从 nums 中选出三个整数，使它们的和与 target 最接近。
//
// 返回这三个数的和。
//
// 假定每组输入只存在恰好一个解。
//
//
//
// 示例 1：
//
//
//输入：nums = [-1,2,1,-4], target = 1
//输出：2
//解释：与 target 最接近的和是 2 (-1 + 2 + 1 = 2) 。
//
//
// 示例 2：
//
//
//输入：nums = [0,0,0], target = 1
//输出：0
//
//
//
//
// 提示：
//
//
// 3 <= nums.length <= 1000
// -1000 <= nums[i] <= 1000
// -104 <= target <= 104
//
// Related Topics 数组 双指针 排序
// 👍 960 👎 0

import java.util.Arrays;

public class No16_threeSumClosest {
    public int threeSumClosest(int[] nums, int t) {
        Arrays.sort(nums);
        int n = nums.length;
        int ans = Integer.MAX_VALUE >> 1;
        for (int i = 0; i < n; i++) {
            int j = i + 1;
            int k = n - 1;
            while (j < k) {
                int sum = nums[i] + nums[j] + nums[k];
                if (Math.abs(t - sum) < Math.abs(t - ans)) {
                    ans = sum;
                }
                if (sum == t) {
                    return t;
                } else if (sum < t) {
                    j++;
                } else {
                    k--;
                }
            }
        }
        return ans;
    }
}
