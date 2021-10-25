package com.moon.leetcode;

import java.util.ArrayDeque;
import java.util.Date;
import java.util.Deque;

/**
 * 1221. 分割平衡字符串<br/>
 * 在一个「平衡字符串」中，'L' 和 'R' 字符的数量是相同的。<br/>
 * 给出一个平衡字符串 s，请你将它分割成尽可能多的平衡字符串。<br/>
 * 返回可以通过分割得到的平衡字符串的最大数量。<br/>
 * <b>注意：</b>这里的分割平衡字符串，要求是分页以后的子串必须每一个都是平衡字符串，同时 子串1+子串2+子串3+···+子串N = 原字符串
 * <p>
 * <p/>
 * 示例 1：<br/>
 * 输入：s = "RLRRLLRLRL"<br/>
 * 输出：4<br/>
 * 解释：s 可以分割为 "RL", "RRLL", "RL", "RL", 每个子字符串中都包含相同数量的 'L' 和 'R'。<br/>
 * <p/>
 * 示例 2：<br/>
 * 输入：s = "RLLLLRRRLR"<br/>
 * 输出：3<br/>
 * 解释：s 可以分割为 "RL", "LLLRRR", "LR", 每个子字符串中都包含相同数量的 'L' 和 'R'。<br/>
 * <p/>
 * 示例 3：<br/>
 * 输入：s = "LLLLRRRR"<br/>
 * 输出：1<br/>
 * 解释：s 只能保持原样 "LLLLRRRR".<br/>
 * <p/>
 * 提示：<br/>
 * 1 <= s.length <= 1000<br/>
 * s[i] = 'L' 或 'R'<br/>
 * 分割得到的每个字符串都必须是平衡字符串。
 */
public class No1221_balancedStringSplit {
    /**
     * 使用贪心算法+平衡值（平衡值会在一边多时偏向这一边，如果归零则表示平衡）
     */
    public static int balancedStringSplit_v1(String s) {
        int cnt = 0;
        int balance = 0;
        char[] chars = s.toCharArray();
        for (char c : chars) {
            if (c == 'L') {
                balance--;
            } else {
                balance++;
            }
            if (balance == 0) cnt++;
        }
        return cnt;
    }

    /**
     * 贪心+栈
     */
    public static int balancedStringSplit_v2(String s) {
        int cnt = 0;
        Deque<Character> stack = new ArrayDeque<>();
        char[] chars = s.toCharArray();
        for (char c : chars) {
            if (stack.isEmpty() || c == stack.peek()) {
                stack.push(c);
            } else {
                stack.pop();
            }
            if (stack.isEmpty()) {
                cnt++;
            }
        }
        return cnt;
    }

    public static void main(String[] args) {
        new Date();
        System.out.println(balancedStringSplit_v1("LLLLRRLRLRRR"));
    }

    public int balancedStringSplit_v3(String s) {
        char[] cs = s.toCharArray();
        int cnt = 0;
        int ans = 0;
        for (char c : cs) {
            cnt += c == 'L' ? 1 : -1;
            ans += cnt == 0 ? 1 : 0;
        }
        return ans;
    }
}
