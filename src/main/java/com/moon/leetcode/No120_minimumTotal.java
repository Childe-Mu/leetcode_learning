package com.moon.leetcode;

import com.google.common.collect.Lists;

import java.util.Arrays;
import java.util.List;

// 120. 三角形最小路径和
// 
//给定一个三角形 triangle ，找出自顶向下的最小路径和。 
//
// 每一步只能移动到下一行中相邻的结点上。相邻的结点 在这里指的是 下标 与 上一层结点下标 相同或者等于 上一层结点下标 + 1 的两个结点。也就是说，如果
//正位于当前行的下标 i ，那么下一步可以移动到下一行的下标 i 或 i + 1 。 
//
// 示例 1：
//
//输入：triangle = [[2],[3,4],[6,5,7],[4,1,8,3]]
//输出：11
//解释：如下面简图所示：
//   2
//  3 4
// 6 5 7
//4 1 8 3
//自顶向下的最小路径和为11（即，2+3+5+1= 11）。
//
// 示例 2： 
//
//输入：triangle = [[-10]]
//输出：-10
//
// 提示： 
//
// 1 <= triangle.length <= 200 
// triangle[0].length == 1 
// triangle[i].length == triangle[i - 1].length + 1 
// -104 <= triangle[i][j] <= 104 
//
// 进阶： 
//
// 你可以只使用 O(n) 的额外空间（n 为三角形的总行数）来解决这个问题吗？ 
// 
// Related Topics 数组 动态规划 
// 👍 729 👎 0
public class No120_minimumTotal {
    public static void main(String[] args) {
        List<List<Integer>> list = Lists.newArrayList();
        list.add(Lists.newArrayList(2));
        list.add(Lists.newArrayList(3, 4));
        list.add(Lists.newArrayList(5, 6, 7));
        list.add(Lists.newArrayList(4, 1, 8, 3));
        System.out.println(new No120_minimumTotal().minimumTotal_v1(list));
    }

    public int minimumTotal_v1(List<List<Integer>> triangle) {
        int n = triangle.size();
        int[][] mem = new int[n][n];
        for (int i = 0; i < n; i++) {
            Arrays.fill(mem[i], Integer.MAX_VALUE);
        }
        return traverseByMem(triangle, 0, 0, mem);
    }

    private int traverseByMem(List<List<Integer>> triangle, int i, int j, int[][] mem) {
        if (i == triangle.size()) {
            return 0;
        }
        if (mem[i][j] != Integer.MAX_VALUE) {
            return mem[i][j];
        }
        return mem[i][j] = Math.min(traverseByMem(triangle, i + 1, j, mem), traverseByMem(triangle, i + 1, j + 1, mem)) + triangle.get(i).get(j);
    }

    public int minimumTotal_v3(List<List<Integer>> triangle) {
        int n = triangle.size();
        int[][] f = new int[n][n];
        for (int i = 0; i < n; i++) {
            Arrays.fill(f[i], Integer.MAX_VALUE);
        }
        f[0][0] = triangle.get(0).get(0);
        for (int i = 1; i < n; i++) {
            List<Integer> list = triangle.get(i);
            for (int j = 0; j < list.size(); j++) {
                f[i][j] = Math.min(f[i - 1][j], j == 0 ? Integer.MAX_VALUE : f[i - 1][j - 1]) + list.get(j);
            }
        }
        return Arrays.stream(f[n - 1]).min().orElse(-1);
    }

    public int minimumTotal_v4(List<List<Integer>> triangle) {
        int n = triangle.size();
        int[] f = new int[n];
        Arrays.fill(f, Integer.MAX_VALUE);
        f[0] = triangle.get(0).get(0);
        for (int i = 1; i < n; i++) {
            List<Integer> list = triangle.get(i);
            for (int j = list.size() - 1; j >= 0; j--) {
                int pre1 = j == 0 ? Integer.MAX_VALUE : f[j - 1], pre2 = f[j];
                f[j] = Math.min(pre1, pre2) + list.get(j);
            }
        }
        return Arrays.stream(f).min().orElse(-1);
    }
}
