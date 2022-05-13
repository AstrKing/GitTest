package com.example.demo.annotation;

@DIYClassAnnotation(diyEnum = DIYEnum.TEACHER, age = 46)
public class Teacher extends Person {
    @DIYFieldAnnotation(sex = "女")
    private String sex;

    @Override
    public void hobby() {
        System.out.println(DIYEnum.TEACHER.getWorker());
    }
}
