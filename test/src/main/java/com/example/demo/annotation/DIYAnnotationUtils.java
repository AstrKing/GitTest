package com.example.demo.annotation;

import java.lang.reflect.Field;

public class DIYAnnotationUtils {
    public static Person getPerson(Person ...persons){
        for (Person person:persons) {
            // 判断这个类是否有这个注解
            if (person.getClass().isAnnotationPresent(DIYClassAnnotation.class)){
                // 得到这个自定义的注解
                DIYClassAnnotation workerAnnotation = person.getClass().getAnnotation(DIYClassAnnotation.class);
                // 判断这个自定义注解注解的值是否是我们想要的
                if (DIYEnum.xiaoJie.getName().equals(workerAnnotation.diyEnum().getName())){
                    // 反射得到这个对象的属性
                    Field[] fields = person.getClass().getDeclaredFields();
                    for (Field field:fields) {
                        // 如果这个字段有这个注解
                        if (field.isAnnotationPresent(DIYFieldAnnotation.class)){
                            // 打印出这个属性上有这个注解的值
                            DIYFieldAnnotation annotation = field.getAnnotation(DIYFieldAnnotation.class);
                            System.out.println(annotation.sex());
                        }
                    }
                    return person;
                }
            }
        }
        return null;
    }
}
