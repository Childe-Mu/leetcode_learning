package com.moon.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 869. 重新排序得到 2 的幂
 * <p>
 * <p>给定正整数 <code>N</code>&nbsp;，我们按任何顺序（包括原始顺序）将数字重新排序，注意其前导数字不能为零。</p>
 * <p>
 * <p>如果我们可以通过上述方式得到&nbsp;2 的幂，返回 <code>true</code>；否则，返回 <code>false</code>。</p>
 * <p>
 * <p>&nbsp;</p>
 *
 * <ol>
 * </ol>
 *
 * <p><strong>示例 1：</strong></p>
 *
 * <pre><strong>输入：</strong>1
 * <strong>输出：</strong>true
 * </pre>
 *
 * <p><strong>示例 2：</strong></p>
 *
 * <pre><strong>输入：</strong>10
 * <strong>输出：</strong>false
 * </pre>
 *
 * <p><strong>示例 3：</strong></p>
 *
 * <pre><strong>输入：</strong>16
 * <strong>输出：</strong>true
 * </pre>
 *
 * <p><strong>示例 4：</strong></p>
 *
 * <pre><strong>输入：</strong>24
 * <strong>输出：</strong>false
 * </pre>
 *
 * <p><strong>示例 5：</strong></p>
 *
 * <pre><strong>输入：</strong>46
 * <strong>输出：</strong>true
 * </pre>
 *
 * <p>&nbsp;</p>
 *
 * <p><strong>提示：</strong></p>
 *
 * <ol>
 * <li><code>1 &lt;= N &lt;= 10^9</code></li>
 * </ol>
 * <div><div>Related Topics</div><div><li>数学</li><li>计数</li><li>枚举</li><li>排序</li></div></div><br><div><li>👍 81</li><li>👎 0</li></div>
 */
public class No869_reorderedPowerOf2 {

    static Set<Integer> set = new HashSet<>();

    static {
        for (int i = 1; i < (int) 1e9 + 10; i *= 2) {
            set.add(i);
        }
    }

    Set<String> powerOf2Digits = new HashSet<>();

    public static void main(String[] args) {
        System.out.println(new No869_reorderedPowerOf2().reorderedPowerOf2_v1(111));
    }

    public boolean reorderedPowerOf2_v1(int n) {
        if ((n & (n - 1)) == 0) {
            return true;
        }
        List<Integer> cnt = new ArrayList<>();
        while (n > 0) {
            cnt.add(n % 10);
            n /= 10;
        }
        cnt.sort(Comparator.comparingInt(a -> a));
        int used = 0;
        for (int i = 0; i < cnt.size(); i++) {
            int t = cnt.get(i);
            if (t != 0) {
                used |= (1 << i);
                if (backtrack(cnt, t, used, 1)) {
                    return true;
                }
                used ^= (1 << i);
            }

        }
        return false;
    }

    private boolean backtrack(List<Integer> cnt, int path, int used, int depth) {
        if (depth == cnt.size()) {
            return (path & (path - 1)) == 0;
        }
        for (int i = 0; i < cnt.size(); i++) {
            int t = cnt.get(i);
            if ((used >> i & 1) == 1 || (i > 0 && t == cnt.get(i - 1) && (used >> (i - 1) & 1) == 0)) {
                continue;
            }
            used |= (1 << i);
            if (backtrack(cnt, path * 10 + t, used, depth + 1)) {
                return true;
            }
            used ^= (1 << i);
        }
        return false;
    }

    public boolean reorderedPowerOf2_v2(int n) {
        int[] cnts = new int[10];
        while (n != 0) {
            cnts[n % 10]++;
            n /= 10;
        }
        int[] cur = new int[10];
        out:
        for (int x : set) {
            Arrays.fill(cur, 0);
            while (x != 0) {
                cur[x % 10]++;
                x /= 10;
            }
            for (int i = 0; i < 10; i++) {
                if (cur[i] != cnts[i]) {
                    continue out;
                }
            }
            return true;
        }
        return false;
    }

    public boolean reorderedPowerOf2_v3(int n) {
        init();
        return powerOf2Digits.contains(countDigits(n));
    }

    public void init() {
        for (int n = 1; n <= 1e9; n <<= 1) {
            powerOf2Digits.add(countDigits(n));
        }
    }

    public String countDigits(int n) {
        char[] cnt = new char[10];
        while (n > 0) {
            ++cnt[n % 10];
            n /= 10;
        }
        return new String(cnt);
    }
}
