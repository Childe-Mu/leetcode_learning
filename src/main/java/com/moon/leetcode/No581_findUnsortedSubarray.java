package com.moon.leetcode;

import java.util.Arrays;

// 581. 最短无序连续子数组
//
//给你一个整数数组 nums ，你需要找出一个 连续子数组 ，如果对这个子数组进行升序排序，那么整个数组都会变为升序排序。
//
// 请你找出符合题意的 最短 子数组，并输出它的长度。
//
//
//
//
//
// 示例 1：
//
//
//输入：nums = [2,6,4,8,10,9,15]
//输出：5
//解释：你只需要对 [6, 4, 8, 10, 9] 进行升序排序，那么整个表都会变为升序排序。
//
//
// 示例 2：
//
//
//输入：nums = [1,2,3,4]
//输出：0
//
//
// 示例 3：
//
//
//输入：nums = [1]
//输出：0
//
//
//
//
// 提示：
//
//
// 1 <= nums.length <= 104
// -105 <= nums[i] <= 105
//
//
//
//
// 进阶：你可以设计一个时间复杂度为 O(n) 的解决方案吗？
//
//
// Related Topics 栈 贪心 数组 双指针 排序 单调栈
// 👍 642 👎 0
public class No581_findUnsortedSubarray {
    public static void main(String[] args) {
        System.out.println(new No581_findUnsortedSubarray().findUnsortedSubarray_v2(new int[]{2, 6, 4, 8, 10, 9, 15}));
    }

    public int findUnsortedSubarray_v1(int[] nums) {
        int[] clone = nums.clone();
        Arrays.sort(nums);
        int n = nums.length;
        int l = 0, r = n - 1;
        while (l <= r && nums[l] == clone[l]) {
            l++;
        }
        while (l <= r && nums[r] == clone[r]) {
            r--;
        }
        return r - l + 1;
    }

    public int findUnsortedSubarray_v2(int[] nums) {
        int n = nums.length;
        int l = 0, r = n - 1;
        while (l < r && nums[l] <= nums[l + 1]) {
            l++;
        }
        while (l < r && nums[r] >= nums[r - 1]) {
            r--;
        }
        if (l >= r) {
            return 0;
        }
        int min = nums[l], max = nums[r];
        int rr = r;
        for (int i = l; i <= rr; i++) {
            while (nums[i] < min) {
                l--;
                min = l < 0 ? (int) -1e5 - 1 : nums[l];
            }
            while (nums[i] > max) {
                r++;
                max = r >= n ? (int) 1e5 + 1 : nums[r];
            }
        }
        return r - l - 1;
    }
}
