package com.moon.leetcode;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.List;

/**
 * 90. 子集 II
 * <p>
 * 给你一个整数数组 nums ，其中可能包含重复元素，请你返回该数组所有可能的子集（幂集）。
 * <p>
 * 解集 不能 包含重复的子集。返回的解集中，子集可以按 任意顺序 排列。
 * <p>
 * 示例 1：
 * <p>
 * 输入：nums = [1,2,2]
 * 输出：[[],[1],[1,2],[1,2,2],[2],[2,2]]
 * <p>
 * 示例 2：
 * <p>
 * 输入：nums = [0]
 * 输出：[[],[0]]
 * <p>
 * 提示：
 * <p>
 * 1 <= nums.length <= 10
 * -10 <= nums[i] <= 10
 */
public class No90_subsetsWithDup {
    public static void main(String[] args) {
        System.out.println(new No90_subsetsWithDup().subsetsWithDup_v1(new int[]{1, 2, 2}));
    }

    public List<List<Integer>> subsetsWithDup_v1(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        Deque<Integer> path = new ArrayDeque<>();
        Arrays.sort(nums);
        backtrack(nums, 0, true, path, res);
        return res;
    }

    private void backtrack(int[] nums, int depth, boolean pickPrev, Deque<Integer> path, List<List<Integer>> res) {
        if (depth == nums.length) {
            res.add(new ArrayList<>(path));
            return;
        }
        path.addLast(nums[depth]);
        backtrack(nums, depth + 1, true, path, res);
        path.removeLast();
        if (pickPrev && depth > 0 && nums[depth] == nums[depth - 1]) {
            return;
        }
        backtrack(nums, depth + 1, false, path, res);
    }
}
