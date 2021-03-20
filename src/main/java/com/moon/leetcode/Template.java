package com.moon.leetcode;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

/**
 * @Description:
 * @Author: quanrui.li
 * @Date: 2021/3/19
 */
public class Template {

    public static void main(String[] args) throws IOException {
        FastScanner in = new FastScanner(System.in);
        PrintWriter out = new PrintWriter(System.out);
        new Template().run(in, out);
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
