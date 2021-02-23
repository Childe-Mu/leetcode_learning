package com.moon.leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * 171. Excel表列序号
 * <p>
 * 给定一个Excel表格中的列名称，返回其相应的列序号。
 * <p>
 * 例如，
 * <p>
 * A -> 1
 * B -> 2
 * C -> 3
 * ...
 * Z -> 26
 * AA -> 27
 * AB -> 28
 * ...
 * <p>
 * 示例 1:
 * <p>
 * 输入: "A"
 * 输出: 1
 * <p>
 * 示例 2:
 * <p>
 * 输入: "AB"
 * 输出: 28
 * <p>
 * 示例 3:
 * <p>
 * 输入: "ZY"
 * 输出: 701
 */
public class No171_titleToNumber {
    public int titleToNumber(String s) {
        int res = 0;
        Map<Character, Integer> map = new HashMap<>();
        for (int i = 'A'; i <= 'Z'; i++) {
            map.put((char) i, i - 'A' + 1);
        }
        for (int i = 0; i < s.length(); i++) {
            res = res * 26 + map.get(s.charAt(i));
        }
        return res;
    }

    public int titleToNumber_v2(String s) {
        int ans = 0;
        for (int i = 0; i < s.length(); i++) {
            int num = s.charAt(i) - 'A' + 1;
            ans = ans * 26 + num;
        }
        return ans;
    }

    public static void main(String[] args) {
        System.out.println(new No171_titleToNumber().titleToNumber("AA"));
    }
}
