package com.moon.javase._juc;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * CountDownLatchDemo
 * <p>
 * countDownLatch这个类使一个线程等待其他线程各自执行完毕后再执行。
 * <p>
 * <b>ps:这个特点非常重要，常见的多线程题目中，可能需要某个线程先执行，某个线程后执行，用这个就可以完美实现</b>
 * <p>
 * 是通过一个计数器来实现的，计数器的初始值是线程的数量。每当一个线程执行完毕后，
 * 计数器的值就-1，当计数器的值为0时，表示所有线程都执行完毕，然后在闭锁上等待的线程就可以恢复工作了。
 * <p>
 * <p>
 * <b>CountDownLatch和CyclicBarrier区别：</b>
 * <p>
 * <i>1.countDownLatch是一个计数器，线程完成一个记录一个，计数器递减，只能只用一次</i>
 * <p>
 * <i>2.CyclicBarrier的计数器更像一个阀门，需要所有线程都到达，然后继续执行，计数器递增，提供reset功能，可以多次使用</i>
 * <p>
 */
public class CountDownLatchDemo {
    public static void main(String[] args) {
        // new CountDownLatchDemo().test1();
        new CountDownLatchDemo().test2();
    }

    /**
     * 主线程-->es2-->es1-->主线程
     */
    private void test1() {
        final CountDownLatch latch = new CountDownLatch(2);
        final CountDownLatch latch1 = new CountDownLatch(1);
        System.out.println("主线程开始执行…… ……");
        //第一个子线程执行
        ExecutorService es1 = Executors.newSingleThreadExecutor();
        es1.execute(() -> {
            try {
                latch1.await();
                System.out.println("子线程：" + Thread.currentThread().getName() + "执行");
                latch.countDown();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        es1.shutdown();

        //第二个子线程执行
        ExecutorService es2 = Executors.newSingleThreadExecutor();
        es2.execute(() -> {
            System.out.println("子线程：" + Thread.currentThread().getName() + "执行");
            latch.countDown();
            latch1.countDown();
        });
        es2.shutdown();
        System.out.println("等待两个线程执行完毕…… ……");
        try {
            latch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("两个子线程都执行完毕，继续执行主线程");
    }

    /**
     * 模拟并发
     */
    private void test2() {
        ExecutorService pool = Executors.newCachedThreadPool();
        CountDownLatch cdl = new CountDownLatch(100);
        for (int i = 0; i < 100; i++) {
            CountRunnable runnable = new CountRunnable(cdl);
            pool.execute(runnable);
        }
    }
}

class CountRunnable implements Runnable {
    private final CountDownLatch countDownLatch;

    CountRunnable(CountDownLatch countDownLatch) {
        this.countDownLatch = countDownLatch;
    }

    @Override
    public void run() {
        try {
            synchronized (countDownLatch) {
                // 每次减少一个容量
                countDownLatch.countDown();
                System.out.println("thread counts = " + (countDownLatch.getCount()));
            }
            countDownLatch.await();
            System.out.println("concurrency counts = " + (100 - countDownLatch.getCount()));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}