package com.moon.leetcode;

import java.util.HashSet;
import java.util.Set;

// 剑指 Offer 03. 数组中重复的数字
//
//找出数组中重复的数字。
//
//
//在一个长度为 n 的数组 nums 里的所有数字都在 0～n-1 的范围内。数组中某些数字是重复的，但不知道有几个数字重复了，也不知道每个数字重复了几次。请
//找出数组中任意一个重复的数字。
//
// 示例 1：
//
// 输入：
//[2, 3, 1, 0, 2, 5, 3]
//输出：2 或 3
//
//
//
//
// 限制：
//
// 2 <= n <= 100000
// Related Topics 数组 哈希表
// 👍 373 👎 0
public class Offer_03_findRepeatNumber {
    public static void main(String[] args) {
        System.out.println(new Offer_03_findRepeatNumber().findRepeatNumber_v2(new int[]{2, 1, 3, 2, 4}));
    }

    public int findRepeatNumber_v1(int[] nums) {
        Set<Integer> set = new HashSet<>(nums.length);
        for (int num : nums) {
            if (!set.add(num)) {
                return num;
            }
        }
        return -1;
    }

    public int findRepeatNumber_v2(int[] nums) {
        int temp;
        for (int i = 0; i < nums.length; i++) {
            while (nums[i] != i) {
                if (nums[i] == nums[nums[i]]) {
                    return nums[i];
                }
                temp = nums[i];
                nums[i] = nums[temp];
                nums[temp] = temp;
            }
        }
        return -1;
    }
}
