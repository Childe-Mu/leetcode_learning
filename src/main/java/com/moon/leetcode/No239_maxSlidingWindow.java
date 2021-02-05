package com.moon.leetcode;

import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * 239. 滑动窗口最大值
 * <p>
 * 给你一个整数数组 nums，有一个大小为 k 的滑动窗口从数组的最左侧移动到数组的最右侧。你只可以看到在滑动窗口内的 k 个数字。滑动窗口每次只向右移动一位。
 * <p>
 * 返回滑动窗口中的最大值。
 * <p>
 * 示例 1：
 * <p>
 * 输入：nums = [1,3,-1,-3,5,3,6,7], k = 3
 * 输出：[3,3,5,5,6,7]
 * 解释：
 * 滑动窗口的位置                最大值
 * ---------------               -----
 * [1  3  -1] -3  5  3  6  7       3
 * 1 [3  -1  -3] 5  3  6  7       3
 * 1  3 [-1  -3  5] 3  6  7       5
 * 1  3  -1 [-3  5  3] 6  7       5
 * 1  3  -1  -3 [5  3  6] 7       6
 * 1  3  -1  -3  5 [3  6  7]      7
 * <p>
 * 示例 2：
 * <p>
 * 输入：nums = [1], k = 1
 * 输出：[1]
 * <p>
 * 示例 3：
 * <p>
 * 输入：nums = [1,-1], k = 1
 * 输出：[1,-1]
 * <p>
 * 示例 4：
 * <p>
 * 输入：nums = [9,11], k = 2
 * 输出：[11]
 * <p>
 * 示例 5：
 * <p>
 * 输入：nums = [4,-2], k = 2
 * 输出：[4]
 * <p>
 * 提示：
 * <p>
 * 1 <= nums.length <= 105
 * -104 <= nums[i] <= 104
 * 1 <= k <= nums.length
 */
public class No239_maxSlidingWindow {
    /**
     * 优先队列（大顶堆）
     */
    public static int[] maxSlidingWindow_v1(int[] nums, int k) {
        Queue<int[]> q = new PriorityQueue<>(k, (o1, o2) -> o1[0] != o2[0] ? o2[0] - o1[0] : o2[1] - o1[1]);
        for (int i = 0; i < k; i++) {
            q.offer(new int[]{nums[i], i});
        }
        int[] res = new int[nums.length - k + 1];
        res[0] = q.peek()[0];
        for (int i = 1; i < nums.length - k + 1; i++) {
            q.offer(new int[]{nums[i + k - 1], i + k - 1});
            while (q.peek()[1] < i) {
                q.poll();
            }
            res[i] = q.peek()[0];
        }
        return res;
    }

    /**
     * 单调队列
     */
    public static int[] maxSlidingWindow_v2(int[] nums, int k) {
        Deque<Integer> q = new LinkedList<>();
        int[] res = new int[nums.length - k + 1];
        for (int i = 0; i < k; i++) {
            while (!q.isEmpty() && nums[i] >= nums[q.peekLast()]) {
                q.pollLast();
            }
            q.offerLast(i);
        }
        res[0] = nums[q.peekFirst()];
        for (int i = k; i < nums.length; i++) {
            while (!q.isEmpty() && nums[i] >= nums[q.peekLast()]) {
                q.pollLast();
            }
            q.offerLast(i);
            while (i - k + 1 > q.peekFirst()) {
                q.pollFirst();
            }
            res[i - k + 1] = nums[q.peekFirst()];
        }
        return res;
    }

    public static void main(String[] args) {
        System.out.println(Arrays.toString(maxSlidingWindow_v2(new int[]{-6, -10, -7, -1, -9, 9, -8, -4, 10, -5, 2, 9, 0, -7, 7, 4, -2, -10, 8, 7}, 7)));
    }
}
