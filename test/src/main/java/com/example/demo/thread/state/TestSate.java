package com.example.demo.thread.state;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class TestSate {
    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(()->{
            for (int i = 0; i < 5; i++) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        });

        log.info("{}"+thread.getState());

        thread.start();
        log.info("{}"+thread.getState());

        while(thread.getState()!=Thread.State.TERMINATED){
            Thread.sleep(100);
            log.info("{}"+thread.getState());
        }
    }
}
