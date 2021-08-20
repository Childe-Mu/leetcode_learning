package com.moon.leetcode;

// 541. 反转字符串 II
//
//给定一个字符串 s 和一个整数 k，从字符串开头算起，每 2k 个字符反转前 k 个字符。
//
//
// 如果剩余字符少于 k 个，则将剩余字符全部反转。
// 如果剩余字符小于 2k 但大于或等于 k 个，则反转前 k 个字符，其余字符保持原样。
//
//
//
//
// 示例 1：
//
//
//输入：s = "abcdefg", k = 2
//输出："bacdfeg"
//
//
// 示例 2：
//
//
//输入：s = "abcd", k = 2
//输出："bacd"
//
//
//
//
// 提示：
//
//
// 1 <= s.length <= 104
// s 仅由小写英文组成
// 1 <= k <= 104
//
// Related Topics 双指针 字符串
// 👍 180 👎 0
public class No541_reverseStr {
    public static void main(String[] args) {
        System.out.println(new No541_reverseStr().reverseStr_v1("abcdefgh", 3));
    }

    public String reverseStr_v1(String s, int k) {
        char[] cs = s.toCharArray();
        int n = cs.length;
        for (int i = 0; i < n; i++) {
            int x = i / k;
            int y = i % k;
            if ((x & 1) == 0) {
                int z = (x + 1) * k - 1;
                if (z < n && y < k / 2) {
                    char t = cs[i];
                    cs[i] = cs[z - y];
                    cs[z - y] = t;
                }
                if (z >= n && y < (n - x * k) / 2) {
                    char t = cs[i];
                    cs[i] = cs[n - 1 - y];
                    cs[n - 1 - y] = t;
                }
            }
        }
        return new String(cs);
    }

    public String reverseStr_v2(String s, int k) {
        int n = s.length();
        char[] arr = s.toCharArray();
        for (int i = 0; i < n; i += 2 * k) {
            reverse(arr, i, Math.min(i + k, n) - 1);
        }
        return new String(arr);
    }

    public void reverse(char[] arr, int left, int right) {
        while (left < right) {
            char temp = arr[left];
            arr[left] = arr[right];
            arr[right] = temp;
            left++;
            right--;
        }
    }
}
