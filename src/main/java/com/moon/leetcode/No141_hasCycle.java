package com.moon.leetcode;

/**
 * 141. 环形链表<br/>
 *
 * <b>!!!注意，这里说的环，和第160道题中说的交点是一个意思，就是一定要内存地址相同，妈的，这破描述。</b><br/>
 * <p>
 * 给定一个链表，判断链表中是否有环。<br/>
 * 为了表示给定链表中的环，我们使用整数 pos 来表示链表尾连接到链表中的位置（索引从 0 开始）。
 * 如果 pos 是 -1，则在该链表中没有环。<br/>
 * <br/>
 * 示例 1：<br/>
 * 输入：head = [3,2,0,-4], pos = 1<br/>
 * 输出：true<br/>
 * 解释：链表中有一个环，其尾部连接到第二个节点。<br/>
 * <br/>
 * 示例 2：<br/>
 * 输入：head = [1,2], pos = 0<br/>
 * 输出：true<br/>
 * 解释：链表中有一个环，其尾部连接到第一个节点。<br/>
 * <br/>
 * 示例 3：<br/>
 * 输入：head = [1], pos = -1<br/>
 * 输出：false<br/>
 * 解释：链表中没有环。<br/>
 * <br/>
 * 进阶：<br/>
 * 你能用 O(1)（即，常量）内存解决此问题吗？<br/>
 */
public class No141_hasCycle {
    // /**
    //  * 通过hashSet来判断是否有重复的节点，有则说明有交点，有环
    //  */
    // public boolean hasCycle(ListNode head) {
    //     Set<ListNode> set = new HashSet<>();
    //     while (head != null) {
    //         if (set.contains(head)) {
    //             return true;
    //         }
    //         set.add(head);
    //         head = head.next;
    //     }
    //     return false;
    // }

    /**
     * 双指针<br/>
     * 通过使用具有 不同速度 的快、慢两个指针遍历链表，空间复杂度可以被降低至 O(1)O(1)。
     * 慢指针每次移动一步，而快指针每次移动两步。<br/>
     * 如果列表中不存在环，最终快指针将会最先到达尾部，此时我们可以返回 false。<br/>
     * 现在考虑一个环形链表，把慢指针和快指针想象成两个在环形赛道上跑步的运动员（分别称之为慢跑者与快跑者）。
     * 而快跑者最终一定会追上慢跑者。这是为什么呢？考虑下面这种情况（记作情况 A）- 假如快跑者只落后慢跑者一步，
     * 在下一次迭代中，它们就会分别跑了一步或两步并相遇。<br/>
     * 其他情况又会怎样呢？例如，我们没有考虑快跑者在慢跑者之后两步或三步的情况。但其实不难想到，
     * 因为在下一次或者下下次迭代后，又会变成上面提到的情况 A。<br/>
     * <br/>
     * 把慢跑者视作参考系，这样来思考，慢跑者站着不动，快跑者速度为1，就会发现一定会相遇<br/>
     */
    public boolean hasCycle(ListNode head) {
        if (head == null || head.next == null) {
            return false;
        }
        ListNode slow = head;
        // 让快跑者先走一步，进入环中转圈，等待慢跑者进入环
        ListNode fast = head.next;
        while (slow != fast) {
            // 因为每次走两步，所以一定要判断自身和下一个是否为空。
            if (fast == null || fast.next == null) {
                return false;
            }
            // 慢跑者每次走一步
            slow = slow.next;
            // 快跑者每次走一步
            fast = fast.next.next;
        }
        return true;
    }

    static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
            next = null;
        }
    }
}
