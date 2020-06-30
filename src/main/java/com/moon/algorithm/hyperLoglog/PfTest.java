package com.moon.algorithm.hyperLoglog;

import java.util.concurrent.ThreadLocalRandom;

/**
 * HyperLogLog 是最早由 Flajolet 及其同事在 2007 年提出的一种 估算基数的近似最优算法。
 * @author admin
 */
public class PfTest {

    static class BitKeeper {

        private int maxbit;

        public void random() {
            long value = ThreadLocalRandom.current().nextLong(2L << 32);
            int bit = lowZeros(value);
            if (bit > this.maxbit) {
                this.maxbit = bit;
            }
        }

        private int lowZeros(long value) {
            int i = 0;
            for (; i < 32; i++) {
                if (value >> i << i != value) {
                    break;
                }
            }
            return i - 1;
        }
    }

    static class Experiment {

        private int n;
        private BitKeeper keeper;

        public Experiment(int n) {
            this.n = n;
            this.keeper = new BitKeeper();
        }

        public void work() {
            for (int i = 0; i < n; i++) {
                this.keeper.random();
            }
        }

        public void debug() {
            System.out.printf("%d %.2f %d\n", this.n, Math.log(this.n) / Math.log(2), this.keeper.maxbit);
        }
    }

    public static void main(String[] args) {

            Experiment exp = new Experiment(10);
            exp.work();
            exp.debug();

    }
}
