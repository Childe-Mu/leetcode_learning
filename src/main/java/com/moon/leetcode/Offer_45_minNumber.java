package com.moon.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

// 剑指 Offer 45. 把数组排成最小的数
//
//输入一个非负整数数组，把数组里所有数字拼接起来排成一个数，打印能拼接出的所有数字中最小的一个。
//
// 示例 1:
//
// 输入: [10,2]
//输出: "102"
//
// 示例 2:
//
// 输入: [3,30,34,5,9]
//输出: "3033459"
//
// 提示:
//
// 0 < nums.length <= 100
//
// 说明:
//
// 输出结果可能非常大，所以你需要返回一个字符串而不是整数
// 拼接起来的数字可能会有前导 0，最后结果不需要去掉前导 0
//
// Related Topics 排序
// 👍 212 👎 0
public class Offer_45_minNumber {
    public static void main(String[] args) {
//        List<Integer> e = new ArrayList<>();
//        e.add(0, 0);
//        e.add(0, 1);
//        System.out.println(e);
        System.out.println(new Offer_45_minNumber().minNumber_v2(new int[]{9, 0, 1}));
    }

    public String minNumber_v1(int[] nums) {
        return Arrays.stream(nums).boxed().map(String::valueOf).sorted((o1, o2) -> {
            String s1 = o1 + o2;
            String s2 = o2 + o1;
            return s1.compareTo(s2);
        }).collect(Collectors.joining());
    }

    public String minNumber_v2(int[] nums) {
        StringBuilder builder = new StringBuilder();
        List<Integer> e = new ArrayList<>();
        List<Integer> f = new ArrayList<>();

        Arrays.stream(nums).boxed().sorted((a, b) -> {
            if (a == 0 || b == 0) {
                return a == 0 ? -1 : 1;
            }
            if (!Objects.equals(a, b)) {
                int c = a;
                int d = b;
                while (c > 0) {
                    e.add(0, c % 10);
                    c /= 10;
                }
                while (d > 0) {
                    f.add(0, d % 10);
                    d /= 10;
                }
                int s1 = e.size();
                int s2 = f.size();
                for (int i = 0; i < s1 + s2; i++) {
                    List<Integer> t1 = i < s1 ? e : f;
                    List<Integer> t2 = i < s2 ? f : e;
                    int i1 = i < s1 ? i : i - s1;
                    int i2 = i < s2 ? i : i - s2;
                    int diff = t1.get(i1) - t2.get(i2);
                    if (diff != 0) {
                        e.clear();
                        f.clear();
                        return diff;
                    }
                }
                e.clear();
                f.clear();
            }
            return 0;
        }).forEach(builder::append);
        return builder.toString();
    }
}
