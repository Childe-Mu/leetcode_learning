package com.moon.leetcode.offer;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

// 剑指 Offer 57. 和为s的两个数字
//
//输入一个递增排序的数组和一个数字s，在数组中查找两个数，使得它们的和正好是s。如果有多对数字的和等于s，则输出任意一对即可。
//
// 示例 1：
//
// 输入：nums = [2,7,11,15], target = 9
//输出：[2,7] 或者 [7,2]
//
// 示例 2：
//
// 输入：nums = [10,26,30,31,47,60], target = 40
//输出：[10,30] 或者 [30,10]
//
// 限制：
//
// 1 <= nums.length <= 10^5
// 1 <= nums[i] <= 10^6
//
// 👍 96 👎 0
public class Offer_57_twoSum {
    public static void main(String[] args) {
        System.out.println(Arrays.toString(new Offer_57_twoSum().twoSum_v2(new int[]{10, 26, 30, 31, 47, 60}, 40)));
    }

    public int[] twoSum_v1(int[] nums, int target) {
        Set<Integer> set = new HashSet<>();
        for (int num : nums) {
            if (set.contains(target - num)) {
                return new int[]{num, target - num};
            }
            set.add(num);
        }
        return null;
    }

    public int[] twoSum_v2(int[] nums, int target) {
        int l = 0, r = nums.length - 1;
        while (r > 0 && nums[r] >= target) {
            r--;
        }
        while (l < r) {
            if (nums[l] + nums[r] == target) {
                return new int[]{nums[l], nums[r]};
            } else if (nums[l] + nums[r] > target) {
                r--;
            } else {
                l++;
            }
        }
        return null;
    }
}
