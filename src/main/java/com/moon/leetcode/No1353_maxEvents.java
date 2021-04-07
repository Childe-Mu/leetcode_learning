package com.moon.leetcode;

import java.util.Arrays;
import java.util.Comparator;
import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Set;

// 1353. 最多可以参加的会议数目
//
//给你一个数组 events，其中 events[i] = [startDayi, endDayi] ，表示会议 i 开始于 startDayi ，结束于 e
//ndDayi 。
//
// 你可以在满足 startDayi <= d <= endDayi 中的任意一天 d 参加会议 i 。注意，一天只能参加一个会议。
//
// 请你返回你可以参加的 最大 会议数目。
//
//
// 示例 1：
//
//
//
// 输入：events = [[1,2],[2,3],[3,4]]
//输出：3
//解释：你可以参加所有的三个会议。
//安排会议的一种方案如上图。
//第 1 天参加第一个会议。
//第 2 天参加第二个会议。
//第 3 天参加第三个会议。
//
//
// 示例 2：
//
// 输入：events= [[1,2],[2,3],[3,4],[1,2]]
//输出：4
//
//
// 示例 3：
//
// 输入：events = [[1,4],[4,4],[2,2],[3,4],[1,1]]
//输出：4
//
//
// 示例 4：
//
// 输入：events = [[1,100000]]
//输出：1
//
//
// 示例 5：
//
// 输入：events = [[1,1],[1,2],[1,3],[1,4],[1,5],[1,6],[1,7]]
//输出：7
//
//
//
//
// 提示：
//
//
// 1 <= events.length <= 10^5
// events[i].length == 2
// 1 <= events[i][0] <= events[i][1] <= 10^5
//
// Related Topics 贪心算法 排序 线段树
// 👍 133 👎 0
public class No1353_maxEvents {
    public static void main(String[] args) {
        System.out.println(new No1353_maxEvents().maxEvents_V2(new int[][]{{1, 5}, {1, 5}, {1, 5}, {2, 3}, {2, 3}}));
    }

    public int maxEvents_v1(int[][] events) {
        Arrays.sort(events, Comparator.comparingInt(o -> o[1]));
        Set<Integer> set = new HashSet<>();
        int res = 0;
        for (int[] event : events) {
            for (int i = event[0]; i <= event[1]; i++) {
                if (!set.contains(i)) {
                    set.add(i);
                    res++;
                    break;
                }
            }
        }
        return res;
    }

    public int maxEvents_V2(int[][] events) {
        Arrays.sort(events, Comparator.comparingInt(o -> o[0]));
        PriorityQueue<Integer> queue = new PriorityQueue<>();
        int curDay = events[0][0], i = 0, n = events.length, res = 0;
        while (i < n || !queue.isEmpty()) {
            while (i < n && events[i][0] == curDay) {
                queue.offer(events[i][1]);
                i++;
            }
            while (!queue.isEmpty() && queue.peek() < curDay) {
                queue.poll();
            }
            if (!queue.isEmpty()) {
                queue.poll();
                res++;
            }
            curDay++;
        }
        return res;
    }
}


