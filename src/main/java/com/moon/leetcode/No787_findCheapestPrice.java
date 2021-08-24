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
        System.out.println(new No787_findCheapestPrice().findCheapestPrice(3, new int[][]{{0, 1, 100}, {1, 2, 100}, {0, 2, 500}}, 0, 2, 0));
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
                    queue.offer(new int[]{p[1], poll[1] + p[2]});
                    dist[p[1]] = Math.min(dist[p[1]], poll[1] + p[2]);
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
}
