package com.moon.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * 118. 杨辉三角<br/>
 * 给定一个非负整数 numRows，生成杨辉三角的前 numRows 行。<br/>
 * 在杨辉三角中，每个数是它左上方和右上方的数的和。<br/>
 * <br/>
 * 示例:<br/>
 * 输入: 5<br/>
 * 输出:<br/>
 * [<br/>
 * [1],<br/>
 * [1,1],<br/>
 * [1,2,1],<br/>
 * [1,3,3,1],<br/>
 * [1,4,6,4,1]<br/>
 * ]<br/>
 */
public class No118_generate {
    // public static List<List<Integer>> generate(int numRows) {
    //     if (numRows <= 0) {
    //         return new ArrayList<>();
    //     }
    //     List<List<Integer>> result = new ArrayList<>();
    //     List<Integer> first = new ArrayList<>();
    //     first.add(1);
    //     result.add(first);
    //     for (int i = 1; i < numRows; i++) {
    //         List<Integer> list = new ArrayList<>();
    //         List<Integer> lastList = result.get(i - 1);
    //         for (int j = 0; j <= i; j++) {
    //             if (j == 0) {
    //                 list.add(lastList.get(j));
    //             } else if (j >= 1 && j < i) {
    //                 list.add(lastList.get(j - 1) + lastList.get(j));
    //             } else {
    //                 list.add(lastList.get(j - 1));
    //             }
    //         }
    //         result.add(list);
    //     }
    //     return result;
    // }

    public static List<List<Integer>> generate(int numRows) {
        List<List<Integer>> triangle = new ArrayList<>();
        if (numRows == 0) {
            return triangle;
        }
        triangle.add(new ArrayList<>());
        triangle.get(0).add(1);
        for (int rowNum = 1; rowNum < numRows; rowNum++) {
            List<Integer> row = new ArrayList<>();
            List<Integer> prevRow = triangle.get(rowNum - 1);
            // 相比于上面的方法，少了两个判断，提升了效率
            row.add(1);
            for (int j = 1; j < rowNum; j++) {
                row.add(prevRow.get(j - 1) + prevRow.get(j));
            }
            row.add(1);
            triangle.add(row);
        }
        return triangle;
    }

    public static void main(String[] args) {
        System.out.println(generate(5));
    }
}
