package com.moon.leetcode;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import java.util.HashSet;
import java.util.Set;
import java.util.Stack;

public class Test3 {
    public boolean isCovered(int[][] ranges, int left, int right) {
        Set<Integer> set = new HashSet<>();
        for (int i = left; i <= right; i++) {
            set.add(i);
        }
        for (int[] range : ranges) {
            int start = range[0];
            int end = range[1];
            for (int i = start; i <= end; i++) {
                set.remove(i);
            }
        }
        return set.isEmpty();
    }

    public int chalkReplacer(int[] chalk, int k) {
        int n = chalk.length;
        long sum = 0;
        for (int i : chalk) {
            sum += i;
        }
        long mod = k % sum;
        int i = 0;
        while (mod > 0) {
            mod -= chalk[i];
            if (mod < 0) {
                return i;
            }
            i = (i + 1) % n;
        }
        return i;
    }

    public int largestMagicSquare(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        int o = Math.min(m, n);
        int ans = 1;
        for (int k = 2; k <= o; k++) {
            for (int i = 0; i < m - k + 1; i++) {
                for (int j = 0; j < n - k + 1; j++) {
                    if (checkSquare(grid, k, i, j)) {
                        ans = Math.max(ans, k);
                    }
                }
            }
        }
        return ans;
    }

    private boolean checkSquare(int[][] grid, int k, int i, int j) {
        int[] row = new int[k];
        int[] col = new int[k];
        int s1 = 0;
        int s2 = 0;
        for (int x = 0; x < k; x++) {
            for (int y = 0; y < k; y++) {
                int g = grid[i + x][j + y];
                row[x] += g;
                col[y] += g;
                if (y == x) {
                    s1 += g;
                }
                if (y == -x + k - 1) {
                    s2 += g;
                }
            }
        }
        if (row[0] == col[0] && col[0] == s1 && s1 == s2) {
            for (int x = 1; x < k; x++) {
                if (row[x] != row[x - 1] || col[x] != col[x - 1]) {
                    return false;
                }
            }
            return true;
        }  else {
            return false;
        }
    }

    private int cal1(String expression)  {
        ScriptEngineManager manager = new ScriptEngineManager();
        ScriptEngine engine = manager.getEngineByName("js");
        Object result = null;
        try {
            result = engine.eval(expression);
        } catch (ScriptException e) {
            e.printStackTrace();
        }
        return (int) result;
    }

    private int cal(char[] cs) {
        Stack<Character> stack = new Stack<>();
        // while (true) {
            // while(!stack.isEmpty() && )
        // }
        return 0;
    }

    public static void main(String[] args) {
        System.out.println(new Test3().largestMagicSquare(new int[][]{{7,1,4,5,6},{2,5,1,6,4},{1,5,4,3,2},{1,2,7,3,4}}));
    }
}
