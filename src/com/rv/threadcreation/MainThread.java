package com.rv.threadcreation;

public class MainThread {

    public static void main(String[] args) {
        WorldThread worldThread = new WorldThread(); // NEW State

        worldThread.start(); // RUNNABLE State // When CPU allots time then it comes to RUNNING State

        for (int i = 0; i < 100; i++)
            System.out.println("Hello");
    }

}
