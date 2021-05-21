package com.moon.leetcode;

import java.util.Arrays;

// 300. 最长递增子序列
//
//给你一个整数数组 nums ，找到其中最长严格递增子序列的长度。
//
// 子序列是由数组派生而来的序列，删除（或不删除）数组中的元素而不改变其余元素的顺序。例如，[3,6,2,7] 是数组 [0,3,1,6,2,2,7] 的子序列。
//
// 示例 1：
//
//输入：nums = [10,9,2,5,3,7,101,18]
//输出：4
//解释：最长递增子序列是 [2,3,7,101]，因此长度为 4 。
//
// 示例 2：
//
//输入：nums = [0,1,0,3,2,3]
//输出：4
//
// 示例 3：
//
//输入：nums = [7,7,7,7,7,7,7]
//输出：1
//
// 提示：
//
// 1 <= nums.length <= 2500
// -104 <= nums[i] <= 104
//
// 进阶：
//
// 你可以设计时间复杂度为 O(n2) 的解决方案吗？
// 你能将算法的时间复杂度降低到 O(n log(n)) 吗?
//
// Related Topics 二分查找 动态规划
// 👍 1621 👎 0
public class No300_lengthOfLIS {
    public static void main(String[] args) {
        System.out.println(new No300_lengthOfLIS().lengthOfLIS_v2(new int[]{0, 1, 0, 3, 2, 3}));
    }

    public int lengthOfLIS_v1(int[] nums) {
        int n = nums.length;
        int max = 0;
        int[] f = new int[n];
        Arrays.fill(f, 1);
        for (int i = 0; i < n; i++) {
            int cur = nums[i];
            int m = 1;
            for (int j = 0; j < i; j++) {
                if (cur > nums[j]) {
                    m = Math.max(f[j] + 1, m);
                }
            }
            f[i] = m;
            max = Math.max(m, max);
        }
        return max;
    }

    public int lengthOfLIS_v2(int[] nums) {
        int n = nums.length;
        int[] f = new int[n];
        f[0] = nums[0];
        int index = 0;
        for (int i = 1; i < n; i++) {
            int cur = nums[i];
            if (f[index] < cur) {
                f[++index] = cur;
                continue;
            }
            int j = binarySearch(f, index, cur);
            if (f[j] > cur) {
                f[j] = cur;
            }
        }
        return index + 1;
    }

    private int binarySearch(int[] f, int limit, int target) {
        int l = 0, r = limit;
        while (l < r) {
            int m = l + (r - l) / 2;
            if (f[m] == target) {
                return m;
            } else if (f[m] < target) {
                l = m + 1;
            } else {
                r = m;
            }
        }
        return r;
    }
}
