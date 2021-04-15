package com.moon.leetcode;

// 剑指 Offer 53 - I. 在排序数组中查找数字 I
//
//统计一个数字在排序数组中出现的次数。
//
// 示例 1:
//
// 输入: nums = [5,7,7,8,8,10], target = 8
//输出: 2
//
// 示例 2:
//
// 输入: nums = [5,7,7,8,8,10], target = 6
//输出: 0
//
// 限制：
//
// 0 <= 数组长度 <= 50000
//
// 注意：本题与主站 34 题相同（仅返回值不同）：https://leetcode-cn.com/problems/find-first-and-last-
//position-of-element-in-sorted-array/
// Related Topics 数组 二分查找
// 👍 118 👎 0
public class Offer_53_search {
    public static void main(String[] args) {
        System.out.println(new Offer_53_search().search_v2(new int[]{5, 7, 7, 8, 8, 10}, 5));
    }

    public int search_v1(int[] nums, int target) {
        int l = 0, r = nums.length - 1, i = -1;
        while (l <= r) {
            int m = (r - l) / 2 + l;
            if (nums[m] == target) {
                i = m;
                break;
            } else if (nums[m] < target) {
                l = m + 1;
            } else {
                r = m - 1;
            }
        }
        if (i == -1) {
            return 0;
        }
        int j = i + 1;
        int count = 0;
        while (i >= 0 && nums[i] == target) {
            count++;
            i--;
        }
        while (j < nums.length && nums[j] == target) {
            count++;
            j++;
        }
        return count;
    }

    public int search_v2(int[] nums, int target) {
        return helper(nums, target) - helper(nums, target - 1);
    }

    int helper(int[] nums, int tar) {
        int l = 0, r = nums.length - 1;
        while (l <= r) {
            int m = (l + r) / 2;
            if (nums[m] <= tar) {
                l = m + 1;
            } else {
                r = m - 1;
            }
        }
        return l;
    }
}
