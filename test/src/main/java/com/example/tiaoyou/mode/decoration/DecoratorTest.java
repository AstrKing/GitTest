package com.example.tiaoyou.mode.decoration;

/**
 * @author WangYH
 * @date 2022/6/10
 *
 * 装饰器模式
 * 1、装修接口
 * 2、装修实现类
 * 3、装饰类，这个要继承装修接口
 * 4、装饰实现类
 *
 * 基于装饰器模式实现的装修功能的代码结构简洁易读，逻辑清晰
 * 如果我们需要扩展新的装修功能，只需要继承装饰类，并实现装饰接口，即可
 */
public class DecoratorTest {
    public static void main(String[] args) {
        IDecorator decorator = new CurtainDecorator(new Decorator());
        decorator.decorate();
    }
}
