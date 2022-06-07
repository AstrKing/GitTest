package com.example.demo.thread;

public class ThreadSort01 {
    public static void main(String[] args) {
        Thread thread1 = new Thread(() -> System.out.println("thread1"));
        Thread thread2 = new Thread(() -> System.out.println("thread2"));
        Thread thread3 = new Thread(() -> System.out.println("thread3"));
        thread1.start();
        thread2.start();
        thread3.start();
    }
}
