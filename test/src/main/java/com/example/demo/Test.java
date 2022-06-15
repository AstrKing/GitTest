package com.example.demo;

import lombok.extern.slf4j.Slf4j;
import org.openjdk.jol.info.ClassLayout;

import java.util.ArrayList;

/**
 * 64位系统
 * markWord 8个字节  这个里面存的还有一个标志位，我们的偏向锁，就是跟着走的
 * classPointer类型 压缩了，4个字节
 *
 * 所以，你就算里面什么都没有，那也是12个字节，然后补齐，补齐至8的倍数，那就是16个字节
 *
 * 加一个int，是12+4=16，不用补齐，就是8的倍数
 * 加两个int，是12+4+4=20，那再补齐至8的倍数，就是24个字节
 *
 * 如果是一个数组,那么咱们存储的是数组的长度，那就是一个int，是4个字节
 *
 *
 */
@Slf4j
public class Test {
    public static void main(String[] args) {
        //查看对象内部信息
        System.out.println(ClassLayout.parseInstance(new Test()).toPrintable());
    }
}