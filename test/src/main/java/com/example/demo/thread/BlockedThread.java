package com.example.demo.thread;

public class BlockedThread implements Runnable{
    @Override
    public void run() {
        synchronized (BlockedThread.class){
            while (true){
                WaitingTime.waitSecond(100);
            }
        }
    }
}
