package com.example.demo.normal.hook.test.thread.test;

import java.util.Random;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 *@author WangYH
 *@date 2022/6/2
 *
 * 注意说有两个构造函数，第一个只带次数，就简单到了就行了
 * 第二个除了次数，还有一个实现Runnable接口的类，我们每次到了后会使用是这个run方法
 */
public class CyclicalBarrier {
    public static void main(String[] args) {
        CyclicBarrier barrier = new CyclicBarrier(2,()->{
            System.out.println("所有线程都到了,开始执行");
            try {
                Thread.sleep(2000);
                System.out.println("run");
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });
        Runner runner1 = new Runner(barrier, "线程1");
        Runner runner2 = new Runner(barrier, "线程2");
        Runner runner3 = new Runner(barrier, "线程3");

        ExecutorService service = Executors.newFixedThreadPool(3);
        service.execute(runner1);
        service.execute(runner2);
        service.execute(runner3);

        service.shutdown();
    }
}

class Runner implements Runnable{
    private CyclicBarrier cyclicBarrier;
    private String name;

    public Runner(CyclicBarrier cyclicBarrier, String name) {
        this.cyclicBarrier = cyclicBarrier;
        this.name = name;
    }

    @Override
    public void run() {
        try {
            Thread.sleep(new Random().nextInt(5000));
            System.out.println(name + "准备好了");
            cyclicBarrier.await();
            System.out.println(name + "开始跑了");
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } catch (BrokenBarrierException e) {
            throw new RuntimeException(e);
        }
    }
}
