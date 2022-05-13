package com.example.demo.annotation;

@DIYClassAnnotation(diyEnum = DIYEnum.CHEF, age = 50)
public class Chef extends Person {
    @DIYFieldAnnotation(sex = "男")
    private String sex;

    @Override
    public void hobby() {

    }
}
