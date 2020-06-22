package com.moon.leetcode;

import java.util.Arrays;

/**
 * 455. 分发饼干<br/>
 * 假设你是一位很棒的家长，想要给你的孩子们一些小饼干。但是，每个孩子最多只能给一块饼干。
 * 对每个孩子 i ，都有一个胃口值 gi ，这是能让孩子们满足胃口的饼干的最小尺寸；并且每块饼干 j，
 * 都有一个尺寸 sj 。如果 sj >= gi ，我们可以将这个饼干 j 分配给孩子 i ，这个孩子会得到满足。
 * 你的目标是尽可能满足越多数量的孩子，并输出这个最大数值。<br/>
 * <br/>
 * 注意：<br/>
 * 你可以假设胃口值为正。<br/>
 * 一个小朋友最多只能拥有一块饼干。<br/>
 * <br/>
 * 示例 1:<br/>
 * 输入: [1,2,3], [1,1]<br/>
 * 输出: 1<br/>
 * 解释:<br/>
 * 你有三个孩子和两块小饼干，3个孩子的胃口值分别是：1,2,3。<br/>
 * 虽然你有两块小饼干，由于他们的尺寸都是1，你只能让胃口值是1的孩子满足。<br/>
 * 所以你应该输出1。<br/>
 * <br/>
 * 示例 2:<br/>
 * 输入: [1,2], [1,2,3]<br/>
 * 输出: 2<br/>
 * 解释:<br/>
 * 你有两个孩子和三块小饼干，2个孩子的胃口值分别是1,2。<br/>
 * 你拥有的饼干数量和尺寸都足以让所有孩子满足。<br/>
 * 所以你应该输出2.<br/>
 */
public class No455_findContentChildren {
    // public static int findContentChildren(int[] g, int[] s) {
    //     Arrays.sort(g);
    //     Arrays.sort(s);
    //     int result = 0;
    //     int i = 0;
    //     for (int gi : g) {
    //         while (i < s.length) {
    //             if (s[i] >= gi) {
    //                 i++;
    //                 result++;
    //                 break;
    //             }
    //             i++;
    //         }
    //     }
    //     return result;
    // }

    /**
     * 精简版
     */
    public static int findContentChildren(int[] g, int[] s) {
        if (g == null || s == null || g.length == 0 || s.length == 0) {
            return 0;
        }
        Arrays.sort(g);
        Arrays.sort(s);
        int gi = 0, si = 0;
        // 逻辑是：按照胃口从小到大，依次满足，不能满足，则向后遍历更大的饼干，
        // 如果有小孩的胃口不能被当前的饼干满足，那么之后小孩，也一定无法被这个饼干满足
        while (gi < g.length && si < s.length) {
            if (g[gi] <= s[si]) {
                gi++;
            }
            si++;
        }
        return gi;
    }

    public static void main(String[] args) {
        System.out.println(findContentChildren(new int[]{1, 2, 3}, new int[]{1, 1}));
    }
}
