package com.moon.leetcode;

// 461. 汉明距离
//
//两个整数之间的汉明距离指的是这两个数字对应二进制位不同的位置的数目。
//
// 给出两个整数 x 和 y，计算它们之间的汉明距离。
//
// 注意：
//0 ≤ x, y < 231.
//
// 示例:
//
//
//输入: x = 1, y = 4
//
//输出: 2
//
//解释:
//1   (0 0 0 1)
//4   (0 1 0 0)
//       ↑   ↑
//
//上面的箭头指出了对应二进制位不同的位置。
//
// Related Topics 位运算
// 👍 430 👎 0
public class No1035_hammingDistance {
    public static void main(String[] args) {
        System.out.println(new No1035_hammingDistance().hammingDistance(1, 4));
    }

    public int hammingDistance(int x, int y) {
        int count = 0;
        for (int i = 31; i >= 0; i--) {
            count += (((x >> i) & 1) ^ ((y >> i) & 1));
        }
        return count;
    }
}
