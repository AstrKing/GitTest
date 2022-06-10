package com.example.tiaoyou.thread;

import lombok.extern.slf4j.Slf4j;

import java.util.Vector;

/**
 * @author WangYH
 * @date 2022/6/9
 */
@Slf4j
public class WaitNotifyTest {
    public static void main(String[] args) {
        Vector<Integer> pool = new Vector<>();
        Producer producer = new Producer(pool, 10);
        Consumer consumer = new Consumer(pool);

        new Thread(producer, "producer").start();
        new Thread(consumer, "consumer").start();
    }
}

@Slf4j
class Producer implements Runnable {
    private Vector<Integer> pool;
    private Integer size;
    public Producer(Vector<Integer> pool, Integer size) {
        this.pool = pool;
        this.size = size;
    }

    @Override
    public void run() {
        for (;;) {
            try {
                log.info("生产者开始生产");
                produce(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private void produce(int i) throws InterruptedException {
        while (pool.size() == size) {
            synchronized (pool) {
                log.info("生产者等待消费者消费商品，当前商品数量为{}", pool.size());
                pool.wait();
            }
        }
        synchronized (pool) {
            pool.add(i);
            pool.notify();
        }
    }
}

@Slf4j
class Consumer implements Runnable {
    private Vector<Integer> pool;
    public Consumer(Vector<Integer> pool) {
        this.pool = pool;
    }
    @Override
    public void run() {
        for (;;) {
            log.info("消费者开始消费");
            try {
                consume();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    private void consume() throws InterruptedException {
        while (pool.isEmpty()) {
            synchronized (pool) {
                log.info("消费者等待生产者生产商品，当前商品数量为{}", pool.size());
                pool.wait();
            }
        }
        synchronized (pool) {
            pool.remove(0);
            log.info("消费者消费商品，当前商品数量为{}", pool.size());
            pool.notify();
        }
    }
}
