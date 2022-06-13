package com.example.tiaoyou.mode.prototype;

/**
 * @author WangYH
 * @date 2022/6/10
 *
 * 原型模式：
 * 原型模式是通过给出一个原型对象来指明所创建的对象的类型，
 * 然后使用自身实现的克隆接口来复制这个原型对象，该模式就是用这种方式来创建出更多同类型的对象
 *
 * 原型模式的优点是：
 * 无需再通过new实例化来创建对象了，所以性能相对new实例化来说，更佳
 *
 * 实现一个原型类，需要具备三个条件：
 * 1、实现cloneable接口：
 * 2、重写object类中的clone方法
 * 3、在重写clone方法中，调用super.clone()
 */
class Prototype implements Cloneable{
    @Override
    // 重写clone方法
    protected Object clone(){
        Prototype prototype = null;
        try {
            prototype = (Prototype)super.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return prototype;
    }
}

class ConcretePrototype extends Prototype{
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void show(){
        System.out.println(name+"【原型模式实现类】");
    }
}

public class Client {
    public static void main(String[] args) {
        ConcretePrototype concretePrototype = new ConcretePrototype();
        for (int i = 0; i < 10; i++) {
            ConcretePrototype cloneCp = (ConcretePrototype) concretePrototype.clone();
            cloneCp.setName("【原型模式实现类】"+i);
            cloneCp.show();
            System.out.println(cloneCp.hashCode());
        }
    }
}
