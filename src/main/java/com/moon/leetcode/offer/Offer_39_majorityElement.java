package com.moon.leetcode.offer;

// 剑指 Offer 39. 数组中出现次数超过一半的数字
//
//数组中有一个数字出现的次数超过数组长度的一半，请找出这个数字。
//
// 你可以假设数组是非空的，并且给定的数组总是存在多数元素。
//
// 示例 1:
//
// 输入: [1, 2, 3, 2, 2, 2, 5, 4, 2]
//输出: 2
//
// 限制：
//
// 1 <= 数组长度 <= 50000
//
// 注意：本题与主站 169 题相同：https://leetcode-cn.com/problems/majority-element/
//
// Related Topics 位运算 分治算法
// 👍 142 👎 0
public class Offer_39_majorityElement {
    public static void main(String[] args) {
        System.out.println(new Offer_39_majorityElement().majorityElement(new int[]{1, 2, 3, 2, 2, 2, 5, 4, 2}));
    }

    public int majorityElement(int[] nums) {
        int count = 1;
        int major = nums[0];
        for (int i = 1; i < nums.length; i++) {
            if (count == 0) {
                major = nums[i];
                count++;
            } else {
                if (nums[i] == major) {
                    count++;
                } else {
                    count--;
                }
            }
        }
        return major;
    }
}
