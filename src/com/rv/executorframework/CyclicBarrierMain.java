package com.rv.executorframework;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
/*

public class CyclicBarrierMain {

    public static void main(String[] args) throws ExecutionException, InterruptedException {

        int numberOfServices = 3;

        ExecutorService executorService = Executors.newFixedThreadPool(numberOfServices);
        CyclicBarrier barrier = new CyclicBarrier(numberOfServices);

        Future<String> future1 = executorService.submit(new DependentService1(barrier));
        Future<String> future2 = executorService.submit(new DependentService1(barrier));
        Future<String> future3 = executorService.submit(new DependentService1(barrier));

        // CyclicBarrier does not make main thread wait
        System.out.println("All dependent services finished. Starting main services....");

        barrier.reset(); // reset the barrier

        executorService.shutdown();
    }

}

class DependentService1 implements Callable<String> {

    private CyclicBarrier barrier;

    public DependentService1(CyclicBarrier barrier) {
        this.barrier = barrier;
    }

    @Override
    public String call() throws Exception {
        System.out.println(Thread.currentThread().getName() + " service started");
        Thread.sleep(2000);
        barrier.await();  // Wait for all threads to reach this barrier and then proceed further
        return "OK";
    }

}
*/

public class CyclicBarrierMain {

    public static void main(String[] args) {
        int numberOfParties = 4;
        CyclicBarrier barrier = new CyclicBarrier(numberOfParties, () ->
                System.out.println("All subsystems are up and running. System startup completed.")
        );

        Thread webServerThread = new Thread(new Subsystem("Web Server", 2000, barrier));
        Thread databaseThread = new Thread(new Subsystem("Database", 4000, barrier));
        Thread cacheThread = new Thread(new Subsystem("Cache", 3000, barrier));
        Thread messagingServiceThread = new Thread(new Subsystem("Messaging Service", 3500, barrier));

        webServerThread.start();
        databaseThread.start();
        cacheThread.start();
        messagingServiceThread.start();
    }
}

class Subsystem implements Runnable {

    private String name;

    private int initializationTime;

    private CyclicBarrier barrier;

    public Subsystem(String name, int initializationTime, CyclicBarrier barrier) {
        this.name = name;
        this.initializationTime = initializationTime;
        this.barrier = barrier;
    }

    @Override
    public void run() {
        try {
            System.out.println(name + " initialization started.");
            Thread.sleep(initializationTime);
            System.out.println(name + "initialization completed.");
            barrier.await();        // When all barrier come at this point then barrier action is executed by last thread
        } catch (InterruptedException | BrokenBarrierException e) {
            e.printStackTrace();
        }
    }
}