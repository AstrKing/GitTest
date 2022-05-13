package com.example.demo.annotation;

import org.springframework.stereotype.Component;

import java.lang.annotation.*;

// 注解到什么地方  方法 上
@Target({ElementType.METHOD})
//  该注解的生命周期
@Retention(RetentionPolicy.RUNTIME)
// 指示默认情况下，带有类型的注释将由javadoc *和类似工具来记录
@Documented
// 可以继承父类注解
@Inherited
// bean
@Component
public @interface DIYMethodAnnotation {
    // 是否校验
    int verification();

    // 接口名称
    String interfaceName();
}
