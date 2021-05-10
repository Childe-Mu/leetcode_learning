package com.moon.leetcode;

// 1855. 下标对中的最大距离
//
//给你两个 非递增 的整数数组 nums1 和 nums2 ，数组下标均 从 0 开始 计数。
//
// 下标对 (i, j) 中 0 <= i < nums1.length 且 0 <= j < nums2.length 。如果该下标对同时满足 i <= j
// 且 nums1[i] <= nums2[j] ，则称之为 有效 下标对，该下标对的 距离 为 j - i 。
//
// 返回所有 有效 下标对 (i, j) 中的 最大距离 。如果不存在有效下标对，返回 0 。
//
// 一个数组 arr ，如果每个 1 <= i < arr.length 均有 arr[i-1] >= arr[i] 成立，那么该数组是一个 非递增 数组。
//
// 示例 1：
//
//输入：nums1 = [55,30,5,4,2], nums2 = [100,20,10,10,5]
//输出：2
//解释：有效下标对是 (0,0), (2,2), (2,3), (2,4), (3,3), (3,4) 和 (4,4) 。
//最大距离是 2 ，对应下标对 (2,4) 。
//
// 示例 2：
//
//输入：nums1 = [2,2,2], nums2 = [10,10,1]
//输出：1
//解释：有效下标对是 (0,0), (0,1) 和 (1,1) 。
//最大距离是 1 ，对应下标对 (0,1) 。
//
// 示例 3：

//输入：nums1 = [30,29,19,5], nums2 = [25,25,25,25,25]
//输出：2
//解释：有效下标对是 (2,2), (2,3), (2,4), (3,3) 和 (3,4) 。
//最大距离是 2 ，对应下标对 (2,4) 。
//
// 示例 4：
//
//输入：nums1 = [5,4], nums2 = [3,2]
//输出：0
//解释：不存在有效下标对，所以返回 0 。
//
// 提示：
//
// 1 <= nums1.length <= 105
// 1 <= nums2.length <= 105
// 1 <= nums1[i], nums2[j] <= 105
// nums1 和 nums2 都是 非递增 数组
//
// Related Topics 贪心算法 双指针 二分查找
// 👍 10 👎 0
public class No1855_maxDistance {
    public static void main(String[] args) {
        System.out.println(new No1855_maxDistance().maxDistance_v1(new int[]{30, 29, 19, 5}, new int[]{25, 25, 25, 25, 25}));
    }

    public int maxDistance_v1(int[] nums1, int[] nums2) {
        int l = 0, r = 0;
        int m = nums1.length;
        int n = nums2.length;
        int max = 0;
        while (l < m && r < n) {
            if (nums1[l] <= nums2[r]) {
                max = r - l;
            } else {
                l++;
            }
            r++;
        }
        return max;
    }

    public int maxDistance_v2(int[] nums1, int[] nums2) {
        int len1 = nums1.length - 1;
        int len2 = nums2.length - 1;
        int max = 0;
        for (int i = len2; i > max; i--) {
            int cur = nums2[i];
            int index = binarySearch(nums1, cur);
            if (i - index > max && cur - nums1[index] >= 0) {
                max = i - index;
            }
        }
        return max;
    }

    // 找到第一个 <= target的数的下标
    private int binarySearch(int[] nums, int target) {
        int l = 0;
        int r = nums.length - 1;
        while (l < r) {
            int mid = (l + r) >>> 1;
            if (nums[mid] > target) {
                l = mid + 1;
            } else {
                r = mid;
            }
        }
        return l;
    }
}
