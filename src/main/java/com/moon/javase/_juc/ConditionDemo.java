package com.moon.javase._juc;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * ConditionDemo<br/>
 * 1. 在调用方法前获取锁<br/>
 * 2. 当调用await()方法后，当前线程会释放锁并在此等待，而其他线程调用Condition对象的signal()方法，
 * 通知当前线程后，当前线程才从await()方法返回，并且在返回前已经获取了锁。<br/>
 */
public class ConditionDemo {
    private Lock lock = new ReentrantLock();
    private Condition condition = lock.newCondition();

    public static void main(String[] args) {
        ConditionDemo demo = new ConditionDemo();
        ExecutorService executorService = Executors.newFixedThreadPool(4);
        // AQS, condition.signal()的信号会给队列首位的线程，
        executorService.execute(demo::conditionWait);
        executorService.execute(demo::conditionWait);
        executorService.execute(demo::conditionSignal);
        executorService.execute(demo::conditionSignal);
    }

    private void conditionWait() {
        lock.lock();
        try {
            System.out.println(Thread.currentThread().getName() + "拿到锁了");
            System.out.println(Thread.currentThread().getName() + "等待信号");
            condition.await();
            System.out.println(Thread.currentThread().getName() + "拿到信号");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    private void conditionSignal() {
        lock.lock();
        try {
            Thread.sleep(5000);
            System.out.println(Thread.currentThread().getName() + "拿到锁了");
            condition.signal();
            System.out.println(Thread.currentThread().getName() + "发出信号");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

}
