package com.moon.leetcode;

import java.util.Arrays;
import java.util.function.IntFunction;

/**
 * 832. 翻转图像
 * <p>
 * 给定一个二进制矩阵 A，我们想先水平翻转图像，然后反转图像并返回结果。
 * <p>
 * 水平翻转图片就是将图片的每一行都进行翻转，即逆序。例如，水平翻转 [1, 1, 0] 的结果是 [0, 1, 1]。
 * <p>
 * 反转图片的意思是图片中的 0 全部被 1 替换， 1 全部被 0 替换。例如，反转 [0, 1, 1] 的结果是 [1, 0, 0]。
 * <p>
 * 示例 1：
 * <p>
 * 输入：[[1,1,0],[1,0,1],[0,0,0]]
 * 输出：[[1,0,0],[0,1,0],[1,1,1]]
 * 解释：首先翻转每一行: [[0,1,1],[1,0,1],[0,0,0]]；
 * 然后反转图片: [[1,0,0],[0,1,0],[1,1,1]]
 * <p>
 * 示例 2：
 * <p>
 * 输入：[[1,1,0,0],[1,0,0,1],[0,1,1,1],[1,0,1,0]]
 * 输出：[[1,1,0,0],[0,1,1,0],[0,0,0,1],[1,0,1,0]]
 * 解释：首先翻转每一行: [[0,0,1,1],[1,0,0,1],[1,1,1,0],[0,1,0,1]]；
 * 然后反转图片: [[1,1,0,0],[0,1,1,0],[0,0,0,1],[1,0,1,0]]
 * <p>
 * 提示：
 * <p>
 * 1 <= A.length = A[0].length <= 20
 * 0 <= A[i][j] <= 1
 */
public class No832_flipAndInvertImage {
    public static int[][] flipAndInvertImage_v1(int[][] A) {
        IntFunction<Integer> f = p -> p == 1 ? 0 : 1;
        int l2 = A[0].length;
        for (int i = 0; i < A.length; i++) {
            for (int j = 0; j < l2 / 2; j++) {
                int left = A[i][j];
                int right = A[i][l2 - j - 1];
                A[i][j] = f.apply(right);
                A[i][l2 - j - 1] = f.apply(left);
            }
            if ((l2 & 1) == 1) {
                A[i][l2 / 2] = f.apply(A[i][l2 / 2]);
            }
        }
        return A;
    }

    /**
     * 优化v1
     */
    public static int[][] flipAndInvertImage_v2(int[][] A) {
        int l2 = A[0].length;
        for (int i = 0; i < A.length; i++) {
            int left = 0, right = l2 - 1;
            while (left < right) {
                if (A[i][left] == A[i][right]) {
                    A[i][left] ^= 1;
                    A[i][right] ^= 1;
                }
                left++;
                right--;
            }
            if ((l2 & 1) == 1) {
                A[i][l2 / 2] ^= 1;
            }
        }
        return A;
    }

    public static void main(String[] args) {
        int[][] ints = {{1, 1, 0}, {1, 0, 1}, {0, 0, 0}};
//        System.out.println(Arrays.toString(ints[0]));
        System.out.println(Arrays.deepToString(flipAndInvertImage_v2(new int[][]{{1, 1, 0}, {1, 0, 1}, {0, 0, 0}})));
    }
}
