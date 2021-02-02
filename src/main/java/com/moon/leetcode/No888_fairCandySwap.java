package com.moon.leetcode;

import java.util.Arrays;
import java.util.BitSet;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * 888. 公平的糖果棒交换
 * <p>
 * 爱丽丝和鲍勃有不同大小的糖果棒：A[i] 是爱丽丝拥有的第 i 根糖果棒的大小，B[j] 是鲍勃拥有的第 j 根糖果棒的大小。
 * <p>
 * 因为他们是朋友，所以他们想交换一根糖果棒，这样交换后，他们都有相同的糖果总量。（一个人拥有的糖果总量是他们拥有的糖果棒大小的总和。）
 * <p>
 * 返回一个整数数组 ans，其中 ans[0] 是爱丽丝必须交换的糖果棒的大小，ans[1] 是 Bob 必须交换的糖果棒的大小。
 * <p>
 * 如果有多个答案，你可以返回其中任何一个。保证答案存在。
 * <p>
 * 示例 1：
 * <p>
 * 输入：A = [1,1], B = [2,2]
 * 输出：[1,2]
 * 示例 2：
 * <p>
 * 输入：A = [1,2], B = [2,3]
 * 输出：[1,2]
 * 示例 3：
 * <p>
 * 输入：A = [2], B = [1,3]
 * 输出：[2,3]
 * 示例 4：
 * <p>
 * 输入：A = [1,2,5], B = [2,4]
 * 输出：[5,4]
 * <p>
 * <p>
 * 提示：
 * <p>
 * 1 <= A.length <= 10000
 * 1 <= B.length <= 10000
 * 1 <= A[i] <= 100000
 * 1 <= B[i] <= 100000
 * 保证爱丽丝与鲍勃的糖果总量不同。
 * 答案肯定存在。
 */
public class No888_fairCandySwap {
    /**
     * 1. 循环，暴力破解，效率低
     * 主体思路是，x - y = (sumA - sumB) / 2
     */
//    public static int[] fairCandySwap(int[] A, int[] B) {
//        int sum = 0;
//        int sumA = 0;
//        for (int i : A) {
//            sum += i;
//            sumA += i;
//        }
//        for (int i : B) {
//            sum += i;
//        }
//        int diffA = sum / 2 - sumA;
//        for (int i : A) {
//            for (int i1 : B) {
//                if (i1 - i == diffA) {
//                    return new int[]{i, i1};
//                }
//            }
//        }
//        return new int[]{0, 0};
//    }

    /**
     * 2.建立映射
     */
//    public static int[] fairCandySwap(int[] A, int[] B) {
//        int sumA = 0;
//        int sumB = 0;
//        Set<Integer> set = new HashSet<>();
//        for (int i : A) {
//            sumA += i;
//        }
//        for (int i : B) {
//            sumB += i;
//            set.add(i);
//        }
//        int diff = (sumA - sumB) / 2;
//        for (int i : A) {
//           if (set.contains(i - diff)) {
//               return new int[]{i, i - diff};
//           }
//        }
//        return new int[]{0, 0};
//    }

    /**
     * 3.优化解法2，使用bitmap
     * 本题只需要判断一个Alice或Bob中是否存在一个数是否存在，如果用Map或者用Set实现效率并不高。
     * 我们知道在判断一个数是否存在时使用BitMap更为高效！
     */
    public static int[] fairCandySwap(int[] A, int[] B) {
        int sumA = 0;
        int sumB = 0;
        // 指定最大长度+1，虽然会导致空间浪费，但是可以节省扩容时间
        BitSet set = new BitSet(100001);
        for (int i : A) {
            sumA += i;
        }
        for (int i : B) {
            sumB += i;
            set.set(i);
        }
        int diff = (sumA - sumB) / 2;
        for (int i : A) {
           if (i - diff > 0 && set.get(i - diff)) {
               return new int[]{i, i - diff};
           }
        }
        return null;
    }

    public static void main(String[] args) {
        System.out.println(Arrays.toString(fairCandySwap(new int[]{1,17,14,1,16}, new int[]{26,11})));
    }
}
