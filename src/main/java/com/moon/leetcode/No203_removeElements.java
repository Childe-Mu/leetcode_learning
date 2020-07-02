package com.moon.leetcode;

/**
 * 203. 移除链表元素<br/>
 * 删除链表中等于给定值 val 的所有节点。<br/>
 * <br/>
 * 示例:<br/>
 * 输入: 1->2->6->3->4->5->6, val = 6<br/>
 * 输出: 1->2->3->4->5<br/>
 */
public class No203_removeElements {
    public ListNode removeElements(ListNode head, int val) {
        while (head != null && head.val == val) {
            head = head.next;
        }
        ListNode temp = head;
        while (temp != null && temp.next != null) {
            if (temp.next.val == val) {
                temp.next = temp.next.next;
            } else {
                temp = temp.next;
            }
        }
        return head;
    }

    static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }
}
