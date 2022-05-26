package com.example.demo.thread;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * public ThreadPoolExecutor(int corePoolSize,
 * int maximumPoolSize,
 * long keepAliveTime,
 * TimeUnit unit,
 * BlockingQueue<Runnable> workQueue,
 * RejectedExecutionHandler handler)
 * <p>
 * <p>
 * corePoolSize：核心线程数
 * maximumPoolSize：最大线程数
 * keepAliveTime：线程最大空闲时间
 * unit：时间单位
 * workQueue：等待队列
 * handler：拒绝策略
 * <p>
 * 当一个新任务被提交到线程池中的时候，如果当前线程小于核心线程corePoolSize，直接新增线程
 * 大于corePoolSize的时候，往那个队列里面加，当队列也满了到时候，就再次新增线程直到maximumPoolSize，所以同时最大能处理 max+队列长度
 * <p>
 * 等待队列也分为三种：
 * 1、直接传递 SynchronousQueue 通过 SynchronousQueue 直接把任务传递给线程。如果当前没可用线程，尝试入队操作会失败，
 * 然后再创建一个新的线程。当处理可能具有内部依赖性的请求时，该策略会避免请求被锁定。直接传递通常需要无界的最大线程数（maximumPoolSize），
 * 避免拒绝新提交的任务。当任务持续到达的平均速度超过可处理的速度时，可能导致线程的无限增长
 * <p>
 * 2、无界队列 LinkedBlockingQueue 使用无界队列（如 LinkedBlockingQueue）作为等待队列，当所有的核心线程都在处理任务时，
 * 新提交的任务都会进入队列等待。因此，不会有大于 corePoolSize 的线程会被创建（maximumPoolSize 也将失去作用）。
 * 这种策略适合每个任务都完全独立于其他任务的情况；例如网站服务器。这种类型的等待队列可以使瞬间爆发的高频请求变得平滑。
 * 当任务持续到达的平均速度超过可处理速度时，可能导致等待队列无限增长。
 * <p>
 * 3、有界队列 ArrayBlockingQueue 当使用有限的最大线程数时，有界队列（如 ArrayBlockingQueue）可以防止资源耗尽，
 * 但是难以调整和控制。队列大小和线程池大小可以相互作用：使用大的队列和小的线程数可以减少CPU使用率、系统资源和上下文切换的开销，
 * 但是会导致吞吐量变低，如果任务频繁地阻塞（例如被I/O限制），系统就能为更多的线程调度执行时间。使用小的队列通常需要更多的线程数，
 * 这样可以最大化CPU使用率，但可能会需要更大的调度开销，从而降低吞吐量。
 * <p>
 * 拒绝策略分为四种：
 * 1、AbortPolicy：默认策略，在需要拒绝任务时抛出RejectedExecutionException；【起码有异常，我们知道哪个被丢弃了】
 * 2、CallerRunsPolicy：直接在 execute 方法的调用线程中运行被拒绝的任务，如果线程池已经关闭，任务将被丢弃；【当前main线程来执行】
 * 3、DiscardPolicy：直接丢弃任务；【没有异常，也没有任何的提示】
 * 4、DiscardOldestPolicy：丢弃队列中等待时间最长的任务，并执行当前提交的任务，如果线程池已经关闭，任务将被丢弃。【丢弃掉队列中最老的那个，也就是等待时间最长的那个】
 */
public class ThreadPoolSerialTest {
    public static void main(String[] args) {
        int corePoolSize = 3;
        int maxMumPoolSize = 6;
        long keepAliveTime = 2;
        TimeUnit unit = TimeUnit.SECONDS;
        ArrayBlockingQueue<Runnable> workQueue = new ArrayBlockingQueue<Runnable>(2);
        ThreadPoolExecutor threadPoolExecutor = null;
        try {
            //创建线程池
            threadPoolExecutor = new ThreadPoolExecutor(corePoolSize,
                    maxMumPoolSize,
                    keepAliveTime,
                    unit,
                    workQueue,
                    new ThreadPoolExecutor.DiscardOldestPolicy());

            //循环提交任务
            for (int i = 0; i < 9; i++) {
                //提交任务的索引
                final int index = (i + 1);
                threadPoolExecutor.submit(() -> {
                    //线程打印输出
                    System.out.println("大家好,我是线程：" + index + "   " + Thread.currentThread().getName());
                    try {
                        //模拟线程执行时间，10s
                        Thread.sleep(10000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                });
                //每个任务提交后休眠500ms再提交下一个任务，用于保证提交顺序
                Thread.sleep(500);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            threadPoolExecutor.shutdown();
        }
    }
}
