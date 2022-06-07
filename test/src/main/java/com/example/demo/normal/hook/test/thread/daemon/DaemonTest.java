package com.example.demo.normal.hook.test.thread.daemon;

import org.junit.jupiter.api.Test;

import java.util.concurrent.ThreadFactory;

/**
 * @author WangYH
 * @date 2022/6/6
 */
class DaemonThreadFactory implements ThreadFactory {
    @Override
    public Thread newThread(Runnable r) {
        Thread thread = new Thread(r);
        thread.setDaemon(true);
        return thread;
    }
}
public class DaemonTest {
    @Test
    public void test1(){

    }
}
