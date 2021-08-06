package com.moon.leetcode;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

// 847. 访问所有节点的最短路径
//
//存在一个由 n 个节点组成的无向连通图，图中的节点按从 0 到 n - 1 编号。
//
// 给你一个数组 graph 表示这个图。其中，graph[i] 是一个列表，由所有与节点 i 直接相连的节点组成。
//
// 返回能够访问所有节点的最短路径的长度。你可以在任一节点开始和停止，也可以多次重访节点，并且可以重用边。
//
// 示例 1：
//
//
//输入：graph = [[1,2,3],[0],[0],[0]]
//输出：4
//解释：一种可能的路径为 [1,0,2,0,3]
//
// 示例 2：
//
//输入：graph = [[1],[0,2,4],[1,3,4],[2],[1,2]]
//输出：4
//解释：一种可能的路径为 [0,1,4,2,3]
//
//
// 提示：
//
//
// n == graph.length
// 1 <= n <= 12
// 0 <= graph[i].length < n
// graph[i] 不包含 i
// 如果 graph[a] 包含 b ，那么 graph[b] 也包含 a
// 输入的图总是连通图
//
// Related Topics 位运算 广度优先搜索 图 动态规划 状态压缩
// 👍 201 👎 0
public class No847_shortestPathLength {
    public int shortestPathLength(int[][] graph) {
        int INF = Integer.MAX_VALUE >> 1;
        int n = graph.length;
        int mask = 1 << n;

        int[][] dist = new int[mask][n];
        for (int i = 0; i < mask; i++) {
            Arrays.fill(dist[i], INF);
        }
        // 因为可以从任意起点出发，先将起始的起点状态入队，并设起点距离为 0
        // state, u
        Deque<int[]> deque = new ArrayDeque<>();
        for (int i = 0; i < n; i++) {
            dist[1 << i][i] = 0;
            deque.add(new int[]{1 << i, i});
        }
        // BFS 过程，如果从点 u 能够到达点 i，则更新距离并进行入队
        while (!deque.isEmpty()) {
            int[] poll = deque.pollFirst();
            int state = poll[0];
            int u = poll[1];
            int step = dist[state][u];
            if (state == mask - 1) {
                return step;
            }
            for (int i : graph[u]) {
                if (dist[state | (1 << i)][i] == INF) {
                    dist[state | (1 << i)][i] = step + 1;
                    deque.addLast(new int[]{state | (1 << i), i});
                }
            }
        }
        return -1;
    }
}
