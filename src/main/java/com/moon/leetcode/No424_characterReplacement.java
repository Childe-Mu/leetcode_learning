package com.moon.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * 424. 替换后的最长重复字符
 * <p>
 * 给你一个仅由大写英文字母组成的字符串，你可以将任意位置上的字符替换成另外的字符，总共可最多替换 k 次。在执行上述操作后，找到包含重复字母的最长子串的长度。
 * <p>
 * 注意：字符串长度 和 k 不会超过 104。
 * <p>
 * 示例 1：
 * <p>
 * 输入：s = "ABAB", k = 2
 * 输出：4
 * 解释：用两个'A'替换为两个'B',反之亦然。
 * <p>
 * 示例 2：
 * <p>
 * 输入：s = "AABABBA", k = 1
 * 输出：4
 * 解释：
 * 将中间的一个'A'替换为'B',字符串变为 "AABBBBA"。
 * 子串 "BBBB" 有最长重复字母, 答案为 4。
 */
public class No424_characterReplacement {
    /**
     * 我靠，秒啊！！！！！
     * <p>
     * 我们可以枚举字符串中的每一个位置作为右端点，然后找到其最远的左端点的位置，满足该区间内除了出现次数最多的那一类字符之外，剩余的字符（即非最长重复字符）数量不超过 k 个。
     * <p>
     * 这样我们可以想到使用双指针维护这些区间，每次右指针右移，如果区间仍然满足条件，那么左指针不移动，否则左指针至多右移一格，保证区间长度不减小。
     * <p>
     * 虽然这样的操作会导致部分区间不符合条件，即该区间内非最长重复字符超过了 k 个。但是这样的区间也同样不可能对答案产生贡献。当我们右指针移动到尽头，左右指针对应的区间的长度必然对应一个长度最大的符合条件的区间。
     * <p>
     * 实际代码中，由于字符串中仅包含大写字母，我们可以使用一个长度为 26 的数组维护每一个字符的出现次数。每次区间右移，我们更新右移位置的字符出现的次数，然后尝试用它更新重复字符出现次数的历史最大值，最后我们使用该最大值计算出区间内非最长重复字符的数量，以此判断左指针是否需要右移即可。
     */
    public static int characterReplacement(String s, int k) {
        int[] num = new int[26];
        int n = s.length();
        int maxn = 0;
        int left = 0, right = 0;
        while (right < n) {
            num[s.charAt(right) - 'A']++;
            maxn = Math.max(maxn, num[s.charAt(right) - 'A']);
            // 如果left到right区间内，除去出现最多的字符以外的字符数量，超过k(实际上只会是超过一个，所以最后返回结果时，是right-left,而不是right-left+1),
            // 这个时候把left和right两个指针同时往右移动，保证最大的区间不变，直到遇到一个更大的区间，或者结束
            if (right - left + 1 - maxn > k) {
                num[s.charAt(left) - 'A']--;
                left++;
            }
            right++;
        }
        return right - left;
    }

    /**
     * 统计字符数量并不好用
     */
//    public static int characterReplacement_v2(String s, int k) {
//        List<Character> c = new ArrayList<>();
//        List<Integer> num = new ArrayList<>();
//        s += 1;
//        int index = 0, sum = 0;
//        for (int i = 0; i < s.length() - 1; i++) {
//            sum++;
//            if (s.charAt(i) != s.charAt(i + 1)) {
//                c.add(s.charAt(i));
//                num.add(sum);
//                sum = 0;
//            }
//        }
//        int left = 0, right = 0, max = 0;
//        for (int i = 0; i < c.size(); i++) {
//            max = Math.max(max, num.get(i));
//        }
//        return 0;
//    }

    public static void main(String[] args) {
        System.out.println(characterReplacement("AAABB", 1));
    }
}
