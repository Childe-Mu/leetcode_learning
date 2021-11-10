package com.moon.javase._juc;

import java.util.Random;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * CyclicBarrierDemo
 * <p>
 * 一个同步辅助类，它允许一组线程互相等待，直到到达某个公共屏障点 (common barrier point)。
 * 在涉及一组固定大小的线程的程序中，这些线程必须不时地互相等待，此时 CyclicBarrier 很有用。
 * 因为该 barrier 在释放等待线程后可以重用，所以称它为循环 的 barrier。
 * <p>
 * CyclicBarrier 支持一个可选的 Runnable 命令，在一组线程中的最后一个线程到达之后（但在释放所有线程之前），
 * 该命令只在每个屏障点运行一次。若在继续所有参与线程之前更新共享状态，此屏障操作 很有用。
 * <p>
 * 不用专门去reset()
 */
public class CyclicBarrierDemo {
    private final Random random = new Random();

    public static void main(String[] args) {
        new CyclicBarrierDemo().test1();
    }

    private void test1() {
        CyclicBarrier cyclicBarrier = new CyclicBarrier(5);
        for (int i = 0; i < 5; i++) {
            int finalI = i;
            new Thread(() -> {
                int r = random.nextInt(10);
                System.out.println("thread--" + finalI + " sleep " + r + "s");
                try {
                    Thread.sleep(r * 1000L);
                    System.out.println("thread--" + finalI + " awake");
                    cyclicBarrier.await();
                } catch (InterruptedException | BrokenBarrierException e) {
                    e.printStackTrace();
                }
                System.out.println("thread--" + finalI + " over! " + System.currentTimeMillis());
            }, "thread--" + i).start();
        }
    }


}
