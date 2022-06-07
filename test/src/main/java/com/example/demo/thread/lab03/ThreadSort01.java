package com.example.demo.thread.lab03;

public class ThreadSort01 {
    public static void main(String[] args) throws InterruptedException {
        Thread thread1 = new Thread(() -> System.out.println("thread1"));
        Thread thread2 = new Thread(() -> System.out.println("thread2"));
        Thread thread3 = new Thread(() -> System.out.println("thread3"));
        thread1.start();
        thread1.join();
        thread2.start();
        thread2.join();
        thread3.start();
        thread3.join();
    }
}
