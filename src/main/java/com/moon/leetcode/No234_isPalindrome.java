package com.moon.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * 234. 回文链表<br/>
 * 请判断一个链表是否为回文链表。<br/>
 * <br/>
 * 示例 1:<br/>
 * 输入: 1->2<br/>
 * 输出: false<br/>
 * <br/>
 * 示例 2:<br/>
 * 输入: 1->2->2->1<br/>
 * 输出: true<br/>
 * <br/>
 * 进阶：<br/>
 * 你能否用 O(n) 时间复杂度和 O(1) 空间复杂度解决此题？<br/>
 */
public class No234_isPalindrome {
    /**
     * 数组+双指针
     */
    public static boolean isPalindrome(ListNode head) {
        if (head == null || head.next == null) {
            return true;
        }
        List<Integer> list = new ArrayList<>();
        while (head != null) {
            list.add(head.val);
            head = head.next;
        }
        // int len = list.size() - 1;
        // for (int i = 0; i <= len; i++) {
        //     if (!list.get(i).equals(list.get(len - i))) {
        //         return false;
        //     }
        // }
        int left = 0;
        int right = list.size() - 1;
        while (left < right) {
            if (!list.get(left).equals(list.get(right))) {
                return false;
            }
            left++;
            right--;
        }
        return true;
    }

    static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }

    public static void main(String[] args) {
        ListNode head = new ListNode(0);
        // ListNode temp = head;
        // for (int i = 1; i < 2; i++) {
        //     temp.next = new ListNode(i);
        //     temp = temp.next;
        // }
        System.out.println(isPalindrome(head));
    }
}
