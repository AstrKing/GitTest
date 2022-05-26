package com.example.demo.normal.hook.test;

public class AchieveClass extends HookAbstractClass{
    @Override
    public boolean HookMethod1() {
        return false;
    }

    public static void main(String[] args) {
        HookAbstractClass hookAbstractClass = new AchieveClass();
        hookAbstractClass.TemplateMethod();
    }
}
