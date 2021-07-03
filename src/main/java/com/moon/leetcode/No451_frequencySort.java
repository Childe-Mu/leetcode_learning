package com.moon.leetcode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// 451. 根据字符出现频率排序
//
//给定一个字符串，请将字符串里的字符按照出现的频率降序排列。
//
// 示例 1:
//
//输入:
//"tree"
//
//输出:
//"eert"
//
//解释:
//'e'出现两次，'r'和't'都只出现一次。
//因此'e'必须出现在'r'和't'之前。此外，"eetr"也是一个有效的答案。
//
// 示例 2:
//
//输入:
//"cccaaa"
//
//输出:
//"cccaaa"
//
//解释:
//'c'和'a'都出现三次。此外，"aaaccc"也是有效的答案。
//注意"cacaca"是不正确的，因为相同的字母必须放在一起。
//
// 示例 3:
//
//输入:
//"Aabb"
//
//输出:
//"bbAa"
//
//解释:
//此外，"bbaA"也是一个有效的答案，但"Aabb"是不正确的。
//注意'A'和'a'被认为是两种不同的字符。
//
// Related Topics 哈希表 字符串 桶排序 计数 排序 堆（优先队列）
// 👍 299 👎 0
public class No451_frequencySort {
    public static void main(String[] args) {
        System.out.println(new No451_frequencySort().frequencySort_v2("bbAa"));
    }

    public String frequencySort_v1(String s) {
        char[] cs = s.toCharArray();
        Map<Character, Integer> cnt = new HashMap<>();
        for (char c : cs) {
            cnt.put(c, cnt.getOrDefault(c, 0) + 1);
        }
        Map<Integer, List<Character>> map = new HashMap<>();
        for (Map.Entry<Character, Integer> entry : cnt.entrySet()) {
            int num = entry.getValue();
            List<Character> list = map.computeIfAbsent(num, o -> new ArrayList<>());
            list.add(entry.getKey());
        }
        List<Integer> keys = new ArrayList<>(map.keySet());
        keys.sort((a, b) -> b - a);
        StringBuilder sb = new StringBuilder();
        for (int key : keys) {
            List<Character> list = map.get(key);
            for (char c : list) {
                for (int i = 0; i < key; i++) {
                    sb.append(c);
                }
            }
        }
        return sb.toString();
    }

    public String frequencySort_v2(String s) {
        Map<Character, Integer> map = new HashMap<>();
        int maxFreq = 0;
        int length = s.length();
        for (int i = 0; i < length; i++) {
            char c = s.charAt(i);
            int frequency = map.getOrDefault(c, 0) + 1;
            map.put(c, frequency);
            maxFreq = Math.max(maxFreq, frequency);
        }
        StringBuilder[] buckets = new StringBuilder[maxFreq + 1];
        for (int i = 0; i <= maxFreq; i++) {
            buckets[i] = new StringBuilder();
        }
        for (Map.Entry<Character, Integer> entry : map.entrySet()) {
            char c = entry.getKey();
            int frequency = entry.getValue();
            buckets[frequency].append(c);
        }
        StringBuilder sb = new StringBuilder();
        for (int i = maxFreq; i > 0; i--) {
            StringBuilder bucket = buckets[i];
            int size = bucket.length();
            for (int j = 0; j < size; j++) {
                for (int k = 0; k < i; k++) {
                    sb.append(bucket.charAt(j));
                }
            }
        }
        return sb.toString();
    }
}
