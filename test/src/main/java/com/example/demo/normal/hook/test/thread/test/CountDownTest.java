package com.example.demo.normal.hook.test.thread.test;

import lombok.extern.slf4j.Slf4j;

import java.util.Date;
import java.util.concurrent.CountDownLatch;

/**
 * @author WangYH
 * @date 2022/6/2
 */
@Slf4j
public class CountDownTest {
    public static void main(String[] args) throws InterruptedException {
        CountDownLatch countDownLatch = new CountDownLatch(2);
        Worker worker1 = new Worker("a", 2000, countDownLatch);
        Worker worker2 = new Worker("b", 3000, countDownLatch);
        worker1.start();
        worker2.start();
        countDownLatch.await();
    }

    static class Worker extends Thread {
        String name;
        int workTime;
        CountDownLatch countDownLatch;

        public Worker(String name, int workTime, CountDownLatch countDownLatch) {
            this.name = name;
            this.workTime = workTime;
            this.countDownLatch = countDownLatch;
        }

        @Override
        public void run() {
            super.run();
            work();
            Date date = new Date();
            log.info("{} 开始工作，时间：{}", name, date);
            countDownLatch.countDown();
        }

        private void work() {
            try {
                Thread.sleep(workTime);
            } catch (InterruptedException e) {
                log.error("线程：{} 工作失败", name);
                e.printStackTrace();
            }
        }
    }
}
