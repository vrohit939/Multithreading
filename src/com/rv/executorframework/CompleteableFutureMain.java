package com.rv.executorframework;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class CompleteableFutureMain {

    public static void main(String[] args) {

        CompletableFuture<String> completableFuture = CompletableFuture.supplyAsync(() -> { // By default it will be a daemon thread
            try {
                Thread.sleep(5000);
                System.out.println("Worker");
            } catch (InterruptedException e) {

            }
            return "OK";
        }).thenApply(s -> s + s).exceptionally(s -> "Timeout occurred");

/*

        Executor executor = Executors.newFixedThreadPool(3);
        CompletableFuture<String> completableFuture = CompletableFuture.supplyAsync(() -> { // By default it will be a daemon thread
            try {
                Thread.sleep(5000);
                System.out.println("Worker");
            } catch (InterruptedException e) {

            }
            return "OK";
        }, executor).thenApply(s -> s + s).exceptionally(s -> "Timeout occurred");
*/

        String s = null;
        try {
            s = completableFuture.get();
//            s = completableFuture.getNow("Task not completed yet");
            System.out.println(s);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } catch (ExecutionException e) {
            throw new RuntimeException(e);
        }

/*

        CompletableFuture<String> f1 = CompletableFuture.supplyAsync(() -> { // By default it will be a daemon thread
            try {
                Thread.sleep(5000);
                System.out.println("Worker");
            } catch (InterruptedException e) {

            }
            return "OK";
        });

        CompletableFuture<String> f2 = CompletableFuture.supplyAsync(() -> { // By default it will be a daemon thread
            try {
                Thread.sleep(5000);
                System.out.println("Worker");
            } catch (InterruptedException e) {

            }
            return "OK";
        });

        CompletableFuture<Void> f = CompletableFuture.allOf(f1, f2);
        f.join();
//        f.get();  // Need to handle exception
*/

        System.out.println("Main");
    }
}
