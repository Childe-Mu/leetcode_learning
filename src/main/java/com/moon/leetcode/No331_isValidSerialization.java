package com.moon.leetcode;

import java.util.Stack;

/**
 * 331. 验证二叉树的前序序列化
 * <p>
 * 序列化二叉树的一种方法是使用前序遍历。当我们遇到一个非空节点时，我们可以记录下这个节点的值。如果它是一个空节点，我们可以使用一个标记值记录，例如 #。
 * <p>
 * _9_
 * /   \
 * 3     2
 * / \   / \
 * 4   1  #  6
 * / \ / \   / \
 * # # # #   # #
 * <p>
 * 例如，上面的二叉树可以被序列化为字符串 "9,3,4,#,#,1,#,#,2,#,6,#,#"，其中 # 代表一个空节点。
 * <p>
 * 给定一串以逗号分隔的序列，验证它是否是正确的二叉树的前序序列化。编写一个在不重构树的条件下的可行算法。
 * <p>
 * 每个以逗号分隔的字符或为一个整数或为一个表示 null 指针的 '#' 。
 * <p>
 * 你可以认为输入格式总是有效的，例如它永远不会包含两个连续的逗号，比如 "1,,3" 。
 * <p>
 * 示例 1:
 * <p>
 * 输入: "9,3,4,#,#,1,#,#,2,#,6,#,#"
 * 输出: true
 * <p>
 * 示例 2:
 * <p>
 * 输入: "1,#"
 * 输出: false
 * <p>
 * 示例 3:
 * <p>
 * 输入: "9,#,#,1"
 * 输出: false
 */
public class No331_isValidSerialization {
    public static boolean isValidSerialization_v1(String preorder) {
        int n = preorder.length();
        Stack<Integer> stack = new Stack<>();
        stack.push(1);
        int i = 0;
        while (i < n) {
            if (stack.isEmpty()) {
                return false;
            }
            if (preorder.charAt(i) == ',') {
                i++;
            } else if (preorder.charAt(i) == '#') {
                int temp = stack.pop() - 1;
                if (temp > 0) {
                    stack.push(temp);
                }
                i++;
            } else {
                // 读一个数字,数字可能是多位，比如大于10
                while (i < n && preorder.charAt(i) != ',') {
                    i++;
                }
                int temp = stack.pop() - 1;
                if (temp > 0) {
                    stack.push(temp);
                }
                stack.push(2);
                i++;
            }
        }
        return stack.isEmpty();
    }

    public static boolean isValidSerialization_v2(String preorder) {
        String[] ss = preorder.split(",");
        int n = ss.length;
        Stack<Integer> stack = new Stack<>();
        stack.push(1);
        int i = 0;
        while (i < n) {
            if (stack.isEmpty()) {
                return false;
            }
            int temp = stack.pop() - 1;
            if (temp > 0) {
                stack.push(temp);
            }
            if (!ss[i].equals("#")) {
                stack.push(2);
            }
            i++;
        }
        return stack.isEmpty();
    }

    public static boolean isValidSerialization_v3(String preorder) {
        String[] ss = preorder.split(",");
        int i = 0;
        int slots = 1;
        while (i < ss.length) {
            if (slots == 0) {
                return false;
            }
            slots--;
            if (!ss[i].equals("#")) {
                slots += 2;
            }
            i++;
        }
        return slots == 0;
    }

    public static void main(String[] args) {
        System.out.println(isValidSerialization_v3("9,#,92,#,#"));
    }
}
