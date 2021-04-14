package com.moon.leetcode;

import java.util.ArrayDeque;
import java.util.Deque;

// 42. 接雨水
//
//给定 n 个非负整数表示每个宽度为 1 的柱子的高度图，计算按此排列的柱子，下雨之后能接多少雨水。
//
// 示例 1：
//
//输入：height = [0,1,0,2,1,0,1,3,2,1,2,1]
//输出：6
//解释：上面是由数组 [0,1,0,2,1,0,1,3,2,1,2,1] 表示的高度图，在这种情况下，可以接 6 个单位的雨水（蓝色部分表示雨水）。
//
// 示例 2：
//
//输入：height = [4,2,0,3,2,5]
//输出：9
//
// 提示：
//
// n == height.length
// 0 <= n <= 3 * 104
// 0 <= height[i] <= 105
//
// Related Topics 栈 数组 双指针 动态规划
// 👍 2265 👎 0
public class No42_trap {
    public static void main(String[] args) {
        System.out.println(new No42_trap().trap_v3(new int[]{5, 5, 1, 7, 1, 1, 5, 2, 7, 6}));
    }

    public int trap_v1(int[] height) {
        int n = height.length;
        if (n == 0) {
            return 0;
        }
        int res = 0;
        int[] left = new int[n];
        int[] right = new int[n];
        left[0] = height[0];
        right[n - 1] = height[n - 1];
        for (int i = 1; i < n; i++) {
            left[i] = Math.max(left[i - 1], height[i]);
        }
        for (int i = n - 2; i >= 0; i--) {
            right[i] = Math.max(right[i + 1], height[i]);
        }
        for (int i = 0; i < n; i++) {
            res = res + Math.min(left[i], right[i]) - height[i];
        }
        return res;
    }

    public int trap_v2(int[] height) {
        Deque<Integer> stack = new ArrayDeque<>();
        stack.push(0);
        int n = height.length;
        int res = 0;
        for (int i = 1; i < n; i++) {
            if (stack.isEmpty()) {
                stack.push(i);
            } else {
                while (!stack.isEmpty() && height[i] > height[stack.peek()]) {
                    int top = stack.pop();
                    if (!stack.isEmpty() && stack.peek() >= 0) {
                        res = res + (Math.min(height[i], height[stack.peek()]) - height[top]) * (i - stack.peek() - 1);
                    }
                }
                stack.push(i);
            }
        }
        return res;
    }

    public int trap_v3(int[] height) {
        int n = height.length;
        if (n == 0) {
            return 0;
        }
        int res = 0;
        int l = 0, r = n - 1;
        int left = height[l], right = height[r];
        while (l < r) {
            left = Math.max(left, height[l]);
            right = Math.max(right, height[r]);

            if (left < right) {
                res = res + left - height[l];
                l++;
            } else {
                res = res + right - height[r];
                r--;
            }
        }
        return res;
    }
}
