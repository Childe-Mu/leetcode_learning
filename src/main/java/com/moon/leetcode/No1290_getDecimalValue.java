package com.moon.leetcode;

/**
 * 1290. 二进制链表转整数<br/>
 * 给你一个单链表的引用结点 head。链表中每个结点的值不是 0 就是 1。已知此链表是一个整数数字的二进制表示形式。<br/>
 * 请你返回该链表所表示数字的 十进制值 。<br/>
 * <br/>
 * 示例 1：<br/>
 * 输入：head = [1,0,1]<br/>
 * 输出：5<br/>
 * 解释：二进制数 (101) 转化为十进制数 (5)<br/>
 * <br/>
 * 示例 2：<br/>
 * 输入：head = [0]<br/>
 * 输出：0<br/>
 * <br/>
 * 示例 3：<br/>
 * 输入：head = [1]<br/>
 * 输出：1<br/>
 * <br/>
 * 示例 4：<br/>
 * 输入：head = [1,0,0,1,0,0,1,1,1,0,0,0,0,0,0]<br/>
 * 输出：18880<br/>
 * <br/>
 * 示例 5：<br/>
 * 输入：head = [0,0]<br/>
 * 输出：0<br/>
 * <br/>
 * 提示：<br/>
 * 链表不为空。<br/>
 * 链表的结点总数不超过 30。<br/>
 * 每个结点的值不是 0 就是 1。<br/>
 */
public class No1290_getDecimalValue {
    // /**
    //  * 利用现成的api
    //  */
    // public int getDecimalValue(ListNode head) {
    //     StringBuilder val = new StringBuilder();
    //     while (head != null) {
    //         val.append(head.val);
    //         head = head.next;
    //     }
    //     return Integer.parseInt(val.toString(), 2);
    // }
    //
    /**
     * 链表移动到右侧下一个节点的过程，其实就是二进制数左移1位的结果。
     */
    public int getDecimalValue(ListNode head) {
        ListNode cur = head;
        int ans = 0;
        while (cur != null) {
            ans <<= 1;
            ans += cur.val;
            cur = cur.next;
        }
        return ans;
    }

    class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }
}
