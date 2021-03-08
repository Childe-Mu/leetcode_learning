package com.moon.leetcode;

/**
 * 718.最长重复子数组
 * <p>
 * 给两个整数数组 A 和 B ，返回两个数组中公共的、长度最长的子数组的长度。
 * <p>
 * 示例：
 * <p>
 * 输入：
 * A: [1,2,3,2,1]
 * B: [3,2,1,4,7]
 * 输出：3
 * <p>
 * 解释：
 * 长度最长的公共子数组是 [3, 2, 1] 。
 * <p>
 * 提示：
 * <p>
 * 1 <= len(A), len(B) <= 1000
 * 0 <= A[i], B[i] < 100
 */
public class No718_findLength {
    public static int findLength(int[] A, int[] B) {
        int n = A.length;
        int m = B.length;
        // f[i][j]表示A中0-i,B中0-j的，以A[i]，B[j]结尾的，公共的、长度最长的子数组的长度，其中i<n,j<m
        // 有，如果A[i] == B[j]，则f[i][j]=f[i-1][j-1]+1，反之，则为0
        int[][] f = new int[n + 1][m + 1];
        int ans = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                f[i + 1][j + 1] = A[i] == B[j] ? f[i][j] + 1 : 0;
                ans = Math.max(ans, f[i + 1][j + 1]);
            }
        }
        return ans;
    }

    public int findLength_v2(int[] A, int[] B) {
        int n = A.length, m = B.length;
        int ret = 0;
        for (int i = 0; i < n; i++) {
            int len = Math.min(m, n - i);
            int maxlen = maxLength(A, B, i, 0, len);
            ret = Math.max(ret, maxlen);
        }
        for (int i = 0; i < m; i++) {
            int len = Math.min(n, m - i);
            int maxlen = maxLength(A, B, 0, i, len);
            ret = Math.max(ret, maxlen);
        }
        return ret;
    }

    public int maxLength(int[] A, int[] B, int addA, int addB, int len) {
        int ret = 0, k = 0;
        for (int i = 0; i < len; i++) {
            if (A[addA + i] == B[addB + i]) {
                k++;
            } else {
                k = 0;
            }
            ret = Math.max(ret, k);
        }
        return ret;
    }

    public static void main(String[] args) {
        System.out.println(findLength(new int[]{3, 2, 1}, new int[]{3, 2, 1}));
    }
}
