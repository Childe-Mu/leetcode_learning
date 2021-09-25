package com.moon.leetcode;

/**
 * 50. Pow(x, n)
 * <p>
 * /**
 * <p>实现 <a href="https://www.cplusplus.com/reference/valarray/pow/" target="_blank">pow(<em>x</em>, <em>n</em>)</a> ，即计算 x 的 n 次幂函数（即，x<sup><span style="font-size:10.8333px">n</span></sup>）。</p>
 *
 * <p> </p>
 *
 * <p><strong>示例 1：</strong></p>
 *
 * <pre>
 * <strong>输入：</strong>x = 2.00000, n = 10
 * <strong>输出：</strong>1024.00000
 * </pre>
 *
 * <p><strong>示例 2：</strong></p>
 *
 * <pre>
 * <strong>输入：</strong>x = 2.10000, n = 3
 * <strong>输出：</strong>9.26100
 * </pre>
 *
 * <p><strong>示例 3：</strong></p>
 * <p>
 * pre>
 * <strong>输入：</strong>x = 2.00000, n = -2
 * <strong>输出：</strong>0.25000
 * <strong>解释：</strong>2<sup>-2</sup> = 1/2<sup>2</sup> = 1/4 = 0.25
 * </pre>
 * <p>
 * p> </p>
 * <p>
 * p><strong>提示：</strong></p>
 *
 * <ul>
 * <li><code>-100.0 < x < 100.0</code></li>
 * <li><code>-2<sup>31</sup> <= n <= 2<sup>31</sup>-1</code></li>
 * <li><code>-10<sup>4</sup> <= x<sup>n</sup> <= 10<sup>4</sup></code></li>
 * </ul>
 * <div><div>Related Topics</div><div><li>递归</li><li>数学</li></div></div><br><div><li>👍 722</li><li>👎 0</li></div>
 */
public class No50_myPow {
    private double ans;

    public static void main(String[] args) {
        System.out.println(new No50_myPow().myPow_v2(2.00000, 5));
    }

    public double myPow_v1(double x, int nn) {
        long n = nn;
        boolean b = n < 0;
        n = Math.abs(n);
        this.ans = 1.0;
        while (n > (1 << 10)) {
            n = cal(x, n);
        }
        while (n > 0) {
            ans *= x;
            n--;
        }
        return b ? 1 / ans : ans;
    }

    int cal(double x, long n) {
        long p = 1;
        double t = x;
        while (p * 2 <= n) {
            t *= t;
            p *= 2;
        }
        ans *= t;
        return (int) (n - p);
    }

    public double myPow_v2(double x, int nn) {
        return (long) nn > 0 ? quickMul(x, nn) : quickMul(x, -(long) nn);
    }

    private double quickMul(double x, long n) {
        if (n == 0) {
            return 1.0;
        } else if ((n & 1) == 0) {
            return quickMul(x, n - 1) * x;
        } else {
            double t = quickMul(x, n >> 1);
            return t * t;
        }
    }

    public double myPow_v3(double x, int n) {
        return n > 0 ? cals(x, n) : 1 / cals(x, -(long) n);
    }

    private double cals(double x, long n) {
        double a = 1;
        while (n > 0) {
            if ((n & 1) == 1) {
                a *= x;
            }
            x *= x;
            n >>= 1;
        }
        return a;
    }
}