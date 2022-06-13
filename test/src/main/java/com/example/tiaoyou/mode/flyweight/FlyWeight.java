package com.example.tiaoyou.mode.flyweight;

/**
 * @author WangYH
 * @date 2022/6/10
 */
public interface FlyWeight {
    // 对外状态对象
    void operation(String name);
    // 对内对象
    String getType();
}
