package com.moon.leetcode;

/**
 * 944. 删列造序
 * <p>
 * 给定由 N 个小写字母字符串组成的数组 A，其中每个字符串长度相等。
 * <p>
 * 你需要选出一组要删掉的列 D，对 A 执行删除操作，使 A 中剩余的每一列都是 非降序 排列的，然后请你返回 D.length 的最小可能值。
 * <p>
 * 删除 操作的定义是：选出一组要删掉的列，删去 A 中对应列中的所有字符，形式上，第 n 列为 [A[0][n], A[1][n], ..., A[A.length-1][n]]）。（可以参见 删除操作范例）
 * <p>
 * 示例 1：
 * 输入：["cba", "daf", "ghi"]
 * 输出：1
 * 解释：
 * 当选择 D = {1}，删除后 A 的列为：["c","d","g"] 和 ["a","f","i"]，均为非降序排列。
 * 若选择 D = {}，那么 A 的列 ["b","a","h"] 就不是非降序排列了。
 * <p>
 * 示例 2：
 * 输入：["a", "b"]
 * 输出：0
 * 解释：D = {}
 * <p>
 * 示例 3：
 * 输入：["zyx", "wvu", "tsr"]
 * 输出：3
 * 解释：D = {0, 1, 2}
 * <p>
 * 提示：
 * 1 <= A.length <= 100
 * 1 <= A[i].length <= 1000
 * <p>
 * 删除操作范例：
 * <p>
 * 比如，有 A = ["abcdef", "uvwxyz"]，
 * <p>
 * 要删掉的列为 {0, 2, 3}，删除后 A 为["bef", "vyz"]， A 的列分别为["b","v"], ["e","y"], ["f","z"]。
 */
public class No944_minDeletionSize {

    // /**
    //  * 先将字符串数组转换成，按照每列字母倒序排放的数组矩阵，倒序是因为Arrays.sort()方法默认是升序排序，
    //  * 然后对每一列元素进行排序，如果排序后结果和排序前一直，则表示这一列元素是降序排列，给结果+1
    //  */
    // public static int minDeletionSize(String[] A) {
    //     int re = 0;
    //     int len = A[0].length();
    //     char[][] temp = new char[A[0].length()][A.length];
    //     for (int i = 0; i < A.length; i++) {
    //         for (int j = 0; j < len; j++) {
    //             temp[len - j - 1][i] = A[i].charAt(j);
    //         }
    //     }
    //     for (char[] chars : temp) {
    //         char[] c = Arrays.copyOfRange(chars, 0, chars.length);
    //         Arrays.sort(c);
    //         if (!Arrays.equals(chars, c)) {
    //             re++;
    //         }
    //     }
    //     return re;
    // }

    /**
     * 神经病啊，看了题解，感觉智障，非降序 = 升序 + 无序 才对吧，为什么要把无序也要删除掉
     */
    public static int minDeletionSize(String[] A) {
        int ans = 0;
        for (int c = 0; c < A[0].length(); ++c) {
            for (int r = 0; r < A.length - 1; ++r) {
                if (A[r].charAt(c) > A[r + 1].charAt(c)) {
                    ans++;
                    break;
                }
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        String[] a = new String[]{"cba", "daf", "ghi"};
        System.out.println(minDeletionSize(a));
    }
}
