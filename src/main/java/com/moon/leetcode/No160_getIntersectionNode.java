package com.moon.leetcode;

/**
 * 160. 相交链表<br/>
 * 编写一个程序，找到两个单链表相交的起始节点。<br/>
 * 如下面的两个链表：<br/>
 * 在节点 c1 开始相交。<br/>
 * <br/>
 * 示例 1：<br/>
 * 输入：intersectVal = 8, listA = [4,1,8,4,5], listB = [5,0,1,8,4,5], skipA = 2, skipB = 3<br/>
 * 输出：Reference of the node with value = 8<br/>
 * 输入解释：相交节点的值为 8 （注意，如果两个链表相交则不能为 0）。从各自的表头开始算起，链表 A 为 [4,1,8,4,5]，
 * 链表 B 为 [5,0,1,8,4,5]。在 A 中，相交节点前有 2 个节点；在 B 中，相交节点前有 3 个节点。<br/>
 *  <br/>
 * 示例 2：<br/>
 * 输入：intersectVal = 2, listA = [0,9,1,2,4], listB = [3,2,4], skipA = 3, skipB = 1<br/>
 * 输出：Reference of the node with value = 2<br/>
 * 输入解释：相交节点的值为 2 （注意，如果两个链表相交则不能为 0）。从各自的表头开始算起，
 * 链表 A 为 [0,9,1,2,4]，链表 B 为 [3,2,4]。在 A 中，相交节点前有 3 个节点；在 B 中，相交节点前有 1 个节点。<br/>
 * <br/>
 * 示例 3：<br/>
 * 输入：intersectVal = 0, listA = [2,6,4], listB = [1,5], skipA = 3, skipB = 2<br/>
 * 输出：null<br/>
 * 输入解释：从各自的表头开始算起，链表 A 为 [2,6,4]，链表 B 为 [1,5]。由于这两个链表不相交，
 * 所以 intersectVal 必须为 0，而 skipA 和 skipB 可以是任意值。<br/>
 * 解释：这两个链表不相交，因此返回 null。<br/>
 *  <br/>
 * 注意：<br/>
 * 如果两个链表没有交点，返回 null.<br/>
 * 在返回结果后，两个链表仍须保持原有的结构。<br/>
 * 可假定整个链表结构中没有循环。<br/>
 * 程序尽量满足 O(n) 时间复杂度，且仅用 O(1) 内存。<br/>
 */
public class No160_getIntersectionNode {
    // /**
    //  * 暴力法， 注意，这里说的交点，是两个节点完全一样，即是同一个节点，节点的内存地址一样，而不是值相同，坑爹！！！
    //  */
    // public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
    //     ListNode a = headA, b = headB;
    //     while (a != null) {
    //         while (b != null) {
    //             if (a == b) {
    //                 return a;
    //             }
    //             b = b.next;
    //         }
    //         a = a.next;
    //         // 这里一定要给b赋值headB，因为前面的b已经转到链表末尾了
    //         b = headB;
    //     }
    //     return null;
    // }

    /**
     * 双指针法<br/>
     * 创建两个指针 pApA 和 pBpB，分别初始化为链表 A 和 B 的头结点。然后让它们向后逐结点遍历。<br/>
     * 当 pApA 到达链表的尾部时，将它重定位到链表 B 的头结点 (你没看错，就是链表 B);
     * 类似的，当 pBpB 到达链表的尾部时，将它重定位到链表 A 的头结点。<br/>
     * 若在某一时刻 pApA 和 pBpB 相遇，则 pApA/pBpB 为相交结点。<br/>
     * 想弄清楚为什么这样可行, 可以考虑以下两个链表: A={1,3,5,7,9,11} 和 B={2,4,9,11}，相交于结点 9。
     * 由于 B.length (=4) < A.length (=6)，pBpB 比 pApA 少经过 22 个结点，会先到达尾部。将 pBpB
     * 重定向到 A 的头结点，pApA 重定向到 B 的头结点后，pBpB 要比 pApA 多走 2 个结点。因此，它们会同时到达交点。<br/>
     * 如果两个链表存在相交，它们末尾的结点必然相同。因此当 pApA/pBpB 到达链表结尾时，记录下链表 A/B 对应的元素。
     * 若最后元素不相同，则两个链表不相交。<br/>
     */
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        if (headA == null || headB == null) {
            return null;
        }
        ListNode aPointer = headA;
        ListNode bPointer = headB;
        while (aPointer != bPointer) {
            aPointer = (aPointer == null ? headB : aPointer.next);
            bPointer = (bPointer == null ? headA : bPointer.next);
        }
        return aPointer;
    }
}

class ListNode {
    int val;
    ListNode next;

    ListNode(int x) {
        val = x;
        next = null;
    }
}