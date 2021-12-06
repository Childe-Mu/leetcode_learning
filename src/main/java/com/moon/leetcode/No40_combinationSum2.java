package com.moon.leetcode;


import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.List;

// 40. 组合总和 II
//给定一个数组 candidates 和一个目标数 target ，找出 candidates 中所有可以使数字和为 target 的组合。
//
// candidates 中的每个数字在每个组合中只能使用一次。
//
// 注意：解集不能包含重复的组合。
//
//
//
// 示例 1:
//
//
//输入: candidates = [10,1,2,7,6,1,5], target = 8,
//输出:
//[
//[1,1,6],
//[1,2,5],
//[1,7],
//[2,6]
//]
//
// 示例 2:
//
//
//输入: candidates = [2,5,2,1,2], target = 5,
//输出:
//[
//[1,2,2],
//[5]
//]
//
//
//
// 提示:
//
//
// 1 <= candidates.length <= 100
// 1 <= candidates[i] <= 50
// 1 <= target <= 30
//
// Related Topics 数组 回溯
// 👍 758 👎 0
public class No40_combinationSum2 {
    private List<List<Integer>> res;

    public List<List<Integer>> combinationSum2(int[] c, int t) {
        this.res = new ArrayList<>();
        Arrays.sort(c);
        Deque<Integer> path = new ArrayDeque<>();
        boolean[] used = new boolean[c.length];
        backtrack(c, path, t, used, 0);
        return res;
    }

    private void backtrack(int[] c, Deque<Integer> path, int t, boolean[] used, int i) {
        if (t == 0) {
            res.add(new ArrayList<>(path));
            return;
        }
        for (; i < c.length; i++) {
            if (t < c[i] || used[i] || (i > 0 && c[i] == c[i - 1] && !used[i - 1])) {
                continue;
            }
            path.push(c[i]);
            used[i] = true;
            backtrack(c, path, t - c[i], used, i + 1);
            path.poll();
            used[i] = false;
        }
    }
}
