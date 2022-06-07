package com.example.demo.java8;

/**
 * @author WangYH
 * @date 2022/6/6
 */
class Java8TesterTest {
    /**
     * 无参数有，返回值的方法
     */
    interface MathOperation {
        int operation(int a, int b);
    }

    /**
     * 有参数，无返回值的方法
     */
    interface GreetingService {
        void sayMessage(String message);
    }

    /**
     * 有参数，有返回值的方法
     */
    private int operate(int a, int b, MathOperation mathOperation){
        return mathOperation.operation(a, b);
    }

    public static void main(String[] args) {
        Java8LambdaTester tester = new Java8LambdaTester();
        // 类型声明
        // 返回值
        Java8LambdaTester.MathOperation addition= Integer::sum;
        GreetingService greetingService = System.out::println;
        greetingService.sayMessage("Hello World!");

        int result = tester.operate(1, 2, addition);
    }
}