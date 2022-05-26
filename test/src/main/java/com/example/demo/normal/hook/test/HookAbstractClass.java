package com.example.demo.normal.hook.test;

/**
 * 验证钩子函数的基类
 */
public abstract class HookAbstractClass {
    //模板方法
    public void TemplateMethod() {
        if (HookMethod1()) {
            SpecificMethod1();
        } else {
            SpecificMethod2();
        }
    }

    //具体方法
    public void SpecificMethod1() {
        System.out.println("抽象类中的具体方法1被调用...");
    }

    //具体方法
    public void SpecificMethod2() {
        System.out.println("抽象类中的具体方法2被调用...");
    }

    //钩子方法1
    public abstract boolean HookMethod1();
}
