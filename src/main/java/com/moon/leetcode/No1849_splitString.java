package com.moon.leetcode;

import java.util.ArrayDeque;
import java.util.Deque;

// 1849. 将字符串拆分为递减的连续值
//
//给你一个仅由数字组成的字符串 s 。
//
// 请你判断能否将 s 拆分成两个或者多个 非空子字符串 ，使子字符串的 数值 按 降序 排列，且每两个 相邻子字符串 的数值之 差 等于 1 。
//
//
// 例如，字符串 s = "0090089" 可以拆分成 ["0090", "089"] ，数值为 [90,89] 。这些数值满足按降序排列，且相邻值相差 1
// ，这种拆分方法可行。
// 另一个例子中，字符串 s = "001" 可以拆分成 ["0", "01"]、["00", "1"] 或 ["0", "0", "1"] 。然而，所有这些
//拆分方法都不可行，因为对应数值分别是 [0,1]、[0,1] 和 [0,0,1] ，都不满足按降序排列的要求。
//
// 如果可以按要求拆分 s ，返回 true ；否则，返回 false 。
//
// 子字符串 是字符串中的一个连续字符序列。
//
// 示例 1：
//
//输入：s = "1234"
//输出：false
//解释：不存在拆分 s 的可行方法。
//
// 示例 2：
//
//输入：s = "050043"
//输出：true
//解释：s 可以拆分为 ["05", "004", "3"] ，对应数值为 [5,4,3] 。
//满足按降序排列，且相邻值相差 1 。
//
// 示例 3：
//
//输入：s = "9080701"
//输出：false
//解释：不存在拆分 s 的可行方法。
//
// 示例 4：
//
//输入：s = "10009998"
//输出：true
//解释：s 可以拆分为 ["100", "099", "98"] ，对应数值为 [100,99,98] 。
//满足按降序排列，且相邻值相差 1 。
//
// 提示：
//
// 1 <= s.length <= 20
// s 仅由数字组成
//
// Related Topics 递归 字符串 回溯算法
// 👍 14 👎 0
public class No1849_splitString {

    private boolean res = false;

    public static void main(String[] args) {
        System.out.println(new No1849_splitString().splitString_v1("99999999999999999998"));
    }

    public boolean splitString_v1(String s) {
        int n = s.length();
        int i = 0;
        while (i < n && s.charAt(i) == '0') {
            i++;
        }
        s = s.substring(i, n);
        n = s.length();
        for (int j = 1; j < Math.min(n, 11); j++) {
            long pre = Long.parseLong(s.substring(0, j));
            if (dfs(s, n, j, pre)) {
                return true;
            }
        }
        return false;
    }

    private boolean dfs(String s, int n, int depth, long pre) {
        if (depth == n) {
            return true;
        }
        for (int i = depth + 1; i <= n; i++) {
            long cur = Long.parseLong(s.substring(depth, i));
            if (pre <= cur) {
                break;
            }
            if (pre - cur == 1 && dfs(s, n, i, cur)) {
                return true;
            }
        }
        return false;
    }

    public boolean splitString_v2(String s) {
        Deque<Long> path = new ArrayDeque<>();
        backtrack(s, s.length(), 0, path);
        return res;
    }

    private void backtrack(String s, int n, int depth, Deque<Long> path) {
        if (res) {
            return;
        }
        if (depth == n) {
            res = path.size() > 1;
            return;
        }
        int limit = depth == 0 ? n - 1 : n;
        if (s.charAt(depth) != '0') {
            limit = Math.min(depth + 10, limit);
        }
        for (int i = depth + 1; i <= limit; i++) {
            String tmp = s.substring(depth, i);
            if (path.isEmpty() || path.peekLast() - Long.parseLong(tmp) == 1) {
                path.offerLast(Long.parseLong(tmp));
                backtrack(s, n, i, path);
                path.removeLast();
            }
        }
    }
}
