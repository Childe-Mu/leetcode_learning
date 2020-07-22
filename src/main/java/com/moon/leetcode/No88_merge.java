package com.moon.leetcode;

import java.util.Arrays;

/**
 * 88. 合并两个有序数组<br/>
 * 给你两个有序整数数组 nums1 和 nums2，请你将 nums2 合并到 nums1 中，使 nums1 成为一个有序数组。<br/>
 * <br/>
 * 说明:<br/>
 * 初始化 nums1 和 nums2 的元素数量分别为 m 和 n 。<br/>
 * 你可以假设 nums1 有足够的空间（空间大小大于或等于 m + n）来保存 nums2 中的元素。<br/>
 * <br/>
 * 示例:<br/>
 * 输入:<br/>
 * nums1 = [1,2,3,0,0,0], m = 3<br/>
 * nums2 = [2,5,6],       n = 3<br/>
 * 输出: [1,2,2,3,5,6]<br/>
 */
public class No88_merge {
    // /**
    //  * 直接排序
    //  */
    // public static void merge(int[] nums1, int m, int[] nums2, int n) {
    //     // 特殊情况的判断，并没有大幅提升效率
    //     // if (n == 0) {
    //     //     return;
    //     // } else if (m == 0 && n > 0) {
    //     //     System.arraycopy(nums2, 0, nums1, m, n);
    //     //     return;
    //     // }
    //     // if (nums1[m - 1] < nums2[0]) {
    //     //     System.arraycopy(nums2, 0, nums1, m, n);
    //     //     return;
    //     // }
    //     // if (nums1[0] > nums2[n - 1]) {
    //     //     System.arraycopy(nums1, 0, nums1, n, m);
    //     //     System.arraycopy(nums2, 0, nums1, 0, n);
    //     //     return;
    //     // }
    //     System.arraycopy(nums2, 0, nums1, m, n);
    //     // 注意，这里只排序前面m+n个元素
    //     Arrays.sort(nums1, 0, m + n);
    // }

    /**
     * 双指针 / 从后往前
     */
    public static void merge(int[] nums1, int m, int[] nums2, int n) {
        int left = m - 1, right = n - 1, index = m + n - 1;
        while (left >= 0 && right >= 0) {
            // if (nums1[left] >= nums2[right]) {
            //     nums1[index] = nums1[left];
            //     index--;
            //     left--;
            // } else {
            //     nums1[index] = nums2[right];
            //     index--;
            //     right--;
            // }

            // 上面的if判断可用下面表达式替换
            nums1[index--] = nums1[left] >= nums2[right] ? nums1[left--] : nums2[right--];
        }
        System.arraycopy(nums2, 0, nums1, 0, right + 1);
    }

    public static void main(String[] args) {
        int[] nums1 = new int[]{1, 0};
        int[] nums2 = new int[]{2};
        merge(nums1, 1, nums2, 1);
        System.out.println(Arrays.toString(nums1));
    }
}
