package com.moon.leetcode.offer;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Objects;
import java.util.Queue;

// 剑指 Offer 59 - II. 队列的最大值
//
//请定义一个队列并实现函数 max_value 得到队列里的最大值，要求函数max_value、push_back 和 pop_front 的均摊时间复杂度都是O(1)。
//
// 若队列为空，pop_front 和 max_value 需要返回 -1
//
// 示例 1：
//
// 输入:
//["MaxQueue","push_back","push_back","max_value","pop_front","max_value"]
//[[],[1],[2],[],[],[]]
//输出:[null,null,null,2,1,2]
//
// 示例 2：
//
// 输入:
//["MaxQueue","pop_front","max_value"]
//[[],[],[]]
//输出:[null,-1,-1]
//
// 限制：
//
// 1 <= push_back,pop_front,max_value的总操作数 <= 10000
// 1 <= value <= 10^5
//
// Related Topics 设计 队列 单调队列
// 👍 259 👎 0
public class Offer_59_2_MaxQueue {
    public static void main(String[] args) {
        MaxQueue maxQueue = new MaxQueue();
        maxQueue.push_back(1);
        maxQueue.push_back(2);
        System.out.println(maxQueue.max_value());
        System.out.println(maxQueue.pop_front());
        System.out.println(maxQueue.max_value());
    }

    static class MaxQueue {
        Queue<Integer> queue;
        Deque<Integer> deque;

        public MaxQueue() {
            this.queue = new LinkedList<>();
            this.deque = new ArrayDeque<>();
        }

        public int max_value() {
            if (this.deque.isEmpty()) {
                return -1;
            }
            return this.deque.peekFirst();
        }

        public void push_back(int value) {
            this.queue.offer(value);
            while (!this.deque.isEmpty() && this.deque.peekLast() < value) {
                this.deque.pollLast();
            }
            this.deque.offerLast(value);
        }

        public int pop_front() {
            if (this.queue.isEmpty()) {
                return -1;
            }
            int ans = this.queue.poll();
            if (Objects.equals(this.deque.peekFirst(), ans)) {
                this.deque.pollFirst();
            }
            return ans;
        }
    }

}
