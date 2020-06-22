package com.moon.javase._thread;

/**
 *
 */
public class SleepTest {
    public static boolean flag = false;
    public static void main(String[] args) {
        Thread read = new Thread(() -> {
            while (!flag) {

            }
        });
        read.start();

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        Thread write = new Thread(() -> flag = true);
        write.start();
    }
}
