package com.moon.leetcode;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * 61. 旋转链表
 * 给你一个链表的头节点 head ，旋转链表，将链表每个节点向右移动 k 个位置。
 * <p>
 * 示例 1：
 * <p>
 * 输入：head = [1,2,3,4,5], k = 2
 * 输出：[4,5,1,2,3]
 * 示例 2：
 * <p>
 * 输入：head = [0,1,2], k = 4
 * 输出：[2,0,1]
 * <p>
 * 提示：
 * <p>
 * 链表中节点的数目在范围 [0, 500] 内
 * -100 <= Node.val <= 100
 * 0 <= k <= 2 * 109
 */
public class No61_rotateRight {
    public ListNode rotateRight_v1(ListNode head, int k) {
        if (k == 0 || head == null || head.next == null) {
            return head;
        }
        Deque<ListNode> stack = new ArrayDeque<>();
        while (head.next != null) {
            stack.addLast(head);
            head = head.next;
        }
        int size = stack.size();
        int mod = k % size;
        if (mod != 0) {
            for (int i = 0; i < mod; i++) {
                ListNode node = stack.pollLast();
                stack.peekLast().next = null;
                node.next = stack.getFirst();
                stack.addFirst(node);
            }
        }
        return stack.getFirst();
    }

    public ListNode rotateRight_v2(ListNode head, int k) {
        if (k == 0 || head == null || head.next == null) {
            return head;
        }
        int n = 1;
        ListNode iter = head;
        while (iter.next != null) {
            iter = iter.next;
            n++;
        }
        int add = n - k % n;
        if (add == n) {
            return head;
        }
        iter.next = head;
        while (add-- > 0) {
            iter = iter.next;
        }
        ListNode ret = iter.next;
        iter.next = null;
        return ret;
    }

    private static class ListNode {
        int val;
        ListNode next;

        ListNode() {
        }

        ListNode(int val) {
            this.val = val;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }
}
