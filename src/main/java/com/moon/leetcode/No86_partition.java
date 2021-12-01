package com.moon.leetcode;

// 86. 分隔链表
//给你一个链表的头节点 head 和一个特定值 x ，请你对链表进行分隔，使得所有 小于 x 的节点都出现在 大于或等于 x 的节点之前。
//
// 你应当 保留 两个分区中每个节点的初始相对位置。
//
//
//
// 示例 1：
//
//
//输入：head = [1,4,3,2,5,2], x = 3
//输出：[1,2,2,4,3,5]
//
//
// 示例 2：
//
//
//输入：head = [2,1], x = 2
//输出：[1,2]
//
//
//
//
// 提示：
//
//
// 链表中节点的数目在范围 [0, 200] 内
// -100 <= Node.val <= 100
// -200 <= x <= 200
//
// Related Topics 链表 双指针
// 👍 489 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
public class No86_partition {

    public ListNode partition(ListNode head, int x) {
        if (head == null) {
            return null;
        }
        ListNode small = new ListNode();
        ListNode large = new ListNode();
        ListNode s = small;
        ListNode l = large;
        while (head != null) {
            if (head.val < x) {
                s.next = head;
                s = head;
            } else {
                l.next = head;
                l = head;
            }
            head = head.next;
        }
        s.next = large.next;
        l.next = null;
        return small.next;
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
