package com.moon.leetcode.offer;

import java.util.ArrayDeque;
import java.util.Deque;

// 剑指 Offer 30. 包含min函数的栈
//
//定义栈的数据结构，请在该类型中实现一个能够得到栈的最小元素的 min 函数在该栈中，调用 min、push 及 pop 的时间复杂度都是 O(1)。
//
// 示例:
//
// MinStack minStack = new MinStack();
//minStack.push(-2);
//minStack.push(0);
//minStack.push(-3);
//minStack.min();   --> 返回 -3.
//minStack.pop();
//minStack.top();      --> 返回 0.
//minStack.min();   --> 返回 -2.
//
// 提示：
//
// 各函数的调用总次数不超过 20000 次
//
// 注意：本题与主站 155 题相同：https://leetcode-cn.com/problems/min-stack/
// Related Topics 栈 设计
// 👍 121 👎 0
public class Offer_30_MinStack {

    public static void main(String[] args) {
        MinStack minStack = new MinStack();
        minStack.push(-2);
        minStack.push(0);
        minStack.push(-3);
        minStack.min();   // --> 返回 -3.
        minStack.pop();
        minStack.top();    //   --> 返回 0.
        minStack.min();  // --> 返回 -2.
    }

    private static class MinStack {
        private Deque<Integer> stack;
        private Deque<Integer> min;

        /**
         * initialize your data structure here.
         */
        public MinStack() {
            stack = new ArrayDeque<>();
            min = new ArrayDeque<>();
        }

        public void push(int x) {
            stack.push(x);
            if (min.isEmpty()) {
                min.push(x);
            } else {
                min.push(Math.min(min.peek(), x));
            }
        }

        public void pop() {
            stack.pop();
            min.pop();
        }

        public int top() {
            return stack.peek();
        }

        public int min() {
            return min.peek();
        }
    }
}
