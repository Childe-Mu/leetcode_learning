package com.moon.leetcode;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

// 368. 最大整除子集
//
//给你一个由 无重复 正整数组成的集合 nums ，请你找出并返回其中最大的整除子集 answer ，子集中每一元素对 (answer[i], answer[
//j]) 都应当满足：
//
// answer[i] % answer[j] == 0 ，或
// answer[j] % answer[i] == 0
//
// 如果存在多个有效解子集，返回其中任何一个均可。
//
// 示例 1：
//
//输入：nums = [1,2,3]
//输出：[1,2]
//解释：[1,3] 也会被视为正确答案。
//
// 示例 2：
//
//输入：nums = [1,2,4,8]
//输出：[1,2,4,8]
//
// 提示：
// 1 <= nums.length <= 1000
// 1 <= nums[i] <= 2 * 109
// nums 中的所有整数 互不相同
//
// Related Topics 数学 动态规划
// 👍 239 👎 0
public class No368_largestDivisibleSubset {

    private final LinkedList<Integer> res = new LinkedList<>();

    public static void main(String[] args) {
        System.out.println(new No368_largestDivisibleSubset().largestDivisibleSubset_v1(new int[]{3, 4, 16, 8}));
    }

    public List<Integer> largestDivisibleSubset_v1(int[] nums) {
        Arrays.sort(nums);
        LinkedList<Integer> path = new LinkedList<>();
        backtrack(nums, 0, path);
        return res;
    }

    private void backtrack(int[] nums, int i, LinkedList<Integer> path) {
        if (i == nums.length) {
            if (res.size() < path.size()) {
                res.clear();
                res.addAll(path);
            }
            return;
        }
        if (path.isEmpty() || nums[i] % path.getLast() == 0) {
            path.add(nums[i]);
            backtrack(nums, i + 1, path);
            path.removeLast();
            backtrack(nums, i + 1, path);
        } else {
            backtrack(nums, i + 1, path);
        }
    }

    public List<Integer> largestDivisibleSubset_v2(int[] nums) {
        Arrays.sort(nums);
        int n = nums.length;
        int[] f = new int[n];
        int[] g = new int[n];
        for (int i = 0; i < n; i++) {
            f[i] = 1;
            g[i] = -1;
            if (i > 0) {
                int max = 0;
                for (int j = i - 1; j >= 0; j--) {
                    if (nums[i] % nums[j] == 0 && f[j] > max) {
                        f[i] = f[j] + 1;
                        g[i] = j;
                        max = f[j];
                    }
                }
            }
        }
        int index = 0;
        int max = 0;
        for (int i = 0; i < n; i++) {
            if (max < f[i]) {
                max = f[i];
                index = i;
            }
        }
        LinkedList<Integer> res = new LinkedList<>();
        for (int i = f[index]; i > 0; i--) {
            res.addFirst(nums[index]);
            index = g[index];

        }
        return res;
    }
}
