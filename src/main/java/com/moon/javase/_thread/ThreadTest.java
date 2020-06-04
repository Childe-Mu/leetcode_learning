package com.moon.javase._thread;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * @author mooon
 */
public class ThreadTest {
    public static void main(String[] args) {
        System.out.println(Thread.currentThread().getName() + ":" + "输出的结果");
        MyThread1 myThread1 = new MyThread1();
        myThread1.start();
        Thread myThread2 = new Thread(new MyThread2());
        myThread2.start();
        FutureTask<String> futureTask = new FutureTask<>(new MyThread3());
        Thread myThread3 = new Thread(futureTask);
        myThread3.start();
        try {
            System.out.println(futureTask.get());
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
    }
}

class MyThread1 extends Thread {
    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName() + " : " + "extends Thread");
    }
}

class MyThread2 implements Runnable {
    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName() + " : " + "implements Runnable");
    }
}

class MyThread3 implements Callable<String> {
    @Override
    public String call() throws Exception {
        System.out.println(Thread.currentThread().getName() + " : " + "implements Callable");
        return Thread.currentThread().getName() + " : " + "implements Callable";
    }
}
