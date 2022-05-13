package com.example.demo.annotation;

@DIYClassAnnotation(diyEnum = DIYEnum.xiaoJie,age=23 )
public class Student extends Person{
    @DIYFieldAnnotation(sex = "男")
    private String sex;

    @Override
    public void hobby() {
        System.out.println(DIYEnum.xiaoJie.getWorker());
    }
}
