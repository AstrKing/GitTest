package com.example.tiaoyou.mode.decoration;

/**
 * @author WangYH
 * @date 2022/6/10
 *
 * 基本装饰类，实现装修接口，然后构造方法中有转装修接口，用于调用装修实现类的通用装修方法
 */
public abstract class BaseDecorator implements IDecorator {
    private final IDecorator decorator;

    public BaseDecorator(IDecorator decorator) {
        this.decorator = decorator;
    }

    /**
     * 调用装饰方法
     */
    public void decorate() {
        if (decorator != null) {
            decorator.decorate();
        }
    }
}
