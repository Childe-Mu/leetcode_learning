package com.moon.leetcode;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * 23. 合并K个升序链表
 * <p>
 * /**
 * <p>给你一个链表数组，每个链表都已经按升序排列。</p>
 *
 * <p>请你将所有链表合并到一个升序链表中，返回合并后的链表。</p>
 *
 * <p>&nbsp;</p>
 *
 * <p><strong>示例 1：</strong></p>
 *
 * <pre><strong>输入：</strong>lists = [[1,4,5],[1,3,4],[2,6]]
 * <strong>输出：</strong>[1,1,2,3,4,4,5,6]
 * <strong>解释：</strong>链表数组如下：
 * [
 * 1-&gt;4-&gt;5,
 * 1-&gt;3-&gt;4,
 * 2-&gt;6
 * ]
 * 将它们合并到一个有序链表中得到。
 * 1-&gt;1-&gt;2-&gt;3-&gt;4-&gt;4-&gt;5-&gt;6
 * </pre>
 *
 * <p><strong>示例 2：</strong></p>
 *
 * <pre><strong>输入：</strong>lists = []
 * <strong>输出：</strong>[]
 * </pre>
 * <p>
 * p><strong>示例 3：</strong></p>
 * <p>
 * pre><strong>输入：</strong>lists = [[]]
 * <strong>输出：</strong>[]
 * </pre>
 * <p>
 * p>&nbsp;</p>
 * <p>
 * p><strong>提示：</strong></p>
 *
 * <ul>
 * <li><code>k == lists.length</code></li>
 * <li><code>0 &lt;= k &lt;= 10^4</code></li>
 * <li><code>0 &lt;= lists[i].length &lt;= 500</code></li>
 * <li><code>-10^4 &lt;= lists[i][j] &lt;= 10^4</code></li>
 * <li><code>lists[i]</code> 按 <strong>升序</strong> 排列</li>
 * <li><code>lists[i].length</code> 的总和不超过 <code>10^4</code></li>
 * </ul>
 * <div><div>Related Topics</div><div><li>链表</li><li>分治</li><li>堆（优先队列）</li><li>归并排序</li></div></div><br><div><li>👍 1550</li><li>👎 0</li></div>
 */
public class No23_mergeKLists {
    public ListNode mergeKLists_v1(ListNode[] lists) {
        PriorityQueue<ListNode> queue = new PriorityQueue<>(Comparator.comparingInt(a -> a.val));
        for (ListNode node : lists) {
            if (node != null) {
                queue.offer(node);
            }
        }
        ListNode dummy = new ListNode();
        ListNode cur = dummy;
        while (!queue.isEmpty()) {
            ListNode node = queue.poll();
            cur.next = node;
            if (node.next != null) {
                queue.offer(node.next);
            }
            cur = cur.next;
        }
        cur.next = null;
        return dummy.next;
    }

    public ListNode mergeKLists_v2(ListNode[] lists) {
        PriorityQueue<ListNode> queue = new PriorityQueue<>(Comparator.comparingInt(a -> a.val));
        for (ListNode node : lists) {
            ListNode head = node;
            while (head != null) {
                queue.offer(head);
                head = head.next;
            }
        }
        ListNode dummy = new ListNode();
        ListNode cur = dummy;
        while (!queue.isEmpty()) {
            cur.next = queue.poll();
            cur = cur.next;
        }
        cur.next = null;
        return dummy.next;
    }

    public ListNode mergeKLists_v3(ListNode[] lists) {
        return merge(lists, 0, lists.length - 1);
    }

    public ListNode merge(ListNode[] lists, int l, int r) {
        if (l == r) {
            return lists[l];
        }
        if (l > r) {
            return null;
        }
        int mid = (l + r) >> 1;
        return mergeTwoLists(merge(lists, l, mid), merge(lists, mid + 1, r));
    }

    public ListNode mergeTwoLists(ListNode a, ListNode b) {
        if (a == null || b == null) {
            return a != null ? a : b;
        }
        ListNode head = new ListNode(0);
        ListNode tail = head, aPtr = a, bPtr = b;
        while (aPtr != null && bPtr != null) {
            if (aPtr.val < bPtr.val) {
                tail.next = aPtr;
                aPtr = aPtr.next;
            } else {
                tail.next = bPtr;
                bPtr = bPtr.next;
            }
            tail = tail.next;
        }
        tail.next = (aPtr != null ? aPtr : bPtr);
        return head.next;
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
