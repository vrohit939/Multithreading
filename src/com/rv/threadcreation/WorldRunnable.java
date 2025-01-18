package com.rv.threadcreation;

public class WorldRunnable implements Runnable {

    @Override
    public void run() {
        for (int i = 0; i < 100; i++)
            System.out.println("World");
    }
}
