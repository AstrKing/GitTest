package com.example.demo.thread;

@SuppressWarnings("InfiniteLoopStatement")
public class WaitingState implements Runnable{
    @Override
    public void run() {
        while (true){
            synchronized (WaitingState.class){
                try {
                    WaitingState.class.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
