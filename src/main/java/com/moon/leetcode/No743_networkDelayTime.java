package com.moon.leetcode;

import java.util.Arrays;

// 743. 网络延迟时间
//
//有 n 个网络节点，标记为 1 到 n。
//
// 给你一个列表 times，表示信号经过 有向 边的传递时间。 times[i] = (ui, vi, wi)，其中 ui 是源节点，vi 是目标节点， w
//i 是一个信号从源节点传递到目标节点的时间。
//
// 现在，从某个节点 K 发出一个信号。需要多久才能使所有节点都收到信号？如果不能使所有节点收到信号，返回 -1 。
//
//
//
// 示例 1：
//
//
//
//
//输入：times = [[2,1,1],[2,3,1],[3,4,1]], n = 4, k = 2
//输出：2
//
//
// 示例 2：
//
//
//输入：times = [[1,2,1]], n = 2, k = 1
//输出：1
//
//
// 示例 3：
//
//
//输入：times = [[1,2,1]], n = 2, k = 2
//输出：-1
//
//
//
//
// 提示：
//
//
// 1 <= k <= n <= 100
// 1 <= times.length <= 6000
// times[i].length == 3
// 1 <= ui, vi <= n
// ui != vi
// 0 <= wi <= 100
// 所有 (ui, vi) 对都 互不相同（即，不含重复边）
//
// Related Topics 深度优先搜索 广度优先搜索 图 最短路 堆（优先队列）
// 👍 374 👎 0
public class No743_networkDelayTime {
    public static void main(String[] args) {
        new No743_networkDelayTime().networkDelayTime(new int[][]{{1, 2, 1}, {2, 3, 2}, {1, 3, 4}}, 3, 1);
    }

    public int networkDelayTime(int[][] times, int n, int k) {
        int max = Integer.MAX_VALUE >> 1;
        int[][] g = new int[n + 1][n + 1];
        for (int i = 0; i <= n; i++) {
            Arrays.fill(g[i], max);
        }
        for (int[] time : times) {
            int x = time[0];
            int y = time[1];
            g[x][y] = time[2];
        }
        boolean[] used = new boolean[n + 1];
        int[] dist = new int[n + 1];
        Arrays.fill(dist, max);
        dist[k] = 0;
        for (int i = 1; i <= n; i++) {
            int x = -1;
            for (int y = 1; y <= n; y++) {
                if (!used[y] && (x == -1 || dist[y] < dist[x])) {
                    x = y;
                }
            }
            used[x] = true;
            for (int y = 1; y <= n; y++) {
                dist[y] = Math.min(dist[y], dist[x] + g[x][y]);
            }
        }
        dist[0] = 0;
        int ans = Arrays.stream(dist).max().orElse(-1);
        return ans == max ? -1 : ans;
    }
}
