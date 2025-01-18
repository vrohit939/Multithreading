package com.rv.executorframework;

import java.util.concurrent.*;

public class ScheduledMain {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
/*
        scheduler.schedule(
                () -> System.out.println("Task executed after 5 seconds delay !"),
                5,
                TimeUnit.SECONDS
        );
*/

        scheduler.scheduleAtFixedRate(
                () -> System.out.println("Task executed after 5 seconds delay !"),
                5,
                5,
                TimeUnit.SECONDS
        );

//        ScheduledFuture<?> scheduledFuture = scheduler.scheduleWithFixedDelay(() -> System.out.println("Task started after 5 seconds !"),
//                5,
//                5,
//                TimeUnit.SECONDS);
//
//        scheduledFuture.get();

        scheduler.schedule(() -> {
            System.out.println("Initiating shutdown....");
            scheduler.shutdown();
        }, 20, TimeUnit.SECONDS);

//        scheduler.shutdown();
    }
}
