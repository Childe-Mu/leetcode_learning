package com.moon.leetcode;

/**
 * 876. 链表的中间结点<br/>
 * 给定一个带有头结点 head 的非空单链表，返回链表的中间结点。<br/>
 * 如果有两个中间结点，则返回第二个中间结点。<br/>
 * <br/>
 * 示例 1：<br/>
 * 输入：[1,2,3,4,5]<br/>
 * 输出：此列表中的结点 3 (序列化形式：[3,4,5])<br/>
 * 返回的结点值为 3 。 (测评系统对该结点序列化表述是 [3,4,5])。<br/>
 * 注意，我们返回了一个 ListNode 类型的对象 ans，这样：<br/>
 * ans.val = 3, ans.next.val = 4, ans.next.next.val = 5, 以及 ans.next.next.next = NULL.<br/>
 * <br/>
 * 示例 2：<br/>
 * 输入：[1,2,3,4,5,6]<br/>
 * 输出：此列表中的结点 4 (序列化形式：[4,5,6])<br/>
 * 由于该列表有两个中间结点，值分别为 3 和 4，我们返回第二个结点。<br/>
 * <br/>
 * 提示：<br/>
 * 给定链表的结点数介于 1 和 100 之间。<br/>
 */
public class No876_middleNode {
    // /**
    //  * 数组
    //  */
    // public ListNode middleNode(ListNode head) {
    //     List<ListNode> list = new ArrayList<>();
    //     while (head != null) {
    //         list.add(head);
    //         head = head.next;
    //     }
    //     return list.get(list.size() / 2);
    // }

    // /**
    //  * 遍历两次，第一次统计大小，第二次遍历一半找中间数
    //  */
    // public ListNode middleNode(ListNode head) {
    //     int n = 0;
    //     ListNode cur = head;
    //     while (cur != null) {
    //         ++n;
    //         cur = cur.next;
    //     }
    //     int k = 0;
    //     cur = head;
    //     while (k < n / 2) {
    //         ++k;
    //         cur = cur.next;
    //     }
    //     return cur;
    // }

    /**
     * 快慢指针法<br/>
     * 用两个指针 slow 与 fast 一起遍历链表。slow 一次走一步，fast 一次走两步。
     * 那么当 fast 到达链表的末尾时，slow 必然位于中间。<br/>
     * 厉害厉害
     */
    public ListNode middleNode(ListNode head) {
        ListNode slow = head, fast = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }

    static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }

    public static void main(String[] args) {
        System.out.println(11 / 2);
    }
}
