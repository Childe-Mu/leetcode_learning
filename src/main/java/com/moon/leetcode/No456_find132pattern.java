package com.moon.leetcode;

import java.util.Deque;
import java.util.LinkedList;
import java.util.TreeMap;

/**
 * 456. 132 模式
 * <p>
 * 给你一个整数数组 nums ，数组中共有 n 个整数。132 模式的子序列 由三个整数 nums[i]、nums[j] 和 nums[k] 组成，并同时满足
 * ：i < j < k 和 nums[i] < nums[k] < nums[j] 。
 * <p>
 * 如果 nums 中存在 132 模式的子序列 ，返回 true ；否则，返回 false 。
 * <p>
 * 进阶：很容易想到时间复杂度为 O(n^2) 的解决方案，你可以设计一个时间复杂度为 O(n logn) 或 O(n) 的解决方案吗？
 * <p>
 * 示例 1：
 * <p>
 * 输入：nums = [1,2,3,4]
 * 输出：false
 * 解释：序列中不存在 132 模式的子序列。
 * <p>
 * 示例 2：
 * <p>
 * 输入：nums = [3,1,4,2]
 * 输出：true
 * 解释：序列中有 1 个 132 模式的子序列： [1, 4, 2] 。
 * <p>
 * 示例 3：
 * <p>
 * 输入：nums = [-1,3,2,0]
 * 输出：true
 * 解释：序列中有 3 个 132 模式的的子序列：[-1, 3, 2]、[-1, 3, 0] 和 [-1, 2, 0] 。
 * <p>
 * 提示：
 * <p>
 * n == nums.length
 * 1 <= n <= 104
 * -109 <= nums[i] <= 109
 */
public class No456_find132pattern {
    /**
     * 枚举3
     */
    public static boolean find132pattern_v1(int[] nums) {
        int n = nums.length;
        if (n < 3) {
            return false;
        }
        TreeMap<Integer, Integer> map = new TreeMap<>();
        int min = nums[0];
        for (int i = 2; i < n; i++) {
            map.put(nums[i], map.getOrDefault(nums[i], 0) + 1);
        }
        for (int i = 1; i < n - 1; i++) {
            Integer temp;
            if (nums[i] > min
                    && (temp = map.lowerKey(nums[i])) != null
                    && temp > min) {
                return true;
            } else {
                min = Math.min(min, nums[i]);
                Integer value = map.get(nums[i + 1]);
                if (value == 1) {
                    map.remove(nums[i + 1]);
                } else {
                    map.put(nums[i + 1], value - 1);
                }
            }
        }
        return false;
    }

    /**
     * 单调栈
     */
    public static boolean find132pattern_v2(int[] nums) {
        int n = nums.length;
        Deque<Integer> candidateK = new LinkedList<>();
        candidateK.push(nums[n - 1]);
        int maxK = Integer.MIN_VALUE;

        for (int i = n - 2; i >= 0; --i) {
            if (nums[i] < maxK) {
                return true;
            }
            while (!candidateK.isEmpty() && nums[i] > candidateK.peek()) {
                maxK = candidateK.pop();
            }
            if (nums[i] > maxK) {
                candidateK.push(nums[i]);
            }
        }

        return false;
    }

    public static void main(String[] args) {
        System.out.println(find132pattern_v2(new int[]{3, 1, 4, 2}));
    }
}
