package com.moon.leetcode;

import java.util.Arrays;
import java.util.TreeSet;

// 1818. 绝对差值和
//
//给你两个正整数数组 nums1 和 nums2 ，数组的长度都是 n 。
//
// 数组 nums1 和 nums2 的 绝对差值和 定义为所有 |nums1[i] - nums2[i]|（0 <= i < n）的 总和（下标从 0 开始
//）。
//
// 你可以选用 nums1 中的 任意一个 元素来替换 nums1 中的 至多 一个元素，以 最小化 绝对差值和。
//
// 在替换数组 nums1 中最多一个元素 之后 ，返回最小绝对差值和。因为答案可能很大，所以需要对 109 + 7 取余 后返回。
//
// |x| 定义为：
//
//
// 如果 x >= 0 ，值为 x ，或者
// 如果 x <= 0 ，值为 -x
//
// 示例 1：
//
//输入：nums1 = [1,7,5], nums2 = [2,3,5]
//输出：3
//解释：有两种可能的最优方案：
//- 将第二个元素替换为第一个元素：[1,7,5] => [1,1,5] ，或者
//- 将第二个元素替换为第三个元素：[1,7,5] => [1,5,5]
//两种方案的绝对差值和都是 |1-2| + (|1-3| 或者 |5-3|) + |5-5| = 3
//
// 示例 2：
//
//输入：nums1 = [2,4,6,8,10], nums2 = [2,4,6,8,10]
//输出：0
//解释：nums1 和 nums2 相等，所以不用替换元素。绝对差值和为 0
//
// 示例 3：
//
//输入：nums1 = [1,10,4,4,2,7], nums2 = [9,3,5,1,7,4]
//输出：20
//解释：将第一个元素替换为第二个元素：[1,10,4,4,2,7] => [10,10,4,4,2,7]
//绝对差值和为 |10-9| + |10-3| + |4-5| + |4-1| + |2-7| + |7-4| = 20
//
// 提示：
//
// n == nums1.length
// n == nums2.length
// 1 <= n <= 105
// 1 <= nums1[i], nums2[i] <= 105
//
// Related Topics 贪心 数组 二分查找 有序集合
// 👍 74 👎 0
public class No1818_minAbsoluteSumDiff {
    public static void main(String[] args) {
        System.out.println(new No1818_minAbsoluteSumDiff().minAbsoluteSumDiff_v2(new int[]{1, 7, 5}, new int[]{2, 3, 5}));
    }

    public int minAbsoluteSumDiff_v1(int[] nums1, int[] nums2) {
        int mod = (int) 1e9 + 7;
        long sum = 0;
        TreeSet<Integer> tree = new TreeSet<>();
        int n = nums1.length;
        for (int i = 0; i < n; i++) {
            sum += Math.abs(nums1[i] - nums2[i]);
            tree.add(nums1[i]);
        }
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < n; i++) {
            int num = nums2[i];
            int old = Math.abs(nums1[i] - nums2[i]);
            Integer ceil = tree.ceiling(num);
            if (ceil != null) {
                int t = Math.abs(nums2[i] - ceil);
                if (old > t && old - t > max) {
                    max = old - t;
                }
            }
            Integer floor = tree.floor(num);
            if (floor != null) {
                int t = Math.abs(nums2[i] - floor);
                if (old > t && old - t > max) {
                    max = old - t;
                }
            }
        }
        sum = max == Integer.MIN_VALUE ? sum : sum - max;
        return (int) (sum % mod);
    }

    public int minAbsoluteSumDiff_v2(int[] nums1, int[] nums2) {
        int mod = (int) 1e9 + 7;
        int n = nums1.length;
        int[] copy = nums1.clone();
        Arrays.sort(copy);
        long sum = 0, max = 0;
        for (int i = 0; i < n; i++) {
            int a = nums1[i];
            int b = nums2[i];
            if (a == b) {
                continue;
            }
            int x = Math.abs(a - b);
            sum += x;
            int l = 0, r = n - 1;
            while (l < r) {
                int m = (l + r + 1) / 2;
                if (copy[m] <= b) {
                    l = m;
                } else {
                    r = m - 1;
                }
            }
            int nd = Math.abs(copy[r] - b);
            if (r + 1 < n) {
                nd = Math.min(nd, Math.abs(copy[r + 1]) - b);
            }
            if (nd < x) {
                max = Math.max(max, x - nd);
            }
        }
        return (int) ((sum - max) % mod);
    }
}
