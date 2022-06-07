package com.example.demo.thread.threadLocal;

import com.alibaba.ttl.TransmittableThreadLocal;
import com.alibaba.ttl.threadpool.TtlExecutors;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.lang.ref.WeakReference;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author WangYH
 * @date 2022/6/7
 */
@Slf4j
public class ThreadLocalTest {
    /**
     * ThreadLocal的初始化值是null
     * 弱引用直接构造方法中传入new的对象，该对象在方法执行完毕后被垃圾回收
     */
    @Test
    public void test1() {
        WeakReference<Object> weakReference0 = new WeakReference<>(new Object());
        log.info("weakReference0: {}", weakReference0.get());
        System.gc();
        log.info("weakReference0: {}", weakReference0.get());
    }

    /**
     * ThreadLocal的初始化值是null
     * 如果在弱引用中是传入参数的形式，那么不再是弱引用了
     */
    @Test
    public void test2() {
        Object object = new Object();
        WeakReference<Object> weakReference1 = new WeakReference<>(object);
        log.info("weakReference0: {}", weakReference1.get());
        System.gc();
        log.info("weakReference0: {}", weakReference1.get());
    }

    @Test
    public void test3() {
        Object object = new Object();
        WeakReference<Object> weakReference1 = new WeakReference<>(object);
        System.out.println(weakReference1.get());
        System.gc();
        System.out.println(weakReference1.get());
        System.gc();
        System.out.println(weakReference1.get());
    }

    @Test
    public void test4() {
        ThreadLocal<Integer> threadLocal = new ThreadLocal<>();
        threadLocal.set(6);
        System.out.println("父线程获取数据：" + threadLocal.get());
        new Thread(() -> System.out.println("子线程获取数据：" + threadLocal.get())).start();
    }

    @Test
    public void test5() {
        InheritableThreadLocal<Integer> threadLocal = new InheritableThreadLocal<>();
        threadLocal.set(6);
        System.out.println("父线程获取数据：" + threadLocal.get());
        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                System.out.println("子线程获取数据：" + threadLocal.get());
            }
        }).start();
        threadLocal.set(7);
        log.info("父线程获取数据：" + threadLocal.get());
    }

    @Test
    public void test6() {
        InheritableThreadLocal<Integer> threadLocal = new InheritableThreadLocal<>();
        threadLocal.set(6);
        System.out.println("父线程获取数据：" + threadLocal.get());
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        threadLocal.set(6);
        executorService.submit(() -> System.out.println("第一次从线程池中获取数据：" + threadLocal.get()));
        threadLocal.set(7);
        executorService.submit(() -> System.out.println("第二次从线程池中获取数据：" + threadLocal.get()));
    }

    @Test
    public void test7() {
        TransmittableThreadLocal<Integer> threadLocal = new TransmittableThreadLocal<>();
        threadLocal.set(6);
        System.out.println("父线程获取数据：" + threadLocal.get());
        ExecutorService ttlExecutorService = TtlExecutors.getTtlExecutorService(Executors.newFixedThreadPool(1));
        threadLocal.set(6);
        ttlExecutorService.submit(() -> System.out.println("第一次从线程池中获取数据：" + threadLocal.get()));
        threadLocal.set(7);
        ttlExecutorService.submit(() -> System.out.println("第二次从线程池中获取数据：" + threadLocal.get()));
    }
}
