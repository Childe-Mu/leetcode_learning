package com.moon.leetcode;

import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Random;

/**
 * 面试题 17.14. 最小K个数
 * <p>
 * /**
 * <p>设计一个算法，找出数组中最小的k个数。以任意顺序返回这k个数均可。</p>
 *
 * <p><strong>示例：</strong></p>
 *
 * <pre><strong>输入：</strong> arr = [1,3,5,7,2,4,6,8], k = 4
 * <strong>输出：</strong> [1,2,3,4]
 * </pre>
 * <p>
 * p><strong>提示：</strong></p>
 *
 * <ul>
 * <li><code>0 &lt;= len(arr) &lt;= 100000</code></li>
 * <li><code>0 &lt;= k &lt;= min(100000, len(arr))</code></li>
 * </ul>
 * <div><div>Related Topics</div><div><li>数组</li><li>分治</li><li>快速选择</li><li>排序</li><li>堆（优先队列）</li></div></div><br><div><li>👍 107</li><li>👎 0</li></div>
 */
public class Interview_17_14_smallestK {
    int k;
    Random random;

    public static void main(String[] args) {
        System.out.println(Arrays.toString(new Interview_17_14_smallestK().smallestK_v3(new int[]{1, 3, 5, 7, 2, 4, 6, 8}, 4)));
    }

    public int[] smallestK_v1(int[] arr, int k) {
        Arrays.sort(arr);
        return Arrays.copyOfRange(arr, 0, k);
    }

    public int[] smallestK_v2(int[] arr, int k) {
        PriorityQueue<Integer> queue = new PriorityQueue<>((a, b) -> b - a);
        for (int a : arr) {
            if (queue.size() < k) {
                queue.offer(a);
            } else {
                if (!queue.isEmpty() && queue.peek() > a) {
                    queue.poll();
                    queue.offer(a);
                }
            }
        }
        int[] ans = new int[k];
        int i = 0;
        while (!queue.isEmpty()) {
            ans[i++] = queue.poll();
        }
        return ans;
    }

    public int[] smallestK_v3(int[] arr, int _k) {
        k = _k;
        random = new Random();
        qs(arr, 0, arr.length - 1);
        return Arrays.copyOf(arr, k);
    }

    private void qs(int[] a, int l, int r) {
        if (l >= r) {
            return;
        }
        int m = random.nextInt(r - l) + l + 1;
        swap(a, l, m);
        int v = a[l];
        int i = l;
        int j = r;
        while (i < j) {
            while (i < j && v <= a[j]) {
                j--;
            }
            while (i < j && a[i] <= v) {
                i++;
            }
            swap(a, i, j);
        }
        swap(a, l, i);
        if (j < k) {
            qs(a, j + 1, r);
        } else if (j > k) {
            qs(a, l, j - 1);
        }
    }

    void swap(int[] arr, int l, int r) {
        int tmp = arr[l];
        arr[l] = arr[r];
        arr[r] = tmp;
    }
}
