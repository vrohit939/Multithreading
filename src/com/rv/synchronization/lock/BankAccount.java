package com.rv.synchronization.lock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class BankAccount {

    private int balance = 100;

    private final Lock lock = new ReentrantLock();

    public void withdraw(int amount) {
        System.out.println(Thread.currentThread().getName() + " attempting to withdraw " + amount);

        try {
            if (lock.tryLock(1, TimeUnit.SECONDS)) {
                if (balance >= amount) {
                    try {
                        System.out.println(Thread.currentThread().getName() + " proceeding with withdrawal");
                        Thread.sleep(3000);
                        balance -= amount;
                        System.out.println(Thread.currentThread().getName() + " completed withdrawal. Remaining balance " + balance);
                    } catch (Exception e) {
                        Thread.currentThread().interrupt();
                    } finally {
                        lock.unlock();
                    }
                } else {
                    System.out.println(Thread.currentThread().getName() + " Insufficient balance");
                }
            } else {
                System.out.println(Thread.currentThread().getName() + " could not acquire the lock. Will try later.");
            }
        } catch (Exception e) {
            Thread.currentThread().interrupt();
        }

//        if (balance >= amount) {
//            System.out.println(Thread.currentThread().getName() + " proceeding with withdrawal");
//            try {
//                Thread.sleep(3000);
//            } catch (InterruptedException e) {
//            }
//            balance -= amount;
//            System.out.println(Thread.currentThread().getName() + " completed withdrawal. Remaining balance " + balance);
//        } else {
//            System.out.println(Thread.currentThread().getName() + " Insufficient balance");
//        }
    }

}
