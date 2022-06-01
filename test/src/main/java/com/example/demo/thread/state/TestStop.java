package com.example.demo.thread.state;

public class TestStop implements Runnable{
    private boolean flag = true;
    @Override
    public void run() {
        int i = 0;
        while (flag){
            System.out.println("run---thread" + i++);
        }
    }

    public void myStop(){
        this.flag = false;
    }

    public static void main(String[] args) {
        TestStop thread = new TestStop();
        new Thread(thread).start();
        for (int j = 0; j < 1000; j++) {
            System.out.println("mian" + j);
            if(j==900){
                thread.myStop();
            }
        }
    }
}
