package com.moon.leetcode;

import java.util.ArrayDeque;
import java.util.Deque;

// 剑指 Offer 06. 从尾到头打印链表
//
//输入一个链表的头节点，从尾到头反过来返回每个节点的值（用数组返回）。
//
// 示例 1：
//
// 输入：head = [1,3,2]
//输出：[2,3,1]
//
// 限制：
//
// 0 <= 链表长度 <= 10000
// Related Topics 链表
// 👍 130 👎 0
public class Offer_06_reversePrint {
    public int[] reversePrint(ListNode head) {
        ListNode node = head;
        Deque<Integer> stack = new ArrayDeque<>();
        while (node.next != null) {
            stack.push(node.val);
            node = node.next;
        }
        return stack.stream().mapToInt(p -> p).toArray();
    }

    private static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }
}
