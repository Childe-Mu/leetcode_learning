package com.moon.leetcode;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * 169. 多数元素
 * <p>
 * 给定一个大小为 n 的数组，找到其中的多数元素。多数元素是指在数组中出现次数 大于 ⌊ n/2 ⌋ 的元素。
 * <p>
 * 你可以假设数组是非空的，并且给定的数组总是存在多数元素。
 * <p>
 * 示例 1：
 * <p>
 * 输入：[3,2,3]
 * 输出：3
 * <p>
 * 示例 2：
 * <p>
 * 输入：[2,2,1,1,1,2,2]
 * 输出：2
 * <p>
 * 进阶：
 * <p>
 * 尝试设计时间复杂度为 O(n)、空间复杂度为 O(1) 的算法解决此问题。
 */
public class No169_majorityElement {
    public int majorityElement_v1(int[] nums) {
        Arrays.sort(nums);
        return nums[nums.length / 2];
    }

    public static int majorityElement_v2(int[] nums) {
        Map<Integer, Integer> counts = new HashMap<>();
        for (int num : nums) {
            counts.put(num, (counts.getOrDefault(num, 0) + 1));
        }
        for (Integer key : counts.keySet()) {
            if (counts.get(key) > nums.length / 2) {
                return key;
            }
        }
        return 0;
    }

    public static int majorityElement_v3(int[] nums) {
        int count = 0;
        Integer candidate = null;
        for (int num : nums) {
            if (count == 0) {
                candidate = num;
            }
            count += (num == candidate) ? 1 : -1;
        }
        return candidate;
    }

    public static void main(String[] args) {
        System.out.println(majorityElement_v2(new int[]{2, 2, 1, 1, 1, 2, 2}));
    }
}
