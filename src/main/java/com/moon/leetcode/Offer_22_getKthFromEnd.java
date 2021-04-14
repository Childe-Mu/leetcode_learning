package com.moon.leetcode;

// 剑指 Offer 22. 链表中倒数第k个节点
//
//输入一个链表，输出该链表中倒数第k个节点。为了符合大多数人的习惯，本题从1开始计数，即链表的尾节点是倒数第1个节点。
//
// 例如，一个链表有 6 个节点，从头节点开始，它们的值依次是 1、2、3、4、5、6。这个链表的倒数第 3 个节点是值为 4 的节点。
//
// 示例：
//
//给定一个链表: 1->2->3->4->5, 和 k = 2.
//
//返回链表 4->5.
// Related Topics 链表 双指针
// 👍 177 👎 0
public class Offer_22_getKthFromEnd {
    public ListNode getKthFromEnd(ListNode head, int k) {
        ListNode h = head;
        ListNode t;
        while (k > 0 && head != null) {
            head = head.next;
            k--;
        }
        if (k > 0) {
            return null;
        }
        t = head;
        while (t != null) {
            t = t.next;
            h = h.next;
        }
        return h;
    }

    private static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }
}
