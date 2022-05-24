package com.example.demo.thread.lab01;

/**
 * 存在当前线程中的threadLocals成员变量中
 *
 * InheritableThreadLocal类继承自ThreadLocal类，它能够让子线程访问到在父线程中设置的本地变量的值，例如，我们将
 * ThreadLocalTest类中的threadLocal静态变量改写成InheritableThreadLocal类的实例
 */
public class ThreadLocalTest02 {
    private static ThreadLocal<String> threadLocal = new InheritableThreadLocal<String>();

    public static void main(String[] args) {
        //在主线程中设置值
        threadLocal.set("ThreadLocalTest");
        //在子线程中获取值
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("子线程获取值：" + threadLocal.get());
            }
        });
        //启动子线程
        thread.start();
        //在主线程中获取值
        System.out.println("主线程获取值：" + threadLocal.get());
    }
}
