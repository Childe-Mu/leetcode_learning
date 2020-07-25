package com.moon.leetcode;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 933. 最近的请求次数<br/>
 * 写一个 RecentCounter 类来计算最近的请求。<br/>
 * 它只有一个方法：ping(int t)，其中 t 代表以毫秒为单位的某个时间。<br/>
 * 返回从 3000 毫秒前到现在的 ping 数。<br/>
 * 任何处于 [t - 3000, t] 时间范围之内的 ping 都将会被计算在内，包括当前（指 t 时刻）的 ping。<br/>
 * 保证每次对 ping 的调用都使用比之前更大的 t 值。<br/>
 * <br/>
 * 示例：<br/>
 * 输入：inputs = ["RecentCounter","ping","ping","ping","ping"], inputs = [[],[1],[100],[3001],[3002]]<br/>
 * 输出：[null,1,2,3,3]<br/>
 * <br/>
 * 提示：<br/>
 * 每个测试用例最多调用 10000 次 ping。<br/>
 * 每个测试用例会使用严格递增的 t 值来调用 ping。<br/>
 * 每次调用 ping 都有 1 <= t <= 10^9。<br/>
 */
public class No933_RecentCounter {
    private Queue<Integer> timeQueue;

    public No933_RecentCounter() {
        this.timeQueue = new LinkedList<>();
    }

    public int ping(int t) {
        timeQueue.add(t);
        while (!timeQueue.isEmpty() && timeQueue.peek() < t - 3000) {
            timeQueue.poll();
        }
        return timeQueue.size();
    }
}
