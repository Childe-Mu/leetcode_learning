package com.moon.leetcode;

import java.util.HashMap;
import java.util.Map;

// 494. 目标和
//
//给你一个整数数组 nums 和一个整数 target 。
//
// 向数组中的每个整数前添加 '+' 或 '-' ，然后串联起所有整数，可以构造一个 表达式 ：
//
// 例如，nums = [2, 1] ，可以在 2 之前添加 '+' ，在 1 之前添加 '-' ，然后串联起来得到表达式 "+2-1" 。
//
// 返回可以通过上述方法构造的、运算结果等于 target 的不同 表达式 的数目。
//
// 示例 1：
//
//输入：nums = [1,1,1,1,1], target = 3
//输出：5
//解释：一共有 5 种方法让最终目标和为 3 。
//-1 + 1 + 1 + 1 + 1 = 3
//+1 - 1 + 1 + 1 + 1 = 3
//+1 + 1 - 1 + 1 + 1 = 3
//+1 + 1 + 1 - 1 + 1 = 3
//+1 + 1 + 1 + 1 - 1 = 3
//
// 示例 2：
//
//输入：nums = [1], target = 1
//输出：1
//
// 提示：
//
// 1 <= nums.length <= 20
// 0 <= nums[i] <= 1000
// 0 <= sum(nums[i]) <= 1000
// -1000 <= target <= 100
//
// Related Topics 深度优先搜索 动态规划
// 👍 723 👎 0
public class No494_findTargetSumWays {
    int n;
    int[] nums;
    int target;
    Map<String, Integer> map;

    public static void main(String[] args) {
        System.out.println(new No494_findTargetSumWays().findTargetSumWays_v1(new int[]{1, 1, 1, 1, 1}, 3));
    }

    public int findTargetSumWays_v1(int[] nums, int target) {
        int n = nums.length;
        int sum = 0;
        for (int num : nums) {
            sum += num;
        }
        int t = (sum - target) / 2;
        if (t < 0) {
            return 0;
        }
        int[][] f = new int[n + 1][t + 1];
        f[0][0] = 1;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j <= t; j++) {
                int k = j - nums[i];
                f[i + 1][j] = f[i][j] + (k < 0 ? 0 : f[i][k]);
            }
        }
        return f[n][t];
    }

    public int findTargetSumWays_v2(int[] nums, int t) {
        int n = nums.length;
        int s = 0;
        for (int i : nums) {
            s += Math.abs(i);
        }
        if (t > s || t < -s) {
            return 0;
        }

        int[][] f = new int[n + 1][2 * s + 1];
        f[0][s] = 1;
        for (int i = 1; i <= n; i++) {
            int x = nums[i - 1];
            for (int j = -s; j <= s; j++) {
                if ((j - x) + s >= 0) {
                    f[i][j + s] += f[i - 1][(j - x) + s];
                }
                if ((j + x) + s <= 2 * s) {
                    f[i][j + s] += f[i - 1][(j + x) + s];
                }
            }
        }
        return f[n][t + s];
    }

    public int findTargetSumWays_v3(int[] nums, int target) {
        this.n = nums.length;
        this.nums = nums;
        this.target = target;
        map = new HashMap<>();

        return dfs(0, 0);
    }

    private int dfs(int i, int sum) {
        String key = i + "_" + sum;
        if (map.containsKey(key)) {
            return map.get(key);
        }
        if (i == n) {
            map.put(key, sum == target ? 1 : 0);
            return map.get(key);
        }
        int value = dfs(i + 1, sum + nums[i]) + dfs(i + 1, sum - nums[i]);
        map.put(key, value);
        return value;
    }
}
