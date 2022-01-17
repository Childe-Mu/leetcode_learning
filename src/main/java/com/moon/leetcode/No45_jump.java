package com.moon.leetcode;

import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

// 45. 跳跃游戏 II
//给你一个非负整数数组 nums ，你最初位于数组的第一个位置。
//
// 数组中的每个元素代表你在该位置可以跳跃的最大长度。
//
// 你的目标是使用最少的跳跃次数到达数组的最后一个位置。
//
// 假设你总是可以到达数组的最后一个位置。
//
//
//
// 示例 1:
//
//
//输入: nums = [2,3,1,1,4]
//输出: 2
//解释: 跳到最后一个位置的最小跳跃数是 2。
//     从下标为 0 跳到下标为 1 的位置，跳 1 步，然后跳 3 步到达数组的最后一个位置。
//
//
// 示例 2:
//
//
//输入: nums = [2,3,0,1,4]
//输出: 2
//
//
//
//
// 提示:
//
//
// 1 <= nums.length <= 104
// 0 <= nums[i] <= 1000
//
// Related Topics 贪心 数组 动态规划
// 👍 1301 👎 0
public class No45_jump {
    public static void main(String[] args) {
        System.out.println(new No45_jump().jump_v4(new int[]{2, 3, 1, 1, 4}));
    }

    public int jump_v1(int[] nums) {
        int n = nums.length;
        int ans = 0;
        int pos = n - 1;
        while (pos > 0) {
            for (int i = 0; i < pos; i++) {
                if (nums[i] + i >= pos) {
                    pos = i;
                    ans++;
                    break;
                }
            }
        }
        return ans;
    }

    public int jump_v2(int[] nums) {
        int n = nums.length;
        int ans = 0;
        int end = 0;
        int pos = 0;
        for (int i = 0; i < n - 1; i++) {
            pos = Math.max(pos, nums[i] + i);
            if (i == end) {
                end = pos;
                ans++;
            }
        }
        return ans;
    }

    public int jump_v3(int[] nums) {
        int n = nums.length;
        int[] f = new int[n];
        Arrays.fill(f, 2 * n);
        f[0] = 0;
        for (int i = 0; i < n; i++) {
            int max = nums[i] + i;
            for (int j = i + 1; j <= max; j++) {
                if (j >= n) {
                    return f[n - 1];
                }
                f[j] = Math.min(f[i] + 1, f[j]);
            }
        }
        return f[n - 1];
    }

    public int jump_v4(int[] nums) {
        int n = nums.length;
        if (n <= 1) {
            return 0;
        }
        Set<Integer> set = new HashSet<>();
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(0);
        set.add(0);
        int ans = 0;
        while (!queue.isEmpty()) {
            ans++;
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                int k = queue.poll();
                int max = nums[k] + k;
                if (max >= n - 1) {
                    return ans;
                }
                for (int j = k + 1; j <= max; j++) {
                    if (!set.contains(j)) {
                        queue.offer(j);
                        set.add(j);
                    }
                }
            }
        }
        return -1;
    }


}
