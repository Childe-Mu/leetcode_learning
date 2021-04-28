package com.moon.leetcode;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

// 剑指 Offer 35. 复杂链表的复制
//
//请实现 copyRandomList 函数，复制一个复杂链表。在复杂链表中，每个节点除了有一个 next 指针指向下一个节点，还有一个 random 指针指
//向链表中的任意节点或者 null。
//
// 示例 1：
//
// 输入：head = [[7,null],[13,0],[11,4],[10,2],[1,0]]
//输出：[[7,null],[13,0],[11,4],[10,2],[1,0]]
//
// 示例 2：
//
// 输入：head = [[1,1],[2,1]]
//输出：[[1,1],[2,1]]
//
// 示例 3：
//
// 输入：head = [[3,null],[3,0],[3,null]]
//输出：[[3,null],[3,0],[3,null]]
//
// 示例 4：
//
// 输入：head = []
//输出：[]
//解释：给定的链表为空（空指针），因此返回 null。
//
// 提示：
//
// -10000 <= Node.val <= 10000
// Node.random 为空（null）或指向链表中的节点。
// 节点数目不超过 1000 。
//
// 注意：本题与主站 138 题相同：https://leetcode-cn.com/problems/copy-list-with-random-pointer/
//
// Related Topics 链表
// 👍 197 👎 0
public class Offer_35_copyRandomList {
    public static void main(String[] args) {
        LinkedHashMap<Node, Node> map = new LinkedHashMap<>();
        System.out.println(map.get((null)));
    }

    public Node copyRandomList_v1(Node head) {
        if (head == null) {
            return null;
        }
        Node dummy = new Node(-1);
        Node pre = dummy;
        Map<Node, Node> map = new HashMap<>();
        while (head != null) {
            pre.next = new Node(head.val);
            map.put(head, pre.next);
            pre = pre.next;
            head = head.next;
        }
        for (Map.Entry<Node, Node> entry : map.entrySet()) {
            Node oldRandom = entry.getKey().random;
            Node newNode = entry.getValue();
            newNode.random = map.get(oldRandom);
        }
        return dummy.next;
    }

    public Node copyRandomList_v2(Node head) {
        if (head == null) return null;
        Node cur = head;
        // 1. 复制各节点，并构建拼接链表
        while (cur != null) {
            Node tmp = new Node(cur.val);
            tmp.next = cur.next;
            cur.next = tmp;
            cur = tmp.next;
        }
        // 2. 构建各新节点的 random 指向
        cur = head;
        while (cur != null) {
            if (cur.random != null)
                cur.next.random = cur.random.next;
            cur = cur.next.next;
        }
        // 3. 拆分两链表
        cur = head.next;
        Node pre = head, res = head.next;
        while (cur.next != null) {
            pre.next = pre.next.next;
            cur.next = cur.next.next;
            pre = pre.next;
            cur = cur.next;
        }
        pre.next = null; // 单独处理原链表尾节点
        return res;      // 返回新链表头节点
    }

    private static class Node {
        int val;
        Node next;
        Node random;

        public Node(int val) {
            this.val = val;
            this.next = null;
            this.random = null;
        }
    }
}
