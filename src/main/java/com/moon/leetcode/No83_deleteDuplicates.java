package com.moon.leetcode;

import java.util.HashSet;
import java.util.Set;

/**
 * 83. 删除排序链表中的重复元素<br/>
 * 给定一个排序链表，删除所有重复的元素，使得每个元素只出现一次。<br/>
 * <br/>
 * 示例 1:<br/>
 * 输入: 1->1->2<br/>
 * 输出: 1->2<br/>
 * <br/>
 * 示例 2:<br/>
 * 输入: 1->1->2->3->3<br/>
 * 输出: 1->2->3<br/>
 */
public class No83_deleteDuplicates {
    /**
     * 循环替换<br/>
     * 当下一个的值和当前的值相同时，丢掉下一个，
     */
    public ListNode deleteDuplicates(ListNode head) {
        if (head == null) {
            return null;
        }
        ListNode temp = head;
        while (temp.next != null) {
            if (temp.val == temp.next.val) {
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
