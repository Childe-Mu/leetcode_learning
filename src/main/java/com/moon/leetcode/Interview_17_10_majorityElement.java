package com.moon.leetcode;

/**
 * 面试题 17.10. 主要元素
 * <p>
 * 数组中占比超过一半的元素称之为主要元素。给定一个整数数组，找到它的主要元素。若没有，返回-1。
 * <p>
 * 示例 1：
 * <p>
 * 输入：[1,2,5,9,5,9,5,5,5]
 * 输出：5
 * <p>
 * 示例 2：
 * <p>
 * 输入：[3,2]
 * 输出：-1
 * <p>
 * 示例 3：
 * <p>
 * 输入：[2,2,1,1,1,2,2]
 * 输出：2
 * <p>
 * 说明：
 * 你有办法在时间复杂度为 O(N)，空间复杂度为 O(1) 内完成吗？
 */
public class Interview_17_10_majorityElement {
    public static int majorityElement(int[] nums) {
        int p = 0, count = 0;
        for (int num : nums) {
            if (count == 0) {
                p = num;
            }
            if (p == num) {
                count++;
            } else {
                count--;
            }
        }
        count = 0;
        for (int num : nums) {
            if (num == p) {
                count++;
            }
        }
        return count > nums.length / 2 ? p : -1;
    }

    public static void main(String[] args) {
        System.out.println(majorityElement(new int[]{1,2,5,9}));
    }
}
