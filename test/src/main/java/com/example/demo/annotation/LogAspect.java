package com.example.demo.annotation;

import com.alibaba.fastjson.JSON;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

@Component
@Aspect
public class LogAspect {
    // 注解的位置
    @Pointcut("@annotation(com.example.demo.annotation.DIYMethodAnnotation)")
    public void diyPointCut(){};

    @Around("diyPointCut()")
    public Object diyAround(ProceedingJoinPoint joinPoint){
        //获得被增强的方法相关信息
        MethodSignature signature = (MethodSignature)joinPoint.getSignature();
        // 获得这个方法
        Method method = signature.getMethod();
        // 获得这个方法上面的注解
        DIYMethodAnnotation diyMethodAnnotation = method.getAnnotation(DIYMethodAnnotation.class);
        // 根据注解自定义的一些属性去做自定义的操作
        if (diyMethodAnnotation.verification() == 1){
            System.out.println("当前校验的是："+diyMethodAnnotation.interfaceName());
            System.out.println("方法名称是："+method.getName());
            System.out.println("传递参数是："+ JSON.toJSONString(joinPoint.getArgs()));
        }

        System.out.println("aop 拦截器里 verification："+diyMethodAnnotation.verification() );


        try {
            return joinPoint.proceed();
        } catch (Throwable throwable) {
            throwable.printStackTrace();
            return null;
        }
    }
}
