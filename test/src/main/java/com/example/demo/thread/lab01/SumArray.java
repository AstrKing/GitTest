package com.example.demo.thread.lab01;

import java.util.Arrays;
import java.util.List;

public class SumArray {
    public static void main(String[] args){
        List<Integer> numberList = Arrays.asList(1,2,3,4,5,6,7,8,9);
        numberList.stream().forEach(System.out::println);
    }
}
