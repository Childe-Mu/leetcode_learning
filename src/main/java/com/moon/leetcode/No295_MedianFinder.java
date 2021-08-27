package com.moon.leetcode;

import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.TreeMap;

/**
 * 295. 数据流的中位数
 * <p>
 * /**
 * <p>中位数是有序列表中间的数。如果列表长度是偶数，中位数则是中间两个数的平均值。</p>
 *
 * <p>例如，</p>
 * <p>
 * p>[2,3,4]&nbsp;的中位数是 3</p>
 * <p>
 * p>[2,3] 的中位数是 (2 + 3) / 2 = 2.5</p>
 * <p>
 * p>设计一个支持以下两种操作的数据结构：</p>
 *
 * <ul>
 * <li>void addNum(int num) - 从数据流中添加一个整数到数据结构中。</li>
 * <li>double findMedian() - 返回目前所有元素的中位数。</li>
 * </ul>
 *
 * <p><strong>示例：</strong></p>
 *
 * <pre>addNum(1)
 * addNum(2)
 * findMedian() -&gt; 1.5
 * addNum(3)
 * findMedian() -&gt; 2</pre>
 *
 * <p><strong>进阶:</strong></p>
 *
 * <ol>
 * <li>如果数据流中所有整数都在 0 到 100 范围内，你将如何优化你的算法？</li>
 * <li>如果数据流中 99% 的整数都在 0 到 100 范围内，你将如何优化你的算法？</li>
 * </ol>
 * <div><div>Related Topics</div><div><li>设计</li><li>双指针</li><li>数据流</li><li>排序</li><li>堆（优先队列）</li></div></div><br><div><li>👍 482</li><li>👎 0</li></div>
 */
public class No295_MedianFinder {

    public static void main(String[] args) {
        MedianFinder_v1 v = new MedianFinder_v1();
        v.addNum(2);
        v.addNum(1);
        System.out.println(v.findMedian());
        v.addNum(3);
        System.out.println(v.findMedian());
    }

    static class MedianFinder_v1 {
        TreeMap<Integer, Integer> nums;
        int n;
        int[] left;
        int[] right;

        public MedianFinder_v1() {
            nums = new TreeMap<>();
            n = 0;
            left = new int[2];
            right = new int[2];
        }

        public void addNum(int num) {
            nums.put(num, nums.getOrDefault(num, 0) + 1);
            if (n == 0) {
                left[0] = right[0] = num;
                left[1] = right[1] = 1;
            } else if ((n & 1) != 0) {
                if (num < left[0]) {
                    decrease(left);
                } else {
                    increase(right);
                }
            } else {
                if (num > left[0] && num < right[0]) {
                    increase(left);
                    decrease(right);
                } else if (num >= right[0]) {
                    increase(left);
                } else {
                    decrease(right);
                    System.arraycopy(right, 0, left, 0, 2);
                }
            }
            n++;
        }

        public double findMedian() {
            return (left[0] + right[0]) / 2.0;
        }

        private void increase(int[] iterator) {
            iterator[1]++;
            if (iterator[1] > nums.get(iterator[0])) {
                iterator[0] = nums.ceilingKey(iterator[0] + 1);
                iterator[1] = 1;
            }
        }

        private void decrease(int[] iterator) {
            iterator[1]--;
            if (iterator[1] == 0) {
                iterator[0] = nums.floorKey(iterator[0] - 1);
                iterator[1] = nums.get(iterator[0]);
            }
        }
    }

    class MedianFinder {
        int size;
        PriorityQueue<Integer> min;
        PriorityQueue<Integer> max;

        /**
         * initialize your data structure here.
         */
        public MedianFinder() {
            size = 0;
            min = new PriorityQueue<>((a, b) -> b - a);
            max = new PriorityQueue<>(Comparator.comparingInt(a -> a));
        }

        public void addNum(int num) {
            int s1 = min.size();
            int s2 = max.size();
            if (s1 == s2) {
                if (min.isEmpty() || num <= max.peek()) {
                    min.add(num);
                } else {
                    min.add(max.poll());
                    max.add(num);
                }
            } else {
                if (!min.isEmpty() && num > min.peek()) {
                    max.add(num);
                } else {
                    max.add(min.poll());
                    min.add(num);
                }
            }
            size++;
        }

        public double findMedian() {
            if (size == 0) {
                return -1;
            }
            if ((size & 1) == 0) {
                return ((double) (min.peek() + max.peek())) / 2;
            } else {
                return min.peek();
            }
        }
    }
}
