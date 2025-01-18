package com.rv.threadcreation;

public class ThreadState extends  Thread{

    @Override
    public void run() {
        System.out.println("RUNNING");
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) throws InterruptedException {
        ThreadState threadState = new ThreadState();
        threadState.setName("Thread1");
        System.out.println(threadState.getState());
        threadState.start();
        System.out.println(threadState.getState());
        ThreadState.sleep(100);
        System.out.println(threadState.getState());
        threadState.join();
        System.out.println(threadState.getState());
    }
}
