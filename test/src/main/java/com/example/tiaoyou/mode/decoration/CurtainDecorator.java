package com.example.tiaoyou.mode.decoration;

/**
 * @author WangYH
 * @date 2022/6/10
 * <p>
 * 窗帘装饰类
 */
public class CurtainDecorator extends BaseDecorator {
    public CurtainDecorator(IDecorator decorator) {
        super(decorator);
    }

    @Override
    public void decorate() {
        super.decorate();
        System.out.println("布置窗帘");
    }
}
