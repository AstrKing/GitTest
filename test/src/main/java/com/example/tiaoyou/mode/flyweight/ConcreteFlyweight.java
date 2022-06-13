package com.example.tiaoyou.mode.flyweight;

/**
 * @author WangYH
 * @date 2022/6/10
 */
public class ConcreteFlyweight implements FlyWeight {
    private String type;

    public ConcreteFlyweight(String type) {
        this.type = type;
    }

    @Override
    public void operation(String name) {
        System.out.printf("[类型 (内在状态)] - [%s] - [名字 (外在状态)] - [%s]\n", type, name);
    }

    @Override
    public String getType() {
        return type;
    }
}
