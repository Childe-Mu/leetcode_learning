package com.moon.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;
import java.util.stream.Stream;

// LCP 32. 批量处理任务
//
//某实验室计算机待处理任务以 `[start,end,period]` 格式记于二维数组 `tasks`，表示完成该任务的时间范围为起始时间 `start`
//至结束时间 `end` 之间，需要计算机投入 `period` 的时长，注意：
//1. `period` 可为不连续时间
//2. 首尾时间均包含在内
//
//处于开机状态的计算机可同时处理任意多个任务，请返回电脑最少开机多久，可处理完所有任务。
//
//**示例 1：**
//>输入：`tasks = [[1,3,2],[2,5,3],[5,6,2]]`
//>
//>输出：`4`
//>
//>解释：
//>tasks[0] 选择时间点 2、3；
//>tasks[1] 选择时间点 2、3、5；
//>tasks[2] 选择时间点 5、6；
//>因此计算机仅需在时间点 2、3、5、6 四个时刻保持开机即可完成任务。
//
//**示例 2：**
//>输入：`tasks = [[2,3,1],[5,5,1],[5,6,2]]`
//>
//>输出：`3`
//>
//>解释：
//>tasks[0] 选择时间点 2 或 3；
//>tasks[1] 选择时间点 5；
//>tasks[2] 选择时间点 5、6；
//>因此计算机仅需在时间点 2、5、6 或 3、5、6 三个时刻保持开机即可完成任务。
//
//**提示：**
//- `2 <= tasks.length <= 10^5`
//- `tasks[i].length == 3`
//- `0 <= tasks[i][0] <= tasks[i][1] <= 10^9`
//- `1 <= tasks[i][2] <= tasks[i][1]-tasks[i][0] + 1`
// 👍 8 👎 0
public class LCP_32_processTasks {
    public static void main(String[] args) {
        System.out.println(new LCP_32_processTasks().processTasks_v2(new int[][]{{0, 1, 1}, {0, 1, 1}, {1, 2, 1}}));
    }

    public int processTasks_v1(int[][] tasks) {
        int n = tasks.length;
        Set<Integer> ts = new TreeSet<>();
        for (int[] task : tasks) {
            ts.add(task[0]);
            ts.add(task[1] + 1);
        }
        List<Integer> vt = new ArrayList<>(ts);
        int m = vt.size();
        // 存放所有任务开始或者结束的时刻点和对应的时刻
        Map<Integer, Integer> mp = new HashMap<>(m);
        for (int i = 0; i < m; ++i) {
            mp.put(vt.get(i), i);
        }

        // 存放某一时刻开始的任务的编号
        List<List<Integer>> starts = Stream.generate(ArrayList<Integer>::new)
                .limit(m)
                .collect(Collectors.toList());
        for (int i = 0; i < n; ++i) {
            starts.get(mp.get(tasks[i][0])).add(i);
        }

        int ans = 0;
        int extra = 0;
        Queue<Pair> pq = new PriorityQueue<>(n, Comparator.comparingInt(o -> o.slots));
        for (int i = 0; i < m - 1; ++i) {
            while (!pq.isEmpty() && tasks[pq.peek().index][1] < vt.get(i)) {
                pq.poll();
            }
            for (int u : starts.get(i)) {
                pq.add(new Pair(extra + tasks[u][1] - vt.get(i) + 1 - tasks[u][2], u));
            }
            int currentSeg = vt.get(i + 1) - vt.get(i);
            if (!pq.isEmpty()) {
                int minSlots = pq.peek().slots - extra;
                int slotsToDel = currentSeg;

                if (minSlots < currentSeg) {
                    int delta = currentSeg - minSlots;
                    ans += delta;
                    slotsToDel -= delta;
                }
                extra += slotsToDel;
            }
        }
        return ans;
    }

    public int processTasks_v2(int[][] tasks) {
        int[][] newTasks = new int[tasks.length + 1][];
        //按起点排序
        Arrays.sort(tasks, Comparator.comparingInt(task -> task[0]));
        System.arraycopy(tasks, 0, newTasks, 0, tasks.length);
        newTasks[tasks.length] = new int[]{1000000001, 1000000001, 1};
        int res = 0;
        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a[0]));
        for (int[] task : newTasks) {
            while (!pq.isEmpty() && pq.peek()[0] + res < task[0]) {
                if (pq.peek()[0] + res >= pq.peek()[1]) {
                    pq.poll();
                } else {
                    res += Math.min(pq.peek()[1], task[0]) - (pq.peek()[0] + res);
                }
            }
            pq.offer(new int[]{task[1] - task[2] + 1 - res, task[1] + 1});
        }
        return res;
    }

    private static class Pair {
        // 任务执行前的空闲时间
        public int slots;
        // 任务编号
        public int index;

        public Pair(int slots, int index) {
            this.slots = slots;
            this.index = index;
        }
    }
}