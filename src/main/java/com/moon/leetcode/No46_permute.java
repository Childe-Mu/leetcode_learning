package com.moon.leetcode;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.List;

/**
 * 46. 全排列
 * <p>
 * 给定一个 没有重复 数字的序列，返回其所有可能的全排列。
 * <p>
 * 示例:
 * <p>
 * 输入: [1,2,3]
 * <p>
 * 输出:
 * [
 * [1,2,3],
 * [1,3,2],
 * [2,1,3],
 * [2,3,1],
 * [3,1,2],
 * [3,2,1]
 * ]
 */
public class No46_permute {
    private static final LinkedHashSet<Integer> set = new LinkedHashSet<>();
    static List<List<Integer>> res = new ArrayList<>();

    public static List<List<Integer>> permute_v1(int[] nums) {
        backtrack(nums);
        return res;
    }

    private static void backtrack(int[] nums) {
        if (set.size() == nums.length) {
            res.add(new ArrayList<>(set));
            return;
        }
        for (int num : nums) {
            if (set.contains(num)) {
                continue;
            }
            set.add(num);
            backtrack(nums);
            set.remove(num);
        }
    }


    public static List<List<Integer>> permute_v2(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();

        List<Integer> output = new ArrayList<>();
        for (int num : nums) {
            output.add(num);
        }

        int n = nums.length;
        backtrack(n, output, res, 0);
        return res;
    }

    public static void backtrack(int n, List<Integer> output, List<List<Integer>> res, int first) {
        // 所有数都填完了
        if (first == n) {
            res.add(new ArrayList<>(output));
        }
        for (int i = first; i < n; i++) {
            // 动态维护数组
            Collections.swap(output, first, i);
            // 继续递归填下一个数
            backtrack(n, output, res, first + 1);
            // 撤销操作
            Collections.swap(output, first, i);
        }
    }

    public static void main(String[] args) {
        System.out.println(permute_v1(new int[]{1, 2, 3}));
    }

}
