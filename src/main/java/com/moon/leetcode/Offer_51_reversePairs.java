package com.moon.leetcode;

import java.util.Arrays;

// 剑指 Offer 51. 数组中的逆序对
//
//在数组中的两个数字，如果前面一个数字大于后面的数字，则这两个数字组成一个逆序对。输入一个数组，求出这个数组中的逆序对的总数。
//
// 示例 1:
//
// 输入: [7,5,6,4]
//输出: 5
//
// 限制：
//
// 0 <= 数组长度 <= 50000
// Related Topics 树状数组 线段树 数组 二分查找 分治 有序集合 归并排序
// 👍 452 👎 0
public class Offer_51_reversePairs {
    int[] copy;
    int[] tmp;

    public static void main(String[] args) {
        System.out.println(new Offer_51_reversePairs().reversePairs(new int[]{7, 5, 6, 4}));
    }

    public int reversePairs(int[] nums) {
        int n = nums.length;
        if (n < 2) {
            return 0;
        }
        this.copy = new int[n];
        this.tmp = new int[n];
        System.arraycopy(nums, 0, copy, 0, n);
        int l = 0, r = n - 1;
        int i = mergerSort(0, n - 1);
        System.out.println(Arrays.toString(copy));
        return i;
    }

    private int mergerSort(int l, int r) {
        if (l == r) {
            return 0;
        }
        int m = (r - l) / 2 + l;
        int ll = mergerSort(l, m);
        int rr = mergerSort(m + 1, r);
        if (copy[m] <= copy[m + 1]) {
            return ll + rr;
        }
        int cur = 0;
        int i = l, j = m + 1, k = l;
        while (k <= r) {
            if (i == m + 1) {
                tmp[k++] = copy[j++];
            } else if (j == r + 1) {
                tmp[k++] = copy[i++];
            } else if (copy[i] <= copy[j]) {
                tmp[k++] = copy[i++];
            } else if (copy[i] > copy[j]) {
                tmp[k++] = copy[j++];
                // 既然 copy[i] > copy[j], 那么copy[i...m] > copy[j]
                cur += (m - i + 1);
            }
        }
        System.arraycopy(tmp, l, copy, l, r - l + 1);
        return cur + ll + rr;
    }


}
