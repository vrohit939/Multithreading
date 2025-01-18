package com.rv.executorframework;

import java.util.concurrent.*;

public class CountDownLatchCachedThreadPoolMain {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
/*
        ExecutorService executorService = Executors.newCachedThreadPool();
        Future<String> future1 = executorService.submit(new DependentService());
        Future<String> future2 = executorService.submit(new DependentService());
        Future<String> future3 = executorService.submit(new DependentService());

        future1.get();
        future2.get();
        future3.get();
*/

        int numberOfServices = 3;

        ExecutorService executorService = Executors.newFixedThreadPool(numberOfServices);
        CountDownLatch latch = new CountDownLatch(numberOfServices);

        Future<String> future1 = executorService.submit(new DependentService(latch));
        Future<String> future2 = executorService.submit(new DependentService(latch));
        Future<String> future3 = executorService.submit(new DependentService(latch));
        latch.await();
//        latch.await(1, TimeUnit.MILLISECONDS);  // Will make the main thread wait for specified time and proceed after that running the dependent service independently

        System.out.println("All dependent services finished. Starting main services....");
        executorService.shutdown();
    }

}

class DependentService implements Callable<String> {

    private CountDownLatch latch;

    public DependentService(CountDownLatch latch) {
        this.latch = latch;
    }

    @Override
    public String call() throws Exception {
        System.out.println(Thread.currentThread().getName() + " service started");
        Thread.sleep(6000);
        latch.countDown();  // Decrement latch count so as all threads are done processing
        return "OK";
    }
}
