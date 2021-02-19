package com.moon.leetcode;

/**
 * 1004. 最大连续1的个数 III
 * <p>
 * 给定一个由若干 0 和 1 组成的数组 A，我们最多可以将 K 个值从 0 变成 1 。
 * <p>
 * 返回仅包含 1 的最长（连续）子数组的长度。
 * <p>
 * 示例 1：
 * <p>
 * 输入：A = [1,1,1,0,0,0,1,1,1,1,0], K = 2
 * 输出：6
 * 解释：
 * [1,1,1,0,0,1,1,1,1,1,1]
 * 粗体数字从 0 翻转到 1，最长的子数组长度为 6。
 * <p>
 * 示例 2：
 * <p>
 * 输入：A = [0,0,1,1,0,0,1,1,1,0,1,1,0,0,0,1,1,1,1], K = 3
 * 输出：10
 * 解释：
 * [0,0,1,1,1,1,1,1,1,1,1,1,0,0,0,1,1,1,1]
 * 粗体数字从 0 翻转到 1，最长的子数组长度为 10。
 * <p>
 * 提示：
 * <p>
 * 1 <= A.length <= 20000
 * 0 <= K <= A.length
 * A[i] 为 0 或 1
 */
public class No1004_longestOnes {
    /**
     * 窗口y一旦撑大，就不缩小，可以避免回溯
     */
    public static int longestOnes(int[] A, int K) {
        int left = 0, right = 0, count = 0;
        while (right < A.length) {
            if (A[right] == 0) {
                if (count >= K) {
                    if (A[left] == 0) {
                        count--;
                    }
                    left++;
                }
                right++;
                count++;
            } else {
                if (count > K) {
                    if (A[left] == 0) {
                        count--;
                    }
                    left++;
                }
                right++;
            }
        }
        return right - left;
    }

    /**
     * 不回溯，v1升级版
     */
    public static int longestOnes_v2(int[] A, int K) {
        int left = 0, right = 0, lSum = 0, rSum = 0;
        while (right < A.length) {
            rSum = rSum + 1 - A[right];
            if (rSum - lSum > K) {
                lSum = lSum + 1 - A[left];
                left++;
            }
            right++;
        }
        return right - left;
    }

    /**
     * 回溯
     */
    public int longestOnes_v3(int[] A, int K) {
        int n = A.length;
        int left = 0, lsum = 0, rsum = 0;
        int ans = 0;
        for (int right = 0; right < n; ++right) {
            rsum += 1 - A[right];
            while (lsum < rsum - K) {
                lsum += 1 - A[left];
                ++left;
            }
            ans = Math.max(ans, right - left + 1);
        }
        return ans;
    }

    /**
     * 二分查找
     */
    public int longestOnes_v4(int[] A, int K) {
        int n = A.length;
        int[] P = new int[n + 1];
        for (int i = 1; i <= n; ++i) {
            P[i] = P[i - 1] + (1 - A[i - 1]);
        }

        int ans = 0;
        for (int right = 0; right < n; ++right) {
            int left = binarySearch(P, P[right + 1] - K);
            ans = Math.max(ans, right - left + 1);
        }
        return ans;
    }

    public int binarySearch(int[] P, int target) {
        int low = 0, high = P.length - 1;
        while (low < high) {
            int mid = (high - low) / 2 + low;
            if (P[mid] < target) {
                low = mid + 1;
            } else {
                high = mid;
            }
        }
        return low;
    }

    public static void main(String[] args) {
        System.out.println(longestOnes_v2(new int[]{0,0,1,1,0,0,1,1,1,0,1,1,0,0,0,1,1,1,1}, 3));
    }
}
