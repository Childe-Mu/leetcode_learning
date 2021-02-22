package com.moon.leetcode;

/**
 * 168. Excel表列名称
 * <p>
 * 给定一个正整数，返回它在 Excel 表中相对应的列名称。
 * <p>
 * 例如，
 * <p>
 * 1 -> A
 * 2 -> B
 * 3 -> C
 * ...
 * 26 -> Z
 * 27 -> AA
 * 28 -> AB
 * ...
 * <p>
 * 示例 1:
 * <p>
 * 输入: 1
 * 输出: "A"
 * <p>
 * 示例 2:
 * <p>
 * 输入: 28
 * 输出: "AB"
 * <p>
 * 示例 3:
 * <p>
 * 输入: 701
 * 输出: "ZY"
 */
public class No168_convertToTitle {

    public static String convertToTitle(int n) {
        StringBuilder res = new StringBuilder();
        while (n > 0) {
            // -1是因为26进制，25位最大值，26就应该进一位，即用0-25表示26个数，1-16就会出问题，参考10进制中0-9
            n -= 1;
            int temp = n % 26;
            res.insert(0, (char) (65 + temp));
            n /= 26;
        }
        return res.toString();
    }

    public static void main(String[] args) {
        System.out.println(convertToTitle(701));
    }
}
