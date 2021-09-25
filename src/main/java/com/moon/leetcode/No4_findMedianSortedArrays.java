package com.moon.leetcode;

// 4. 寻找两个正序数组的中位数
//
//给定两个大小分别为 m 和 n 的正序（从小到大）数组 nums1 和 nums2。请你找出并返回这两个正序数组的 中位数 。
//
// 示例 1：
//
//输入：nums1 = [1,3], nums2 = [2]
//输出：2.00000
//解释：合并数组 = [1,2,3] ，中位数 2
//
// 示例 2：
//
//输入：nums1 = [1,2], nums2 = [3,4]
//输出：2.50000
//解释：合并数组 = [1,2,3,4] ，中位数 (2 + 3) / 2 = 2.5
//
// 示例 3：
//
//输入：nums1 = [0,0], nums2 = [0,0]
//输出：0.00000
//
// 示例 4：
//
//输入：nums1 = [], nums2 = [1]
//输出：1.00000
//
// 示例 5：
//
//输入：nums1 = [2], nums2 = []
//输出：2.00000
//
// 提示：
//
// nums1.length == m
// nums2.length == n
// 0 <= m <= 1000
// 0 <= n <= 1000
// 1 <= m + n <= 2000
// -106 <= nums1[i], nums2[i] <= 106
//
// 进阶：你能设计一个时间复杂度为 O(log (m+n)) 的算法解决此问题吗？
// Related Topics 数组 二分查找 分治算法
// 👍 3975 👎 0
public class No4_findMedianSortedArrays {
    public static void main(String[] args) {
        System.out.println(new No4_findMedianSortedArrays().findMedianSortedArrays_v2(new int[]{2}, new int[]{1, 3, 4}));
    }

    /**
     * 二分查找，寻找第k小的数字
     */
    public double findMedianSortedArrays_v1(int[] nums1, int[] nums2) {
        int m = nums1.length;
        int n = nums2.length;
        int len = m + n;
        boolean status = (len & 1) == 0;
        int k = (len + 1) / 2;
        int offset = k / 2 - 1;
        int l1 = 0;
        int l2 = 0;
        while (k > 1) {
            if (l1 <= m - 1 && l2 <= n - 1) {
                if (l1 + offset >= m) {
                    offset = m - 1 - l1;
                } else if (l2 + offset >= n) {
                    offset = n - 1 - l2;
                }
                if (nums1[l1 + offset] <= nums2[l2 + offset]) {
                    l1 = l1 + offset + 1;
                } else {
                    l2 = l2 + offset + 1;
                }
            } else {
                if (l1 < m) {
                    return status ? (double) (nums1[l1 + k] + nums1[l1 + k - 1]) / 2 : nums1[l1 + k - 1];
                } else {
                    return status ? (double) (nums2[l2 + k] + nums2[l2 + k - 1]) / 2 : nums2[l2 + k - 1];
                }
            }
            k = k - offset - 1;
            offset = k / 2 - 1;
        }
        if (status) {
            if (l1 >= m) {
                return (double) (nums2[l2] + nums2[l2 + 1]) / 2;
            } else if (l2 >= n) {
                return (double) (nums1[l1] + nums1[l1 + 1]) / 2;
            } else {
                if (nums1[l1] == nums2[l2]) {
                    return (double) (nums2[l2] + nums1[l1]) / 2;
                } else if (nums1[l1] > nums2[l2]) {
                    if (l2 + 1 < n) {
                        return (double) (nums2[l2] + Math.min(nums1[l1], nums2[l2 + 1])) / 2;
                    } else {
                        return (double) (nums2[l2] + nums1[l1]) / 2;
                    }
                } else {
                    if (l1 + 1 < m) {
                        return (double) (nums1[l1] + Math.min(nums1[l1 + 1], nums2[l2])) / 2;
                    } else {
                        return (double) (nums1[l1] + nums2[l2]) / 2;
                    }
                }
            }
        } else {
            if (l1 >= m) {
                return nums2[l2];
            } else if (l2 >= n) {
                return nums1[l1];
            } else {
                return Math.min(nums1[l1], nums2[l2]);
            }
        }
    }

    public double findMedianSortedArrays_v2(int[] nums1, int[] nums2) {
        int m = nums1.length, n = nums2.length;
        int len = m + n;
        if ((len & 1) == 1) {
            int k = (len + 1) / 2;
            return getKth(nums1, nums2, k);
        } else {
            int k1 = (len + 1) / 2;
            int k2 = (len + 2) / 2;
            return (getKth(nums1, nums2, k1) + getKth(nums1, nums2, k2)) / 2;
        }
    }

    /**
     * 主要思路：要找到第 k (k>1) 小的元素，那么就取 pivot1 = nums1[k/2-1] 和 pivot2 = nums2[k/2-1] 进行比较
     * 这里的 "/" 表示整除
     * nums1 中小于等于 pivot1 的元素有 nums1[0 .. k/2-2] 共计 k/2-1 个
     * nums2 中小于等于 pivot2 的元素有 nums2[0 .. k/2-2] 共计 k/2-1 个
     * 取 pivot = min(pivot1, pivot2)，两个数组中小于等于 pivot 的元素共计不会超过 (k/2-1) + (k/2-1) <= k-2 个
     * 这样 pivot 本身最大也只能是第 k-1 小的元素
     * 如果 pivot = pivot1，那么 nums1[0 .. k/2-1] 都不可能是第 k 小的元素。把这些元素全部 "删除"，剩下的作为新的 nums1 数组
     * 如果 pivot = pivot2，那么 nums2[0 .. k/2-1] 都不可能是第 k 小的元素。把这些元素全部 "删除"，剩下的作为新的 nums2 数组
     * 由于我们 "删除" 了一些元素（这些元素都比第 k 小的元素要小），因此需要修改 k 的值，减去删除的数的个数
     */
    private double getKth(int[] nums1, int[] nums2, int k) {
        int m = nums1.length, n = nums2.length;
        int i = 0, j = 0;
        while (true) {
            if (i == m) {
                return nums2[j + k - 1];
            }
            if (j == n) {
                return nums1[i + k - 1];
            }
            if (k == 1) {
                return Math.min(nums1[i], nums2[j]);
            }
            int h = k / 2;
            int i1 = Math.min(i + h, m) - 1;
            int j1 = Math.min(j + h, n) - 1;
            if (nums1[i1] <= nums2[j1]) {
                k = k - (i1 - i + 1);
                i = i1 + 1;
            } else {
                k = k - (j1 - j + 1);
                j = j1 + 1;
            }
        }
    }

    public double findMedianSortedArrays_v3(int[] nums1, int[] nums2) {
        int m = nums1.length;
        int n = nums2.length;
        int[] nums = new int[m + n];
        int i = 0, j = 0;
        for (int k = 0; k < m + n; k++) {
            int t1 = i < m ? nums1[i] : Integer.MAX_VALUE;
            int t2 = j < n ? nums2[j] : Integer.MAX_VALUE;
            if (t1 <= t2) {
                nums[k] = t1;
                i++;
            } else {
                nums[k] = t2;
                j++;
            }
        }
        int l = m + n;
        return (l & 1) == 1 ? nums[l >> 1] : (nums[l >> 1] + nums[(l >> 1) - 1]) / 2.0;
    }
}
