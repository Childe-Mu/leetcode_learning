package com.moon.leetcode.offer;

import java.util.Stack;

/**
 * 剑指 Offer 09. 用两个栈实现队列<br/>
 * 用两个栈实现一个队列。队列的声明如下，请实现它的两个函数 appendTail 和 deleteHead ，
 * 分别完成在队列尾部插入整数和在队列头部删除整数的功能。(若队列中没有元素，deleteHead 操作返回 -1 )<br/>
 * <br/>
 * 示例 1：<br/>
 * 输入：<br/>
 * ["CQueue","appendTail","deleteHead","deleteHead"]<br/>
 * [[],[3],[],[]]<br/>
 * 输出：[null,null,3,-1]<br/>
 * <br/>
 * 示例 2：<br/>
 * 输入：<br/>
 * ["CQueue","deleteHead","appendTail","appendTail","deleteHead","deleteHead"]<br/>
 * [[],[],[5],[2],[],[]]<br/>
 * 输出：[null,-1,null,null,5,2]<br/>
 * <br/>
 * 提示：<br/>
 * 1 <= values <= 10000<br/>
 * 最多会对 appendTail、deleteHead 进行 10000 次调用<br/>
 *
 * @author admin
 */
public class Offer_09_CQueue {
    public static void main(String[] args) {
        CQueue cQueue = new CQueue();
        System.out.println(cQueue.deleteHead());
        cQueue.appendTail(5);
        cQueue.appendTail(2);
        System.out.println(cQueue.deleteHead());
        System.out.println(cQueue.deleteHead());
    }
    static class CQueue {
        private Stack<Integer> addStack;
        private Stack<Integer> deleteStack;

        public CQueue() {
            this.addStack = new Stack<>();
            this.deleteStack = new Stack<>();
        }

        public void appendTail(int value) {
            addStack.push(value);
        }

        public int deleteHead() {
            if (deleteStack.empty()) {
                if (addStack.empty()) {
                    return -1;
                } else {
                    while (!addStack.empty()) {
                        deleteStack.push(addStack.pop());
                    }
                    return deleteStack.pop();
                }
            } else {
                return deleteStack.peek();
            }
        }
        // /**
        //  * 相较于上面直接if判断，下面这种通过while循环判断，然后三母运算符判断，
        //  * 虽然逻辑上一样，代码上更少，但是实际运行效率查了上面的代码十几秒
        //  */
        // public int deleteHead() {
        //     if (deleteStack.empty()) {
        //         while (!addStack.empty()) {
        //             deleteStack.push(addStack.pop());
        //         }
        //         return deleteStack.empty() ? -1 : deleteStack.pop();
        //     } else {
        //         return deleteStack.pop();
        //     }
        // }
    }
}

