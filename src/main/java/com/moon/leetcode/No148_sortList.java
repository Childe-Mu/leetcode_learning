package com.moon.leetcode;

// 148. 排序链表
//给你链表的头结点 head ，请将其按 升序 排列并返回 排序后的链表 。
//
// 进阶：
//
//
// 你可以在 O(n log n) 时间复杂度和常数级空间复杂度下，对链表进行排序吗？
//
//
//
//
// 示例 1：
//
//
//输入：head = [4,2,1,3]
//输出：[1,2,3,4]
//
//
// 示例 2：
//
//
//输入：head = [-1,5,3,4,0]
//输出：[-1,0,3,4,5]
//
//
// 示例 3：
//
//
//输入：head = []
//输出：[]
//
//
//
//
// 提示：
//
//
// 链表中节点的数目在范围 [0, 5 * 104] 内
// -105 <= Node.val <= 105
//
// Related Topics 链表 双指针 分治 排序 归并排序
// 👍 1394 👎 0


//leetcode submit region begin(Prohibit modification and deletion)

public class No148_sortList {
    public static void main(String[] args) {
        ListNode head = new ListNode(4);
        head.next = new ListNode(2);
        head.next.next = new ListNode(3);
        head.next.next.next = new ListNode(1);
        ListNode re = new No148_sortList().sortList_v1(head);
        System.out.println(re);
    }

    public ListNode sortList_v1(ListNode head) {
        if (head == null) {
            return null;
        }
        if (head.next == null) {
            return head;
        }
        ListNode mid = findMid(head);
        ListNode next = mid.next;
        mid.next = null;
        ListNode left = sortList_v1(head);
        ListNode right = sortList_v1(next);
        return merge(left, right);
    }

    private ListNode findMid(ListNode head) {
        ListNode fast = head.next;
        ListNode slow = head;
        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
        }
        return slow;
    }

    private ListNode merge(ListNode left, ListNode right) {
        ListNode dummy = new ListNode();
        ListNode pre = dummy;
        while (left != null && right != null) {
            if (left.val <= right.val) {
                pre.next = left;
                left = left.next;
            } else {
                pre.next = right;
                right = right.next;
            }
            pre = pre.next;
        }
        if (left != null) {
            pre.next = left;
        } else if (right != null) {
            pre.next = right;
        }
        return dummy.next;
    }


    public ListNode sortList_v2(ListNode head) {
        if (head == null) {
            return null;
        }
        int n = 0;
        ListNode node = head;
        while (node != null) {
            n++;
            node = node.next;
        }
        ListNode dummy = new ListNode(0, head);
        for (int subLen = 1; subLen < n; subLen <<= 1) {
            ListNode pre = dummy, cur = dummy.next;
            while (cur != null) {
                ListNode left = cur;
                for (int i = 1; i < subLen && cur.next != null; i++) {
                    cur = cur.next;
                }
                ListNode right = cur.next;
                cur.next = null;
                cur = right;
                for (int i = 1; i < subLen && cur != null && cur.next != null; i++) {
                    cur = cur.next;
                }
                ListNode next = null;
                if (cur != null) {
                    next = cur.next;
                    cur.next = null;
                }
                pre.next = merge(left, right);
                while (pre.next != null) {
                    pre = pre.next;
                }
                cur = next;
            }
        }
        return dummy.next;
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
        public String toString() {
            return String.valueOf(val);
        }
    }
}
