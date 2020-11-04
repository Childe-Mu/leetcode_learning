package com.moon.javase._juc;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/**
 * SemaphoreDemo
 * <p>
 * 　Semaphore也是一个线程同步的辅助类，可以维护当前访问自身的线程个数，并提供了同步机制。使用Semaphore可以控制同时访问资源的线程个数，例如，实现一个文件允许的并发访问数。
 * <p>
 * Semaphore的主要方法摘要：
 * <p>
 * 　　void acquire():从此信号量获取一个许可，在提供一个许可前一直将线程阻塞，否则线程被中断。
 * <p>
 * 　　void release():释放一个许可，将其返回给信号量。
 * <p>
 * 　　int availablePermits():返回此信号量中当前可用的许可数。
 * <p>
 * 　　boolean hasQueuedThreads():查询是否有线程正在等待获取。
 */
public class SemaphoreDemo {
    public static void main(String[] args) {
        new SemaphoreDemo().test1();
        // new SemaphoreDemo().test2();
    }

    /**
     * 从这个测试可以看出，初始化的许可并不是总量，即所谓的许可就是一个数字，这个数字不能小于0，因为数字减小是通过acquire()进行的，
     * 如果为0就会阻塞，但是数字却可以不断地增大，增大调用的release()方法并没有限制
     */
    private void test1() {
        // 初始化信号量为0
        Semaphore barSemaphore = new Semaphore(0);
        // 打印可以看到可用的许可为0
        System.out.println(barSemaphore.availablePermits());
        // 直接释放
        barSemaphore.release();
        // barSemaphore.release();
        // barSemaphore.release();
        // barSemaphore.release();
        // 打印可以看到可用的许可为1（释放几次，就是几）
        System.out.println(barSemaphore.availablePermits());
    }

    /**
     * 正常使用
     */
    private void test2() {
        ExecutorService service = Executors.newCachedThreadPool();
        //创建Semaphore信号量，初始化许可大小为3
        final Semaphore sp = new Semaphore(3);
        for (int i = 0; i < 10; i++) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e2) {
                e2.printStackTrace();
            }
            Runnable runnable = () -> {
                try {
                    //请求获得许可，如果有可获得的许可则继续往下执行，许可数减1。否则进入阻塞状态
                    sp.acquire();
                    System.out.println("线程" + Thread.currentThread().getName() + "进入，当前已有" + (3 - sp.availablePermits()) + "个并发");
                    Thread.sleep((long) (Math.random() * 10000));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("线程" + Thread.currentThread().getName() + "即将离开");
                //释放许可，许可数加1
                sp.release();
                //下面代码有时候执行不准确，因为其没有和上面的代码合成原子单元
                // System.out.println("线程" + Thread.currentThread().getName() + "已离开，当前已有" + (3 - sp.availablePermits()) + "个并发");
            };
            service.execute(runnable);
        }
    }
}
