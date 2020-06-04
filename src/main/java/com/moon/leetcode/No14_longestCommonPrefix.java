package com.moon.leetcode;

/**
 * 14. 最长公共前缀  <br/>
 * 编写一个函数来查找字符串数组中的最长公共前缀。<br/>
 * 如果不存在公共前缀，返回空字符串 ""。 <br/>
 * <br/>
 * 示例 1: <br/>
 * 输入: ["flower","flow","flight"]<br/>
 * 输出: "fl"<br/>
 * <br/>
 * 示例 2: <br/>
 * 输入: ["dog","racecar","car"]<br/>
 * 输出: ""<br/>
 * 解释: 输入不存在公共前缀。<br/>
 * <p>
 * 说明: <br/>
 * 所有输入只包含小写字母 a-z 。<br/>
 * Related Topics 字符串<br/>
 */
public class No14_longestCommonPrefix {
    // /**
    //  * 水平扫描1，从前往后
    //  */
    // public static String longestCommonPrefix(String[] strs) {
    //     if (strs == null || strs.length == 0) {
    //         return "";
    //     }
    //     String shortestStr = strs[0];
    //     String result = "";
    //     for (String str : strs) {
    //         if (shortestStr.length() > str.length()) {
    //             shortestStr = str;
    //         }
    //     }
    //     for (int i = 0; i < shortestStr.length(); i++) {
    //         char c = shortestStr.charAt(i);
    //         for (String str : strs) {
    //             if (str.charAt(i) != c) {
    //                 return shortestStr.substring(0, i);
    //             }
    //         }
    //     }
    //     return shortestStr;
    // }

    // /**
    //  * 水平扫描2，从前往后
    //  */
    // public static String longestCommonPrefix(String[] strs) {
    //     if (strs == null || strs.length == 0) return "";
    //     for (int i = 0; i < strs[0].length() ; i++){
    //         char c = strs[0].charAt(i);
    //         for (int j = 1; j < strs.length; j ++) {
    //             if (i == strs[j].length() || strs[j].charAt(i) != c)
    //                 return strs[0].substring(0, i);
    //         }
    //     }
    //     return strs[0];
    // }

    // /**
    //  * 水平扫描3，从后往前
    //  * 此解法相较于前面两种从前往后的，要快不少，原因是，匹配最长前缀，情况下，前面n个字符都是相同的，后面m个是不同的，
    //  * 从前往后，会导致 n x strs.length()，而从前往后，则只需要比较 m x 1次
    //  */
    // public static String longestCommonPrefix(String[] strs) {
    //     if (strs == null || strs.length == 0){
    //         return "";
    //     }
    //     String prefix = strs[0];
    //     for (String str : strs) {
    //         while (str.indexOf(prefix) != 0) {
    //             prefix = prefix.substring(0, prefix.length() - 1);
    //             if (prefix.isEmpty()) {
    //                 return "";
    //             }
    //         }
    //     }
    //     return prefix;
    // }

    /**
     * 二分法
     */
    public static String longestCommonPrefix(String[] strs) {
        if (strs == null || strs.length == 0) {
            return "";
        }
        int left = 1;
        int right = strs[0].length();
        int mid = 0;
        while (left <= right) {
            mid = (left + right) / 2;
            String temp = strs[0].substring(0, mid);
            System.out.println(strs[0].substring(0, mid));
            if (isCommonPrefix(strs, temp)) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return strs[0].substring(0, (left + right) / 2);
    }

    private static boolean isCommonPrefix(String[] strs, String temp) {
        for (String str : strs) {
            if (str.indexOf(temp) != 0) {
                return false;
            }
        }
        return true;
    }


    public static void main(String[] args) {
        // System.out.println("123".startsWith(""));
        System.out.println(longestCommonPrefix(new String[]{"flower","flow","flight"}));
    }
}
