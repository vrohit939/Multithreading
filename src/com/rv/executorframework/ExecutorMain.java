package com.rv.executorframework;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class ExecutorMain {

    public static void main(String[] args) {

        // Using Executor interface
//        Executor executors = Executors.newFixedThreadPool(3);
//
//        for (int i = 1; i < 10; i++) {
//            int finalI = i;
//            executors.execute(() -> {
//                long result = factorial(finalI);
//                System.out.println(result);
//            });
//        }

        // Using ExecutorService interface
        ExecutorService executorService = Executors.newFixedThreadPool(3);

        for (int i = 1; i < 10; i++) {
            int finalI = i;
            executorService.submit(() -> {
                long result = factorial(finalI);
                System.out.println(result);
            });
        }
//        executorService.shutdownNow(); // Immediate shutdown
        executorService.shutdown();
        try {
//            executorService.awaitTermination(100, TimeUnit.SECONDS); // Wait for specified time to complete
            while (!executorService.awaitTermination(100, TimeUnit.SECONDS)) {
                // Will wait until all the tasks are completed (Unlimited wait)
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        System.out.println("Executor completed");
    }

    public static long factorial(int n) {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        long result = 1;
        for (int i = 1; i <= n; i++) {
            result *= i;
        }
        return result;
    }
}
