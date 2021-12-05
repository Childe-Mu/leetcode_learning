package com.moon.leetcode;

import java.util.Arrays;

// 34. 在排序数组中查找元素的第一个和最后一个位置
//给定一个按照升序排列的整数数组 nums，和一个目标值 target。找出给定目标值在数组中的开始位置和结束位置。
//
// 如果数组中不存在目标值 target，返回 [-1, -1]。
//
// 进阶：
//
//
// 你可以设计并实现时间复杂度为 O(log n) 的算法解决此问题吗？
//
//
//
//
// 示例 1：
//
//
//输入：nums = [5,7,7,8,8,10], target = 8
//输出：[3,4]
//
// 示例 2：
//
//
//输入：nums = [5,7,7,8,8,10], target = 6
//输出：[-1,-1]
//
// 示例 3：
//
//
//输入：nums = [], target = 0
//输出：[-1,-1]
//
//
//
// 提示：
//
//
// 0 <= nums.length <= 105
// -109 <= nums[i] <= 109
// nums 是一个非递减数组
// -109 <= target <= 109
//
// Related Topics 数组 二分查找
// 👍 1324 👎 0
public class No34_searchRange {
    public static void main(String[] args) {
        System.out.println(Arrays.toString(new No34_searchRange().searchRange(new int[]{5, 7, 7, 8, 8, 10}, 6)));
    }

    public int[] searchRange(int[] nums, int target) {
        int n = nums.length;
        int[] ans = new int[]{-1, -1};
        if (n == 0) {
            return ans;
        }
        if (n == 1) {
            return nums[0] == target ? new int[]{0, 0} : ans;
        }
        int l = 0;
        int r = n - 1;
        while (l < r) {
            int m = (r - l + 1) / 2 + l;
            if (nums[m] < target) {
                l = m;
            } else {
                r = m - 1;
            }
        }
        ans[0] = nums[l] == target ? l : l + 1;
        l = 0;
        r = n - 1;
        while (l < r) {
            int m = (r - l) / 2 + l;
            if (nums[m] <= target) {
                l = m + 1;
            } else {
                r = m;
            }
        }
        ans[1] = nums[r] == target ? r : r - 1;
        return ans[0] <= ans[1] ? ans : new int[]{-1, -1};
    }
}
