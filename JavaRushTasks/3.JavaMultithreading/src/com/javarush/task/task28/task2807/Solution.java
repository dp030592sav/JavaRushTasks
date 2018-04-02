package com.javarush.task.task28.task2807;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/* 
Знакомство с ThreadPoolExecutor
*/
public class Solution {
    public static void main(String[] args) throws InterruptedException {
        BlockingQueue<Runnable> runnables = new LinkedBlockingQueue<>();

        for (int i = 1; i <= 10; i++) {
            final int count = i;
            runnables.add(new Runnable() {
                @Override
                public void run() {
                    doExpensiveOperation(count);
                }
            });
        }

        ThreadPoolExecutor poolExecutor = new ThreadPoolExecutor(3, 5, 1000, TimeUnit.MILLISECONDS, runnables);
        poolExecutor.prestartAllCoreThreads();
        poolExecutor.shutdown();
        poolExecutor.awaitTermination(5, TimeUnit.SECONDS);
    }

    private static void doExpensiveOperation(int localId) {
        System.out.println(Thread.currentThread().getName() + ", localId=" + localId);
    }
}
