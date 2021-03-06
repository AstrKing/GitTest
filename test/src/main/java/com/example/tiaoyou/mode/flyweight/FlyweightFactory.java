package com.example.tiaoyou.mode.flyweight;

import java.util.HashMap;
import java.util.Map;

/**
 * @author WangYH
 * @date 2022/6/10
 */
public class FlyweightFactory {
    private static final Map<String, FlyWeight> FLYWEIGHT_MAP = new HashMap<>(); // 享元模式的共享对象池

    public static FlyWeight getFlyweight(String type) {
        if (FLYWEIGHT_MAP.containsKey(type)) {// 如果在享元池中存在对象，则直接获取
            return FLYWEIGHT_MAP.get(type);
        } else {// 在响应池不存在，则新创建对象，并放入到享元池
            ConcreteFlyweight flyweight = new ConcreteFlyweight(type);
            FLYWEIGHT_MAP.put(type, flyweight);
            return flyweight;
        }
    }
}
