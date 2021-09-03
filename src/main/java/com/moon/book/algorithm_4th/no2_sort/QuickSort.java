package com.moon.book.algorithm_4th.no2_sort;

import java.util.Arrays;
import java.util.Random;

/**
 * @author yaojia.mu@joymo.tech
 * @date 2021-09-03 21:12:18
 */
public class QuickSort {
    Random random = new Random();

    public static void main(String[] args) {
        int[] a = new int[]{6, 3, 5, 7, 2, 4, 1, 8};

        System.out.println(Arrays.toString(new QuickSort().sort(a)));
    }

    public int[] sort(int[] a) {
        quickSort(a, 0, a.length - 1);
        return a;
    }

//    private int partition(int[] a, int l, int r) {
//        int v = a[l];
//        while (l < r) {
//            while (l < r && v <= a[r]) {
//                r--;
//            }
//            a[l] = a[r];
//            while (l < r && a[l] <= v) {
//                l++;
//            }
//            a[r] = a[l];
//        }
//        a[l] = v;
//        return l;
//    }

    private void quickSort(int[] a, int l, int r) {
        if (l >= r) {
            return;
        }
        int i = partition(a, l, r);
        quickSort(a, l, i - 1);
        quickSort(a, i + 1, r);
    }

    private int partition(int[] a, int l, int r) {
        int i = l;
        int v = a[l];
        while (l < r) {
            while (l < r && v <= a[r]) {
                r--;
            }
            while (l < r && a[l] <= v) {
                l++;
            }
            swap(a, l, r);
        }
        swap(a, l, i);
        return l;
    }

    private void swap(int[] a, int i, int j) {
        int t = a[i];
        a[i] = a[j];
        a[j] = t;
    }
}
