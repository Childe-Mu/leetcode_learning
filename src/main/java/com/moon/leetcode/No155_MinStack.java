package com.moon.leetcode;

import java.util.Stack;

/**
 * 155. 最小栈<br/>
 * 设计一个支持 push ，pop ，top 操作，并能在常数时间内检索到最小元素的栈。<br/>
 * push(x) —— 将元素 x 推入栈中。<br/>
 * pop() —— 删除栈顶的元素。<br/>
 * top() —— 获取栈顶元素。<br/>
 * getMin() —— 检索栈中的最小元素。<br/>
 * <br/>
 * 示例:<br/>
 * 输入：<br/>
 * ["MinStack","push","push","push","getMin","pop","top","getMin"]<br/>
 * [[],[-2],[0],[-3],[],[],[],[]]<br/>
 * 输出：<br/>
 * [null,null,null,null,-3,null,0,-2]<br/>
 * 解释：<br/>
 * <code>
 * MinStack minStack = new MinStack();<br/>
 * minStack.push(-2);<br/>
 * minStack.push(0);<br/>
 * minStack.push(-3);<br/>
 * minStack.getMin();   --> 返回 -3.<br/>
 * minStack.pop();<br/>
 * minStack.top();      --> 返回 0.<br/>
 * minStack.getMin();   --> 返回 -2.<br/>
 * </code>
 * <br/>
 * 提示：<br/>
 * pop、top 和 getMin 操作总是在 非空栈 上调用。<br/>
 */
public class No155_MinStack {
    public static void main(String[] args) {
        MinStack minStack = new MinStack();
        minStack.push(-2);
        minStack.push(0);
        minStack.push(-3);
        System.out.println(minStack.getMin());
        minStack.pop();
        System.out.println(minStack.top());
        System.out.println(minStack.getMin());

        // Stack stack = new Stack();
        // stack.push(1);
        // System.out.println(stack.peek());
        // stack.pop();
        // System.out.println(stack.peek());
    }
}

/**
 * 在对象里存储对应的最小值
 */
class MinStack {
    /**
     * 存放元素
     */
    private Stack<Element> stack;

    /**
     * initialize your data structure here.
     */
    public MinStack() {
        this.stack = new Stack<>();
        // Element element = new Element(null, Integer.MAX_VALUE);
        // stack.push(element);
    }

    public void push(int x) {
        Element element;
        if (this.stack.size() == 0) {
            element = new Element(x,x);
        } else {
            element = new Element(x,null);
            x = Math.min(x, this.stack.peek().getMinValue());
            element.setMinValue(x);
        }
        this.stack.push(element);
    }

    public void pop() {
        this.stack.pop();
    }

    public int top() {
        return this.stack.peek().getValue();
    }

    public int getMin() {
        return this.stack.peek().getMinValue();
    }
}

class Element {
    private Integer value;
    private Integer minValue;

    public Integer getValue() {
        return value;
    }

    public Element(Integer value, Integer minValue) {
        this.value = value;
        this.minValue = minValue;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    public Integer getMinValue() {
        return minValue;
    }

    public void setMinValue(Integer minValue) {
        this.minValue = minValue;
    }
}

// /**
//  * 辅助栈，辅助栈和主栈一一对应，主栈保存数据，辅助栈保存对应的最小值
//  */
// class MinStack {
//     /**
//      * 存放元素
//      */
//     private Stack<Integer> stack;
//     /**
//      * 存放对应的最小元素
//      */
//     private Stack<Integer> minStack;
//
//     /**
//      * initialize your data structure here.
//      */
//     public MinStack() {
//         this.stack = new Stack<>();
//         this.minStack = new Stack<>();
//         this.minStack.push(Integer.MAX_VALUE);
//     }
//
//     public void push(int x) {
//         this.stack.push(x);
//         x = Math.min(x, this.minStack.peek());
//         this.minStack.push(x);
//     }
//
//     public void pop() {
//         this.stack.pop();
//         this.minStack.pop();
//     }
//
//     public int top() {
//         return this.stack.peek();
//     }
//
//     public int getMin() {
//         return this.minStack.peek();
//     }
// }