package com.moon.leetcode;

/**
 * 58. 最后一个单词的长度
 * <p>
 * 给定一个仅包含大小写字母和空格 ' ' 的字符串 s，返回其最后一个单词的长度。如果字符串从左向右滚动显示，那么最后一个单词就是最后出现的单词。
 * <p>
 * 如果不存在最后一个单词，请返回 0 。
 * <p>
 * 说明：一个单词是指仅由字母组成、不包含任何空格字符的 最大子字符串。
 * <p>
 * 示例:
 * <p>
 * 输入: "Hello World"
 * <p>
 * 输出: 5
 */
public class No58_lengthOfLastWord {
    /**
     * 利用内置方法
     */
    //    public static int lengthOfLastWord(String s) {
//        String[] ss = s.split(" ");
//        return ss.length > 0 ? ss[ss.length-1].length() : 0;
//    }
//

    /**
     * 从后往前，寻找" ",找到则循环结束，返回长度，需要注意的是，要去除掉字符串最后的空格
     */
    public static int lengthOfLastWord(String s) {
        s = s.trim();
        int i = s.length() - 1;
        int l = 0;
        while (i >= 0 && s.charAt(i--) != ' ') {
            l++;
        }
        return l;
    }

    public static void main(String[] args) {

        System.out.println(lengthOfLastWord("a    "));
    }
}
