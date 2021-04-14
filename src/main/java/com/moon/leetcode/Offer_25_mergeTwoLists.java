package com.moon.leetcode;

// 剑指 Offer 25. 合并两个排序的链表
//
//输入两个递增排序的链表，合并这两个链表并使新链表中的节点仍然是递增排序的。
//
// 示例1：
//
// 输入：1->2->4, 1->3->4
//输出：1->1->2->3->4->4
//
// 限制：
//
// 0 <= 链表长度 <= 1000
//
// 注意：本题与主站 21 题相同：https://leetcode-cn.com/problems/merge-two-sorted-lists/
// Related Topics 分治算法
// 👍 111 👎 0
public class Offer_25_mergeTwoLists {
    public ListNode mergeTwoLists_v1(ListNode l1, ListNode l2) {
        ListNode dummy = new ListNode(-1);
        ListNode head = dummy;
        ListNode p = l1, q = l2;
        while (p != null && q != null) {
            if (p.val < q.val) {
                head.next = p;
                p = p.next;
                head = head.next;
            } else {
                head.next = q;
                q = q.next;
                head = head.next;
            }
        }
        if (q != null) {
            head.next = q;
        }
        if (p != null) {
            head.next = p;
        }
        return dummy.next;
    }

    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        if (l1 == null || l2 == null) {
            return l1 != null ? l1 : l2;
        }
        if (l1.val <= l2.val) {
            l1.next = mergeTwoLists(l1.next, l2);
            return l1;
        } else {
            l2.next = mergeTwoLists(l1, l2.next);
            return l2;
        }
    }

    private static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }
}
