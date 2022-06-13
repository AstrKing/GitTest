package com.example.tiaoyou.mode.lock;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * @author WangYH
 * @date 2022/6/10
 * 我们再面试的时候，写生产者和消费者的时候，就用这个写，它内部是安全的，
 * 还用到了condition，比一般的wait、notify、notifyAll更加灵活
 * 我们无需关注它内部的队列是否被消费至0了，它自己内部的condition会自动维护
 */
public class BlockingQueueTest {
    private final int maxInventory = 10; // 最大库存
    private final BlockingQueue<String> product = new LinkedBlockingQueue<>(maxInventory);

    /**
     * 新增商品库存
     */
    public void produce(String e) {
        try {
            product.put(e);
            System.out.println(" 放入一个商品库存，总库存为：" + product.size());
        } catch (InterruptedException e1) {
            e1.printStackTrace();
        }
    }

    /**
     * 消费商品
     */
    public void consume() {
        try {
            product.take();
            System.out.println(" 消费一个商品，总库存为：" + product.size());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * 生产者
     * @author admin
     *
     */
    private class Producer implements Runnable{
        @Override
        public void run() {
            for (int i = 0; i < 20; i++) {
                produce(" 商品 " + i);
            }
        }
    }

    /**
     * 消费者
     * @author admin
     *
     */
    private class Customer implements Runnable {
        public void run() {
            for (int i = 0; i < 20; i++) {
                consume();
            }
        }
    }

    public static void main(String[] args) {
        BlockingQueueTest lc = new BlockingQueueTest();
        new Thread(lc.new Producer()).start();
        new Thread(lc.new Customer()).start();
    }
}
