package com.rv.threadcreation;

public class ThreadMethod extends Thread {

    public ThreadMethod(String name){
        super(name);
    }

    @Override
    public void run() {
        for (int i = 0; i < 5; i++) {
            try {
                Thread.sleep(1000);
                Thread.yield();
                System.out.println(Thread.currentThread().getName() + " - PRIORITY - " + Thread.currentThread().getPriority());
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        ThreadMethod threadMethod1 = new ThreadMethod("Thread-1");
        threadMethod1.setPriority(10);

        ThreadMethod threadMethod2 = new ThreadMethod("Thread-2");
        threadMethod2.setPriority(1);

        threadMethod1.start();
        threadMethod2.start();

//        threadMethod1.interrupt();

        threadMethod1.join();
        threadMethod2.join();
        System.out.println("Main Thread");
    }

//    public static void main(String[] args) {
//        ThreadMethod threadMethod = new ThreadMethod("Daemon");
//        threadMethod.setDaemon(true);
//        threadMethod.start();
//    }

}
