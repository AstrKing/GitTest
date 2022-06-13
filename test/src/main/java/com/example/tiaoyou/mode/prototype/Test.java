package com.example.tiaoyou.mode.prototype;

import lombok.extern.slf4j.Slf4j;

/**
 * @author WangYH
 * @date 2022/6/10
 *
 * 单单的new一个对象出来，然后通过赋值给另外一个对象，这个不是复制，是两个指向同一个对象
 *
 * 通过clone方法复制的对象才是真正的对象复制，clone方法赋值的对象完全是一个对立的对象。
 * Object类的clone方法是一个本地方法，它直接操作内存中的二进制流，特别是复制大对象时，
 * 性能的差别非常明显
 */
@Slf4j
class Student{
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
public class Test {
    public static void main(String[] args) {
        Student stu1 = new Student();
        stu1.setName("张三");

        Student stu2 = stu1;
        stu1.setName("李四");

        System.out.println(" 学生 1:" + stu1.getName());
        System.out.println(" 学生 2:" + stu2.getName());
    }
}
