package com.moon.leetcode;

/**
 * 206. 反转链表<br/>
 * 反转一个单链表。<br/>
 * <br/>
 * 示例:<br/>
 * 输入: 1->2->3->4->5->NULL<br/>
 * 输出: 5->4->3->2->1->NULL<br/>
 * 进阶:<br/>
 * 你可以迭代或递归地反转链表。你能否用两种方法解决这道题？<br/>
 */
public class No206_reverseList {
    // /**
    //  * 栈，翻转第一反应就是用栈
    //  */
    // public static ListNode reverseList(ListNode head) {
    //     if (head == null || head.next == null) {
    //         return head;
    //     }
    //     Stack<ListNode> stack = new Stack<>();
    //     ListNode temp = head;
    //     while (temp != null) {
    //         stack.push(temp);
    //         temp = temp.next;
    //     }
    //     temp = stack.pop();
    //     head = temp;
    //     while (!stack.empty()) {
    //         temp.next = stack.pop();
    //         temp = temp.next;
    //         temp.next = null;
    //     }
    //     return head;
    // }

    // /**
    //  * 递归，第二反应是用递归，但容易翻车，递着递着，就不知道归到哪去。但实际上这种方法最简单省事
    //  */
    // public static ListNode reverseList(ListNode head) {
    //     if (head == null || head.next == null){
    //         return head;
    //     }
    //     ListNode p = reverseList(head.next);
    //     head.next.next = head;
    //     head.next = null;
    //     return p;
    // }

    /**
     * 迭代。。。没考虑过，虽然凡是能用递归的地方，都可以用迭代，但是能用递归的地方最好不要用迭代，这玩意逻辑比递归还混乱
     */
    public static ListNode reverseList(ListNode head) {
        ListNode prev = null;
        ListNode curr = head;
        while (curr != null) {
            ListNode nextTemp = curr.next;
            curr.next = prev;
            prev = curr;
            curr = nextTemp;
        }
        return prev;
    }

    static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }

        @Override
        public String toString() {
            return val + "-";
        }
    }

    public static void main(String[] args) {
        ListNode head = new ListNode(0);
        ListNode temp = head;
        for (int i = 1; i < 5; i++) {
            temp.next = new ListNode(i);
            temp = temp.next;
        }
        ListNode re = reverseList(head);
        while (re != null) {
            System.out.print(re);
            re = re.next;
        }
        System.out.println();
    }
}
