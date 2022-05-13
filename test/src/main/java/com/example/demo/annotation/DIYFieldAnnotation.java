package com.example.demo.annotation;

import org.springframework.stereotype.Component;

import java.lang.annotation.*;

// 注解到什么地方  属性 上
@Target({ElementType.FIELD})
//  该注解的生命周期
@Retention(RetentionPolicy.RUNTIME)
// 指示默认情况下，带有类型的注释将由javadoc *和类似工具来记录
@Documented
// 可以继承父类注解
@Inherited
// bean
@Component
public @interface DIYFieldAnnotation {
    // 性别
    String sex();
}
