package com.javarush.task.task28.task2806;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/* 
Знакомство с Executors
*/
public class Solution {
    public static void main(String[] args) throws InterruptedException {
        ExecutorService service = Executors.newFixedThreadPool(5);

        for (int i = 1; i <= 10; i++) {
            final int count = i;
            service.submit(new Runnable() {
                @Override
                public void run() {
                    doExpensiveOperation(count);
                }
            });
        }
        service.shutdown();
        service.awaitTermination(5, TimeUnit.SECONDS);
    }

    private static void doExpensiveOperation(int localId) {
        System.out.println(Thread.currentThread().getName() + ", localId=" + localId);
    }
}
