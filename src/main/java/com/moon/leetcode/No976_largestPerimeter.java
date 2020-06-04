package com.moon.leetcode;

import java.util.Arrays;

/**
 * 976. 三角形的最大周长<br/>
 * 给定由一些正数（代表长度）组成的数组 A，返回由其中三个长度组成的、面积不为零的三角形的最大周长。<br/>
 * 如果不能形成任何面积不为零的三角形，返回 0。<br/>
 * <br/>
 * 示例 1：<br/>
 * 输入：[2,1,2]<br/>
 * 输出：5<br/>
 * <br/>
 * 示例 2：<br/>
 * 输入：[1,2,1]<br/>
 * 输出：0<br/>
 * <br/>
 * 示例 3：<br/>
 * 输入：[3,2,3,4]<br/>
 * 输出：10<br/>
 * <br/>
 * 示例 4：<br/>
 * 输入：[3,6,2,3]<br/>
 * 输出：8<br/>
 * <br/>
 * 提示：<br/>
 * 3 <= A.length <= 10000<br/>
 * 1 <= A[i] <= 10^6<br/>
 */
public class No976_largestPerimeter {
    public static int largestPerimeter(int[] a) {
        Arrays.sort(a);
        for (int i = a.length - 1; i >= 0; i--) {
            if (a[i] < a[i - 1] + a[i - 2]) {
                return a[i] + a[i - 1] + a[i - 2];
            }
        }
        return 0;
    }

    public static void main(String[] args) {
        System.out.println(largestPerimeter(new int[]{2, 1, 2}));
    }
}
