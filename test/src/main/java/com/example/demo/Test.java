package com.example.demo;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Test {
    public static void main(String[] args) {
        String test = "a,b,c,,,";
        System.out.println(test.split(",").length);
        System.out.println(test.split(",", -1).length);
    }
}