package com.moon.leetcode;

import java.util.Arrays;

// 611. 有效三角形的个数
//
//给定一个包含非负整数的数组，你的任务是统计其中可以组成三角形三条边的三元组个数。
//
// 示例 1:
//
//
//输入: [2,2,3,4]
//输出: 3
//解释:
//有效的组合是:
//2,3,4 (使用第一个 2)
//2,3,4 (使用第二个 2)
//2,2,3
//
//
// 注意:
//
//
// 数组长度不超过1000。
// 数组里整数的范围为 [0, 1000]。
//
// Related Topics 贪心 数组 双指针 二分查找 排序
// 👍 251 👎 0
public class No611_triangleNumber {
    public static void main(String[] args) {
        System.out.println(new No611_triangleNumber().triangleNumber_v1(new int[]{2, 2, 3, 4}));
    }

    public int triangleNumber(int[] nums) {
        int n = nums.length;
        Arrays.sort(nums);
        int ans = 0;
        for (int i = 0; i < n - 2; i++) {
            for (int j = i + 1; j < n - 1; j++) {
                int a = nums[i];
                int b = nums[j];
                int r = binarySearch(nums, j + 1, n - 1, a + b - 1);
                ans += (r - j);
            }
        }
        return ans;
    }

    private int binarySearch(int[] nums, int l, int r, int t) {
        while (l < r) {
            int m = (l + r) / 2;
            if (nums[m] <= t) {
                l = m + 1;
            } else {
                r = m;
            }
        }
        return l;
    }

    public int triangleNumber_v1(int[] nums) {
        int n = nums.length;
        Arrays.sort(nums);
        int index = 0;
        while (nums[index] == 0) {
            index++;
        }
        int ans = 0;
        for (int i = index; i < n; ++i) {
            for (int j = i + 1; j < n; ++j) {
                int a = nums[i];
                int b = nums[j];
                int l = j + 1, r = n - 1;
                int k = search(nums, l, r, a + b);
                ans += k - j;
            }
        }
        return ans;
    }

    private int search(int[] nums, int l, int r, int t) {
        while (l <= r) {
            int mid = (l + r) / 2;
            if (nums[mid] < t) {
                l = mid + 1;
            } else {
                r = mid - 1;
            }
        }
        return l;
    }
}
