package com.example.demo.java8;

/**
 * @author WangYH
 * @date 2022/6/6
 */
public class java8DefaultInterfaceTest {
}

interface Vehicle {
    default void print(){
        System.out.println("我是一辆车!");
    }

    static void blowHorn(){
        System.out.println("按喇叭!!!");
    }
}

interface FourWheeler {
    default void print(){
        System.out.println("我是一辆四轮车!");
    }
}

class Che implements Vehicle, FourWheeler {
    @Override
    public void print() {
        Vehicle.super.print();
        System.out.println("我是一辆车!");
    }
}
