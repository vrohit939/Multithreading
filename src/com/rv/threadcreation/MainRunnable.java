package com.rv.threadcreation;

public class MainRunnable {

    public static void main(String[] args) {
        WorldRunnable world = new WorldRunnable();
        Thread t1 = new Thread(world);
        t1.start();

        for (int i = 0; i < 100; i++)
            System.out.println("Hello");
    }

}
