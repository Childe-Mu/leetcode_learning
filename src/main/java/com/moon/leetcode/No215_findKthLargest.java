package com.moon.leetcode;

import java.util.Random;

// 215. 数组中的第K个最大元素
//
//在未排序的数组中找到第 k 个最大的元素。请注意，你需要找的是数组排序后的第 k 个最大的元素，而不是第 k 个不同的元素。
//
// 示例 1:
//
// 输入: [3,2,1,5,6,4] 和 k = 2
//输出: 5
//
//
// 示例 2:
//
// 输入: [3,2,3,1,2,4,5,5,6] 和 k = 4
//输出: 4
//
// 说明:
//
// 你可以假设 k 总是有效的，且 1 ≤ k ≤ 数组的长度。
// Related Topics 堆 分治算法
// 👍 1083 👎 0
public class No215_findKthLargest {
    Random random = new Random();

    public static void main(String[] args) {
        System.out.println(new No215_findKthLargest().findKthLargest(new int[]{3, 2, 3, 1, 2, 4, 5, 5, 6}, 4));
    }

    public int findKthLargest(int[] nums, int k) {
        int kth = nums.length - k;
        kthElement(nums, 0, nums.length - 1, kth);
        return nums[kth];
    }

    private void kthElement(int[] nums, int low, int high, int kth) {
        if (low >= high) {
            return;
        }
        int l = low, r = high, i = low + 1;
        int pivot = low + random.nextInt(high - low);
        swap(nums, pivot, low);
        int v = nums[low];
        while (i <= r) {
            int cur = nums[i];
            if (cur < v) {
                swap(nums, l++, i++);
            } else if (cur > v) {
                swap(nums, i, r--);
            } else {
                i++;
            }
        }

        if (l <= kth && kth <= r) {
            return;
        } else if (kth < l) {
            kthElement(nums, low, l - 1, kth);
        } else {
            kthElement(nums, r + 1, high, kth);
        }
    }

    private void swap(int[] nums, int i, int j) {
        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }
}
