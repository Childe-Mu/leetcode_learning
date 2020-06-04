package com.moon.leetcode;

import java.util.Arrays;

/**
 * 922. 按奇偶排序数组 II<br/>
 * 给定一个非负整数数组 A， A 中一半整数是奇数，一半整数是偶数。<br/>
 * 对数组进行排序，以便当 A[i] 为奇数时，i 也是奇数；当 A[i] 为偶数时， i 也是偶数。<br/>
 * 你可以返回任何满足上述条件的数组作为答案。<br/>
 * <br/>
 * 示例：<br/>
 * 输入：[4,2,5,7]<br/>
 * 输出：[4,5,2,7]<br/>
 * 解释：[4,7,2,5]，[2,5,4,7]，[2,7,4,5] 也会被接受。<br/>
 * <br/>
 * 提示：<br/>
 * 2 <= A.length <= 20000<br/>
 * A.length % 2 == 0<br/>
 * 0 <= A[i] <= 1000<br/>
 */
public class No922_sortArrayByParityII {
    // /**
    //  * 双指针循环，需要开辟额外空间
    //  */
    // public static int[] sortArrayByParityII(int[] a) {
    //     int[] result = new int[a.length];
    //     int i = 0;
    //     int j = 0;
    //     for (int n : a) {
    //         if ((n & 1) == 1) {
    //             result[2 * i + 1] = n;
    //             i++;
    //         } else {
    //             result[2 * j] = n;
    //             j++;
    //         }
    //     }
    //     return result;
    // }

    /**
     * 找到偶数位上的奇数,找到奇数位上的偶数，然后二者交换，不需要额外空间
     * 核心思想是，只要有一个偶数位上是奇数，那就必定存在一个奇数位上为偶数，
     * 只要偶数位排对了，奇数位一定正确
     */
    public static int[] sortArrayByParityII(int[] a) {
        int j = 1;
        for (int i = 0; i < a.length; i += 2) {
            // 偶数位为奇数
            if ((a[i] & 1) == 1) {
                // 寻找奇数位上的偶数（找到则停止循环）
                while ((a[j] & 1) == 1) {
                    j += 2;
                }
                // Swap A[i] and A[j]
                int tmp = a[i];
                a[i] = a[j];
                a[j] = tmp;
            }
        }
        return a;
    }
    public static void main(String[] args) {
        System.out.println(Arrays.toString(sortArrayByParityII(new int[]{4, 2, 5, 7})));
    }
}
