package com.example.demo.thread.lab01;

public class ThreadTest {
    private long count = 0;

    private void addCount() {
        count++;
    }

    public long execute() throws InterruptedException {
        Thread threadA = new Thread(() -> {
            for (int i = 0; i < 1000; i++) {
                addCount();
            }
        });
        Thread threadB = new Thread(() -> {
            for (int i = 0; i < 1000; i++) {
                addCount();
            }
        });
        threadA.start();
        threadB.start();
        threadA.join();
        threadB.join();
        return count;
    }

    public static void main(String[] args) throws InterruptedException {
        ThreadTest threadTest = new ThreadTest();
        long count = threadTest.execute();
        System.out.println(count);
    }
}
