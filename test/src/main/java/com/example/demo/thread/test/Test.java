package com.example.demo.thread.test;

public class Test {
    public static void main(String[] args) {
        Aobing aobing = new Aobing();
        aobing.start();
    }
}

class Aobing extends Thread{
    private boolean flag = false;

    public boolean isFlag(){
        return flag;
    }
    @Override
    public void run() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        flag = true;
    }
}
