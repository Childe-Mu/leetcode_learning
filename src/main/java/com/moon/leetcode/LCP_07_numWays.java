package com.moon.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

// LCP 07. 传递信息
//
//小朋友 A 在和 ta 的小伙伴们玩传信息游戏，游戏规则如下：
//
// 有 n 名玩家，所有玩家编号分别为 0 ～ n-1，其中小朋友 A 的编号为 0
// 每个玩家都有固定的若干个可传信息的其他玩家（也可能没有）。传信息的关系是单向的（比如 A 可以向 B 传信息，但 B 不能向 A 传信息）。
// 每轮信息必须需要传递给另一个人，且信息可重复经过同一个人
//
// 给定总玩家数 n，以及按 [玩家编号,对应可传递玩家编号] 关系组成的二维数组 relation。返回信息从小 A (编号 0 ) 经过 k 轮传递到编号
//为 n-1 的小伙伴处的方案数；若不能到达，返回 0。
//
// 示例 1：
//
// 输入：n = 5, relation = [[0,2],[2,1],[3,4],[2,3],[1,4],[2,0],[0,4]], k = 3
//
// 输出：3
//
// 解释：信息从小 A 编号 0 处开始，经 3 轮传递，到达编号 4。共有 3 种方案，分别是 0->2->0->4， 0->2->1->4， 0->2->3->4。
//
// 示例 2：
//
// 输入：n = 3, relation = [[0,2],[2,1]], k = 2
//
// 输出：0
//
// 解释：信息不能从小 A 处经过 2 轮传递到编号 2
//
// 限制：
//
// 2 <= n <= 10
// 1 <= k <= 5
// 1 <= relation.length <= 90, 且 relation[i].length == 2
// 0 <= relation[i][0],relation[i][1] < n 且 relation[i][0] != relation[i][1]
//
// Related Topics 深度优先搜索 广度优先搜索 图 动态规划
// 👍 87 👎 0
public class LCP_07_numWays {
    Map<Integer, List<Integer>> path;
    int k;
    int n;

    public static void main(String[] args) {
        System.out.println(new LCP_07_numWays().numWays_v3(5, new int[][]{{0, 2}, {2, 1}, {3, 4}, {2, 3}, {1, 4}, {2, 0}, {0, 4}}, 3));
    }

    public int numWays_v1(int n, int[][] relation, int k) {
        Map<Integer, List<Integer>> map = new HashMap<>();
        for (int[] a : relation) {
            List<Integer> list = map.computeIfAbsent(a[0], o -> new ArrayList<>());
            list.add(a[1]);
        }
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(0);
        int i = 0;
        while (i < k && !queue.isEmpty()) {
            int size = queue.size();
            for (int j = 0; j < size; j++) {
                Integer cur = queue.poll();
                List<Integer> nexts = map.get(cur);
                if (nexts != null) {
                    for (Integer next : nexts) {
                        queue.offer(next);
                    }
                }
            }
            i++;
        }
        if (queue.isEmpty()) {
            return 0;
        }
        int cnt = 0;
        while (!queue.isEmpty()) {
            Integer poll = queue.poll();
            if (poll == n - 1) {
                cnt++;
            }
        }
        return cnt;
    }

    public int numWays_v2(int n, int[][] relation, int k) {
        this.n = n;
        this.k = k;
        path = new HashMap<>();
        for (int[] a : relation) {
            List<Integer> list = path.computeIfAbsent(a[0], o -> new ArrayList<>());
            list.add(a[1]);
        }
        int[][] mem = new int[n][k + 1];
        for (int i = 0; i < n; i++) {
            Arrays.fill(mem[i], -1);
            mem[i][k] = 0;
        }
        mem[n - 1][k] = 1;
        return dfs(0, 0, mem);
    }

    private int dfs(int step, int cur, int[][] mem) {
        if (mem[cur][step] != -1) {
            return mem[cur][step];
        }
        List<Integer> nexts = path.getOrDefault(cur, new ArrayList<>());
        int cnt = 0;
        for (Integer next : nexts) {
            cnt += dfs(step + 1, next, mem);
        }
        mem[cur][step] = cnt;
        return mem[cur][step];
    }

    public int numWays_v3(int n, int[][] relation, int k) {
        int[][] f = new int[k + 1][n];
        f[0][0] = 1;
        for (int i = 1; i <= k; i++) {
            for (int[] r : relation) {
                int a = r[0], b = r[1];
                f[i][b] += f[i - 1][a];
            }
        }
        return f[k][n - 1];
    }
}
