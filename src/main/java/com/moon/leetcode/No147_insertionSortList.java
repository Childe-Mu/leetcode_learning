package com.moon.leetcode;

// 147. 对链表进行插入排序
//对链表进行插入排序。
//
//
//插入排序的动画演示如上。从第一个元素开始，该链表可以被认为已经部分排序（用黑色表示）。
//每次迭代时，从输入数据中移除一个元素（用红色表示），并原地将其插入到已排好序的链表中。
//
//
//
// 插入排序算法：
//
//
// 插入排序是迭代的，每次只移动一个元素，直到所有元素可以形成一个有序的输出列表。
// 每次迭代中，插入排序只从输入数据中移除一个待排序的元素，找到它在序列中适当的位置，并将其插入。
// 重复直到所有输入数据插入完为止。
//
//
//
//
// 示例 1：
//
// 输入: 4->2->1->3
//输出: 1->2->3->4
//
//
// 示例 2：
//
// 输入: -1->5->3->4->0
//输出: -1->0->3->4->5
//
// Related Topics 链表 排序
// 👍 453 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
public class No147_insertionSortList {

    public ListNode insertionSortList(ListNode head) {
        ListNode pre = new ListNode();
        pre.next = head;
        head = head.next;
        pre.next.next = null;
        while (head != null) {
            ListNode t = head;
            head = head.next;
            t.next = null;
            insert(pre, t);
        }
        return pre.next;
    }

    private void insert(ListNode pre, ListNode t) {
        while (pre.next != null && pre.next.val < t.val) {
            pre = pre.next;
        }
        t.next = pre.next;
        pre.next = t;
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
