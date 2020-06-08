package com.moon.leetcode;

import java.util.Arrays;
import java.util.Comparator;

/**
 * 1030. 距离顺序排列矩阵单元格<br/>
 * 给出 R 行 C 列的矩阵，其中的单元格的整数坐标为 (r, c)，满足 0 <= r < R 且 0 <= c < C。<br/>
 * 另外，我们在该矩阵中给出了一个坐标为 (r0, c0) 的单元格。<br/>
 * 返回矩阵中的所有单元格的坐标，并按到 (r0, c0) 的距离从最小到最大的顺序排，其中，两单元格(r1, c1) 和 (r2, c2)
 * 之间的距离是曼哈顿距离，|r1 - r2| + |c1 - c2|。（你可以按任何满足此条件的顺序返回答案。）<br/>
 * <br/>
 * 示例 1：<br/>
 * 输入：R = 1, C = 2, r0 = 0, c0 = 0<br/>
 * 输出：[[0,0],[0,1]]<br/>
 * 解释：从 (r0, c0) 到其他单元格的距离为：[0,1]<br/>
 * <br/>
 * 示例 2：<br/>
 * 输入：R = 2, C = 2, r0 = 0, c0 = 1<br/>
 * 输出：[[0,1],[0,0],[1,1],[1,0]]<br/>
 * 解释：从 (r0, c0) 到其他单元格的距离为：[0,1,1,2]<br/>
 * [[0,1],[1,1],[0,0],[1,0]] 也会被视作正确答案。<br/>
 * <br/>
 * 示例 3：<br/>
 * 输入：R = 2, C = 3, r0 = 1, c0 = 2<br/>
 * 输出：[[1,2],[0,2],[1,1],[0,1],[1,0],[0,0]]<br/>
 * 解释：从 (r0, c0) 到其他单元格的距离为：[0,1,1,2,2,3]<br/>
 * 其他满足题目要求的答案也会被视为正确，例如 [[1,2],[1,1],[0,2],[1,0],[0,1],[0,0]]。<br/>
 * <br/>
 * 提示：<br/>
 * 1 <= R <= 100<br/>
 * 1 <= C <= 100<br/>
 * 0 <= r0 < R<br/>
 * 0 <= c0 < C<br/>
 */
public class No1030_allCellsDistOrder {

    /**
     * 直接排序
     */
    public static int[][] allCellsDistOrder(int R, int C, int r0, int c0) {
        int[][] re = new int[R * C][2];
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                int t = i * C + j;
                re[t][0] = i;
                re[t][1] = j;
            }
        }
        Arrays.sort(re, Comparator.comparingInt(o -> (Math.abs(o[0] - r0) + Math.abs(o[1] - c0))));
        return re;
    }

    public static void main(String[] args) {
        System.out.println(Arrays.deepToString(allCellsDistOrder(2, 2, 0, 1)));
    }
}
