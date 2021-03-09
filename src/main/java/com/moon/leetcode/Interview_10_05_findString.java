package com.moon.leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * 面试题 10.05. 稀疏数组搜索
 * <p>
 * 稀疏数组搜索。有个排好序的字符串数组，其中散布着一些空字符串，编写一种方法，找出给定字符串的位置。
 * <p>
 * 示例1:
 * <p>
 * 输入: words = ["at", "", "", "", "ball", "", "", "car", "", "","dad", "", ""], s = "ta"
 * 输出：-1
 * 说明: 不存在返回-1。
 * <p>
 * 示例2:
 * <p>
 * 输入：words = ["at", "", "", "", "ball", "", "", "car", "", "","dad", "", ""], s = "ball"
 * 输出：4
 * 提示:
 * <p>
 * words的长度在[1, 1000000]之间
 */
public class Interview_10_05_findString {
    public static int findString_v1(String[] words, String s) {
        Map<String, Integer> map = new HashMap<>();
        for (int i = 0; i < words.length; i++) {
            map.put(words[i], i);
        }
        return map.getOrDefault(s, -1);
    }

    public static int findString_v2(String[] words, String s) {
        int left = 0;
        int right = words.length - 1;
        return find(words, left, right, s);

    }

    static int find(String[] words, int left, int right, String s) {
        // 1.左右缩界，直到找到非空String
        while (left < right && words[left].equals("")) {
            left++;
        }
        while (left < right && words[right].equals("")) {
            right--;
        }
        //1.1若左边界的值都大于String，说明数组中不存在此s
        //1.2同理，若右边界的值都小于String，说明数组中不存在此s
        if (s.compareTo(words[right]) > 0 || s.compareTo(words[left]) < 0) return -1;
        //2.寻找中间节点，促使二分法进行
        int mid = left + (right - left) / 2;
        //2.1防止中间节点为空，向左找一直找到非空的word【mid】
        while (mid > left && words[mid].equals("")) {
            mid--;
        }
        //2.2若与s相同，则说明找到了解，返回mid
        if (words[mid].equals(s)) return mid;
        int co = s.compareTo(words[mid]);
        //3 根据比较结果决定向左或向右查找
        if (co < 0)
            return find(words, left, mid - 1, s);
        if (co > 0)
            return find(words, mid + 1, right, s);
        //左右都没有返回值，说明无解，返回
        return -1;
    }


    public static int findString_v3(String[] words, String s) {
        int left = 0, right = words.length - 1;
        return findStr(words, s, left, right);
    }

    private static int findStr(String[] words, String s, int left, int right) {
        while (words[left].equals("") && left < right) {
            left++;
        }
        while (words[right].equals("") && left < right){
            right--;
        }
        if (words[left].compareTo(s) > 0 || words[right].compareTo(s) < 0) {
            return -1;
        }
        int mid = left + (right - left) / 2;
        while (words[mid].equals("") && mid < right) {
            mid++;
        }
        if (words[mid].compareTo(s) > 0) {
            return findStr(words, s, left, mid - 1);
        } else if (words[mid].compareTo(s) < 0) {
            return findStr(words, s, mid + 1, right);
        } else {
            return mid;
        }
    }

    public static void main(String[] args) {
        System.out.println(findString_v3(new String[]{"at", "", "", "", "ball", "", "", "car", "", "", "dad", "", ""}, "ball"));
    }
}
