package com.moon.leetcode.offer;

// 剑指 Offer 46. 把数字翻译成字符串
//
//给定一个数字，我们按照如下规则把它翻译为字符串：0 翻译成 “a” ，1 翻译成 “b”，……，11 翻译成 “l”，……，25 翻译成 “z”。一个数字可
//能有多个翻译。请编程实现一个函数，用来计算一个数字有多少种不同的翻译方法。
//
// 示例 1:
//
// 输入: 12258
//输出: 5
//解释: 12258有5种不同的翻译，分别是"bccfi", "bwfi", "bczi", "mcfi"和"mzi"
//
// 提示：
//
// 0 <= num < 231
//
// 👍 239 👎 0
public class Offer_46_translateNum {
    char[] cs;
    int n;
    int ans = 0;

    public static void main(String[] args) {
        System.out.println(new Offer_46_translateNum().translateNum_v2(12258));
    }

    public int translateNum_v1(int num) {
        this.cs = String.valueOf(num).toCharArray();
        this.n = cs.length;
        dfs(0);
        return ans;
    }

    private void dfs(int i) {
        if (i == n) {
            ans++;
            return;
        }
        dfs(i + 1);
        if (i < n - 1 && (cs[i] == '1' || cs[i] == '2' && cs[i + 1] < '6')) {
            dfs(i + 2);
        }
    }

    public int translateNum_v2(int num) {
        this.cs = String.valueOf(num).toCharArray();
        this.n = cs.length;
        int[] f = new int[n + 1];
        f[0] = 1;
        for (int i = 0; i < n; i++) {
            if (i >= 1 && (cs[i - 1] == '1' || cs[i - 1] == '2' && cs[i] < '6')) {
                f[i + 1] = f[i - 1] + f[i];
            } else {
                f[i + 1] = f[i];
            }
        }
        return f[n];
    }
}
