package com.example.demo.annotation;

import org.springframework.stereotype.Component;

import java.lang.annotation.*;

// 注解可以作用在哪里
@Target({ElementType.TYPE})
//  该注解的生命周期
@Retention(RetentionPolicy.RUNTIME)
// 指示默认情况下，带有类型的注释将由javadoc *和类似工具来记录
@Documented
// 可以继承父类注解
@Inherited
// bean
@Component
public @interface DIYClassAnnotation {
    // 自定义枚举类型
    DIYEnum diyEnum();
    // 年龄默认24 岁
    int age() default 24;
}
