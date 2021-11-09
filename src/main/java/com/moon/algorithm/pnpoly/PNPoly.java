package com.moon.algorithm.pnpoly;

import java.util.Arrays;

/**
 * PNPoly解决点和多边形问题
 * <p>
 * 盒马店的配送范围由一些点组成的多边形确定，给定一个点判断其是否在配送范围内，若在，则此点不需要挪动，打印"no 0"；若不在，则给出此点需要挪动到配送范围的最短距离，打印"yes 距离"。
 *
 * @author moon
 */
public class PNPoly {
    private static void polygon(double[] xs, double[] ys, double x, double y) {
        boolean contained = false;
        // 点是否包含在多边形内
        double xMin = Arrays.stream(xs).min().getAsDouble();
        double xMax = Arrays.stream(xs).max().getAsDouble();
        double yMin = Arrays.stream(ys).min().getAsDouble();
        double yMax = Arrays.stream(ys).max().getAsDouble();
        if (x > xMax || x < xMin || y > yMax || y < yMin) {
            contained = false;
        }
        // 核心算法部分
        int n = xs.length;
        double dist = Double.MAX_VALUE;
        for (int i = 0, j = n - 1; i < n; j = i++) {
            if (((ys[j] > y) != (ys[i] > y)) && (x < (xs[j] - xs[i]) * (y - ys[i]) / (ys[j] - ys[i]) + xs[i])) {
                contained = !contained;
            }
            dist = Math.min(dist, pointToSegmentDist(x, y, xs[i], ys[i], xs[j], ys[j]));
        }
        System.out.println(contained ? "no 0" : "yes" + " " + dist);
    }

    private static double pointToSegmentDist(double px, double py, double ax, double ay, double bx, double by) {
        double ABx = bx - ax;
        double ABy = by - ay;
        double APx = px - ax;
        double APy = py - ay;
        double AB_AP = ABx * APx + ABy * APy;
        double distAB2 = ABx * ABx + ABy * ABy;
        double Dx = ax, Dy = ay;
        if (distAB2 != 0) {
            double t = AB_AP / distAB2;
            if (t >= 1) {
                Dx = bx;
                Dy = by;
            } else if (t > 0) {
                Dx = ax + ABx * t;
                Dy = ay + ABy * t;
            } else {
                Dx = ax;
                Dy = ay;
            }
        }
        double PDx = Dx - px, PDy = Dy - py;
        return Math.sqrt(PDx * PDx + PDy * PDy);
    }


}
