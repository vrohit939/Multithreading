package com.rv.synchronization.lock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class FairnessExample {

    private final Lock fair = new ReentrantLock(true);

    public void accessResource() {
        fair.lock();
        try {
            System.out.println(Thread.currentThread().getName() + " acquired the lock");
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        } finally {
            System.out.println(Thread.currentThread().getName() + " released the lock");
            fair.unlock();
        }
    }

    public static void main(String[] args) {
        FairnessExample fairnessExample = new FairnessExample();
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                fairnessExample.accessResource();
            }
        };

        Thread t1 = new Thread(runnable);
        Thread t2 = new Thread(runnable);
        Thread t3 = new Thread(runnable);

        t1.start();
        t2.start();
        t3.start();
    }

}
