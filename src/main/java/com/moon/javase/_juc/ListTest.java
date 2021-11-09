package com.moon.javase._juc;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author moon
 */
public class ListTest {
    public static void main(String[] args) {
        List<Integer> l = new ArrayList<>();
        l.add(1);

        int[] a = new int[]{1};
        a = Arrays.copyOf(a, 2);
        System.out.println(Arrays.toString(a));

    }
}
