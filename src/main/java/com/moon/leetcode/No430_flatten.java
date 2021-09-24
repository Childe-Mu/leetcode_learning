package com.moon.leetcode;

/**
 * 430. 扁平化多级双向链表
 * <p>多级双向链表中，除了指向下一个节点和前一个节点指针之外，它还有一个子链表指针，可能指向单独的双向链表。这些子列表也可能会有一个或多个自己的子项，依此类推，生成多级数据结构，如下面的示例所示。</p>
 *
 * <p>给你位于列表第一级的头节点，请你扁平化列表，使所有结点出现在单级双链表中。</p>
 *
 * <p>&nbsp;</p>
 *
 * <p><strong>示例 1：</strong></p>
 *
 * <pre><strong>输入：</strong>head = [1,2,3,4,5,6,null,null,null,7,8,9,10,null,null,11,12]
 * <strong>输出：</strong>[1,2,3,7,8,11,12,9,10,4,5,6]
 * <strong>解释：
 * </strong>
 * 输入的多级列表如下图所示：
 *
 * <img src="https://assets.leetcode-cn.com/aliyun-lc-upload/uploads/2018/10/12/multilevellinkedlist.png" style="height: 363px; width: 640px;">
 *
 * 扁平化后的链表如下图：
 *
 * <img src="https://assets.leetcode-cn.com/aliyun-lc-upload/uploads/2018/10/12/multilevellinkedlistflattened.png" style="height: 80px; width: 1100px;">
 * </pre>
 *
 * <p><strong>示例 2：</strong></p>
 *
 * <pre><strong>输入：</strong>head = [1,2,null,3]
 * <strong>输出：</strong>[1,3,2]
 * <strong>解释：
 *
 * </strong>输入的多级列表如下图所示：
 *
 * 1---2---NULL
 * |
 * 3---NULL
 * </pre>
 *
 * <p><strong>示例 3：</strong></p>
 *
 * <pre><strong>输入：</strong>head = []
 * <strong>输出：</strong>[]
 * </pre>
 *
 * <p>&nbsp;</p>
 *
 * <p><strong>如何表示测试用例中的多级链表？</strong></p>
 *
 * <p>以 <strong>示例 1</strong> 为例：</p>
 *
 * <pre> 1---2---3---4---5---6--NULL
 * |
 * 7---8---9---10--NULL
 * |
 * 11--12--NULL</pre>
 * <p>
 * p>序列化其中的每一级之后：</p>
 * <p>
 * pre>[1,2,3,4,5,6,null]
 * [7,8,9,10,null]
 * [11,12,null]
 * </pre>
 * <p>
 * p>为了将每一级都序列化到一起，我们需要每一级中添加值为 null 的元素，以表示没有节点连接到上一级的上级节点。</p>
 * <p>
 * pre>[1,2,3,4,5,6,null]
 * [null,null,7,8,9,10,null]
 * [null,11,12,null]
 * </pre>
 * <p>
 * p>合并所有序列化结果，并去除末尾的 null 。</p>
 * <p>
 * pre>[1,2,3,4,5,6,null,null,null,7,8,9,10,null,null,11,12]</pre>
 * <p>
 * p>&nbsp;</p>
 * <p>
 * p><strong>提示：</strong></p>
 *
 * <ul>
 * <li>节点数目不超过 1000</li>
 * <li><code>1 &lt;= Node.val &lt;= 10^5</code></li>
 * </ul>
 * <div><div>Related Topics</div><div><li>深度优先搜索</li><li>链表</li><li>双向链表</li></div></div><br><div><li>👍 266</li><li>👎 0</li></div>
 */

public class No430_flatten {
    // 记录链表的最后一个节点
    Node last;

    public Node flatten(Node head) {
        dfs_v2(head);
        return head;
    }

    private void dfs_v1(Node node) {
        if (node == null) {
            return;
        }
        last = node;
        // 关键在这里，如果在这里不记录下一个节点，而在最后通过dfs_v1(node.next)来遍历，
        // 就有可能会访问到已经拼接后的node.child节点，相当于多次遍历，O(n^2)
        Node next = node.next;
        if (node.child != null) {
            dfs_v1(node.child);
            node.next = node.child;
            node.child.prev = node;
            last.next = next;
            if (next != null) {
                next.prev = last;
            }
            node.child = null;
        }
        dfs_v1(next);
    }


    public Node dfs_v2(Node node) {
        Node cur = node;
        while (cur != null) {
            Node next = cur.next;
            //  如果有子节点，那么首先处理子节点
            if (cur.child != null) {
                Node childLast = dfs_v2(cur.child);

                next = cur.next;
                //  将 node 与 child 相连
                cur.next = cur.child;
                cur.child.prev = cur;

                //  如果 next 不为空，就将 last 与 next 相连
                if (next != null) {
                    childLast.next = next;
                    next.prev = childLast;
                }

                // 将 child 置为空
                cur.child = null;
                last = childLast;
            } else {
                last = cur;
            }
            cur = next;
        }
        return last;
    }


    private static class Node {
        private int val;
        private Node prev;
        private Node next;
        private Node child;
    }
}
