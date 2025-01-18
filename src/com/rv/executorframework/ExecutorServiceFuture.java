package com.rv.executorframework;

import java.util.concurrent.*;

public class ExecutorServiceFuture {

    public static void main(String[] args) throws InterruptedException, ExecutionException {
        ExecutorService executorService = Executors.newSingleThreadExecutor();

        Callable<String> callable = () -> {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                System.out.println("Exception occured: " + e);
            }
            return "42";
        };

        Future<?> future = executorService.submit(callable);

        if (future.isDone()) {
            System.out.println("Future Completed");
        }

//        Future<String> submit = executorService.submit(() -> System.out.println("Hello from runnable"), "Completed");
//        if (submit.isDone()) {
//            System.out.println("Runnable Completed ");
//        }
//        System.out.println(submit.get());

        Thread.sleep(1000);
        future.cancel(false);
        System.out.println(future.isCancelled());

//        System.out.println(future.get());
        executorService.shutdown();
        Thread.sleep(1000);
        System.out.println(executorService.isTerminated()); // returns true when processing completed


/*
        // Invoke All

        Callable<Integer> callable1 = () -> {
            System.out.println("Task 1");
            Thread.sleep(1000);
            return 1;
        };
        Callable<Integer> callable2 = () -> {
            System.out.println("Task 2");
            Thread.sleep(1000);
            return 2;
        };
        Callable<Integer> callable3 = () -> {
            System.out.println("Task 3");
            Thread.sleep(1000);
            return 3;
        };

        List<Callable<Integer>> callables = Arrays.asList(callable1, callable2, callable3);

        List<Future<Integer>> futures = null;
*/

/*
        try {
            futures = executorService.invokeAll(callables, 1, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        for (Future<Integer> future : futures) {
            try {
                System.out.println(future.get());
            } catch (CancellationException | ExecutionException e) {

            } catch (InterruptedException e) {

            }
        }
*/
/*
        // Invoke Any
        try {
            Integer integer = executorService.invokeAny(callables);
            System.out.println(integer);
        } catch (InterruptedException | ExecutionException e) {
        }

        executorService.shutdown();
*/

        System.out.println("Main Done");
    }

}
