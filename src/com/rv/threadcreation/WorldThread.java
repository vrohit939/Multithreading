package com.rv.threadcreation;

public class WorldThread extends Thread {

    @Override
    public void run() {
        for (int i = 0; i < 100; i++)
            System.out.println("World");
    }

}
