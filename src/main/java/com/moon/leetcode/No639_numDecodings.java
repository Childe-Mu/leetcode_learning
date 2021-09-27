package com.moon.leetcode;

/**
 * 639. 解码方法 II
 * <p>一条包含字母&nbsp;<code>A-Z</code> 的消息通过以下的方式进行了编码：</p>
 *
 * <pre>
 * 'A' -&gt; 1
 * 'B' -&gt; 2
 * ...
 * 'Z' -&gt; 26
 * </pre>
 * <p>
 * <p>要 <strong>解码</strong> 一条已编码的消息，所有的数字都必须分组，然后按原来的编码方案反向映射回字母（可能存在多种方式）。例如，<code>"11106"</code> 可以映射为：</p>
 *
 * <ul>
 * <li><code>"AAJF"</code> 对应分组 <code>(1 1 10 6)</code></li>
 * <li><code>"KJF"</code> 对应分组 <code>(11 10 6)</code></li>
 * </ul>
 *
 * <p>注意，像 <code>(1 11 06)</code> 这样的分组是无效的，因为 <code>"06"</code> 不可以映射为 <code>'F'</code> ，因为 <code>"6"</code> 与 <code>"06"</code> 不同。</p>
 *
 * <p><strong>除了</strong> 上面描述的数字字母映射方案，编码消息中可能包含 <code>'*'</code> 字符，可以表示从 <code>'1'</code> 到 <code>'9'</code> 的任一数字（不包括 <code>'0'</code>）。例如，编码字符串 <code>"1*"</code> 可以表示 <code>"11"</code>、<code>"12"</code>、<code>"13"</code>、<code>"14"</code>、<code>"15"</code>、<code>"16"</code>、<code>"17"</code>、<code>"18"</code> 或 <code>"19"</code> 中的任意一条消息。对 <code>"1*"</code> 进行解码，相当于解码该字符串可以表示的任何编码消息。</p>
 *
 * <p>给你一个字符串 <code>s</code> ，由数字和 <code>'*'</code> 字符组成，返回 <strong>解码</strong> 该字符串的方法 <strong>数目</strong> 。</p>
 *
 * <p>由于答案数目可能非常大，返回对 <code>10<sup>9</sup> + 7</code> <strong>取余</strong> 的结果。</p>
 *
 * <p>&nbsp;</p>
 *
 * <p><strong>示例 1：</strong></p>
 *
 * <pre>
 * <strong>输入：</strong>s = "*"
 * <strong>输出：</strong>9
 * <strong>解释：</strong>这一条编码消息可以表示 "1"、"2"、"3"、"4"、"5"、"6"、"7"、"8" 或 "9" 中的任意一条。
 * 可以分别解码成字符串 "A"、"B"、"C"、"D"、"E"、"F"、"G"、"H" 和 "I" 。
 * 因此，"*" 总共有 9 种解码方法。
 * </pre>
 *
 * <p><strong>示例 2：</strong></p>
 *
 * <pre>
 * <strong>输入：</strong>s = "1*"
 * <strong>输出：</strong>18
 * <strong>解释：</strong>这一条编码消息可以表示 "11"、"12"、"13"、"14"、"15"、"16"、"17"、"18" 或 "19" 中的任意一条。
 * 每种消息都可以由 2 种方法解码（例如，"11" 可以解码成 "AA" 或 "K"）。
 * 因此，"1*" 共有 9 * 2 = 18 种解码方法。
 * </pre>
 *
 * <p><strong>示例 3：</strong></p>
 *
 * <pre>
 * <strong>输入：</strong>s = "2*"
 * <strong>输出：</strong>15
 * <strong>解释：</strong>这一条编码消息可以表示 "21"、"22"、"23"、"24"、"25"、"26"、"27"、"28" 或 "29" 中的任意一条。
 * "21"、"22"、"23"、"24"、"25" 和 "26" 由 2 种解码方法，但 "27"、"28" 和 "29" 仅有 1 种解码方法。
 * 因此，"2*" 共有 (6 * 2) + (3 * 1) = 12 + 3 = 15 种解码方法。
 * </pre>
 *
 * <p>&nbsp;</p>
 *
 * <p><strong>提示：</strong></p>
 *
 * <ul>
 * <li><code>1 &lt;= s.length &lt;= 10<sup>5</sup></code></li>
 * <li><code>s[i]</code> 是 <code>0 - 9</code> 中的一位数字或字符 <code>'*'</code></li>
 * </ul>
 * <div><div>Related Topics</div><div><li>字符串</li><li>动态规划</li></div></div><br><div><li>👍 125</li><li>👎 0</li></div>
 */
public class No639_numDecodings {
    private static final int mod = (int) 1e9 + 7;

    public static void main(String[] args) {
        new No639_numDecodings().numDecodings_v1("7*9*3*6*3*0*5*4*9*7*3*7*1*8*3*2*0*0*6*");
        new No639_numDecodings().numDecodings_v2("7*9*3*6*3*0*5*4*9*7*3*7*1*8*3*2*0*0*6*");
        System.out.println();
    }

    public int numDecodings_v1(String s) {
        char[] cs = s.toCharArray();
        int n = cs.length;
        long[] f = new long[n + 1];
        f[0] = 1;
        f[1] = 1;
        if (cs[0] == '*') {
            f[1] = 9;
        }
        if (cs[0] == '0') {
            f[1] = 0;
        }

        for (int i = 1; i < n; i++) {
            char c = cs[i];
            char p = cs[i - 1];
            if (p == '0') {
                if (c == '0') {
                    f[i + 1] = 0;
                } else if (c == '*') {
                    f[i + 1] = f[i] * 9;
                } else {
                    f[i + 1] = f[i];
                }
            } else if (p == '*') {
                if (c == '0') {
                    f[i + 1] = f[i - 1] * 2;
                } else if (c == '*') {
                    f[i + 1] = f[i] * 9 + f[i - 1] * 15;
                } else {
                    f[i + 1] = f[i] + (c <= '6' ? f[i - 1] * 2 : f[i - 1]);
                }
            } else {
                if (c == '0') {
                    if (p <= '2') {
                        f[i + 1] = f[i - 1];
                    } else {
                        f[i + 1] = 0;
                    }
                } else if (c == '*') {
                    if (p == '1') {
                        f[i + 1] = f[i] * 9 + f[i - 1] * 9;
                    } else if (p == '2') {
                        f[i + 1] = f[i] * 9 + f[i - 1] * 6;
                    } else {
                        f[i + 1] = f[i] * 9;
                    }
                } else {
                    if (p == '1') {
                        f[i + 1] = f[i] + f[i - 1];
                    } else if (p == '2') {
                        f[i + 1] = f[i] + (c <= '6' ? f[i - 1] : 0);
                    } else {
                        f[i + 1] = f[i];
                    }
                }
            }
            f[i + 1] = f[i + 1] % ((int) 1e9 + 7);
        }
        return (int) f[n];
    }

    public int numDecodings_v2(String s) {
        char[] cs = s.toCharArray();
        int n = cs.length;
        long[] f = new long[3];
        f[0] = 1;
        f[1] = 1;
        if (cs[0] == '*') {
            f[1] = 9;
        }
        if (cs[0] == '0') {
            f[1] = 0;
        }

        for (int i = 1; i < n; i++) {
            char c = cs[i];
            char p = cs[i - 1];
            if (p == '0') {
                if (c == '0') {
                    f[2] = 0;
                } else if (c == '*') {
                    f[2] = f[1] * 9;
                } else {
                    f[2] = f[1];
                }
            } else if (p == '*') {
                if (c == '0') {
                    f[2] = f[0] * 2;
                } else if (c == '*') {
                    f[2] = f[1] * 9 + f[0] * 15;
                } else {
                    f[2] = f[1] + (c <= '6' ? f[0] * 2 : f[0]);
                }
            } else {
                if (c == '0') {
                    if (p <= '2') {
                        f[2] = f[0];
                    } else {
                        f[2] = 0;
                    }
                } else if (c == '*') {
                    if (p == '1') {
                        f[2] = f[1] * 9 + f[0] * 9;
                    } else if (p == '2') {
                        f[2] = f[1] * 9 + f[0] * 6;
                    } else {
                        f[2] = f[1] * 9;
                    }
                } else {
                    if (p == '1') {
                        f[2] = f[1] + f[0];
                    } else if (p == '2') {
                        f[2] = f[1] + (c <= '6' ? f[0] : 0);
                    } else {
                        f[2] = f[1];
                    }
                }
            }
            f[0] = f[1];
            f[2] = f[1] = f[2] % ((int) 1e9 + 7);
        }
        return (int) f[2];
    }

    public int numDecodings_v3(String s) {
        char[] cs = s.toCharArray();
        int n = cs.length;
        // a = f[i-2], b = f[i-1], c = f[i]
        long a = 0, b = 1, c = 0;
        for (int i = 1; i < n; i++) {
            c = b * check1digit(cs[i - 1]) % mod;
            if (i > 1) {
                c = (c + a * check2digits(cs[i - 2], cs[i - 1])) % mod;
            }
            a = b;
            b = c;
        }
        return (int) c;
    }

    private long check2digits(char c0, char c1) {
        if (c0 == '*' && c1 == '*') {
            return 15;
        }
        if (c0 == '*') {
            return c1 <= '6' ? 2 : 1;
        }
        if (c1 == '*') {
            if (c0 == '1') {
                return 9;
            }
            if (c0 == '2') {
                return 6;
            }
            return 0;
        }
        return (c0 != '0' && (c0 - '0') * 10 + (c1 - '0') <= 26) ? 1 : 0;
    }

    private long check1digit(char c) {
        if (c == '0') {
            return 0;
        }
        return c == '*' ? 9 : 1;
    }

    public int numDecodings_v4(String s) {
        int n = s.length();
        long[] f = new long[3];
        f[0] = 1;
        for (int i = 1; i <= n; i++) {
            char c = s.charAt(i - 1);
            int t = c - '0';
            long cnt = 0;
            int p1 = (i - 1) % 3;
            int p2 = (i - 2) % 3;
            // 枚举组成什么 item（A -> 1; B -> 2 ...）
            for (int item = 1; item <= 26; item++) {
                // 该 item 由一个字符组成
                if (item < 10) {
                    if (c == '*' || t == item) {
                        cnt += f[p1];
                    }
                } else {
                    // 该 item 由两个字符组成
                    if (i < 2) {
                        break;
                    }
                    char pre = s.charAt(i - 2);
                    int u = pre - '0';
                    int a = item / 10;
                    int b = item % 10;
                    if ((pre == '*' || u == a) && (t == b || (c == '*' && b != 0))) {
                        cnt += f[p2];
                    }
                }
            }
            f[i % 3] = cnt % mod;
        }
        return (int) (f[n % 3]);
    }
}
