package com.moon.leetcode;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Set;

/**
 * 219. 存在重复元素 II
 * <p>
 * 给定一个整数数组和一个整数 k，判断数组中是否存在两个不同的索引 i 和 j，使得 nums [i] = nums [j]，并且 i 和 j 的差的 绝对值
 * 至多为 k。
 * <p>
 * 示例 1:
 * <p>
 * 输入: nums = [1,2,3,1], k = 3
 * 输出: true
 * <p>
 * 示例 2:
 * <p>
 * 输入: nums = [1,0,1,1], k = 1
 * 输出: true
 * <p>
 * 示例 3:
 * <p>
 * 输入: nums = [1,2,3,1,2,3], k = 2
 * 输出: false
 */
public class No219_containsNearbyDuplicate {
    public static boolean containsNearbyDuplicate_v1(int[] nums, int k) {
        Map<Integer, LinkedList<Integer>> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            int num = nums[i];
            LinkedList<Integer> l;
            if (map.containsKey(num)) {
                l = map.get(num);
                if (i - l.getLast() <= k) {
                    return true;
                }
            } else {
                l = new LinkedList<>();
            }
            l.addLast(i);
            map.put(num, l);
        }
        return false;
    }

    public static boolean containsNearbyDuplicate_v2(int[] nums, int k) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            int num = nums[i];
            if (i - map.getOrDefault(num, -(k + 1)) <= k) {
                return true;
            }
            map.put(num, i);
        }
        return false;
    }

    public static boolean containsNearbyDuplicate_v3(int[] nums, int k) {
        Set<Integer> set = new HashSet<>();
        for (int i = 0; i < nums.length; ++i) {
            if (set.contains(nums[i])) return true;
            set.add(nums[i]);
            if (set.size() > k) {
                set.remove(nums[i - k]);
            }
        }
        return false;
    }

    public static void main(String[] args) {
        System.out.println(containsNearbyDuplicate_v3(new int[]{1,0,1,1}, 1));
    }
}
