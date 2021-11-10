package com.moon.javase._juc;

import com.google.common.collect.Lists;

import java.util.List;
import java.util.concurrent.CompletableFuture;

/**
 * @author moon
 */
public class CompletableFutureTest {
    public static void main(String[] args) {
        List<CompletableFuture<String>> completableFutures = Lists.newArrayList();
        for (int i = 0; i < 10; i++) {
            int finalI = i;
            completableFutures.add(CompletableFuture.supplyAsync(() -> {
                String s = finalI + "1";
                System.out.println(s);
                try {
                    Thread.sleep(finalI);
                    System.out.println("thread--" + finalI + " awake");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("thread--" + finalI + " over! " + System.currentTimeMillis());
//                if (finalI == 9) {
//                    throw new RuntimeException("ex test");
//                }
                return s;
            }));
        }

//        CompletableFuture<Void> task6 = CompletableFuture.supplyAsync(() -> {
//            //⾃定义业务操作
//        });

        CompletableFuture<Void> headerFuture = CompletableFuture.allOf(completableFutures.toArray(new CompletableFuture[0]));
        try {
            headerFuture.join();
            System.out.println("all done. ");
        } catch (Exception ex) {
            ex.printStackTrace();
            System.out.println("ex. ");
        }


    }
}
