package com.example.demo.annotation;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {
    // 此方法需要校验
    @DIYMethodAnnotation(verification = 1,interfaceName = "学生爱好接口")
    @RequestMapping("/verification")
    public   String   verificationMethod(String id){
        new Student().hobby();
        return "校验";
    }

    // 此方法不需要校验
    @DIYMethodAnnotation(verification = 0,interfaceName = "老师爱好接口")
    @RequestMapping("/noVerification")
    public   String   noVerificationMethod(String id){
        new Teacher().hobby();

        return "不校验";
    }

    // 此方法没有注解
    @RequestMapping("/noAnnotation")
    public   String   noAnnotationMethod(String id){
        new Chef().hobby();

        return "无注解";
    }
}
