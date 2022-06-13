package com.example.tiaoyou.mode.decoration;

/**
 * @author WangYH
 * @date 2022/6/10
 *
 * 装修基本类，也是我们通用的一些功能
 */
public class Decorator implements IDecorator {
    @Override
    public void decorate() {
        System.out.println(" 水电装修、天花板以及粉刷墙。。。");
    }
}
