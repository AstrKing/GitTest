package com.example.demo.java8;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;

/**
 * @author WangYH
 * @date 2022/6/6
 * <p>
 * 常用的4个函数式接口
 */
@Slf4j
public class FourFunctionalInterfaceTest {
    /***
     * Function 函数型接口, 有一个输入参数，有一个输出参数
     * 第一个参传参数，第二个参数返回参数
     * 只要是 函数型接口 可以 用 lambda表达式简化
     */
    @Test
    public void test1() {
        //Function<String, String> function = new
        //        Function<String, String>() {
        //            @Override
        //            public String apply(String str) {
        //                return str;
        //            }
        //        };
        // lambda 表达式简化：
        Function<String, Integer> function = Integer::valueOf;
        log.info("{}", function.apply(String.valueOf(1)));
    }

    /**
     * Predicate 函数型接口, 有一个输入参数，有一个返回值
     * 断定型接口：有一个输入参数，返回值只能是 布尔值！
     */
    @Test
    public void test2() {
        // 判断字符串是否为空
        /*Predicate<String> predicate = new Predicate<String>(){
            @Override
            public boolean test(String str) {
                return str.isEmpty();//true或false
            }
        };*/
        Predicate<String> predicate = String::isEmpty;
        log.info("{}", predicate.test(""));
    }

    /**
     * Consumer 消费型接口
     * 有一个输入参数，没有返回值
     */
    @Test
    public void test3() {
        /*Consumer<String> consumer = new Consumer<String>() {
            @Override
            public void accept(String str) {
                System.out.println(str);
            }
        };*/
        Consumer<String> consumer = System.out::println;
        consumer.accept("hello");
    }

    /**
     * Supplier 供给型接口 没有参数，只有返回值
     */
    @Test
    public void test4(){
        /*Supplier supplier = new Supplier<Integer>() {
            @Override
            public Integer get() {
                System.out.println("get()");
                return 1024;
            }
        };*/

        Supplier supplier = ()-> 1024;
        System.out.println(supplier.get());
    }
}
