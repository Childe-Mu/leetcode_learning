package com.moon.leetcode;

import java.util.Arrays;

/**
 * 480. 滑动窗口中位数
 * <p>
 * 中位数是有序序列最中间的那个数。如果序列的大小是偶数，则没有最中间的数；此时中位数是最中间的两个数的平均数。
 * <p>
 * 例如：
 * <p>
 * [2,3,4]，中位数是 3
 * [2,3]，中位数是 (2 + 3) / 2 = 2.5
 * 给你一个数组 nums，有一个大小为 k 的窗口从最左端滑动到最右端。窗口中有 k 个数，每次窗口向右移动 1 位。你的任务是找出每次窗口移动后得到的新窗口中元素的中位数，并输出由它们组成的数组。
 * <p>
 * <p>
 * <p>
 * 示例：
 * <p>
 * 给出 nums = [1,3,-1,-3,5,3,6,7]，以及 k = 3。
 * <p>
 * 窗口位置                      中位数
 * ---------------               -----
 * [1  3  -1] -3  5  3  6  7       1
 * 1 [3  -1  -3] 5  3  6  7      -1
 * 1  3 [-1  -3  5] 3  6  7      -1
 * 1  3  -1 [-3  5  3] 6  7       3
 * 1  3  -1  -3 [5  3  6] 7       5
 * 1  3  -1  -3  5 [3  6  7]      6
 * 因此，返回该滑动窗口的中位数数组 [1,-1,-1,3,5,6]。
 * <p>
 * 提示：
 * <p>
 * 你可以假设 k 始终有效，即：k 始终小于输入的非空数组的元素个数。
 * 与真实值误差在 10 ^ -5 以内的答案将被视作正确答案。
 */
public class No480_medianSlidingWindow {
    public static double[] medianSlidingWindow(int[] nums, int k) {
        boolean f = (k & 1) == 0;
        int m = 0, n = 0, o = 0;
        if (f) {
            m = k / 2;
            n = m - 1;
        } else {
            o = k / 2;
        }
        double[] res = new double[nums.length - k + 1];
        for (int i = 0; i <= nums.length - k; i++) {
            int[] temp = Arrays.copyOfRange(nums, i, k+i);
            Arrays.sort(temp);
            if (f) {
                res[i] =  ((double)temp[m] + (double)temp[n]) / 2;
            } else {
                res[i] = temp[o];
            }
        }
        return res;
    }

    public static void main(String[] args) {
        System.out.println(Arrays.toString(medianSlidingWindow(new int[]{2147483647,2147483647}, 2)));
    }
}
