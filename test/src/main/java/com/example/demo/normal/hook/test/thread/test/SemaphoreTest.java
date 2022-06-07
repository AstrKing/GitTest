package com.example.demo.normal.hook.test.thread.test;

import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/**
 * @author WangYH
 * @date 2022/6/2
 */
public class SemaphoreTest {
    private static final int MAX_SIZE = 20;

    public static void main(String[] args) {
        ExecutorService service = Executors.newFixedThreadPool(MAX_SIZE);
        Semaphore semaphore = new Semaphore(5);
        Random random = new Random();
        for (int i = 0; i < MAX_SIZE; i++) {
            final int no = i;
            service.execute(() -> {
                try {
                    semaphore.acquire();
                    System.out.println(Thread.currentThread().getName() + "正在消费，当前消费号码：" + no);
                    Thread.sleep(random.nextInt(2000));
                    semaphore.release();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        }
    }
}
