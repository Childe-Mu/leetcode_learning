package com.moon.leetcode;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * 217. 存在重复元素
 * <p>
 * 给定一个整数数组，判断是否存在重复元素。
 * <p>
 * 如果存在一值在数组中出现至少两次，函数返回 true 。如果数组中每个元素都不相同，则返回 false 。
 * <p>
 * 示例 1:
 * <p>
 * 输入: [1,2,3,1]
 * 输出: true
 * <p>
 * 示例 2:
 * <p>
 * 输入: [1,2,3,4]
 * 输出: false
 * <p>
 * 示例 3:
 * <p>
 * 输入: [1,1,1,3,3,4,3,2,4,2]
 * 输出: true
 */
public class No217_containsDuplicate {
    public boolean containsDuplicate_v1(int[] nums) {
        Set<Integer> set = new HashSet<>();
        for (int num : nums) {
            set.add(num);
        }
        return set.size() != nums.length;
    }

    public boolean containsDuplicate_v2(int[] nums) {
        return nums.length != Arrays.stream(nums).distinct().count();
    }

    public boolean containsDuplicate_v3(int[] nums) {
        Set<Integer> set = new HashSet<>();
        for (int num : nums) {
            if (!set.add(num)) {
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        System.out.println(new No217_containsDuplicate().containsDuplicate_v2(new int[]{1, 1, 1, 3, 3, 4, 3, 2, 4, 2}));
    }
}
