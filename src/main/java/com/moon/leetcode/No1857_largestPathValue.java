package com.moon.leetcode;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Queue;

// 1857. 有向图中最大颜色值
//
//给你一个 有向图 ，它含有 n 个节点和 m 条边。节点编号从 0 到 n - 1 。
//
// 给你一个字符串 colors ，其中 colors[i] 是小写英文字母，表示图中第 i 个节点的 颜色 （下标从 0 开始）。同时给你一个二维数组 ed
//ges ，其中 edges[j] = [aj, bj] 表示从节点 aj 到节点 bj 有一条 有向边 。
//
// 图中一条有效 路径 是一个点序列 x1 -> x2 -> x3 -> ... -> xk ，对于所有 1 <= i < k ，从 xi 到 xi+1 在图
//中有一条有向边。路径的 颜色值 是路径中 出现次数最多 颜色的节点数目。
//
// 请你返回给定图中有效路径里面的 最大颜色值 。如果图中含有环，请返回 -1 。
//
// 示例 1：
//
// 输入：colors = "abaca", edges = [[0,1],[0,2],[2,3],[3,4]]
//输出：3
//解释：路径 0 -> 2 -> 3 -> 4 含有 3 个颜色为 "a" 的节点（上图中的红色节点）。
//
// 示例 2：
//
// 输入：colors = "a", edges = [[0,0]]
//输出：-1
//解释：从 0 到 0 有一个环。
//
// 提示：
//
// n == colors.length
// m == edges.length
// 1 <= n <= 105
// 0 <= m <= 105
// colors 只含有小写英文字母。
// 0 <= aj, bj < n
//
// Related Topics 拓扑排序 动态规划
// 👍 9 👎 0
public class No1857_largestPathValue {
    private int ans = 0;

    public static void main(String[] args) {
        System.out.println(new No1857_largestPathValue().largestPathValue("hhqhuqhqff", new int[][]{
                {0, 1},
                {0, 2},
                {2, 3},
                {3, 4},
                {3, 5},
                {5, 6},
                {2, 7},
                {6, 7},
                {7, 8}, {3, 8}, {5, 8}, {8, 9}, {3, 9}, {6, 9}}));
    }

    public int largestPathValue(String colors, int[][] edges) {
        char[] chars = colors.toCharArray();
        int m = colors.length();
        int[] color = new int[26];
        int[] inDegree = new int[m];
        Map<Integer, List<Integer>> map = new HashMap<>();
        for (int[] edge : edges) {
            inDegree[edge[1]]++;
            List<Integer> list = map.getOrDefault(edge[0], new ArrayList<>());
            list.add(edge[1]);
            map.put(edge[0], list);
        }
        List<Integer> starts = new ArrayList<>();
        for (int i = 0; i < m; i++) {
            if (inDegree[i] == 0) {
                starts.add(i);
            }
        }
        if (checkLoop(inDegree, map, starts)) {
            return -1;
        }

        Integer[][] mem = new Integer[m][26];
        for (Integer start : starts) {
            dfs(chars, map, color, start, mem);
        }
        return ans;
    }

    private boolean checkLoop(int[] inDegree, Map<Integer, List<Integer>> map, List<Integer> starts) {
        if (starts.isEmpty()) {
            return true;
        }
        Queue<Integer> queue = new ArrayDeque<>();
        for (Integer start : starts) {
            queue.offer(start);

        }
        int count = 0;
        while (!queue.isEmpty()) {
            Integer cur = queue.poll();
            List<Integer> list = map.getOrDefault(cur, new ArrayList<>());
            count++;
            for (Integer i : list) {
                inDegree[i]--;
                if (inDegree[i] == 0) {
                    queue.offer(i);
                }
            }
        }
        return count != inDegree.length;
    }

    private void dfs(char[] chars, Map<Integer, List<Integer>> map, int[] color, int index, Integer[][] mem) {
        if (mem[index] != null) {

        }
        List<Integer> list = map.get(index);
        if (list == null || list.isEmpty()) {
            return;
        }
        mem[index][chars[index] - 'a']++;
        for (Integer i : list) {
            dfs(chars, map, color, i, mem);
            for (int j = 0; j < 26; j++) {
                mem[index][j] = mem[index][j] == null ? mem[i][j] : mem[index][j] + mem[i][j];
            }
        }
    }
}
