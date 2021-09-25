package com.moon.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

/**
 * 787. K 站中转内最便宜的航班
 *
 * <p>有 <code>n</code> 个城市通过一些航班连接。给你一个数组&nbsp;<code>flights</code> ，其中&nbsp;<code>flights[i] = [from<sub>i</sub>, to<sub>i</sub>, price<sub>i</sub>]</code> ，表示该航班都从城市 <code>from<sub>i</sub></code> 开始，以价格 <code>price<sub>i</sub></code> 抵达 <code>to<sub>i</sub></code>。</p>
 *
 * <p>现在给定所有的城市和航班，以及出发城市 <code>src</code> 和目的地 <code>dst</code>，你的任务是找到出一条最多经过 <code>k</code>&nbsp;站中转的路线，使得从 <code>src</code> 到 <code>dst</code> 的 <strong>价格最便宜</strong> ，并返回该价格。 如果不存在这样的路线，则输出 <code>-1</code>。</p>
 *
 * <p>&nbsp;</p>
 *
 * <p><strong>示例 1：</strong></p>
 *
 * <pre>
 * <strong>输入:</strong>
 * n = 3, edges = [[0,1,100],[1,2,100],[0,2,500]]
 * src = 0, dst = 2, k = 1
 * <strong>输出:</strong> 200
 * <strong>解释:</strong>
 * 城市航班图如下
 * <img alt="" src="https://s3-lc-upload.s3.amazonaws.com/uploads/2018/02/16/995.png" style="height: 180px; width: 246px;" />
 *
 * 从城市 0 到城市 2 在 1 站中转以内的最便宜价格是 200，如图中红色所示。</pre>
 *
 * <p><strong>示例 2：</strong></p>
 *
 * <pre>
 * <strong>输入:</strong>
 * n = 3, edges = [[0,1,100],[1,2,100],[0,2,500]]
 * src = 0, dst = 2, k = 0
 * <strong>输出:</strong> 500
 * <strong>解释:</strong>
 * 城市航班图如下
 * <img alt="" src="https://s3-lc-upload.s3.amazonaws.com/uploads/2018/02/16/995.png" style="height: 180px; width: 246px;" />
 *
 * 从城市 0 到城市 2 在 0 站中转以内的最便宜价格是 500，如图中蓝色所示。</pre>
 * <p>
 * p>&nbsp;</p>
 * <p>
 * p><strong>提示：</strong></p>
 *
 * <ul>
 * <li><code>1 &lt;= n &lt;= 100</code></li>
 * <li><code>0 &lt;= flights.length &lt;= (n * (n - 1) / 2)</code></li>
 * <li><code>flights[i].length == 3</code></li>
 * <li><code>0 &lt;= from<sub>i</sub>, to<sub>i</sub> &lt; n</code></li>
 * <li><code>from<sub>i</sub> != to<sub>i</sub></code></li>
 * <li><code>1 &lt;= price<sub>i</sub> &lt;= 10<sup>4</sup></code></li>
 * <li>航班没有重复，且不存在自环</li>
 * <li><code>0 &lt;= src, dst, k &lt; n</code></li>
 * <li><code>src != dst</code></li>
 * </ul>
 * <div><div>Related Topics</div><div><li>深度优先搜索</li><li>广度优先搜索</li><li>图</li><li>动态规划</li><li>最短路</li><li>堆（优先队列）</li></div></div><br><div><li>👍 374</li><li>👎 0</li></div>
 */
public class No787_findCheapestPrice {

    public static void main(String[] args) {
        System.out.println(new No787_findCheapestPrice().findCheapestPrice(17, new int[][]{{0, 12, 28}, {5, 6, 39}, {8, 6, 59}, {13, 15, 7}, {13, 12, 38}, {10, 12, 35}, {15, 3, 23}, {7, 11, 26}, {9, 4, 65}, {10, 2, 38}, {4, 7, 7}, {14, 15, 31}, {2, 12, 44}, {8, 10, 34}, {13, 6, 29}, {5, 14, 89}, {11, 16, 13}, {7, 3, 46}, {10, 15, 19}, {12, 4, 58}, {13, 16, 11}, {16, 4, 76}, {2, 0, 12}, {15, 0, 22}, {16, 12, 13}, {7, 1, 29}, {7, 14, 100}, {16, 1, 14}, {9, 6, 74}, {11, 1, 73}, {2, 11, 60}, {10, 11, 85}, {2, 5, 49}, {3, 4, 17}, {4, 9, 77}, {16, 3, 47}, {15, 6, 78}, {14, 1, 90}, {10, 5, 95}, {1, 11, 30}, {11, 0, 37}, {10, 4, 86}, {0, 8, 57}, {6, 14, 68}, {16, 8, 3}, {13, 0, 65}, {2, 13, 6}, {5, 13, 5}, {8, 11, 31}, {6, 10, 20}, {6, 2, 33}, {9, 1, 3}, {14, 9, 58}, {12, 3, 19}, {11, 2, 74}, {12, 14, 48}, {16, 11, 100}, {3, 12, 38}, {12, 13, 77}, {10, 9, 99}, {15, 13, 98}, {15, 12, 71}, {1, 4, 28}, {7, 0, 83}, {3, 5, 100}, {8, 9, 14}, {15, 11, 57}, {3, 6, 65}, {1, 3, 45}, {14, 7, 74}, {2, 10, 39}, {4, 8, 73}, {13, 5, 77}, {10, 0, 43}, {12, 9, 92}, {8, 2, 26}, {1, 7, 7}, {9, 12, 10}, {13, 11, 64}, {8, 13, 80}, {6, 12, 74}, {9, 7, 35}, {0, 15, 48}, {3, 7, 87}, {16, 9, 42}, {5, 16, 64}, {4, 5, 65}, {15, 14, 70}, {12, 0, 13}, {16, 14, 52}, {3, 10, 80}, {14, 11, 85}, {15, 2, 77}, {4, 11, 19}, {2, 7, 49}, {10, 7, 78}, {14, 6, 84}, {13, 7, 50}, {11, 6, 75}, {5, 10, 46}, {13, 8, 43}, {9, 10, 49}, {7, 12, 64}, {0, 10, 76}, {5, 9, 77}, {8, 3, 28}, {11, 9, 28}, {12, 16, 87}, {12, 6, 24}, {9, 15, 94}, {5, 7, 77}, {4, 10, 18}, {7, 2, 11}, {9, 5, 41}},
                13, 4, 13));
    }

    /**
     * 最短路径+广度优先搜索，会超出内存限制
     */
    public int findCheapestPrice(int n, int[][] flights, int src, int dst, int k) {
        Map<Integer, List<int[]>> g = new HashMap<>();
        for (int[] flight : flights) {
            g.computeIfAbsent(flight[0], o -> new ArrayList<>()).add(flight);
        }
        int[] dist = new int[n];
        Arrays.fill(dist, Integer.MAX_VALUE / 2);
        dist[src] = 0;

        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{src, 0});
        int stop = 0;
        while (!queue.isEmpty() && stop <= k) {
            stop++;
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                int[] poll = queue.poll();
                List<int[]> list = g.get(poll[0]);
                if (list == null) {
                    continue;
                }
                for (int[] p : list) {
                    if (dist[p[1]] > poll[1] + p[2]) {
                        queue.offer(new int[]{p[1], poll[1] + p[2]});
                        dist[p[1]] = poll[1] + p[2];
                    }
                }
            }
        }
        return dist[dst] == Integer.MAX_VALUE / 2 ? -1 : dist[dst];
    }

    public int findCheapestPrice_v2(int n, int[][] flights, int src, int dst, int k) {
        final int INF = 10000 * 101 + 1;
        int[][] f = new int[k + 2][n];
        for (int i = 0; i < k + 2; ++i) {
            Arrays.fill(f[i], INF);
        }
        f[0][src] = 0;
        for (int t = 1; t <= k + 1; ++t) {
            for (int[] flight : flights) {
                int j = flight[0], i = flight[1], cost = flight[2];
                f[t][i] = Math.min(f[t][i], f[t - 1][j] + cost);
            }
        }
        int ans = INF;
        for (int t = 1; t <= k + 1; ++t) {
            ans = Math.min(ans, f[t][dst]);
        }
        return ans == INF ? -1 : ans;
    }

    public int findCheapestPrice_v3(int n, int[][] flights, int src, int dst, int k) {
        int[] dist = new int[n];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[src] = 0;
        for (int limit = 0; limit < k + 1; limit++) {
            int[] clone = dist.clone();
            for (int[] f : flights) {
                int x = f[0], y = f[1], w = f[2];
                dist[y] = Math.min(dist[y], clone[x] + w);
            }
        }
        return dist[dst] == Integer.MAX_VALUE ? -1 : dist[dst];
    }

    public int findCheapestPrice_v4(int n, int[][] flights, int src, int dst, int k) {
        int[] dis = new int[n];
        Arrays.fill(dis, -1);
        dis[src] = 0;
        List<int[]>[] adj = new ArrayList[n];
        for (int[] flight : flights) {
            if (adj[flight[0]] == null) {
                adj[flight[0]] = new ArrayList<>();
            }
            adj[flight[0]].add(flight);
        }
        Queue<int[]> queue = new LinkedList<>();
        if (adj[src] == null) return -1;
        for (int[] f : adj[src]) {
            int[] x = new int[]{f[0], f[1], f[2], 0};
            queue.offer(x);
        }
        while (!queue.isEmpty()) {
            int[] x = queue.poll();
            if (dis[x[1]] != -1 && dis[x[1]] < x[2]) {
                continue;
            }
            dis[x[1]] = x[2];
            if (adj[x[1]] != null && x[3] < k) {
                for (int[] f : adj[x[1]]) {
                    int[] y = new int[]{f[0], f[1], x[2] + f[2], x[3] + 1};
                    if (dis[y[1]] != -1 && dis[y[1]] < y[2]) {
                        continue;
                    }
                    queue.offer(y);
                }
            }
        }
        return dis[dst];
    }
}