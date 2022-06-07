package com.example.demo.thread;

public class BlockedThread implements Runnable{
    @Override
    @SuppressWarnings("InfiniteLoopStatement")
    public void run() {
        synchronized (BlockedThread.class){
            while (true){
                WaitingTime.waitSecond(100);
            }
        }
    }
}
