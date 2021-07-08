package com.moon.leetcode;

import java.util.HashMap;
import java.util.Map;

// 930. 和相同的二元子数组
//
//给你一个二元数组 nums ，和一个整数 goal ，请你统计并返回有多少个和为 goal 的 非空 子数组。
//
// 子数组 是数组的一段连续部分。
//
// 示例 1：
//
//输入：nums = [1,0,1,0,1], goal = 2
//输出：4
//解释：
//如下面黑体所示，有 4 个满足题目要求的子数组：
//[1,0,1,0,1]
//[1,0,1,0,1]
//[1,0,1,0,1]
//[1,0,1,0,1]
//
// 示例 2：
//
//输入：nums = [0,0,0,0,0], goal = 0
//输出：15
//
// 提示：
//
// 1 <= nums.length <= 3 * 104
// nums[i] 不是 0 就是 1
// 0 <= goal <= nums.length
//
// Related Topics 数组 哈希表 前缀和 滑动窗口
// 👍 149 👎 0
public class No930_numSubarraysWithSum {
    public static void main(String[] args) {
        System.out.println(new No930_numSubarraysWithSum().numSubarraysWithSum_v1(new int[]{1, 0, 1, 0, 1}, 2));
    }

    public int numSubarraysWithSum_v1(int[] nums, int goal) {
        int n = nums.length;

        // left1与left2之间夹着的是很多个0
        int left1 = 0, left2 = 0, right = 0;
        int sum1 = 0, sum2 = 0;
        int res = 0;

        // 右边界
        while (right < n) {
            sum1 += nums[right];
            // sum1 要等于 goal-1
            while (left1 <= right && sum1 > goal) {
                sum1 -= nums[left1];
                left1++;
            }
            sum2 += nums[right];
            // sum2 要等于 goal
            while (left2 <= right && sum2 >= goal) {
                sum2 -= nums[left2];
                left2++;
            }
            // 其中的每个0都能算一种情况
            res += left2 - left1;
            // 右指针右移
            right++;
        }
        return res;
    }

    public int numSubarraysWithSum_v2(int[] nums, int goal) {
        int sum = 0;
        Map<Integer, Integer> cnt = new HashMap<>();
        int ans = 0;
        for (int num : nums) {
            cnt.put(sum, cnt.getOrDefault(sum, 0) + 1);
            sum += num;
            ans += cnt.getOrDefault(sum - goal, 0);
        }
        return ans;
    }
}
