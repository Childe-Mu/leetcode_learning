package com.moon.leetcode;

/**
 * 273. 整数转换英文表示
 *
 * <p>将非负整数 <code>num</code> 转换为其对应的英文表示。</p>
 *
 * <p> </p>
 *
 * <p><strong>示例 1：</strong></p>
 *
 * <pre>
 * <strong>输入：</strong>num = 123
 * <strong>输出：</strong>"One Hundred Twenty Three"
 * </pre>
 *
 * <p><strong>示例 2：</strong></p>
 *
 * <pre>
 * <strong>输入：</strong>num = 12345
 * <strong>输出：</strong>"Twelve Thousand Three Hundred Forty Five"
 * </pre>
 *
 * <p><strong>示例 3：</strong></p>
 *
 * <pre>
 * <strong>输入：</strong>num = 1234567
 * <strong>输出：</strong>"One Million Two Hundred Thirty Four Thousand Five Hundred Sixty Seven"
 * </pre>
 * <p>
 * p><strong>示例 4：</strong></p>
 * <p>
 * pre>
 * <strong>输入：</strong>num = 1234567891
 * <strong>输出：</strong>"One Billion Two Hundred Thirty Four Million Five Hundred Sixty Seven Thousand Eight Hundred Ninety One"
 * </pre>
 * <p>
 * p> </p>
 * <p>
 * p><strong>提示：</strong></p>
 *
 * <ul>
 * <li><code>0 <= num <= 2<sup>31</sup> - 1</code></li>
 * </ul>
 * <div><div>Related Topics</div><div><li>递归</li><li>数学</li><li>字符串</li></div></div><br><div><li>👍 189</li><li>👎 0</li></div>
 */
public class No237_numberToWords {
    static String[] num2str_small = {
            "Zero",
            "One", "Two", "Three", "Four", "Five", "Six", "Seven", "Eight", "Nine", "Ten",
            "Eleven", "Twelve", "Thirteen", "Fourteen", "Fifteen", "Sixteen", "Seventeen", "Eighteen", "Nineteen"
    };
    static String[] num2str_medium = {
            "", "", "Twenty", "Thirty", "Forty", "Fifty", "Sixty", "Seventy", "Eighty", "Ninety"
    };
    static String[] num2str_large = {
            "Billion", "Million", "Thousand", "",
    };

    public static void main(String[] args) {
        System.out.println(new No237_numberToWords().numberToWords(1234567891));
    }

    String num2Str(int x) {
        String ans = "";
        if (x >= 100) {
            ans += num2str_small[x / 100] + " Hundred ";
            x %= 100;
        }
        if (x >= 20) {
            ans += num2str_medium[x / 10] + " ";
            x %= 10;
        }
        if (x != 0) {
            ans += num2str_small[x] + " ";
        }
        return ans;
    }

    public String numberToWords(int num) {
        if (num == 0) {
            return num2str_small[0];
        }
        StringBuilder sb = new StringBuilder();
        for (int i = (int) 1e9, j = 0; i >= 1; i /= 1000, j++) {
            if (num < i) {
                continue;
            }
            sb.append(num2Str(num / i)).append(num2str_large[j]).append(" ");
            num %= i;
        }
        while (sb.charAt(sb.length() - 1) == ' ') {
            sb.deleteCharAt(sb.length() - 1);
        }
        return sb.toString();
    }
}
