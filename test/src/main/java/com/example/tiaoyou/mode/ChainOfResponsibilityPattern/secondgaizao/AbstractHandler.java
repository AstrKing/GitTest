package com.example.tiaoyou.mode.ChainOfResponsibilityPattern.secondgaizao;

/**
 * @author WangYH
 * @date 2022/6/13
 *
 * 责任了模式，搞个抽象类，定义一下我们的固定方法这样
 * 后续每个实现抽象类接口的类都要实现这个方法
 */
public abstract class AbstractHandler {
    /**
     * 下一关用当前抽象类来接收
     */
    protected AbstractHandler next;

    public void setNext(AbstractHandler next) {
        this.next = next;
    }

    public abstract int handler();
}
