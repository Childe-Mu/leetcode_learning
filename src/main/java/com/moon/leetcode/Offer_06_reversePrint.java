package com.moon.leetcode;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.List;

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
    public static void main(String[] args) {
        ListNode head = new ListNode(2);
        head.next = new ListNode(3);
        head.next.next = new ListNode(1);
        System.out.println(Arrays.toString(new Offer_06_reversePrint().reversePrint_v2(head)));
    }

    public int[] reversePrint_v1(ListNode head) {
        ListNode node = head;
        Deque<Integer> stack = new ArrayDeque<>();
        while (node.next != null) {
            stack.push(node.val);
            node = node.next;
        }
        return stack.stream().mapToInt(p -> p).toArray();
    }

    public int[] reversePrint_v2(ListNode head) {
        List<Integer> res = new ArrayList<>();
        traverse(head, res);
        return res.stream().mapToInt(p -> p).toArray();
    }

    private void traverse(ListNode node, List<Integer> res) {
        if (node == null) {
            return;
        }
        traverse(node.next, res);
        res.add(node.val);
    }

    private static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            this.val = x;
        }

        ListNode(int x, ListNode next) {
            this.val = x;
            this.next = next;
        }
    }
}
