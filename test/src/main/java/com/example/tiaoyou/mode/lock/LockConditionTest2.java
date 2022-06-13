package com.example.tiaoyou.mode.lock;

import java.util.LinkedList;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author WangYH
 * @date 2022/6/10
 *
 * 【好处】
 * 我们分别创建了两个线程，一个生产者，一个消费者，
 * 同时两个锁
 */
public class LockConditionTest2 {
    private LinkedList<String> product = new LinkedList<>();
    private AtomicInteger inventory = new AtomicInteger(0);// 实时库存
    private int maxInventory = 10; // 最大库存
    private Lock consumerLock = new ReentrantLock();// 消费锁
    private Lock productLock = new ReentrantLock();// 生成锁
    private Condition notEmptyCondition = consumerLock.newCondition(); // 消费状态
    private Condition notFullCondition = productLock.newCondition(); // 生产状态

    /**
     * 新增商品库存
     *
     * @param e
     */
    public void produce(String e) {
        productLock.lock();
        try {
            while (inventory.get() == maxInventory) {
                notFullCondition.await();
            }
            product.add(e);
            inventory.incrementAndGet();
            System.out.println(" 放入一个商品库存，总库存为：" + inventory.get());

            if (inventory.get() < maxInventory) {
                notFullCondition.signalAll();
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            productLock.unlock();
        }

        if (inventory.get() > 0) {
            try {
                consumerLock.lockInterruptibly();
                notEmptyCondition.signalAll();
            } catch (InterruptedException ex) {
                throw new RuntimeException(ex);
            } finally {
                consumerLock.unlock();
            }
        }
    }

    /**
     * 消费商品
     *
     * @return
     */
    public String consume() {
        String result = null;
        consumerLock.lock();
        try {
            while (inventory.get() == 0) {
                notEmptyCondition.await();
            }
            result = product.removeLast();
            System.out.println(" 消费一个商品，总库存为：" + inventory.decrementAndGet());
            if (inventory.get() > 0) {
                notEmptyCondition.signalAll();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            consumerLock.unlock();
        }

        if (inventory.get() < maxInventory) {
            try {
                productLock.lockInterruptibly();
                notFullCondition.signalAll();
            } catch (InterruptedException e1) {
                e1.printStackTrace();
            } finally {
                productLock.unlock();
            }
        }
        return result;
    }

    /**
     * 生产者
     *
     * @author admin
     */
    private class Producer implements Runnable {
        public void run() {
            for (int i = 0; i < 20; i++) {
                produce(" 商品 " + i);
            }
        }
    }

    /**
     * 消费者
     *
     * @author admin
     */
    private class Customer implements Runnable {
        public void run() {
            for (int i = 0; i < 20; i++) {
                consume();
            }
        }
    }

    public static void main(String[] args) {
        LockConditionTest2 lc = new LockConditionTest2();
        new Thread(lc.new Producer()).start();
        new Thread(lc.new Customer()).start();
    }
}