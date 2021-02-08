package com.moon.leetcode;

/**
 * 978. 最长湍流子数组<br/>
 * 当 A 的子数组 A[i], A[i+1], ..., A[j] 满足下列条件时，我们称其为湍流子数组：<br/>
 * <p>
 * 若 i <= k < j，当 k 为奇数时， A[k] > A[k+1]，且当 k 为偶数时，A[k] < A[k+1]；<br/>
 * 或 若 i <= k < j，当 k 为偶数时，A[k] > A[k+1] ，且当 k 为奇数时， A[k] < A[k+1]。<br/>
 * 也就是说，如果比较符号在子数组中的每个相邻元素对之间翻转，则该子数组是湍流子数组。<br/>
 * <p>
 * 返回 A 的最大湍流子数组的长度。<br/>
 * <p>
 * 示例 1：<br/>
 * 输入：[9,4,2,10,7,8,8,1,9]<br/>
 * 输出：5<br/>
 * 解释：(A[1] > A[2] < A[3] > A[4] < A[5])<br/>
 * <p>
 * 示例 2：<br/>
 * 输入：[4,8,12,16]<br/>
 * 输出：2<br/>
 * <p>
 * 示例 3：<br/>
 * 输入：[100]<br/>
 * 输出：1<br/>
 * <p>
 * 提示：<br/>
 * 1 <= A.length <= 40000<br/>
 * 0 <= A[i] <= 10^9<br/>
 */
public class No978_maxTurbulenceSize {
    public int maxTurbulenceSize(int[] arr) {
        int len = arr.length;
        if (len < 2) {
            return len;
        }

        int left = 0;
        int right = 1;
        // 为 true 表示 arr[i - 1] < arr[i]
        boolean pre = false;
        int res = 1;
        while (right < len) {
            boolean current = arr[right - 1] < arr[right];
            if (current == pre) {
                left = right - 1;
            }
            if (arr[right - 1] == arr[right]) {
                left = right;
            }
            right++;
            res = Math.max(res, right - left);
            pre = current;
        }
        return res;
    }
}
