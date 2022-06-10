package com.example.tiaoyou.string;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

/**
 * @author WangYH
 * @date 2022/6/8
 */
@Slf4j
public class Test1 {
    /**
     * 记住 intern首先会在常量池中找，有的话，就直接引用，
     * 没有的话，会在常量池中添加，然后引用
     *
     * final修饰的话，直接成变量了
     */
    @Test
    public void test1() {
        String s1 = "abc";
        String s2 = new String("abc");
        String s4 = new String("abc");
        String s3 = s2.intern();

        log.info("{}", s1 == s2); // false
        log.info("{}", s2 == s3); // false
        log.info("{}", s1 == s3); // true
        log.info("{}", s2 == s4); // false
    }

    @Test
    public void test2() {
        String s1 = "abc";
        String s2 = "abc";
        System.out.println(s1 == s2); // true， 二者都在字符串常量池中
    }

    @Test
    public void test3() {
        final String s1 = new String("abc");
        final String s2 = new String("abc");
        System.out.println(s1 == s2); // false， 两个引用堆中的不同的对象
    }

    @Test
    public void test4() {
        String s1 = "abc";
        String s2 = "a";
        String s3 = "bc";

        String s4 = s2 + s3;

        System.out.println(s1 == s4); // false 1,2,3都是在常量池中,但是你直接加，调用的是SB的append，里面就是new了
    }

    @Test
    public void test5() {
        String s1 = "abc";
        final String s2 = "a";
        final String s3 = "bc";
        String s4 = s2 + s3;
        System.out.println(s4 == s1); // true final的话直接替换成对应的值了
    }

    @Test
    public void test6() {
        String s = new String("abc");
        String s1 = "abc";
        String s2 = new String("abc");

        System.out.println(s == s1.intern()); // false
        System.out.println(s == s2.intern()); // false
        System.out.println(s1 == s2.intern()); // true
    }
}