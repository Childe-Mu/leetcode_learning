package com.moon.leetcode;

import java.util.PriorityQueue;

/**
 * 703. 数据流中的第K大元素<br/>
 * 设计一个找到数据流中第K大元素的类（class）。注意是排序后的第K大元素，不是第K个不同的元素。<br/>
 * 你的 KthLargest 类需要一个同时接收整数 k 和整数数组nums 的构造器，它包含数据流中的初始元素。
 * 每次调用 KthLargest.add，返回当前数据流中第K大的元素。<br/>
 * <br/>
 * 示例:<br/>
 * <code>
 * int k = 3;<br/>
 * int[] arr = [4,5,8,2];<br/>
 * KthLargest kthLargest = new KthLargest(3, arr);<br/>
 * kthLargest.add(3);   // returns 4<br/>
 * kthLargest.add(5);   // returns 5<br/>
 * kthLargest.add(10);  // returns 5<br/>
 * kthLargest.add(9);   // returns 8<br/>
 * kthLargest.add(4);   // returns 8<br/>
 * </code>
 * <br/>
 * 说明:<br/>
 * 你可以假设 nums 的长度≥ k-1 且k ≥ 1。<br/>
 */
public class No703_KthLargest {
    public static void main(String[] args) {
        int k = 3;
        int[] arr = new int[]{4, 5, 8,8, 2, 2};
        KthLargest kthLargest = new KthLargest(k, arr);
        System.out.println(kthLargest.add(3));
        System.out.println(kthLargest.add(5));
        System.out.println(kthLargest.add(10));
        System.out.println(kthLargest.add(9));
        System.out.println(kthLargest.add(11));
        System.out.println(kthLargest.add(4));
    }
}


/**
 * 本例子中使用无界优先级队列PriorityQueue存储元素，这样保存以后的元素全是有序，
 * 然后结合大小条件limit，就可以模拟出一个小顶堆，而堆顶的元素就是第K大的元素
 */
class KthLargest {
    /**
     * PriorityQueue是一个基于优先级堆的无界优先级队列。此队列按照在构造时所指定的顺序对元素排序，
     * 既可以根据元素的自然顺序 来指定排序（参阅 Comparable），也可以根据 Comparator 来指定，
     * 这取决于使用哪种构造方法。优先级队列不允许 null 元素。依靠自然排序的优先级队列还不允许插入
     * 不可比较的对象（这样做可能导致 ClassCastException）。 <br/>
     * 此队列的头 是按指定排序方式的最小 元素。如果多个元素都是最小值，则头是其中一个元素——选择方法是任意的。
     * 队列检索操作 poll、remove、peek 和 element 访问处于队列头的元素。<br/>
     */
    private PriorityQueue<Integer> queue;
    private int limit;

    public KthLargest(int k, int[] nums) {
        limit = k;
        queue = new PriorityQueue<>(k);
        for (int num : nums) {
            add(num);
        }
    }

    public int add(int val) {
        if (queue.size() < limit) {
            queue.add(val);
        } else if (val > queue.peek()) {
            queue.poll();
            queue.add(val);
        }
        return queue.peek();
    }

}
