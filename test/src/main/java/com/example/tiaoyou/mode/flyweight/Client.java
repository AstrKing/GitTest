package com.example.tiaoyou.mode.flyweight;

/**
 * @author WangYH
 * @date 2022/6/10
 *
 * 享元模式是运用共享技术有效地最大限度地复用细粒度对象的一种模式
 *
 * 原型是省的多new，享元模式是复用
 * 具体分为内部对象和外部对象，内部数据是可共享出来的信息，外部数据则是在不同运行时被标记了不同的值
 * 享元模式一般分为三个角色：
 * 1、FlyWeight：享元接口，定义了一个接口，用于访问和共享ConcreteFlyweight对象
 * 2、ConcreteFlyweight：具体享元类，实现了FlyWeight接口，实现了共享机制
 * 3、FlyweightFactory：享元工厂类，负责创建和管理ConcreteFlyweight对象
 *
 * 如果对象已经存在于享元池中，则直接从享元池中获取，否则创建一个新的对象，而是共用享元池内部数据一致的对象
 * 这样就减少了对象的创建，同时也节省了同样内部数据的对象所占用的内存空间
 *
 * 【适用场景】
 * Java的String字符串，在一些字符串常量中，会共享常量池中字符串对象，从而减少重复创建相同值对象，占用内存空间
 */
public class Client {
    public static void main(String[] args) {
        FlyWeight fw0 = FlyweightFactory.getFlyweight("a");
        FlyWeight fw1 = FlyweightFactory.getFlyweight("b");
        FlyWeight fw2 = FlyweightFactory.getFlyweight("a");
        FlyWeight fw3 = FlyweightFactory.getFlyweight("b");
        fw1.operation("abc");
        System.out.printf("[结果 (对象对比)] - [%s]\n", fw0 == fw2);
        System.out.printf("[结果 (内在状态)] - [%s]\n", fw1.getType());
    }
}
