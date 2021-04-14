package com.moon.leetcode;

// 剑指 Offer 24. 反转链表
//
//定义一个函数，输入一个链表的头节点，反转该链表并输出反转后链表的头节点。
//
// 示例:
//
// 输入: 1->2->3->4->5->NULL
//输出: 5->4->3->2->1->NULL
//
// 限制：
//
// 0 <= 节点个数 <= 5000
//
// 注意：本题与主站 206 题相同：https://leetcode-cn.com/problems/reverse-linked-list/
// Related Topics 链表
// 👍 219 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
public class Offer_24_reverseList {

    public ListNode reverseList_v1(ListNode head) {
        ListNode pre = null;
        ListNode cur = head;
        while (cur != null) {
            ListNode next = cur.next;
            cur.next = pre;
            pre = cur;
            cur = next;
        }
        return pre;
    }

    public ListNode reverseList(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode newHead = reverseList(head.next);
        head.next.next = head;
        head.next = null;
        return newHead;
    }

    private static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }
}
