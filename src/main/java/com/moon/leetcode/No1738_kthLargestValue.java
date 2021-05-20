package com.moon.leetcode;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

// 1738. 找出第 K 大的异或坐标值
//
//给你一个二维矩阵 matrix 和一个整数 k ，矩阵大小为 m x n 由非负整数组成。
//
// 矩阵中坐标 (a, b) 的 值 可由对所有满足 0 <= i <= a < m 且 0 <= j <= b < n 的元素 matrix[i][j]（下
//标从 0 开始计数）执行异或运算得到。
//
// 请你找出 matrix 的所有坐标中第 k 大的值（k 的值从 1 开始计数）。
//
//
//
// 示例 1：
//
// 输入：matrix = [[5,2],[1,6]], k = 1
//输出：7
//解释：坐标 (0,1) 的值是 5 XOR 2 = 7 ，为最大的值。
//
// 示例 2：
//
// 输入：matrix = [[5,2],[1,6]], k = 2
//输出：5
//解释：坐标 (0,0) 的值是 5 = 5 ，为第 2 大的值。
//
// 示例 3：
//
// 输入：matrix = [[5,2],[1,6]], k = 3
//输出：4
//解释：坐标 (1,0) 的值是 5 XOR 1 = 4 ，为第 3 大的值。
//
// 示例 4：
//
// 输入：matrix = [[5,2],[1,6]], k = 4
//输出：0
//解释：坐标 (1,1) 的值是 5 XOR 2 XOR 1 XOR 6 = 0 ，为第 4 大的值。
//
//
//
// 提示：
//
//
// m == matrix.length
// n == matrix[i].length
// 1 <= m, n <= 1000
// 0 <= matrix[i][j] <= 106
// 1 <= k <= m * n
//
// Related Topics 数组
// 👍 38 👎 0
public class No1738_kthLargestValue {
    public static void main(String[] args) {
        System.out.println(new No1738_kthLargestValue().kthLargestValue_v2(new int[][]{{10, 9, 5}, {2, 0, 4}, {1, 0, 9}, {3, 4, 8}}, 10));
    }

    public int kthLargestValue_v1(int[][] matrix, int k) {
        int m = matrix.length;
        int n = matrix[0].length;
        Queue<Integer> queue = new PriorityQueue<>();
        int[][] f = new int[m + 1][n + 1];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                int xor = f[i + 1][j] ^ f[i][j + 1] ^ f[i][j] ^ matrix[i][j];
                f[i + 1][j + 1] = xor;
                if (queue.size() < k) {
                    queue.offer(xor);
                } else if (!queue.isEmpty() && queue.peek() < xor) {
                    queue.poll();
                    queue.offer(xor);
                }
            }
        }
        return queue.peek();
    }

    public int kthLargestValue_v2(int[][] matrix, int k) {
        int m = matrix.length;
        int n = matrix[0].length;
        List<Integer> result = new ArrayList<>();
        int[][] f = new int[m + 1][n + 1];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                int xor = f[i + 1][j] ^ f[i][j + 1] ^ f[i][j] ^ matrix[i][j];
                f[i + 1][j + 1] = xor;
                result.add(xor);
            }
        }
        int kth = result.size() - k;
        nthElement_v1(result, 0, result.size() - 1, kth);
        return result.get(kth);
    }

    private void nthElement(List<Integer> result, int left, int right, int kth) {
        if (left == right) {
            return;
        }
        int pivot = (int) (left + Math.random() * (right - left + 1));
        Integer v = result.get(pivot);
        swap(result, pivot, right);
        int sepl = left - 1, sepr = left - 1;
        for (int i = left; i <= right; i++) {
            Integer cur = result.get(i);
            if (cur > v) {
                swap(result, ++sepr, right);
                swap(result, ++sepl, sepr);
            } else if (cur.equals(v)) {
                swap(result, ++sepr, i);
            }
        }
        if (sepl < left + kth && left + kth <= sepr) {
            return;
        } else if (left + kth <= sepl) {
            nthElement(result, left, kth, sepl);
        } else {
            nthElement(result, sepr + 1, kth - (sepr - left + 1), right);
        }
    }

    private void nthElement_v1(List<Integer> result, int left, int right, int kth) {
        if (left == right) {
            return;
        }
        int pivot = (int) (left + Math.random() * (right - left + 1));
        swap(result, pivot, left);
        Integer v = result.get(left);
        int l = left, r = right, i = left + 1;
        while (i <= r) {
            Integer cur = result.get(i);
            if (cur > v) {
                swap(result, i, r--);
            } else if (cur < v) {
                swap(result, i++, l++);
            } else {
                i++;
            }
        }
        if (l <= kth && kth <= r) {
            return;
        } else if (kth < l) {
            nthElement_v1(result, left, l - 1, kth);
        } else {
            nthElement_v1(result, r + 1, right, kth);
        }
    }

    private void swap(List<Integer> result, int i, int j) {
        int tmp = result.get(i);
        result.set(i, result.get(j));
        result.set(j, tmp);
    }
}
