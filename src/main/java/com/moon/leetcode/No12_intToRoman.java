package com.moon.leetcode;

import java.util.HashMap;
import java.util.Map;

// 12. 整数转罗马数字
//
//罗马数字包含以下七种字符： I， V， X， L，C，D 和 M。
//
//字符          数值
//I             1
//V             5
//X             10
//L             50
//C             100
//D             500
//M             1000
//
// 例如， 罗马数字 2 写做 II ，即为两个并列的 1。12 写做 XII ，即为 X + II 。 27 写做 XXVII, 即为 XX + V + II 。
//
// 通常情况下，罗马数字中小的数字在大的数字的右边。但也存在特例，例如 4 不写做 IIII，而是 IV。数字 1 在数字 5 的左边，所表示的数等于大数 5
// 减小数 1 得到的数值 4 。同样地，数字 9 表示为 IX。这个特殊的规则只适用于以下六种情况：
//
//
// I 可以放在 V (5) 和 X (10) 的左边，来表示 4 和 9。
// X 可以放在 L (50) 和 C (100) 的左边，来表示 40 和 90。
// C 可以放在 D (500) 和 M (1000) 的左边，来表示 400 和 900。
//
// 给你一个整数，将其转为罗马数字。
//
// 示例 1:
//
//输入:num = 3
//输出: "III"
//
// 示例 2:
//
//输入:num = 4
//输出: "IV"
//
// 示例 3:
//
//输入:num = 9
//输出: "IX"
//
// 示例 4:
//
//输入:num = 58
//输出: "LVIII"
//解释: L = 50, V = 5, III = 3.
//
// 示例 5:
//
//输入:num = 1994
//输出: "MCMXCIV"
//解释: M = 1000, CM = 900, XC = 90, IV = 4.
//
// 提示：
//
// 1 <= num <= 3999
//
// Related Topics 数学 字符串
// 👍 573 👎 0
public class No12_intToRoman {
    public static void main(String[] args) {
        System.out.println(new No12_intToRoman().intToRoman(58));
    }

    public String intToRoman(int num) {
        Map<Integer, String> map = new HashMap<>();
        map.put(1, "I");
        map.put(4, "IV");
        map.put(5, "V");
        map.put(9, "IX");
        map.put(10, "X");
        map.put(40, "XL");
        map.put(50, "L");
        map.put(90, "XC");
        map.put(100, "C");
        map.put(400, "CD");
        map.put(500, "D");
        map.put(900, "CM");
        map.put(1000, "M");
        int e = 1;
        StringBuilder builder = new StringBuilder();
        while (num > 0) {
            int n = num % 10;
            if (map.containsKey(n * e)) {
                builder.insert(0, map.get(n * e));
            } else if (n < 4) {
                String s = map.get(e);
                for (int i = 0; i < n; i++) {
                    builder.insert(0, s);
                }
            } else {
                String s1 = map.get(5 * e);
                builder.insert(0, s1);
                String s2 = map.get(e);
                for (int i = 6; i <= n; i++) {
                    builder.insert(1, s2);
                }
            }
            num /= 10;
            e *= 10;
        }
        return builder.toString();
    }
}
