package com.moon.codeforces.con1497;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.PrintWriter;
import java.util.Arrays;

public class E2 {

    static FastScanner sc = new FastScanner();
    static PrintWriter out = new PrintWriter(System.out);

    public static void main(String[] args) {
//        generate();
        int MAX = (int) 1e7;
        int[] spf = new int[MAX + 1];
        for (int i = 2; i <= MAX; i++) {
            if (spf[i] == 0) {
                spf[i] = i;
                for (int j = i + i; j <= MAX; j += i) {
                    if (spf[j] == 0) {
                        spf[j] = i;
                    }
                }
            }
        }
        int[] freq = new int[MAX + 1];
        int T = sc.nextInt();
        while (T-- > 0) {
            int N = sc.nextInt();
            int K = sc.nextInt();
            int[] a = new int[N + 1];
            for (int i = 1; i <= N; i++) {
                a[i] = sc.nextInt();
                int canonical = 1;
                while (a[i] > 1) {
                    int factor = spf[a[i]];
                    int parity = 0;
                    while (a[i] % factor == 0) {
                        a[i] /= factor;
                        parity ^= 1;
                    }
                    if (parity == 1) {
                        canonical *= factor;
                    }
                }
                a[i] = canonical;
            }
            int[][] transition = new int[K + 1][N + 1];
//            HashMap<Integer, Integer> freq = new HashMap<>();
            for (int k = 0; k <= K; k++) {
                int l = N + 1;
                int duplicates = 0;
                for (int r = N; r >= 1; r--) {
                    while (l - 1 >= 1) {
                        int nextDuplicates = duplicates;
                        if (freq[a[l - 1]] >= 1) {
                            nextDuplicates++;
                        }
                        if (nextDuplicates <= k) {
                            duplicates = nextDuplicates;
                            freq[a[l - 1]]++;
                            l--;
                        } else {
                            break;
                        }
                    }
                    transition[k][r] = l;
                    if (--freq[a[r]] >= 1) {
                        duplicates--;
                    }
                }
            }
            int[][] dp = new int[K + 1][N + 1];
            int oo = (int) 1e9;
            for (int[] row : dp) {
                Arrays.fill(row, oo);
            }
            for (int k = 0; k <= K; k++) {
                dp[k][0] = 0;
            }
            for (int r = 1; r <= N; r++) {
                for (int k = 0; k <= K; k++) {
                    for (int delta = 0; delta <= k; delta++) {
                        dp[k][r] = Math.min(dp[k][r], dp[k - delta][transition[delta][r] - 1] + 1);
                    }
                }
            }
            out.println(dp[K][N]);
        }
        out.close();
    }

    static void ASSERT(boolean assertion, String message) {
        if (!assertion) throw new AssertionError(message);
    }

    static void ASSERT(boolean assertion) {
        if (!assertion) throw new AssertionError();
    }

    static class FastScanner {
        private int BS = 1 << 16;
        private char NC = (char) 0;
        private byte[] buf = new byte[BS];
        private int bId = 0, size = 0;
        private char c = NC;
        private double cnt = 1;
        private BufferedInputStream in;

        public FastScanner() {
            in = new BufferedInputStream(System.in, BS);
        }

        public FastScanner(String s) {
            try {
                in = new BufferedInputStream(new FileInputStream(s), BS);
            } catch (Exception e) {
                in = new BufferedInputStream(System.in, BS);
            }
        }

        char getChar() {
            while (bId == size) {
                try {
                    size = in.read(buf);
                } catch (Exception e) {
                    return NC;
                }
                if (size == -1) return NC;
                bId = 0;
            }
            return (char) buf[bId++];
        }

        int nextInt() {
            return (int) nextLong();
        }

        long nextLong() {
            cnt = 1;
            boolean neg = false;
            if (c == NC) c = getChar();
            for (; (c < '0' || c > '9'); c = getChar()) {
                if (c == '-') neg = true;
            }
            long res = 0;
            for (; c >= '0' && c <= '9'; c = getChar()) {
                res = (res << 3) + (res << 1) + c - '0';
                cnt *= 10;
            }
            return neg ? -res : res;
        }

        double nextDouble() {
            boolean neg = false;
            if (c == NC) c = getChar();
            for (; (c < '0' || c > '9'); c = getChar()) {
                if (c == '-') neg = true;
            }
            double cur = nextLong();
            if (c != '.') {
                return neg ? -cur : cur;
            } else {
                double frac = nextLong() / cnt;
                return neg ? -cur - frac : cur + frac;
            }
        }

        String next() {
            StringBuilder res = new StringBuilder();
            while (c <= 32) c = getChar();
            while (c > 32) {
                res.append(c);
                c = getChar();
            }
            return res.toString();
        }

        String nextLine() {
            StringBuilder res = new StringBuilder();
            while (c <= 32) c = getChar();
            while (c != '\n') {
                res.append(c);
                c = getChar();
            }
            return res.toString();
        }

        boolean hasNext() {
            if (c > 32) return true;
            while (true) {
                c = getChar();
                if (c == NC) return false;
                else if (c > 32) return true;
            }
        }
    }
}
