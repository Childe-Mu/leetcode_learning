package com.moon.leetcode;

import java.util.Arrays;
import java.util.HashMap;

/**
 * 167. 两数之和 II - 输入有序数组<br/>
 * 给定一个已按照升序排列 的有序数组，找到两个数使得它们相加之和等于目标数。<br/>
 * 函数应该返回这两个下标值 index1 和 index2，其中 index1 必须小于 index2。<br/>
 * <br/>
 * 说明:<br/>
 * 返回的下标值（index1 和 index2）不是从零开始的。<br/>
 * 你可以假设每个输入只对应唯一的答案，而且你不可以重复使用相同的元素。<br/>
 * <br/>
 * 示例:<br/>
 * 输入: numbers = [2, 7, 11, 15], target = 9<br/>
 * 输出: [1,2]<br/>
 * 解释: 2 与 7 之和等于目标数 9 。因此 index1 = 1, index2 = 2 。<br/>
 */
public class No167_twoSum {
    // /**
    //  * 哈希表，未使用排序特性
    //  */
    // public static int[] twoSum(int[] numbers, int target) {
    //     if (numbers.length < 2) {
    //         return new int[0];
    //     }
    //     HashMap<Integer, Integer> map = new HashMap<>();
    //     for (int i = 0; i < numbers.length; i++) {
    //         if (map.containsKey(target - numbers[i])) {
    //             return new int[]{map.get(target - numbers[i]), i + 1};
    //         } else {
    //             map.put(numbers[i], i + 1);
    //         }
    //     }
    //     return new int[0];
    // }

    /**
     * 双指针
     */
    public static int[] twoSum(int[] numbers, int target) {
        if (numbers.length < 2) {
            return new int[0];
        }
        int left = 0, right = numbers.length - 1;
        while (left < right) {
            if (numbers[left] + numbers[right] == target) {
                return new int[]{left + 1, right + 1};
            } else if (numbers[left] + numbers[right] > target) {
                right--;
            } else {
                left++;
            }
        }
        return new int[0];
    }

    public static void main(String[] args) {
        System.out.println(Arrays.toString(twoSum(new int[]{2, 7, 11, 15}, 9)));
    }
}
