package com.moon.leetcode.offer;

import java.util.Arrays;
import java.util.PriorityQueue;

/**
 * 剑指 Offer 40. 最小的k个数<br/>
 * 输入整数数组 arr ，找出其中最小的 k 个数。例如，输入4、5、1、6、2、7、3、8这8个数字，
 * 则最小的4个数字是1、2、3、4。<br/>
 * <br/>
 * 示例 1：<br/>
 * 输入：arr = [3,2,1], k = 2<br/>
 * 输出：[1,2] 或者 [2,1]<br/>
 * <br/>
 * 示例 2：<br/>
 * 输入：arr = [0,1,2,1], k = 1<br/>
 * 输出：[0]<br/>
 * <br/>
 * 限制：<br/>
 * 0 <= k <= arr.length <= 10000<br/>
 * 0 <= arr[i] <= 10000<br/>
 */
public class Offer_40_getLeastNumbers {
    // /**
    //  * 数组直接排序
    //  */
    // public static int[] getLeastNumbers(int[] arr, int k) {
    //     Arrays.sort(arr);
    //     return Arrays.copyOfRange(arr, 0, k);
    // }

    /**
     * 优先队列
     */
    public static int[] getLeastNumbers(int[] arr, int k) {
        if (k == 0) {
            return new int[]{};
        }
        PriorityQueue<Integer> queue = new PriorityQueue<>(k, (o1, o2) -> (o2 - o1));
        for (int i = 0; i < arr.length; i++) {
            if (i < k) {
                queue.offer(arr[i]);
            } else {
                if (queue.peek() > arr[i]) {
                    queue.poll();
                    queue.offer(arr[i]);
                }
            }
        }
        int[] result = new int[k];
        for (int i = 0; i < k; i++) {
            result[i] = queue.poll();
        }
        return result;
    }

    public static void main(String[] args) {
        System.out.println(Arrays.toString(getLeastNumbers(new int[]{3, 2, 1}, 2)));
    }
}
