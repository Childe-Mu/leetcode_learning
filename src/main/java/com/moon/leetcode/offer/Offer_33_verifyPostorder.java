package com.moon.leetcode.offer;

import java.util.Stack;

// 剑指 Offer 33. 二叉搜索树的后序遍历序列
//
//输入一个整数数组，判断该数组是不是某二叉搜索树的后序遍历结果。如果是则返回 true，否则返回 false。假设输入的数组的任意两个数字都互不相同。
//
// 参考以下这颗二叉搜索树：
//
//      5
//    / \
//   2   6
//  / \
// 1   3
//
// 示例 1：
//
// 输入: [1,6,3,2,5]
//输出: false
//
// 示例 2：
//
// 输入: [1,3,2,6,5]
//输出: true
//
// 提示：
//
// 数组长度 <= 1000
//
// 👍 247 👎 0
public class Offer_33_verifyPostorder {
    public static void main(String[] args) {
        System.out.println(new Offer_33_verifyPostorder().verifyPostorder_v2(new int[]{3, 6, 5, 9, 8, 12, 13, 11, 10}));
    }

    public boolean verifyPostorder_v1(int[] postorder) {
        if (postorder == null || postorder.length == 0) {
            return true;
        }
        return traverse(postorder, 0, postorder.length - 1);
    }

    private boolean traverse(int[] postorder, int i, int j) {
        // 如果没有右节点，则可能存在 i>j的情况
        if (i >= j) {
            return true;
        }
        int k = i;
        while (postorder[k] < postorder[j]) {
            k++;
        }
        for (int m = k; m < j; m++) {
            if (postorder[m] < postorder[j]) {
                return false;
            }
        }
        return traverse(postorder, i, k - 1) && traverse(postorder, k, j - 1);
    }

    public boolean verifyPostorder_v2(int[] postorder) {
        Stack<Integer> stack = new Stack<>();
        int parent = Integer.MAX_VALUE;
        //注意for循环是倒叙遍历的
        for (int i = postorder.length - 1; i >= 0; i--) {
            int cur = postorder[i];
            //当如果前节点小于栈顶元素，说明栈顶元素和当前值构成了倒叙，
            //说明当前节点是前面某个节点的左子节点，我们要找到他的父节点
            while (!stack.isEmpty() && stack.peek() > cur) {
                parent = stack.pop();
            }
            //只要遇到了某一个左子节点，才会执行上面的代码，才会更
            //新parent的值，否则parent就是一个非常大的值，也就
            //是说如果一直没有遇到左子节点，那么右子节点可以非常大
            if (cur > parent) {
                return false;
            }
            //入栈
            stack.add(cur);
        }
        return true;
    }
}
