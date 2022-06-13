package com.example.tiaoyou.mode.lock;

import java.util.LinkedList;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author WangYH
 * @date 2022/6/10
 *
 *
 * 这个生成者和消费者，我们首先确认了一个最大库存，同时我们使用了condition来控制生成和消费的状态
 * 但是问题还是存在的，我们这个锁是用了同一把锁，锁的范围太大了，不够颗粒化
 */
public class LockConditionTest {
    private LinkedList<String> product = new LinkedList<>();
    private int maxInventory = 10;  // 最大库存
    private Lock lock = new ReentrantLock(); // 创建锁对象，是一个重入锁，默认是非公平锁
    private Condition condition = lock.newCondition(); // 信号量，是比一般的object多了一个await和signal方法
    /**
     * 新增商品库存
     * 生成者
     * @param e
     */
    public void produce(String e) {
        // 第一步，获取锁
        lock.lock();
        try {
            // while判断，不要用if判断，因为if判断是单线程的，不是多线程的
            while (product.size() == maxInventory) {
                // 当我们的库存到达了我们所设置的最大库存，那么就需要等待，wait是直接释放锁的，而sleep不会，这个要记住
                condition.await();
            }
            // 如果说库存没有达到最大库存，那么就可以生产了
            product.add(e);
            System.out.println(" 放入一个商品库存，总库存为：" + product.size());
            // 只要说我们有生成者生产，那么我的消费者就是可以直接消费的
            condition.signalAll();
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            // 最后别忘记释放锁，这个就是lock和synchronized的区别，一个是需要手动释放，一个是自动释放
            lock.unlock();
        }
    }
    /**
     * 消费商品
     *
     * @return
     */
    public String consume() {
        String result = null;
        // 同样的，我们先获取一把锁
        lock.lock();
        try {
            // 当我们的库存为0的时候，那么就需要等待，wait是直接释放锁的，而sleep不会，这个要记住
            while (product.size() == 0) {
                condition.await();
            }
            result = product.removeLast();
            System.out.println(" 消费一个商品，总库存为：" + product.size());
            // 我先消费了库存，就可以让前面的生成者生产了
            condition.signalAll();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // 最后别忘记搞锁的释放
            lock.unlock();
        }
        return result;
    }
    /**
     * 生产者
     * @author admin
     *
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
        LockConditionTest lc = new LockConditionTest();
        new Thread(lc.new Producer()).start();
        new Thread(lc.new Customer()).start();
    }
}