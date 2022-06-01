package com.example.demo.thread.test;

public class TestLambda {
    //2.静态内部类
    static class Like2 implements Alike {
        @Override
        public void make() {
            System.out.println("make2");
        }
    }

    public static void main(String[] args) {
        // 1、新建
        Alike like = new Like();
        like.make();

        Alike like2 = new Like2();
        like2.make();
        // 局部内部类
        class Like3 implements Alike {
            @Override
            public void make() {
                System.out.println("make3");
            }
        }

        Alike alike3 = new Like3();
        alike3.make();

        Alike alike4 = new Alike() {
            @Override
            public void make() {
                System.out.println("alike4");
            }
        };
        alike4.make();

        Alike alike5 = () -> {
            System.out.println("alike5");
        };
        alike5.make();
    }
}

interface Alike {
    void make();
}

// 最麻烦的，1，写新的实现类
class Like implements Alike {
    @Override
    public void make() {
        System.out.println("make1");
    }
}