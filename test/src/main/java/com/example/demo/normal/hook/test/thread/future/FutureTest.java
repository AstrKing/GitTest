package com.example.demo.normal.hook.test.thread.future;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;

/**
 * @author WangYH
 * @date 2022/6/6
 *
 * Callable和Runnable的区别：
 * Runnable run方法是被线程调用的，在run方法是异步执行的
 * Callable的call方法，不是异步执行的，是由Future的run方法调用的
 */
@Slf4j
public class FutureTest {
    /*public static void main(String[] args) throws ExecutionException, InterruptedException {
        Callable<Integer> callable = () -> {
            Thread.sleep(3000);
            return 123;
        };
        FutureTask<Integer> futureTask = new FutureTask<>(callable);
        new Thread(futureTask).start();
        // do something
        log.info("do something");
        Integer result = futureTask.get();
        log.info("result: {}", result);
    }*/

    @Test
    public void test1(){
        Callable<Integer> callable = ()->{
            Thread.sleep(4000);
            return 123;
        };

        FutureTask futureTask = new FutureTask(callable);
        new Thread(futureTask).start();
    }
}
