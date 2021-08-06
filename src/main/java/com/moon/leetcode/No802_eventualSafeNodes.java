package com.moon.leetcode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

// 802. 找到最终的安全状态
//
//在有向图中，以某个节点为起始节点，从该点出发，每一步沿着图中的一条有向边行走。如果到达的节点是终点（即它没有连出的有向边），则停止。
//
// 对于一个起始节点，如果从该节点出发，无论每一步选择沿哪条有向边行走，最后必然在有限步内到达终点，则将该起始节点称作是 安全 的。
//
// 返回一个由图中所有安全的起始节点组成的数组作为答案。答案数组中的元素应当按 升序 排列。
//
// 该有向图有 n 个节点，按 0 到 n - 1 编号，其中 n 是 graph 的节点数。图以下述形式给出：graph[i] 是编号 j 节点的一个列表，
//满足 (i, j) 是图的一条有向边。
//
//
//
//
//
// 示例 1：
//
//
//输入：graph = [[1,2],[2,3],[5],[0],[5],[],[]]
//输出：[2,4,5,6]
//解释：示意图如上。
//
//
// 示例 2：
//
//
//输入：graph = [[1,2,3,4],[1,2],[3,4],[0,4],[]]
//输出：[4]
//
//
//
//
// 提示：
//
//
// n == graph.length
// 1 <= n <= 104
// 0 <= graph[i].length <= n
// graph[i] 按严格递增顺序排列。
// 图中可能包含自环。
// 图中边的数目在范围 [1, 4 * 104] 内。
//
//
//
// Related Topics 深度优先搜索 广度优先搜索 图 拓扑排序
// 👍 199 👎 0
public class No802_eventualSafeNodes {

    public List<Integer> eventualSafeNodes_v1(int[][] graph) {
        int n = graph.length;
        int[] color = new int[n];
        List<Integer> res = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            if (safe(graph, color, i)) {
                res.add(i);
            }
        }
        return res;
    }

    private boolean safe(int[][] g, int[] color, int x) {
        // 如果已经标记过颜色，那么判断是否为黑色，黑色为安全，灰色为不安全
        if (color[x] > 0) {
            return color[x] == 2;
        }
        color[x] = 1;
        for (int i : g[x]) {
            if (!safe(g, color, i)) {
                return false;
            }
        }
        color[x] = 2;
        return true;
    }

    public List<Integer> eventualSafeNodes_v2(int[][] graph) {
        List<Integer> res = new ArrayList<>();
        int n = graph.length;
        Map<Integer, List<Integer>> reverse = new HashMap<>();
        int[] inDegree = new int[n];
        for (int i = 0; i < n; i++) {
            for (int v : graph[i]) {
                reverse.computeIfAbsent(v, o -> new ArrayList<>()).add(i);
            }
            inDegree[i] = graph[i].length;
        }
        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < n; i++) {
            if (inDegree[i] == 0) {
                queue.offer(i);
            }
        }
        while (!queue.isEmpty()) {
            Integer poll = queue.poll();
            List<Integer> list = reverse.get(poll);
            for (Integer i : list) {
                if (--inDegree[i] == 0) {
                    queue.offer(i);
                }
            }
        }
        for (int i = 0; i < n; i++) {
            if (inDegree[i] == 0) {
                res.add(i);
            }
        }
        return res;
    }

}
