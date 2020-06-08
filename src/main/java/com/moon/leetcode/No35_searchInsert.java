package com.moon.leetcode;

/**
 * 35. 搜索插入位置<br/>
 * 给定一个排序数组和一个目标值，在数组中找到目标值，并返回其索引。如果目标值不存在于数组中，返回它将会被按顺序插入的位置。<br/>
 * 你可以假设数组中无重复元素。<br/>
 * <br/>
 * 示例 1:<br/>
 * 输入: [1,3,5,6], 5<br/>
 * 输出: 2<br/>
 * <br/>
 * 示例 2:<br/>
 * 输入: [1,3,5,6], 2<br/>
 * 输出: 1<br/>
 * <br/>
 * 示例 3:<br/>
 * 输入: [1,3,5,6], 7<br/>
 * 输出: 4<br/>
 * <br/>
 * 示例 4:<br/>
 * 输入: [1,3,5,6], 0<br/>
 * 输出: 0<br/>
 */
public class No35_searchInsert {
    /**
     * 错误解法，跳出循环的条件和left是否+1，right是否-1没有考虑清楚
     */
    // public static int searchInsert(int[] nums, int target) {
    //     if (nums[0] > target) {
    //         return 0;
    //     }
    //     if (nums[nums.length - 1] < target) {
    //         return nums.length;
    //     }
    //     int left = 0;
    //     int right = nums.length - 1;
    //     while (left < right) {
    //         int mid = (right + left) / 2;
    //         if (nums[mid] == target) {
    //             return mid;
    //         }
    //         if (right - left == 1 && nums[right] > target && nums[left] < target) {
    //             return right;
    //         }
    //         if (nums[mid] > target) {
    //             right = mid;
    //         } else if (nums[mid] < target) {
    //             left = mid;
    //         }
    //     }
    //     return 0;
    // }

    public static int searchInsert(int[] nums, int target) {
        if (nums[0] > target) {
            return 0;
        }
        if (nums[nums.length - 1] < target) {
            return nums.length;
        }
        int left = 0, right = nums.length - 1; // 注意
        while (left <= right) { // 注意
            int mid = (left + right) / 2; // 注意
            if (nums[mid] == target) { // 注意
                return mid;
                // 相关逻辑
            } else if (nums[mid] < target) {
                left = mid + 1; // 注意
            } else {
                right = mid - 1; // 注意
            }
        }
        // 相关返回值
        return left;
    }

    public static void main(String[] args) {
        System.out.println(searchInsert(new int[]{1, 3, 5, 6}, 2));
    }
}
