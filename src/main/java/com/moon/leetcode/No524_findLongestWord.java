package com.moon.leetcode;

import com.google.common.collect.Lists;

import java.util.Arrays;
import java.util.List;

/**
 * 524. 通过删除字母匹配到字典里最长单词
 * <p>给你一个字符串 <code>s</code> 和一个字符串数组 <code>dictionary</code> 作为字典，找出并返回字典中最长的字符串，该字符串可以通过删除 <code>s</code> 中的某些字符得到。</p>
 *
 * <p>如果答案不止一个，返回长度最长且字典序最小的字符串。如果答案不存在，则返回空字符串。</p>
 *
 * <p> </p>
 *
 * <p><strong>示例 1：</strong></p>
 *
 * <pre>
 * <strong>输入：</strong>s = "abpcplea", dictionary = ["ale","apple","monkey","plea"]
 * <strong>输出：</strong>"apple"
 * </pre>
 *
 * <p><strong>示例 2：</strong></p>
 * <p>
 * pre>
 * <strong>输入：</strong>s = "abpcplea", dictionary = ["a","b","c"]
 * <strong>输出：</strong>"a"
 * </pre>
 * <p>
 * p> </p>
 * <p>
 * p><strong>提示：</strong></p>
 *
 * <ul>
 * <li><code>1 <= s.length <= 1000</code></li>
 * <li><code>1 <= dictionary.length <= 1000</code></li>
 * <li><code>1 <= dictionary[i].length <= 1000</code></li>
 * <li><code>s</code> 和 <code>dictionary[i]</code> 仅由小写英文字母组成</li>
 * </ul>
 * <div><div>Related Topics</div><div><li>数组</li><li>双指针</li><li>字符串</li><li>排序</li></div></div><br><div><li>👍 241</li><li>👎 0</li></div>
 */
public class No524_findLongestWord {
    public static void main(String[] args) {
        System.out.println(new No524_findLongestWord().findLongestWord_v2("wsmzffsupzgauxwokahurhhikapmqitytvcgrfpavbxbmmzdhnrazartkzrnsmoovmiofmilihynvqlmwcihkfskwozyjlnpkgdkayioieztjswgwckmuqnhbvsfoevdynyejihombjppgdgjbqtlauoapqldkuhfbynopisrjsdelsfspzcknfwuwdcgnaxpevwodoegzeisyrlrfqqavfziermslnlclbaejzqglzjzmuprpksjpqgnohjjrpdlofruutojzfmianxsbzfeuabhgeflyhjnyugcnhteicsvjajludwizklkkosrpvhhrgkzctzwcghpxnbsmkxfydkvfevyewqnzniofhsriadsoxjmsswgpiabcbpdjjuffnbvoiwotrbvylmnryckpnyemzkiofwdnpnbhkapsktrkkkakxetvdpfkdlwqhfjyhvlxgywavtmezbgpobhikrnebmevthlzgajyrmnbougmrirsxi", Lists.newArrayList("nbmxgkduynigvzuyblwjepn", "jpthiudqzzeugzwwsngebdeai", "apple", "monkey", "plea")));
    }

    public String findLongestWord_v1(String s, List<String> dictionary) {
        int[] cnt = new int[26];
        char[] css = s.toCharArray();
        int n = s.length();
        for (char c : css) {
            cnt[c - 'a']++;
        }
        dictionary.sort((a, b) -> a.length() == b.length() ? a.compareTo(b) : b.length() - a.length());
        for (String d : dictionary) {
            int m = d.length();
            if (m > n) {
                continue;
            }
            char[] csd = d.toCharArray();
            int i = 0, j = 0;
            while (i < m && j < n) {
                if (cnt[csd[i] - 'a'] == 0) {
                    break;
                }
                if (csd[i] == css[j]) {
                    i++;
                    j++;
                } else {
                    j++;
                }
            }
            if (i == m) {
                return d;
            }
        }
        return "";
    }

    public String findLongestWord_v2(String s, List<String> dictionary) {
        int n = s.length();
        char[] css = s.toCharArray();
        int[][] f = new int[n + 1][26];
        Arrays.fill(f[n], n);
        for (int i = n - 1; i >= 0; i--) {
            for (int j = 0; j < 26; j++) {
                if (css[i] == 'a' + j) {
                    f[i][j] = i;
                } else {
                    f[i][j] = f[i + 1][j];
                }
            }
        }
        dictionary.sort((a, b) -> a.length() == b.length() ? a.compareTo(b) : b.length() - a.length());
        for (String d : dictionary) {
            int m = d.length();
            if (m > n) {
                continue;
            }
            char[] csd = d.toCharArray();
            int i = 0, j = 0;
            while (i < m) {
                if (f[j][csd[i] - 'a'] == n) {
                    break;
                } else {
                    j = f[j][csd[i++] - 'a'] + 1;
                }
            }
            if (i == m) {
                return d;
            }
        }
        return "";
    }
}



