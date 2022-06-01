package com.example.demo.thread.state;

/**
 * @author WangYH
 * @date 2022/6/1
 */
public class Test {
    public static void main(String[] args) {
        Thread t = new Thread(() -> {
            System.out.println("thread start");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("thread end");
        });
        t.start();
        System.out.println("main end");
    }
}
