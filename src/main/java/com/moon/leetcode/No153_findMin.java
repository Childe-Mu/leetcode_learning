package com.moon.leetcode;

// 153. 寻找旋转排序数组中的最小值
//
//已知一个长度为 n 的数组，预先按照升序排列，经由 1 到 n 次 旋转 后，得到输入数组。例如，原数组 nums = [0,1,2,4,5,6,7] 在变
//化后可能得到：
//
// 若旋转 4 次，则可以得到 [4,5,6,7,0,1,2]
// 若旋转 7 次，则可以得到 [0,1,2,4,5,6,7]
//
// 注意，数组 [a[0], a[1], a[2], ..., a[n-1]] 旋转一次 的结果为数组 [a[n-1], a[0], a[1], a[2],
//..., a[n-2]] 。
//
// 给你一个元素值 互不相同 的数组 nums ，它原来是一个升序排列的数组，并按上述情形进行了多次旋转。请你找出并返回数组中的 最小元素 。
//
// 示例 1：
//
//输入：nums = [3,4,5,1,2]
//输出：1
//解释：原数组为 [1,2,3,4,5] ，旋转 3 次得到输入数组。
//
// 示例 2：
//
//输入：nums = [4,5,6,7,0,1,2]
//输出：0
//解释：原数组为 [0,1,2,4,5,6,7] ，旋转 4 次得到输入数组。
//
// 示例 3：
//
//输入：nums = [11,13,15,17]
//输出：11
//解释：原数组为 [11,13,15,17] ，旋转 4 次得到输入数组。
//
// 提示：
//
// n == nums.length
// 1 <= n <= 5000
// -5000 <= nums[i] <= 5000
// nums 中的所有整数 互不相同
// nums 原来是一个升序排序的数组，并进行了 1 至 n 次旋转
//
// Related Topics 数组 二分查找
// 👍 439 👎 0
public class No153_findMin {
    public static void main(String[] args) {
        System.out.println(new No153_findMin().findMin_v1(new int[]{6, 7, 0, 1, 2, 3, 4}));
    }

    public int findMin_v1(int[] nums) {
        int l = 0, r = nums.length - 1, min = 5001;
        while (l <= r) {
            int m = (r - l) / 2 + l;
            int left = nums[l];
            int mid = nums[m];
            if (left <= mid) {
                min = Math.min(left, min);
                l = m + 1;
            } else {
                min = Math.min(mid, min);
                r = m - 1;
            }
        }
        return min;
    }

    public int findMin_v2(int[] nums) {
        int l = 0;
        int r = nums.length - 1;
        while (l < r) {
            int m = l + (r - l) / 2;
            if (nums[m] < nums[r]) {
                r = m;
            } else {
                l = m + 1;
            }
        }
        return nums[l];
    }
}
