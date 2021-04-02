package com.moon.leetcode;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * 面试题 17.21. 直方图的水量
 * <p>
 * 给定一个直方图(也称柱状图)，假设有人从上面源源不断地倒水，最后直方图能存多少水量?直方图的宽度为 1。
 * <p>
 * 上面是由数组 [0,1,0,2,1,0,1,3,2,1,2,1] 表示的直方图，在这种情况下，可以接 6 个单位的水（蓝色部分表示水）。 感谢 Marco
 * s 贡献此图。
 * <p>
 * 示例:
 * <p>
 * 输入: [0,1,0,2,1,0,1,3,2,1,2,1]
 * 输出: 6
 */
public class Interview_17_21_trap {

    public static void main(String[] args) {
        System.out.println(new Interview_17_21_trap().trap_v2(new int[]{0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1}));
    }

    public int trap_v1(int[] height) {
        int len = height.length;
        if (len == 0) {
            return 0;
        }
        int ans = 0;
        int[] leftMax = new int[len];
        int[] rightMax = new int[len];
        leftMax[0] = height[0];
        for (int i = 1; i < len; i++) {
            leftMax[i] = Math.max(leftMax[i - 1], height[i]);
        }
        rightMax[len - 1] = height[len - 1];
        for (int i = len - 2; i >= 0; i--) {
            rightMax[i] = Math.max(rightMax[i + 1], height[i]);
        }
        for (int i = 0; i < len; i++) {
            ans += Math.min(leftMax[i], rightMax[i]) - height[i];
        }
        return ans;
    }

    public int trap_v2(int[] height) {
        int len = height.length;
        if (len == 0) {
            return 0;
        }
        Deque<Integer> stack = new ArrayDeque<>();
        stack.push(0);
        int ans = 0;
        for (int i = 1; i < len; i++) {
            while (!stack.isEmpty() && height[i] > height[stack.peek()]) {
                int top = stack.pop();
                if (stack.isEmpty()) {
                    break;
                }
                ans += (i - stack.peek() - 1) * (Math.min(height[i], height[stack.peek()]) - height[top]);
            }
            stack.push(i);
        }
        return ans;
    }

    public int trap_v3(int[] height) {
        int ans = 0;
        int left = 0, right = height.length - 1;
        int leftMax = 0, rightMax = 0;
        while (left < right) {
            leftMax = Math.max(leftMax, height[left]);
            rightMax = Math.max(rightMax, height[right]);
            if (height[left] < height[right]) {
                ans += leftMax - height[left];
                ++left;
            } else {
                ans += rightMax - height[right];
                --right;
            }
        }
        return ans;
    }
}
