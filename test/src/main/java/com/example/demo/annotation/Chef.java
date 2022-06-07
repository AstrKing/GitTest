package com.example.demo.annotation;

@DIYClassAnnotation(diyEnum = DIYEnum.CHEF, age = 50)
public class Chef extends Person {
    @DIYFieldAnnotation(sex = "男")
    private String sex;

    public Chef() {
    }

    @Override
    public void hobby() {

    }
}
