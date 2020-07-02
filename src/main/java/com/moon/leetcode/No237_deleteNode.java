package com.moon.leetcode;

/**
 * 237. 删除链表中的节点<br/>
 * 请编写一个函数，使其可以删除某个链表中给定的（非末尾）节点，你将只被给定要求被删除的节点。<br/>
 * 现有一个链表 -- head = [4,5,1,9]，它可以表示为:<br/>
 * <br/>
 * 示例 1:<br/>
 * 输入: head = [4,5,1,9], node = 5<br/>
 * 输出: [4,1,9]<br/>
 * 解释: 给定你链表中值为 5 的第二个节点，那么在调用了你的函数之后，该链表应变为 4 -> 1 -> 9.<br/>
 * <br/>
 * 示例 2:<br/>
 * 输入: head = [4,5,1,9], node = 1<br/>
 * 输出: [4,5,9]<br/>
 * 解释: 给定你链表中值为 1 的第三个节点，那么在调用了你的函数之后，该链表应变为 4 -> 5 -> 9.<br/>
 * <br/>
 * 说明:<br/>
 * 链表至少包含两个节点。<br/>
 * 链表中所有节点的值都是唯一的。<br/>
 * 给定的节点为非末尾节点并且一定是链表中的一个有效节点。<br/>
 * 不要从你的函数中返回任何结果。<br/>
 */
public class No237_deleteNode {
    /**
     * 神经病，这题描述，入参node就是要删除的节点，因为不知道前驱结点，所以删除就只能在后面想办法，
     * 所以变通一下，把node的下一个节点的值复制到node，然后把下一个节点删除即可
     *
     */
    public void deleteNode(ListNode node) {
        node.val = node.next.val;
        node.next = node.next.next;
    }

    static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }
}
