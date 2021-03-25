package com.moon.leetcode;

/**
 * 82. 删除排序链表中的重复元素 II
 * <p>
 * 存在一个按升序排列的链表，给你这个链表的头节点 head ，请你删除链表中所有存在数字重复情况的节点，只保留原始链表中 没有重复出现 的数字。
 * <p>
 * 返回同样按升序排列的结果链表。
 * <p>
 * 示例 1：
 * <p>
 * 输入：head = [1,2,3,3,4,4,5]
 * 输出：[1,2,5]
 * <p>
 * 示例 2：
 * <p>
 * 输入：head = [1,1,1,2,3]
 * 输出：[2,3]
 * <p>
 * 提示：
 * <p>
 * 链表中节点数目在范围 [0, 300] 内
 * -100 <= Node.val <= 100
 * 题目数据保证链表已经按升序排列
 */
public class No82_deleteDuplicates {
    public static ListNode deleteDuplicates_v1(ListNode head) {
        if (head == null) {
            return null;
        }
        ListNode tag = new ListNode(101);
        tag.next = head;
        ListNode prev = head;
        ListNode retain = tag;
        while (prev != null && prev.next != null) {
            ListNode cur = prev.next;
            if (prev.val != cur.val) {
                retain.next = prev;
                retain = prev;
            } else {
                while (cur.next != null && cur.val == cur.next.val) {
                    cur = cur.next;
                }
                cur = cur.next;
            }
            prev = cur;
        }
        retain.next = prev;
        return tag.next;
    }

    public static ListNode deleteDuplicates_v2(ListNode head) {
        if (head == null) {
            return null;
        }
        ListNode dummy = new ListNode(0, head);
        ListNode cur = dummy;
        while (cur.next != null && cur.next.next != null) {
            if (cur.next.val == cur.next.next.val) {
                int value = cur.next.val;
                while (cur.next != null && cur.next.val == value) {
                    cur.next = cur.next.next;
                }
            } else {
                cur = cur.next;
            }
        }
        return dummy.next;
    }

    public static void main(String[] args) {
        int[] a = new int[]{1, 1};
        ListNode head = new ListNode(101);
        ListNode pre = head;
        for (int i : a) {
            pre.next = new ListNode(i);
            pre = pre.next;
        }
        System.out.println(deleteDuplicates_v1(head.next));
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

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append(this.val);
            ListNode t = next;
            while (t != null) {
                sb.append('-').append(t.val);
                t = t.next;
            }
            return sb.toString();
        }
    }
}
