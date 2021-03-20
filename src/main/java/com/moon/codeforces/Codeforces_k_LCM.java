package com.moon.codeforces;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

/**
 * C2. k-LCM
 * <p>
 * It is the hard version of the problem. The only difference is that in this version 3≤k≤n.
 * <p>
 * You are given a positive integer n. Find k positive integers a1,a2,…,ak, such that:
 * <p>
 * a1+a2+…+ak=n
 * LCM(a1,a2,…,ak)≤n2
 * Here LCM is the least common multiple of numbers a1,a2,…,ak.
 * <p>
 * We can show that for given constraints the answer always exists.
 * <p>
 * Input
 * The first line contains a single integer t (1≤t≤104)  — the number of test cases.
 * <p>
 * The only line of each test case contains two integers n, k (3≤n≤109, 3≤k≤n).
 * <p>
 * It is guaranteed that the sum of k over all test cases does not exceed 105.
 * <p>
 * Output
 * For each test case print k positive integers a1,a2,…,ak, for which all conditions are satisfied.
 * <p>
 * Example
 * inputCopy
 * 2
 * 6 4
 * 9 5
 * outputCopy
 * 1 2 2 1
 * 1 3 3 1 1
 */
public class Codeforces_k_LCM {

    public static void main(String[] args) throws IOException {
        FastScanner in = new FastScanner(System.in);
        PrintWriter out = new PrintWriter(System.out);
        new Codeforces_k_LCM().run(in, out);
        out.close();
    }

    void run(FastScanner in, PrintWriter out) {
        int t = in.nextInt();
        while (t-- > 0) {
            int n = in.nextInt();
            int k = in.nextInt();
            while (k-- > 3) {
                out.print(1 + " ");
                n--;
            }
            if ((n & 1) == 1) {
                out.println(1 + " " + (n >> 1) + " " + (n >> 1));
            } else {
                int temp = n >> 1;
                if ((temp & 1) == 0) {
                    out.println(temp + " " + (temp >> 1) + " " + (temp >> 1));
                } else {
                    out.println(2 + " " + (temp - 1) + " " + (temp - 1));
                }
            }
        }
    }

    static class FastScanner {
        BufferedReader br;
        StringTokenizer st;

        public FastScanner(InputStream in) {
            br = new BufferedReader(new InputStreamReader(in));
            st = null;
        }

        String next() {
            while (st == null || !st.hasMoreElements()) {
                try {
                    st = new StringTokenizer(br.readLine());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return st.nextToken();
        }

        int nextInt() {
            return Integer.parseInt(next());
        }

        long nextLong() {
            return Long.parseLong(next());
        }
    }
}
